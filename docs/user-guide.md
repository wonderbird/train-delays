# Train Delays User Guide

## As a Normal User ...

... just navigate to the [Train Delays Web Application](https://train-delays-lvnrwcqd7q-ew.a.run.app/).

## As a Software Developer ...

### Prerequisite

Please register with and subscribe to the [Timetables
API](https://developers.deutschebahn.com/db-api-marketplace/apis/product/timetables/api/1309#/Timetables_10197/overview).
This is described in detail in section [7.2.1 Prerequisites of the Architecture
Document](architecture.adoc#721-prerequisites).

### Run the Application in a Docker Container

The [build pipeline](../.github/workflows/build.yml) publishes the application to [Docker
Hub](https://hub.docker.com/r/boos/train-delays). Thus, you can pull an image and run it.

Then, in the following, replace `<YOUR CLIENT ID>` and `<YOUR API KEY>` with the values displayed in the section "Berechtigungsnachweise" for your application in [DB API Marketplace / Anwendungen](https://developers.deutschebahn.com/db-api-marketplace/apis/application).

Note: The API Key will not be revealed. Please save the key to a password safe when creating it (see [7.2.1 Prerequisites of the Architecture
Document](architecture.adoc#721-prerequisites)).

```sh
export API_KEY=<YOUR API KEY>
export CLIENT_ID=<YOUR CLIENT ID>
docker run -p 8080:8080 --env API_KEY=$API_KEY --env CLIENT_ID=$CLIENT_ID boos/train-delays
```

To test the local Docker container, fire an HTTP GET request to http://localhost:8080/nextdeparture
See [traindelays.http](./traindelays.http) for a sample request.

### Run the Application Using a Local Java Installation

```sh
API_KEY="<YOUR API KEY>" ./gradlew bootRun
```

### Query the Next Departure

Fire an HTTP GET request to http://localhost:8080/nextdeparture

```sh
curl --include http://localhost:8080/nextdeparture
```
