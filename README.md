# Starter Template for new .NET Projects

[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/wonderbird/dotnet-starter)
[![Build Status Badge](https://github.com/wonderbird/dotnet-starter/workflows/.NET/badge.svg)](https://github.com/wonderbird/dotnet-starter/actions?query=workflow%3A%22.NET%22)
[![Test Coverage (coveralls)](https://img.shields.io/coveralls/github/wonderbird/dotnet-starter)](https://coveralls.io/github/wonderbird/dotnet-starter)
[![Test Coverage (codeclimate)](https://img.shields.io/codeclimate/coverage-letter/wonderbird/dotnet-starter)](https://codeclimate.com/github/wonderbird/dotnet-starter/trends/test_coverage_total)
[![Code Maintainability](https://img.shields.io/codeclimate/maintainability-percentage/wonderbird/dotnet-starter)](https://codeclimate.com/github/wonderbird/dotnet-starter)
[![Issues in Code](https://img.shields.io/codeclimate/issues/wonderbird/dotnet-starter)](https://codeclimate.com/github/wonderbird/dotnet-starter/issues)
[![Technical Debt](https://img.shields.io/codeclimate/tech-debt/wonderbird/dotnet-starter)](https://codeclimate.com/github/wonderbird/dotnet-starter)
[![CodeScene Code Health](https://codescene.io/projects/13371/status-badges/code-health)](https://codescene.io/projects/13371/jobs/latest-successful/results)
[![CodeScene System Mastery](https://codescene.io/projects/13371/status-badges/system-mastery)](https://codescene.io/projects/13371/jobs/latest-successful/results)

This repository provides a starter template for new C# projects.

## Thanks

Many thanks to [JetBrains](https://www.jetbrains.com/?from=dotnet-starter) who provide
an [Open Source License](https://www.jetbrains.com/community/opensource/) for this project ❤️.

# Development

### Creating a New Project From this Template

After having forked this starter project, you'll need to adapt the project names inside this solution, fix **the links**
in the badges section above, the textual description in this README.md file and - if using
[CodeClimate (Quality)](https://codeclimate.com) - add the `CC_TEST_REPORTER_ID` to the github project secrets:

1. Change the LICENSE to your needs

2. Renaming from `DotnetStarter` to ...
   - rename the `.sln`, the contained projects and the root namespaces to match your new project
   - adapt the test entry in the `.github/workflow/dotnet.yml` file
   - adapt the environment variable in `tools/dupfinder.sh` and `tools/dupfinder.bat`, respectively
   - adapt the test directory in the `.gitpod.yml` file

3. If you have a [coveralls.io](https://coveralls.io) account, add the new project and replace the corresponding `wonderbird/dotnet-starter` entries above by your Github name and the name of this project.

4. If you have a [CodeClimate (Quality)](https://codeclimate.com) account,
   - add the new project and replace the corresponding `wonderbird/dotnet-starter` entries above by your Github name and the name of this project
   - get the `CC_TEST_REPORTER_ID` from the [CodeClimate](https://codeclimate.com) test coverage settings and set it as a repository secret in your Github project

5. If you have a [CodeScene](https://codescene.io) account,
   - add the new project and trigger an analysis
   - in the [CodeScene](https://codescene.io) status badges configuration, tick the checboxes next to Code Health and System Mastery in order to make the badges visible to the public
   - in the badges section above, replace the [CodeScene](https://codescene.io) project number `13371` by the number of your project

6. Delete all badges from above, which you don't need

7. Commit and push in order to trigger a build. Then check whether all badges work as expected, i.e. click them and check whether they redirect to the correct projects.

### Quick-Start

Click the [![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/wonderbird/dotnet-starter) badge (also above) to launch a web IDE.

If that does not work for you or if you'd like to have the project on your local machine, then continue reading.

### Prerequisites

To compile, test and run this project the latest [.NET Core SDK](https://dotnet.microsoft.com/download) is required on
your machine. For calculating code metrics I recommend [metrix++](https://github.com/metrixplusplus/metrixplusplus).
This requires python.

If you are interested in test coverage, then you'll need the following tools installed:

```shell
dotnet tool install --global coverlet.console --configfile NuGet-OfficialOnly.config
dotnet tool install --global dotnet-reportgenerator-globaltool --configfile NuGet-OfficialOnly.config
```

## Build, Test, Run

Run the following commands from the folder containing the `.sln` file in order to build and test.

### Build the Solution and Run the Tests

```sh
dotnet build
dotnet test

# If you like continuous testing then use the dotnet file watcher to trigger your tests
dotnet watch -p ./RomanNumerals.Logic.Tests test

# As an alternative, run the tests with coverage and produce a coverage report
rm -r RomanNumerals.Logic.Tests/TestResults && \
  dotnet test --no-restore --verbosity normal /p:CollectCoverage=true /p:CoverletOutputFormat=cobertura /p:CoverletOutput='./TestResults/coverage.cobertura.xml' && \
  reportgenerator "-reports:RomanNumerals.Logic.Tests/TestResults/*.xml" "-targetdir:report" "-reporttypes:Html;lcov" "-title:RomanNumerals"
open report/index.html
```

### Before Creating a Pull Request ...

... apply code formatting rules

```shell
dotnet format
```

... and check code metrics using [metrix++](https://github.com/metrixplusplus/metrixplusplus)

```shell
# Collect metrics
metrix++ collect --std.code.complexity.cyclomatic --std.code.lines.code --std.code.todo.comments --std.code.maintindex.simple -- .

# Get an overview
metrix++ view --db-file=./metrixpp.db

# Apply thresholds
metrix++ limit --db-file=./metrixpp.db --max-limit=std.code.complexity:cyclomatic:5 --max-limit=std.code.lines:code:25:function --max-limit=std.code.todo:comments:0 --max-limit=std.code.mi:simple:1
```

At the time of writing, I want to stay below the following thresholds:

```shell
--max-limit=std.code.complexity:cyclomatic:5
--max-limit=std.code.lines:code:25:function
--max-limit=std.code.todo:comments:0
--max-limit=std.code.mi:simple:1
```

I allow generated files named `*.feature.cs` to exceed these thresholds.

Finally, remove all code duplication. The next section describes how to detect code duplication.

## Identify Code Duplication

The `tools\dupfinder.bat` or `tools/dupfinder.sh` file calls
the [JetBrains dupfinder](https://www.jetbrains.com/help/resharper/dupFinder.html) tool and creates an HTML report of
duplicated code blocks in the solution directory.

In order to use the `dupfinder` you need to globally install
the [JetBrains ReSharper Command Line Tools](https://www.jetbrains.com/help/resharper/ReSharper_Command_Line_Tools.html)
On Unix like operating systems you also need [xsltproc](http://xmlsoft.org/XSLT/xsltproc2.html), which is pre-installed
on macOS.

From the folder containing the `.sln` file run

```sh
tools\dupfinder.bat
```

or

```sh
tools/dupfinder.sh
```

respectively.

The report will be created as `dupfinder-report.html` in the current directory.

# References

## .NET Core

* GitHub: [aspnet / Hosting / samples / GenericHostSample](https://github.com/aspnet/Hosting/tree/2.2.0/samples/GenericHostSample)

## Code Quality

* Continuous Testing
  * Scott Hanselman: [Command Line: Using dotnet watch test for continuous testing with .NET Core 1.0 and XUnit.net](https://www.hanselman.com/blog/command-line-using-dotnet-watch-test-for-continuous-testing-with-net-core-10-and-xunitnet)
  * Steve Smith (Ardalis): [Automate Testing and Running Apps with dotnet watch](https://ardalis.com/automate-testing-and-running-apps-with-dotnet-watch/)
* Microsoft: [Use code coverage for unit testing](https://docs.microsoft.com/en-us/dotnet/core/testing/unit-testing-code-coverage?tabs=linux)
* GitHub: [coverlet-coverage / coverlet](https://github.com/coverlet-coverage/coverlet)
* GitHub: [danielpalme / ReportGenerator](https://github.com/danielpalme/ReportGenerator)
* JetBrains s.r.o.: [dupFinder Command-Line Tool](https://www.jetbrains.com/help/resharper/dupFinder.html)
* Scott Hanselman: [EditorConfig code formatting from the command line with .NET Core's dotnet format global tool](https://www.hanselman.com/blog/editorconfig-code-formatting-from-the-command-line-with-net-cores-dotnet-format-global-tool)
* [EditorConfig.org](https://editorconfig.org)
* GitHub: [dotnet / roslyn - .editorconfig](https://github.com/dotnet/roslyn/blob/master/.editorconfig)
* Check all the badges on top of this README
