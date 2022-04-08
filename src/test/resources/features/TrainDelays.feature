Feature: Train Delays

  Scenario Outline: Running the application
    Given The next train is expected to leave at "<expected departure time>"
    When I run the application
    Then I should see "<expected departure time>" as scheduled departure time for the next train
  Examples:
    | expected departure time |
    | 23:59 |
    | 09:00 |

  Scenario: Replay recorded API response
    Given The mock service returns a recorded API response
    When I run the application
    Then I should see "22:11" as scheduled departure time for the next train
