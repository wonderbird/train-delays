# Train Delays

[![Build Status Badge](https://github.com/wonderbird/train-delays/workflows/Java%20CI%20with%20Gradle/badge.svg)](https://github.com/wonderbird/train-delays/actions/workflows/gradle.yml?query=workflow%3A%22Java+CI+with+Gradle%22)
[![Coverage Status Badge](https://coveralls.io/repos/github/wonderbird/train-delays/badge.svg?branch=main)](https://coveralls.io/github/wonderbird/train-delays?branch=main)
[![Maintainability](https://api.codeclimate.com/v1/badges/90718a2dfc4e3bde6d44/maintainability)](https://codeclimate.com/github/wonderbird/train-delays/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/90718a2dfc4e3bde6d44/test_coverage)](https://codeclimate.com/github/wonderbird/train-delays/test_coverage)

Find out when will the next train will leave the station Rösrath Stümpen.

The [User Guide](docs/user-guide.md) describes how to find out the corrected departure time of the train RB25 from
the train station Rösrath Stümpen. The user interface is the
[IntelliJ IDEA HTTP Client Plugin](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html),
which requires an [IntelliJ IDEA Ultimate](https://www.jetbrains.com/products/compare/?product=idea&product=idea-ce)
license.

The current version is one step on the road to the [Product Vision](docs/product-vision.md).

## Thanks

Many thanks to [JetBrains](https://www.jetbrains.com/?from=train-delays) who provide
an [Open Source License](https://www.jetbrains.com/community/opensource/) for this project ❤️.

## Build, Test, Run

### Build the Solution and Run the Tests

```sh
./gradlew clean build test
```

## Before Creating a Pull Request

### Check Code Metrics

... check code metrics using [metrix++](https://github.com/metrixplusplus/metrixplusplus)

- Collect metrics
  ```sh
  metrix++ collect --std.code.complexity.cyclomatic --std.code.lines.code --std.code.todo.comments --std.code.maintindex.simple -- .
  ```

- Get an overview
  ```sh
  metrix++ view --db-file=./metrixpp.db
  ```

- Apply thresholds
  ```sh
  metrix++ limit --db-file=./metrixpp.db --max-limit=std.code.complexity:cyclomatic:5 --max-limit=std.code.lines:code:25:function --max-limit=std.code.todo:comments:0 --max-limit=std.code.mi:simple:1
  ```

At the time of writing, I want to stay below the following thresholds:

```sh
--max-limit=std.code.complexity:cyclomatic:5
--max-limit=std.code.lines:code:25:function
--max-limit=std.code.todo:comments:0
--max-limit=std.code.mi:simple:1
```

I allow generated files to exceed these thresholds.

Finally, remove all code duplication. The next section describes how to detect code duplication.

### Remove Code Duplication Where Appropriate

To detect duplicates I use the [CPD Copy Paste Detector](https://pmd.github.io/latest/pmd_userdocs_cpd.html)
tool from the [PMD Source Code Analyzer Project](https://pmd.github.io/latest/index.html).

If you have installed PMD by download & unzip, replace `pmd` by `./run.sh`.
The [homebrew pmd formula](https://formulae.brew.sh/formula/pmd) makes the `pmd` command globally available. 

```sh
pmd cpd --minimum-tokens 50 --files src
```

## References

* Deutsche Bahn, Germany: [Open API-Portal](https://developer.deutschebahn.com/store/site/pages/home.jag)
