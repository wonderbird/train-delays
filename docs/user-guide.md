# Train Delays User Guide

## Prerequisites

### Subscribe to the Deutsche Bahn Open Data Timetables v1 API

At the time of writing, this application requires an access key for the Deutsche Bahn Open API-Portal. You can get
one for free:

- Register a free account for [Open API-Portal](https://developer.deutschebahn.com/store/site/pages/home.jag)
  of Deutsche Bahn, Germany.

- On the [My Subscriptions Tab](https://developer.deutschebahn.com/store/site/pages/subscriptions.jag)
  generate a key for the production environment.

- On the
  [Timetables - v1 API page](https://developer.deutschebahn.com/store/apis/info?name=Timetables&version=v1&provider=DBOpenData)
    - Select `DefaultApplication` in the `Application` dropdown
        - Click `Subscribe`

## Run the Application in a Docker Container

The [build pipeline](../.github/workflows/gradle.yml) publishes the application to the GitHub Packages directory. Thus,
you can pull an image and run it.

In the following, replace `<YOUR API KEY>` with the `Access Token` displayed on the [My Subscriptions
Tab](https://developer.deutschebahn.com/store/site/pages/subscriptions.jag).

```sh
docker run -p 8080:8080 --env API_KEY=<YOUR API KEY> --name train-delays-app --rm boos/train-delays:feature-web-app
```

## Alternative for Developers: Run the Application Using a Local Java Installation

```sh
API_KEY="<YOUR API KEY>" ./gradlew bootRun
```

## Query the Next Departure

Fire an HTTP GET request to http://localhost:8080/nextdeparture

```sh
curl --include http://localhost:8080/nextdeparture
```
