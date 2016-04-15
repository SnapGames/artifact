[![Dependency Status](https://www.versioneye.com/user/projects/571162b6fcd19a004544111a/badge.svg?style=flat)](https://www.versioneye.com/user/projects/571162b6fcd19a004544111a#dialog_dependency_badge)
[![AppVeyor (win32) Build status](https://ci.appveyor.com/api/projects/status/2xid3rsoeqtoacra?svg=true)](https://ci.appveyor.com/project/SnapGames/platform2D "AppVeyor (win32) Build status")
[![Travis-CI (linux) Build status](https://travis-ci.org/SnapGames/artifact.svg?branch=master)](https://travis-ci.org/SnapGames/artifact/requests "Travis-CI (linux) Build Status")

Artifact
======

Artifact is the resulting porject from ForeignGuyMike about [creating a 2D platform game tutorial serie](https://www.youtube.com/playlist?list=PL-2t7SM0vDfcIedoMIghzzgQqZq45jYGv) on YouTube.

After some days of trying to hack this, I finally refactor the project to extract game framework from game. 

Small transformation has been operated:

### The main menu

* A new entry was added to route to Options screen.

![Main menu](docs/artifact-menu.png "Main menu")

Main menu

* Here is the new option screen, giving access to language selection

![Options menu](docs/artifact-options.png "Options menu")

* And the well known Level 1 screen, but with the new refactored framework. 

![Level 1](docs/artifact-level1.png "Level 1")

