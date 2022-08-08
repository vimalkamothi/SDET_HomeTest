#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: DailyMaiWebsite_Functionality_Validations

  Scenario: Validate Daily Mail website basic functionalities
 
    Given User launches browser
    When User navigates to DailyMail home page
    Then User validates the current date and time
    When User clicks on Sports menu
    Then User validates the primary and secondary navigation colors
    When User clicks on Football sub-navigation
    Then User clicks the first article displayed and traverse to the gallery
    When User clicks on the gallery icon
    Then gallery load on full screen and has previous and next buttons
    And User clicks on Facebook icon and validates modal dialog opens
    And User clicks on embeded video and validates fullscreen and minimize mode
 		And User exhibit the points and position from PREMIER LEAGUE table for "Liverpool" team
		And User closes the browser

 
