Feature: HepsiBurada Login Case

  Background:
    #Given I Open To Chrome Browser
    #And I go to application
    #And screen to maximize


  @test
  Scenario: LoginCase
    When I open login page
    And I LOGIN username:nusahin44@gmail.com and password:Abc123def and see profileName:bites otomasyon
    Then I clear to my basket and return home page
    When I select laptop menu
    Then I added to basket first item, go to basket and verification price



  Scenario: test2
    Given I Open To Chrome Browser
    And I go to application
    And screen to maximize
    When I open login page
    And I see in login page username textbox element
    And I see in login page password textbox element
    And I see in login page username textbox element and fill nusahin44@gmail.com
    And I see in login page password textbox element and fill Abc123def
    And I see in login page login button element is click
    And I wait to 10 seconds