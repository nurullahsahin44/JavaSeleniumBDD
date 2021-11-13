Feature: Bites Test Cases

  Background:
    Given I Open To Chrome Browser
    And I go to hepsiburada.com adress
    And screen to maximize


  @test
  Scenario: LoginCase
    And I open login page
    And I wait to 2 seconds
    And I LOGIN username:nusahin44@gmail.com and password:Abc123def and see profileName:bites otomasyon
    And I clear to my basket and return home page
    And I select laptop menu
    And I wait to 3 seconds
    And I added to basket first item, go to basket and verification price
