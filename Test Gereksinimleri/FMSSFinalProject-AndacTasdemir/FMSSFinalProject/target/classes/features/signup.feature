Feature: User Signup

  Background: User launch Appium Driver And Opens App SignUp
    Given User launch Appium Driver And InstallApk SignUp

  Scenario: Successful signup with valid details
    Given The user is on the signup page
    When The user enters valid details (username, password, email, etc.)
    And The user clicks on the signup button

  Scenario: Unsuccessful signup with an existing username
    Given The user is on the signup page
    When The user enters a username that already exists
    And The user clicks on the signup button

  Scenario: Unsuccessful signup with invalid email format
    Given The user is on the signup page
    When The user enters an invalid email format
    And The user clicks on the signup button

  Scenario: Unsuccessful signup with mismatched password confirmation
    Given The user is on the signup page
    When The user enters a password and a different password confirmation
    And The user clicks on the signup button

  Scenario: Unsuccessful signup with empty required fields
    Given The user is on the signup page
    When The user leaves required fields empty (e.g., username, password)
    And The user clicks on the signup button
