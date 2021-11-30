Feature: HepsiBurada Login Case

  Background:
    Given I Open To Chrome Browser
    And I go to application
    And screen to maximize


    @test2
  Scenario: Deployment
    When I see login page
    When I see my account element in 20 seconds
    And I mouseover my account element
    And I wait to 2 seconds
    And I click open login page button element

    Then I see username textbox element
    And I am registered with user1
    When I fill:
      | username textbox | my username |
    And I click login button element

    Then I see password textbox element
    When I fill:
      | password textbox | my password |
    And I click login button2 element

    Then I see home page
    Then I see profile name element
    When I save profile name element, get text and save the profile
    Then I verify the profile equals "bites otomasyon" with text

    And I wait to 3 seconds
    Then I see elektronic categories element
    When I save elektronic categories element, get text and save the electronic name
    Then I verify the electronic name equals "Elektronik" with text
    Then I not verify the electronic name equals "TestAutomation" texts


