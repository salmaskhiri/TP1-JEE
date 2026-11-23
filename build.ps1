# Compilation et deploiement MANUELS (sans IDE ni Maven) dans Tomcat.
# Usage :  .\build.ps1            -> compile + copie dans $env:CATALINA_HOME\webapps\TP1
#          .\build.ps1 -War       -> produit aussi build\TP1.war
param(
    [string]$TomcatHome = $(if ($env:CATALINA_HOME) { $env:CATALINA_HOME } else { "C:\tomcat\apache-tomcat-10.1.62" }),
    [string]$JavaHome   = $(if ($env:JAVA_HOME) { $env:JAVA_HOME } else { "$env:USERPROFILE\.jdks\ms-17.0.20.1" }),
    [switch]$War
)
$ErrorActionPreference = "Stop"
$root    = $PSScriptRoot
$out     = Join-Path $root "build\TP1"
$classes = Join-Path $out "WEB-INF\classes"

# 1. Structure d'une application web : racine (HTML/JSP) + WEB-INF\web.xml + WEB-INF\classes
if (Test-Path $out) { Remove-Item -Recurse -Force $out }
Copy-Item -Recurse (Join-Path $root "src\main\webapp") $out
New-Item -ItemType Directory -Force $classes | Out-Null

# 2. Compilation : l'API Servlet est fournie par Tomcat (lib\servlet-api.jar), elle n'est pas embarquee
$sources = Get-ChildItem -Recurse (Join-Path $root "src\main\java") -Filter *.java | ForEach-Object { $_.FullName }
& "$JavaHome\bin\javac.exe" -encoding UTF-8 --release 17 -cp "$TomcatHome\lib\servlet-api.jar" -d $classes $sources
if ($LASTEXITCODE -ne 0) { throw "Echec de la compilation" }

# 3. Deploiement : copie du dossier dans webapps (Tomcat le detecte - autoDeploy)
$target = Join-Path $TomcatHome "webapps\TP1"
if (Test-Path $target) { Remove-Item -Recurse -Force $target }
Copy-Item -Recurse $out $target
Write-Host "Deploye dans $target -> http://localhost:8080/TP1/"

if ($War) {
    Push-Location $out
    & "$JavaHome\bin\jar.exe" -cf (Join-Path $root "build\TP1.war") .
    Pop-Location
    Write-Host "WAR : build\TP1.war"
}
