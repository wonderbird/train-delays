Feature: Train Delays

  Scenario: Running the application
    When I run the application
    Then I should see "23:59" as scheduled departure time for the next train
