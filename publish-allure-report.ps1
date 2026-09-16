<#
.SYNOPSIS
    Generates the Allure HTML report from the last local test run and
    publishes it to the "gh-pages" branch so it's viewable on GitHub Pages.

.DESCRIPTION
    Run this AFTER running the automation suite locally (mvn test / mvn
    -DsuiteFile=... test). It does not run the tests itself.

    Workflow:
      1. mvn test -DsuiteFile=testng.xml       (or realDevice.xml)
      2. .\publish-allure-report.ps1

    First time only: enable GitHub Pages in the repo
    (Settings > Pages > Source: "Deploy from a branch" > Branch: gh-pages / root).
    The report is then live at:
      https://<org>.github.io/<repo>/
#>

$ErrorActionPreference = "Stop"

$repoRoot   = git rev-parse --show-toplevel
Set-Location $repoRoot

$resultsDir = "target/allure-results"
$reportDir  = "target/allure-report"
$branch     = "gh-pages"
$worktree   = Join-Path $env:TEMP "allure-gh-pages-worktree"

if (-not (Test-Path $resultsDir) -or (Get-ChildItem $resultsDir -File -ErrorAction SilentlyContinue).Count -eq 0) {
    Write-Error "No Allure results found in '$resultsDir'. Run the tests first, e.g.:`n  mvn test -DsuiteFile=testng.xml"
    exit 1
}

Write-Host "Generating Allure report from $resultsDir ..." -ForegroundColor Cyan
mvn allure:report -q
if (-not (Test-Path $reportDir)) {
    Write-Error "Allure report was not generated at '$reportDir'."
    exit 1
}

if (Test-Path $worktree) {
    Remove-Item -Recurse -Force $worktree
}

git fetch origin $branch 2>$null
$remoteBranchExists = git ls-remote --heads origin $branch

if ($remoteBranchExists) {
    git worktree add $worktree $branch
} else {
    Write-Host "Branch '$branch' does not exist yet — creating it." -ForegroundColor Yellow
    git worktree add --orphan -b $branch $worktree
}

Write-Host "Copying report into the $branch worktree..." -ForegroundColor Cyan
Get-ChildItem $worktree -Force | Where-Object { $_.Name -ne ".git" } | Remove-Item -Recurse -Force
Copy-Item "$reportDir\*" $worktree -Recurse -Force
New-Item -ItemType File -Path (Join-Path $worktree ".nojekyll") -Force | Out-Null

Push-Location $worktree
git add -A
$hasChanges = git status --porcelain
if ($hasChanges) {
    git commit -m "Publish Allure report ($(Get-Date -Format 'yyyy-MM-dd HH:mm:ss'))" | Out-Null
    git push origin $branch
    Write-Host "`nReport pushed to '$branch'. It will be live shortly on GitHub Pages." -ForegroundColor Green
} else {
    Write-Host "`nNo changes — report is identical to the last published version." -ForegroundColor Yellow
}
Pop-Location

git worktree remove $worktree --force
