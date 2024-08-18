Feature: User Login Functionality

  Background: User launch Appium Driver And Opens App
    Given User launch Appium Driver And InstallApk

  Scenario: Successful login with valid credentials
    Given The user is on the login page
    When The user enters valid username as "Admin" and password as "admin123"
    And The user clicks on the login button
    And A success message should be displayed

  Scenario: Unsuccessful login with invalid password
    Given The user is on the login page
    When The user enters a valid username as "Admin" and an invalid password as "invalid"
    And The user clicks on the login button
    Then The user should see an error message indicating invalid password credentials

  Scenario: Unsuccessful login with invalid username
    Given The user is on the login page
    When The user enters an invalid username as "Username" and a valid password as "admin123"
    And The user clicks on the login button
    Then The user should see an error message indicating invalid username credentials

  Scenario: Unsuccessful login with empty username and password
    Given The user is on the login page
    When The user leaves the username and password as "" fields empty as ""
    And The user clicks on the login button
    Then The user should see an error message indicating required fields

  Scenario: Successful login with "Remember Me" option selected
    Given The user is on the login page
    When The user enters valid username as "Admin" and password as "admin123"
    And The user selects the Remember Me checkbox
    And The user clicks on the login button
    And The user's credentials should be saved for future logins