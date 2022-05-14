Feature: Train Delays

  Scenario Outline: Requesting expected departure from API
    Given the next train is expected to leave in <expected departure> minutes
    When I call the API
    Then the expected departure is <expected departure> minutes in the future
    Examples:
      | expected departure |
      | 10                 |
      | 30                 |
