# Starter Template for new .NET Projects

[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/wonderbird/train-delays)
[![Build Status Badge](https://github.com/wonderbird/train-delays/workflows/.NET/badge.svg)](https://github.com/wonderbird/train-delays/actions?query=workflow%3A%22.NET%22)
[![Test Coverage (coveralls)](https://img.shields.io/coveralls/github/wonderbird/train-delays)](https://coveralls.io/github/wonderbird/train-delays)
[![Test Coverage (codeclimate)](https://img.shields.io/codeclimate/coverage-letter/wonderbird/train-delays)](https://codeclimate.com/github/wonderbird/train-delays/trends/test_coverage_total)
[![Code Maintainability](https://img.shields.io/codeclimate/maintainability-percentage/wonderbird/train-delays)](https://codeclimate.com/github/wonderbird/train-delays)
[![Issues in Code](https://img.shields.io/codeclimate/issues/wonderbird/train-delays)](https://codeclimate.com/github/wonderbird/train-delays/issues)
[![Technical Debt](https://img.shields.io/codeclimate/tech-debt/wonderbird/train-delays)](https://codeclimate.com/github/wonderbird/train-delays)

Demo for querying some [APIs provided by Deutsche Bahn, Germany](https://developer.deutschebahn.com/).

## Thanks

Many thanks to [JetBrains](https://www.jetbrains.com/?from=train-delays) who provide
an [Open Source License](https://www.jetbrains.com/community/opensource/) for this project ❤️.

## Development

### Quick-Start

Click the [![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/wonderbird/train-delays) badge (also above) to launch a web IDE.

If that does not work for you or if you'd like to have the project on your local machine, then continue reading.

### Prerequisites

To compile, test and run this project the latest [.NET Core SDK](https://dotnet.microsoft.com/download) is required on
your machine. For calculating code metrics I recommend [metrix++](https://github.com/metrixplusplus/metrixplusplus).
This requires python.

If you are interested in test coverage, then you'll need the following tools installed:

```sh
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
dotnet watch -p ./TrainDelays.Logic.Tests test

# As an alternative, run the tests with coverage and produce a coverage report
rm -r TrainDelays.Logic.Tests/TestResults && \
  dotnet test --no-restore --verbosity normal /p:CollectCoverage=true /p:CoverletOutputFormat=cobertura /p:CoverletOutput='./TestResults/coverage.cobertura.xml' && \
  reportgenerator "-reports:TrainDelays.Logic.Tests/TestResults/*.xml" "-targetdir:report" "-reporttypes:Html;lcov" "-title:Train Delays"
open report/index.html
```

### Before Creating a Pull Request ...

... apply code formatting rules

```sh
dotnet format
```

... and check code metrics using [metrix++](https://github.com/metrixplusplus/metrixplusplus)

```sh
# Collect metrics
metrix++ collect --std.code.complexity.cyclomatic --std.code.lines.code --std.code.todo.comments --std.code.maintindex.simple -- .

# Get an overview
metrix++ view --db-file=./metrixpp.db

# Apply thresholds
metrix++ limit --db-file=./metrixpp.db --max-limit=std.code.complexity:cyclomatic:5 --max-limit=std.code.lines:code:25:function --max-limit=std.code.todo:comments:0 --max-limit=std.code.mi:simple:1
```

At the time of writing, I want to stay below the following thresholds:

```sh
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

* Deutsche Bahn, Germany: [Open API-Portal](https://developer.deutschebahn.com/store/site/pages/home.jag)
