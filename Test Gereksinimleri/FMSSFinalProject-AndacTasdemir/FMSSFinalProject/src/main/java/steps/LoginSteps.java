package steps;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;


public class LoginSteps {

    static AppiumDriver driver;
    LoginPage loginPage = new LoginPage();

    @Given("User launch Appium Driver And InstallApk")
    public void userLaunchAppiumDriverAndInstallApk() {
        loginPage.launchAppiumDriverAndInstallApk(driver);
    }

    @Given("The user is on the login page")
    public void theUserIsOnTheLoginPage() {
        loginPage.navigateToLoginPage(driver);
    }

    //1.Senaryo
    @When("The user enters valid username as {string} and password as {string}")
    public void theUserEntersValidUsernameAsAndPasswordAs(String username, String password) {
        loginPage.fillUsername(username, driver);
        loginPage.fillPassword(password, driver);
    }

    @And("The user clicks on the login button")
    public void theUserClicksOnTheLoginButton() {
        loginPage.clickLoginButton(driver);
    }

    @And("A success message should be displayed")
    public void aSuccessMessageShouldBeDisplayed() {
        boolean aSuccessMessage = loginPage.clickLoginButtonStatus(driver);
        loginPage.assertTrueFunc("Login successful",aSuccessMessage);
    }

    //2.Senaryo
    @When("The user enters a valid username as {string} and an invalid password as {string}")
    public void theUserEntersAValidUsernameAsAndAnInvalidPasswordAs(String username, String invalid) {
        loginPage.fillUsername(username, driver);
        loginPage.fillPassword(invalid, driver);
    }

    @Then("The user should see an error message indicating invalid password credentials")
    public void theUserShouldSeeAnErrorMessageIndicatingInvalidPasswordCredentials() {
        boolean invalidCredentialPassword = loginPage.clickLoginButtonStatus(driver);
        loginPage.assertTrueFunc("Login Unsuccessful! Password is invalid", invalidCredentialPassword);
    }

    //3.Senaryo
    @When("The user enters an invalid username as {string} and a valid password as {string}")
    public void theUserEntersAnInvalidUsernameAsAndAValidPasswordAs(String invalid, String password) {
        loginPage.fillUsername(invalid, driver);
        loginPage.fillPassword(password, driver);
    }

    @Then("The user should see an error message indicating invalid username credentials")
    public void theUserShouldSeeAnErrorMessageIndicatingInvalidUsernameCredentials() {
        boolean invalidCredentialUsername = loginPage.clickLoginButtonStatus(driver);
        loginPage.assertTrueFunc("Login Unsuccessful! Username is invalid", invalidCredentialUsername);
    }

    //4.Senaryo
    @When("The user leaves the username and password as {string} fields empty as {string}")
    public void theUserLeavesTheUsernameAndPasswordAsFieldsEmptyAs(String emptyusername, String emptypassword) {
        loginPage.fillUsername(emptyusername, driver);
        loginPage.fillPassword(emptypassword, driver);
    }

    @Then("The user should see an error message indicating required fields")
    public void theUserShouldSeeAnErrorMessageIndicatingRequiredFields() {
        boolean invalidCredential = loginPage.clickLoginButtonStatus(driver);
        loginPage.assertTrueFunc("Login Unsuccessful! Username and password is empty", invalidCredential);
    }

    //5.Senaryo
    @And("The user selects the Remember Me checkbox")
    public void theUserSelectsTheRememberMeCheckbox() {
        loginPage.selectRememberMeCheckbox(driver);
    }


    @And("The user's credentials should be saved for future logins")
    public void theUserSCredentialsShouldBeSavedForFutureLogins() {
        boolean isRemembered = loginPage.clickLoginButtonStatus(driver);
        loginPage.assertTrueFunc("Login Success, Credentials are not remembered", isRemembered);
    }

}
