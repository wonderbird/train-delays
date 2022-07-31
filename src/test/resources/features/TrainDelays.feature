Feature: API to query the next train leaving the station Roesrath Stuempen

  Scenario Outline: Query the expected departure time of the next train
    Given the next train is expected to leave in <expected departure> minutes
    When I call the API
    Then the expected departure is <expected departure> minutes in the future
    Examples:
      | expected departure |
      | 10                 |
      | 30                 |

    @wip
    Scenario: Query the expected delay of the next train
      Given the planned departure for the next train is in 5 minutes
      And the next train is expected to leave in 10 minutes
      When  I call the API
      Then the expected delay is 5 minutes
