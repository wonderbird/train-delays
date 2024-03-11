# Train Delays

[![Build Status Badge](https://github.com/wonderbird/train-delays/workflows/Build%20and%20Publish/badge.svg)](https://github.com/wonderbird/train-delays/actions/workflows/build.yml?query=workflow%3A%22Build+and+Publish%22)
[![Coverage Status Badge](https://coveralls.io/repos/github/wonderbird/train-delays/badge.svg?branch=main)](https://coveralls.io/github/wonderbird/train-delays?branch=main)
[![Test Coverage](https://api.codeclimate.com/v1/badges/90718a2dfc4e3bde6d44/test_coverage)](https://codeclimate.com/github/wonderbird/train-delays/test_coverage)
[![Maintainability](https://api.codeclimate.com/v1/badges/90718a2dfc4e3bde6d44/maintainability)](https://codeclimate.com/github/wonderbird/train-delays/maintainability)
[![Image Size](https://img.shields.io/docker/image-size/boos/train-delays)](https://hub.docker.com/repository/docker/boos/train-delays)

Find out when will the next train will leave the station Rösrath Stümpen.

This web application shows the expected departure time of the next train from the station Rösrath-Stümpen.
The [User Guide](docs/user-guide.md) describes how to run the application.

The current version is one step on the road to the [Product Vision](docs/product-vision.md).

## Thanks

Many thanks to [JetBrains](https://www.jetbrains.com/?from=train-delays) who provide
an [Open Source License](https://www.jetbrains.com/community/opensource/) for this project ❤️.

## Documentation Overview

- [User Guide](docs/user-guide.md)
- [Architecture](docs/architecture.adoc)

## Build, Test, Run

### Prerequisites

The build is tested with the following versions:

- [Java 21.0.2](https://adoptopenjdk.net/)
- [Gradle](https://gradle.org/) is configured by the [gradle-wrapper.properties](gradle/wrapper/gradle-wrapper.properties)

### Build the Solution and Run the Tests

```sh
./gradlew clean build test --warning-mode all
```

### Build and Run the Docker Image

Build the Docker image:

```sh
docker build -t boos/train-delays .
```

Pass the environment variable `DB_API_KEY` and `CLIENT_ID` to the Docker container when running it:

```sh
export DB_API_KEY=your-api-key
export CLIENT_ID=your-client-id
docker run -p 8080:8080 --env DB_API_KEY=$DB_API_KEY --env CLIENT_ID=$CLIENT_ID boos/train-delays
```

## Before Creating a Pull Request

### Check Code Metrics

... check code metrics using [metrix++](https://github.com/metrixplusplus/metrixplusplus)

- Configure the location of the cloned metrix++ scripts
  ```sh
  export METRIXPP=/path/to/metrixplusplus
  ```

- Collect metrics
  ```sh
  python "$METRIXPP/metrix++.py" collect --std.code.complexity.cyclomatic --std.code.lines.code --std.code.todo.comments --std.code.maintindex.simple -- .
  ```

- Get an overview
  ```sh
  python "$METRIXPP/metrix++.py" view --db-file=./metrixpp.db
  ```

- Apply thresholds
  ```sh
  python "$METRIXPP/metrix++.py" limit --db-file=./metrixpp.db --max-limit=std.code.complexity:cyclomatic:5 --max-limit=std.code.lines:code:25:function --max-limit=std.code.todo:comments:0 --max-limit=std.code.mi:simple:1
  ```

At the time of writing, I want to stay below the following thresholds:

```
--max-limit=std.code.complexity:cyclomatic:5
--max-limit=std.code.lines:code:25:function
--max-limit=std.code.todo:comments:0
--max-limit=std.code.mi:simple:1
```

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
