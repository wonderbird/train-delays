# Train Delays Developer Guide

## Prerequisite

Please register with and subscribe to the [Timetables
API](https://developers.deutschebahn.com/db-api-marketplace/apis/product/timetables/api/1309#/Timetables_10197/overview).
This is described in detail in section [7.2.1 Prerequisites of the Architecture
Document](architecture.adoc#721-prerequisites).

## Run the Application in a Docker Container

The [build pipeline](../.github/workflows/build.yml) publishes the application to [Docker
Hub](https://hub.docker.com/r/boos/train-delays). Thus, you can pull an image and run it.

Then, in the following, replace `<YOUR CLIENT ID>` and `<YOUR API KEY>` with the values displayed in the section "Berechtigungsnachweise" for your application in [DB API Marketplace / Applications](https://developers.deutschebahn.com/db-api-marketplace/apis/application).

Note: The API Key will not be revealed. Please save the key to a password safe when creating it (see [7.2.1 Prerequisites of the Architecture
Document](architecture.adoc#721-prerequisites)).

```sh
export API_KEY=<YOUR API KEY>
export CLIENT_ID=<YOUR CLIENT ID>
docker run -p 8080:8080 --env API_KEY=$API_KEY --env CLIENT_ID=$CLIENT_ID boos/train-delays
```

## Run the Application Using a Local Java Installation

To simply run the application:

```sh
API_KEY="<YOUR API KEY>" CLIENT_ID="<YOUR CLIENT ID>" ./gradlew bootRun
```

If you want to rebuild and reload the application automatically when the source code changes, you will need two terminals.

In the first terminal, recompile the application continuously:

```sh
./gradlew build --continuous
```

In the second terminal, run the application with live reload:

```sh
API_KEY="<YOUR API KEY>" CLIENT_ID="<YOUR CLIENT ID>" ./gradlew bootRun
```

See: [Stackoverflow: Spring Boot bootRun with continuous build](https://stackoverflow.com/questions/52092504/spring-boot-bootrun-with-continuous-build)

## Open the User Interface

Open a web browser and navigate to http://localhost:8080

## Query the Next Departure

Fire an HTTP GET request to http://localhost:8080/nextdeparture

```sh
curl --include http://localhost:8080/nextdeparture
```

[traindelays.http](./traindelays.http) contains additional sample requests.

## Query the Open API Specification

The Swagger UI contains online documentation and is available at http://localhost:8080/swagger-ui.html

The Open API specification is available as JSON document at http://localhost:8080/v3/api-docs

The YAML version of the Open API specification is available at http://localhost:8080/v3/api-docs.yaml

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