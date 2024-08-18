Feature: Database Validations for User Authentication

  Scenario: Verify user credentials are stored securely in the database
    Given A new user signs up with a username and password
    When The signup is successful
    Then The user's password should be stored in the database as a hashed value
    And The username should be stored correctly in the corresponding table

  Scenario: Verify user login attempts are logged in the database
    Given The user attempts to login with valid or invalid credentials
    When The login attempt is made
    Then The login attempt should be recorded in the database with the timestamp
    And The status of the attempt successful-failed should be logged

  Scenario: Verify user session details are correctly saved and removed in the database
    Given The user logs in successfully
    When The session is established
    Then The session details should be saved in the database with the correct user ID
    And When the user logs out, the session details should be removed or marked as inactive

  Scenario: Verify duplicate email addresses are not allowed during signup
    Given A user tries to sign up with an email address already in use
    When The signup attempt is made
    Then The database should prevent the insertion of the duplicate email
    And The user should receive an error message indicating the email is already taken

  Scenario: Verify account status is updated correctly after multiple failed login attempts
    Given The user enters incorrect credentials multiple times
    When The number of failed login attempts exceeds the limit
    Then The user's account status should be updated in the database to "locked"
    And The user should be prevented from further login attempts until the account is unlocked
