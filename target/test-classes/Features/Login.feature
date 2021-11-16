Feature: HepsiBurada Login Case

  Background:
    Given I Open To Chrome Browser
    And I go to application
    And screen to maximize


  @test
  Scenario: LoginCase
    When I open login page
    And I LOGIN username:nusahin44@gmail.com and password:Abc123def and see profileName:bites otomasyon
    Then I clear to my basket and return home page
    When I select laptop menu
    Then I added to basket first item, go to basket and verification price


  @test2
  Scenario: Deployment
    When I open login page
    And I see login in page username textbox element
    And I see login in page password textbox element
    When I fill login in page:
    |username textbox|nusahin44@gmail.com|
    |password textbox|Abc123def          |
    And I click login in page login button element
    Then I see home in page profile name element
    When I save in home page profile name element, get text and save the profile
    Then I verify the profile equals "bites otomasyon" with text

    And I wait to 5 seconds
    Then I see home in page elektronic categories element
    When I save in home page elektronic categories element, get text and save the electronic name
    Then I verify the electronic name equals "Elektronik" with text
    Then I not verify the electronic name equals "TestAutomation" texts


