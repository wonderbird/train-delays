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

## Run the Application

```sh
API_KEY="<YOUR API KEY>" ./gradlew bootRun
```
