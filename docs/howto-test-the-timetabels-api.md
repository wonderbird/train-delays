# HOWTO: Test the Timetable API

This guide describes how to call the
[Timetables - v1 API](https://developer.deutschebahn.com/store/apis/info?name=Timetables&version=v1&provider=DBOpenData)
using the
[IntelliJ IDEA HTTP Client Plugin](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html).

## Prerequisites

### Licenses

- You need a license for
  [IntelliJ IDEA Ultimate](https://www.jetbrains.com/products/compare/?product=idea&product=idea-ce)
  in order to use the
  [IntelliJ IDEA HTTP Client Plugin](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html)

### Subscribe to the Deutsche Bahn Open Data Timetables v1 API

- Register a free account for [Open API-Portal](https://developer.deutschebahn.com/store/site/pages/home.jag)
  of Deutsche Bahn, Germany.

- On the [My Subscriptions Tab](https://developer.deutschebahn.com/store/site/pages/subscriptions.jag)
  generate a key for the production environment.

- On the
  [Timetables - v1 API page](https://developer.deutschebahn.com/store/apis/info?name=Timetables&version=v1&provider=DBOpenData)
    - Select `DefaultApplication` in the `Application` dropdown
    - Click `Subscribe`

### Configure the HTTP Client Plugin

> ⚠️ Attention:
>
> Never check the file `http-client.private.env.json` into source control.

- In the `docs` directory create a text file called `http-client.private.env.json` and copy-paste the following content:
  ```json
  {
    "production": {
      "auth_token": "YOUR_AUTH_TOKEN"
    }
  }
  ```

- Replace `YOUR_AUTH_TOKEN` with your authentication token from the
  [My Subscriptions Tab](https://developer.deutschebahn.com/store/site/pages/subscriptions.jag)

## Usage

- Open [timetables.http](timetables.http) file in the
  [IntelliJ IDEA HTTP Client Plugin](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html)

- Run the **Query all known changes for the station eva id** request
    - Select the **production** environment from the dropdown

- Open the response file and search for the first departure entry for your current date and time
    - Format of the search string: `dp ct="YYMMDDHHmm` where
        - `YY` are the last two digits of the year
        - `MM` are the two digits of the month (leading 0 if necessary)
        - `DD` are the two digits of the day (leading 0 if necessary)
        - `HH` are the two digits of the hour (leading 0 if necessary)
        - `mm` are the two digits of the minute (leading 0 if necessary)
    - The fields of interest are
        - `dp`: scheduled departure
        - `ct`: recently changed time of departure

## References

- The **Interface description** document in the **Documentation** tab of the
  [Timetables - v1 API](https://developer.deutschebahn.com/store/apis/info?name=Timetables&version=v1&provider=DBOpenData)
  describes the data model of the API in detail.
