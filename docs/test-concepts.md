# Test Concepts

## Acceptance Tests

Features and scenarios are specified using the Gherkin DSL and executed with [Cucumber](https://cucumber.io/).

The following articles describe the used concepts:

- Moisés
  Macero: [Cucumber Tests in Spring Boot with Dependency Injection](https://thepracticaldeveloper.com/cucumber-tests-spring-boot-dependency-injection/)
- baeldung: [Asserting Log Messages With JUnit](https://www.baeldung.com/junit-asserting-logs)

## API Verification

This application uses [Pact](https://pact.io/) tests to verify the following APIs:

- Consuming the [Deutsche Bahn Timetables v1
  API](https://developer.deutschebahn.com/store/apis/info?name=Timetables&version=v1&provider=DBOpenData)
- Providing the TrainDelays API

## Low Level Tests

JUnit5 based unit tests verify implementation details and most error scenarios.

## References

- Philip Riecks: [Guide to @SpringBootTest for Spring Boot Integration
  Tests](https://rieckpil.de/guide-to-springboottest-for-spring-boot-integration-tests/)
