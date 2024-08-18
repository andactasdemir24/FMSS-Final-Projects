Feature: User Logout Functionality

  Background: User launch Appium Driver And Opens App Logout
    Given User launch Appium Driver And InstallApk Logout

  Scenario: Successful logout from the homepage
    Given The user is logged in Username as "Admin" password as "admin123"
    When The user clicks on the logout button
    And A success message should be displayed (logout)

  Scenario: Logout without active session
    Given The user is on the login page (logout)
    When The user navigates to the logout URL manually
    Then The user should see an error message indicating no active session
    And The user should remain on the login page

  Scenario: Logout from a different page (e.g., booking page)
    Given The user is on the booking page and logged in
    When The user clicks on the logout button
    And The session should be terminated

  Scenario: Logout and ensure session is terminated
    Given The user is logged in Username as "Admin" password as "admin123"
    When The user clicks on the logout button
    And The user tries to navigate back using the browser's back button
    And The session should not be active

  Scenario: Logout and attempt to access a restricted page
    Given The user is logged in Username as "Admin" password as "admin123"
    When The user logs out
    And The user attempts to access a restricted page (e.g., dashboard)
    And The user should see an access denied message
