@API
Feature: API Tests

  Background:

  @GET
  Scenario: GET Test
    And I execute templateGET template rest service


  Scenario: POST Test
    And I execute templatePOST template rest service with parameters:
      | name | nurullah       |
      | job  | testAutomation |