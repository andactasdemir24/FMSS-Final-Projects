package steps;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.SignupPage;

public class SignupSteps {

    static AppiumDriver driver;
    SignupPage signupPage = new SignupPage();

    @Given("User launch Appium Driver And InstallApk SignUp")
    public void userLaunchAppiumDriverAndInstallApkSignUp() {
        signupPage.launchAppiumDriverAndInstallApk(driver);
    }

    @Given("The user is on the signup page")
    public void theUserIsOnTheSignupPage() {
        signupPage.navigateToSignupPage(driver);
    }

    @When("The user enters valid details \\(username, password, email, etc.)")
    public void theUserEntersValidDetailsUsernamePasswordEmailEtc() {
        signupPage.enterSignupDetails(driver, "validUser", "validPass", "validEmail@example.com", "validPass");
    }

    @When("The user enters a username that already exists")
    public void theUserEntersAUsernameThatAlreadyExists() {
        signupPage.enterSignupDetails(driver, "existingUser", "validPass", "validEmail@example.com", "validPass");
    }

    @When("The user enters an invalid email format")
    public void theUserEntersAnInvalidEmailFormat() {
        signupPage.enterSignupDetails(driver, "validUser", "validPass", "invalidEmail", "validPass");
    }

    @When("The user enters a password and a different password confirmation")
    public void theUserEntersAPasswordAndADifferentPasswordConfirmation() {
        signupPage.enterSignupDetails(driver, "validUser", "validPass", "validEmail@example.com", "differentPass");
    }

    @When("The user leaves required fields empty \\(e.g., username, password)")
    public void theUserLeavesRequiredFieldsEmptyEGUsernamePassword() {
        signupPage.enterSignupDetails(driver, "", "", "validEmail@example.com", "");
    }

    @And("The user clicks on the signup button")
    public void theUserClicksOnTheSignupButton() {
        signupPage.clickSignupButton(driver);
    }

    @Then("The signup should be successful")
    public void theSignupShouldBeSuccessful() {
        Assert.assertTrue(signupPage.isSignupSuccessful(driver));
    }

    @Then("The user should see an error message \\(username exists)")
    public void theUserShouldSeeAnErrorMessageUsernameExists() {
        Assert.assertTrue(signupPage.isErrorMessageDisplayed(driver,"Username already exists"));
    }

    @Then("The user should see an error message \\(invalid email format)")
    public void theUserShouldSeeAnErrorMessageInvalidEmailFormat() {
        Assert.assertTrue(signupPage.isErrorMessageDisplayed(driver,"Invalid email format"));
    }

    @Then("The user should see an error message \\(password mismatch)")
    public void theUserShouldSeeAnErrorMessagePasswordMismatch() {
        Assert.assertTrue(signupPage.isErrorMessageDisplayed(driver,"Passwords do not match"));
    }

    @Then("The user should see an error message \\(required fields empty)")
    public void theUserShouldSeeAnErrorMessageRequiredFieldsEmpty() {
        Assert.assertTrue(signupPage.isErrorMessageDisplayed(driver,"Required fields cannot be empty"));
    }
}
