<#
.SYNOPSIS
    Generates the Allure HTML report from the last local test run and
    publishes it to the "gh-pages" branch so it's viewable on GitHub Pages,
    keeping a browsable history of past runs plus a stable "latest" link.

.DESCRIPTION
    Run this AFTER running the automation suite locally. It does not run
    the tests itself.

    Workflow (fresh results every time, via the Maven clean lifecycle):
      1. mvn clean test -DsuiteFile=realDevice.xml   (or testng.xml)
      2. .\publish-allure-report.ps1

    First time only: enable GitHub Pages in the repo
    (Settings > Pages > Source: "Deploy from a branch" > Branch: gh-pages / root).

    Published layout on gh-pages:
      /index.html            - dashboard listing every run, with pass/fail stats
      /latest/                - always mirrors the most recent run
      /reports/<timestamp>/   - one full report per run (oldest pruned after
                                $maxHistoryRuns to keep the branch small)

    Dashboard: https://<org>.github.io/<repo>/
    Latest:    https://<org>.github.io/<repo>/latest/
#>

$ErrorActionPreference = "Stop"
# Git writes routine progress/status messages to stderr. On PowerShell 7+,
# $ErrorActionPreference = "Stop" would otherwise turn those into terminating
# errors even though the command succeeded. This restores the old behavior
# (no-op / harmless on Windows PowerShell 5.1, which has no such setting).
$PSNativeCommandUseErrorActionPreference = $false

$repoRoot       = git rev-parse --show-toplevel
Set-Location $repoRoot

$resultsDir     = "target/allure-results"
$reportDir      = "target/allure-report"
$branch         = "gh-pages"
$worktree       = Join-Path $env:TEMP "allure-gh-pages-worktree"
$reportsSubdir  = "reports"
$maxHistoryRuns = 20

if (-not (Test-Path $resultsDir) -or (Get-ChildItem $resultsDir -File -ErrorAction SilentlyContinue).Count -eq 0) {
    Write-Error "No Allure results found in '$resultsDir'. Run the tests first, e.g.:`n  mvn clean test -DsuiteFile=realDevice.xml"
    exit 1
}

if (Test-Path $worktree) {
    Remove-Item -Recurse -Force $worktree
}

$remoteBranchExists = git ls-remote --heads origin $branch

if ($remoteBranchExists) {
    git worktree add $worktree $branch
} else {
    Write-Host "Branch '$branch' does not exist yet - creating it." -ForegroundColor Yellow
    git worktree add --orphan -b $branch $worktree
}

$reportsDirInWorktree = Join-Path $worktree $reportsSubdir
$previousRuns = @()
if (Test-Path $reportsDirInWorktree) {
    $previousRuns = @(Get-ChildItem $reportsDirInWorktree -Directory | Sort-Object Name -Descending)
}

# Seed Allure's trend/history data from the last published run, if any,
# so the new report's Trend widget keeps building on past executions.
if ($previousRuns.Count -gt 0) {
    $lastHistory = Join-Path $previousRuns[0].FullName "history"
    if (Test-Path $lastHistory) {
        Write-Host "Carrying forward trend history from '$($previousRuns[0].Name)'..." -ForegroundColor Cyan
        $localHistory = Join-Path $resultsDir "history"
        New-Item -ItemType Directory -Force -Path $localHistory | Out-Null
        Copy-Item "$lastHistory\*" $localHistory -Recurse -Force
    }
}

Write-Host "Generating Allure report from $resultsDir ..." -ForegroundColor Cyan
mvn allure:report -q
if (-not (Test-Path $reportDir)) {
    Write-Error "Allure report was not generated at '$reportDir'."
    exit 1
}

$stamp     = Get-Date -Format "yyyy-MM-dd_HH-mm-ss"
$newRunDir = Join-Path $reportsDirInWorktree $stamp
New-Item -ItemType Directory -Force -Path $newRunDir | Out-Null
Copy-Item "$reportDir\*" $newRunDir -Recurse -Force

$latestDir = Join-Path $worktree "latest"
if (Test-Path $latestDir) { Remove-Item -Recurse -Force $latestDir }
New-Item -ItemType Directory -Force -Path $latestDir | Out-Null
Copy-Item "$reportDir\*" $latestDir -Recurse -Force

# Prune old runs beyond the retention limit so the branch doesn't grow forever.
$allRuns = @(Get-ChildItem $reportsDirInWorktree -Directory | Sort-Object Name -Descending)
if ($allRuns.Count -gt $maxHistoryRuns) {
    $allRuns | Select-Object -Skip $maxHistoryRuns | ForEach-Object {
        Write-Host "Pruning old run '$($_.Name)' (retention limit: $maxHistoryRuns)" -ForegroundColor DarkYellow
        Remove-Item $_.FullName -Recurse -Force
    }
    $allRuns = @(Get-ChildItem $reportsDirInWorktree -Directory | Sort-Object Name -Descending)
}

function Get-RunStats($runDir) {
    $summaryPath = Join-Path $runDir "widgets/summary.json"
    if (Test-Path $summaryPath) {
        try {
            return (Get-Content $summaryPath -Raw | ConvertFrom-Json).statistic
        } catch {
            return $null
        }
    }
    return $null
}

$rows = ""
foreach ($run in $allRuns) {
    $stats = Get-RunStats $run.FullName
    if ($stats) {
        $passed  = $stats.passed
        $failed  = $stats.failed
        $broken  = $stats.broken
        $skipped = $stats.skipped
        $total   = $stats.total
    } else {
        $passed = "-"; $failed = "-"; $broken = "-"; $skipped = "-"; $total = "-"
    }
    $displayDate = $run.Name -replace '_', ' '
    $rows += "<tr><td><a href=`"$reportsSubdir/$($run.Name)/index.html`">$displayDate</a></td><td>$total</td><td class=`"pass`">$passed</td><td class=`"fail`">$failed</td><td class=`"broken`">$broken</td><td class=`"skip`">$skipped</td></tr>`n"
}

# Clean everything at the root except .git and the reports archive, then
# regenerate the dashboard fresh (removes leftover assets from older
# versions of this script that used to publish the report at the root).
Get-ChildItem $worktree -Force | Where-Object { $_.Name -ne ".git" -and $_.Name -ne $reportsSubdir -and $_.Name -ne "latest" } | Remove-Item -Recurse -Force

$html = @"
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Allure Report Dashboard</title>
<style>
body { font-family: Arial, sans-serif; margin: 2rem; background:#f7f7f9; color:#222; }
h1 { margin-bottom: 0.2rem; }
.latest-link { display:inline-block; margin: 1rem 0; padding: 0.7rem 1.2rem; background:#5b3ef5; color:#fff; text-decoration:none; border-radius:6px; font-weight:bold; }
table { border-collapse: collapse; width:100%; background:#fff; box-shadow:0 1px 3px rgba(0,0,0,0.1); }
th, td { padding: 0.6rem 1rem; text-align:left; border-bottom:1px solid #eee; }
th { background:#eee; }
.pass { color:#2e7d32; font-weight:bold; }
.fail { color:#c62828; font-weight:bold; }
.broken { color:#ef6c00; font-weight:bold; }
.skip { color:#757575; }
</style>
</head>
<body>
<h1>Allure Report Dashboard</h1>
<p>Intelehealth Mobile Automation - all runs published from local execution. Showing the most recent $maxHistoryRuns runs.</p>
<a class="latest-link" href="latest/index.html">View Latest Report</a>
<table>
<tr><th>Run</th><th>Total</th><th>Passed</th><th>Failed</th><th>Broken</th><th>Skipped</th></tr>
$rows
</table>
</body>
</html>
"@
Set-Content -Path (Join-Path $worktree "index.html") -Value $html -Encoding utf8
New-Item -ItemType File -Path (Join-Path $worktree ".nojekyll") -Force | Out-Null

Push-Location $worktree
git add -A
$hasChanges = git status --porcelain
if ($hasChanges) {
    git commit -m "Publish Allure report ($stamp)" | Out-Null
    git push origin $branch
    Write-Host "`nReport pushed to '$branch'. It will be live shortly on GitHub Pages." -ForegroundColor Green
} else {
    Write-Host "`nNo changes - report is identical to the last published version." -ForegroundColor Yellow
}
Pop-Location

git worktree remove $worktree --force
