Feature: Train Delays

  Scenario Outline: Running the application
    Given Today is April 9, 2022 at 08:30 am
    And The next train is expected to leave at "<expected departure time>"
    When I run the application
    Then I should see "<expected departure time>" as scheduled departure time for the next train
    Examples:
      | expected departure time |
      | 23:59                   |
      | 09:00                   |
