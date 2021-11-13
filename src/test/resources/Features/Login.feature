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
