package steps;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DatabasePage;
import pages.LoginPage;
import pages.SignupPage;

import static org.junit.Assert.assertTrue;


public class DatabaseSteps {

    DatabasePage databasePage = new DatabasePage();
    LoginPage loginPage = new LoginPage();
    SignupPage signupPage = new SignupPage();

    @Before
    public void setUp() {
        databasePage.connectToDatabase();
    }

    @Given("A new user signs up with a username and password")
    public void aNewUserSignsUpWithAUsernameAndPassword()  {
        databasePage.aNewUserSignsUpWithAUsernameAndPassword();
    }

    @When("The signup is successful")
    public void theSignupIsSuccessful(AppiumDriver driver) {
        boolean isSuccess = signupPage.isSignupSuccessful(driver);
        assertTrue("Signup was not successful", isSuccess);
    }


    @Then("The user's password should be stored in the database as a hashed value")
    public void theUserSPasswordShouldBeStoredInTheDatabaseAsAHashedValue() {
        databasePage.hashedValue();
    }


    @And("The username should be stored correctly in the corresponding table")
    public void theUsernameShouldBeStoredCorrectlyInTheCorrespondingTable() {
        databasePage.theUsernameShouldBeStoredCorrectlyInTheCorrespondingTable();
    }

    @Given("The user attempts to login with valid or invalid credentials")
    public void theUserAttemptsToLoginWithValidOrInvalidCredentials() {
        databasePage.theUserAttemptsToLoginWithValidOrInvalidCredentials();
    }

    @When("The login attempt is made")
    public void theLoginAttemptIsMade(AppiumDriver driver) {
        loginPage.clickLoginButton(driver);
    }

    @Then("The login attempt should be recorded in the database with the timestamp")
    public void theLoginAttemptShouldBeRecordedInTheDatabaseWithTheTimestamp() {
        databasePage.theLoginAttemptShouldBeRecordedInTheDatabaseWithTheTimestamp();
    }

    @And("The status of the attempt successful-failed should be logged")
    public void theStatusOfTheAttemptSuccessfulFailedShouldBeLogged(){
        databasePage.theStatusOfTheAttemptSuccessfulFailedShouldBeLogged();
    }

    @Given("The user logs in successfully")
    public void theUserLogsInSuccessfully(AppiumDriver driver) {
        loginPage.navigateToLoginPage(driver);
        loginPage.fillUsername("validUsername", driver);
        loginPage.fillPassword("validPassword", driver);
        loginPage.clickLoginButton(driver);
        assertTrue("Login was not successful", loginPage.clickLoginButtonStatus(driver));
    }

    @When("The session is established")
    public void theSessionIsEstablished() {
        databasePage.theSessionIsEstablished();
    }

    @Then("The session details should be saved in the database with the correct user ID")
    public void theSessionDetailsShouldBeSavedInTheDatabaseWithTheCorrectUserID() {
        databasePage.theSessionDetailsShouldBeSavedInTheDatabaseWithTheCorrectUserID();
    }

    @And("When the user logs out, the session details should be removed or marked as inactive")
    public void whenTheUserLogsOutTheSessionDetailsShouldBeRemovedOrMarkedAsInactive() {
        databasePage.whenTheUserLogsOutTheSessionDetailsShouldBeRemovedOrMarkedAsInactive();
    }

    @Given("A user tries to sign up with an email address already in use")
    public void aUserTriesToSignUpWithAnEmailAddressAlreadyInUse(){
        databasePage.aUserTriesToSignUpWithAnEmailAddressAlreadyInUse();
    }

    @When("The signup attempt is made")
    public void theSignupAttemptIsMade(AppiumDriver driver) {
        signupPage.clickSignupButton(driver);
    }

    @Then("The database should prevent the insertion of the duplicate email")
    public void theDatabaseShouldPreventTheInsertionOfTheDuplicateEmail() {
        databasePage.theDatabaseShouldPreventTheInsertionOfTheDuplicateEmail();
    }

    @And("The user should receive an error message indicating the email is already taken")
    public void theUserShouldReceiveAnErrorMessageIndicatingTheEmailIsAlreadyTaken(AppiumDriver driver) {
        boolean isErrorDisplayed = signupPage.isErrorMessageDisplayed(driver, "Email already taken");
        assertTrue("Error message not displayed", isErrorDisplayed);
    }


    @Given("The user enters incorrect credentials multiple times")
    public void theUserEntersIncorrectCredentialsMultipleTimes(AppiumDriver driver) {
        for(int i = 0; i < 3; i++) {
            loginPage.fillUsername("invalidUsername", driver);
            loginPage.fillPassword("invalidPassword", driver);
            loginPage.clickLoginButton(driver);
        }
    }


    @When("The number of failed login attempts exceeds the limit")
    public void theNumberOfFailedLoginAttemptsExceedsTheLimit(){
        databasePage.theNumberOfFailedLoginAttemptsExceedsTheLimit();
    }

    @Then("The user's account status should be updated in the database to {string}")
    public void theUserSAccountStatusShouldBeUpdatedInTheDatabaseTo()  {
        databasePage.theUserSAccountStatusShouldBeUpdatedInTheDatabaseTo();

    }

    @And("The user should be prevented from further login attempts until the account is unlocked")
    public void theUserShouldBePreventedFromFurtherLoginAttemptsUntilTheAccountIsUnlocked(AppiumDriver driver) {
        boolean isLockedOut = loginPage.isAccountLockedOut(driver);
        assertTrue("Account was not locked out", isLockedOut);
    }


    @After
    public void tearDown() {
        databasePage.closeDatabaseConnection();
    }

}
