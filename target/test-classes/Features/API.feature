@API
Feature: API Tests

  Background:

  @GET
  Scenario: GET Test
    Given I execute GET parameters URL:https://reqres.in PATH:/api/users?page=2 RESPONSECODE:200

  @POST
  Scenario: POST Test
    Given I execute POST parameters URL:https://reqres.in PATH:/api/users RESPONSECODE:201 CHECK:createdAt BODY:
      | name | nurullah       |
      | job  | testAutomation |

  @PUT
  Scenario: PUT Test
    Given I execute PUT parameters URL:https://reqres.in PATH:/api/users/2 RESPONSECODE:200 CHECK:updatedAt BODY:
      | name | nurullah       |
      | job  | testAutomation |

  @DELETE
  Scenario: DELETE Test
    Given I execute DELETE parameters URL:https://reqres.in PATH:/api/users/2 RESPONSECODE:204 CHECK:

  @GET
  Scenario: GET Test
    And I execute templateGET template rest service


  Scenario: POST Test
    And I execute templatePOST template rest service with parameters:
      | name | nurullah       |
      | job  | testAutomation |