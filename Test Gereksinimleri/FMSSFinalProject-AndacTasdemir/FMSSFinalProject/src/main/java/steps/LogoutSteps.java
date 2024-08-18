package steps;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.LogoutPage;

public class LogoutSteps {

    static AppiumDriver driver;
    LoginPage loginPage = new LoginPage();
    LogoutPage logoutPage = new LogoutPage();

    @Given("User launch Appium Driver And InstallApk Logout")
    public void userLaunchAppiumDriverAndInstallApkLogout() {
        logoutPage.launchAppiumDriverAndInstallApkLogout(driver);
    }

    //1.Senaryo
    @Given("The user is logged in Username as {string} password as {string}")
    public void theUserIsLoggedInUsernameAsPasswordAs(String username, String password) {
        loginPage.fillUsername(username, driver);
        loginPage.fillPassword(password, driver);
        loginPage.clickLoginButton(driver);

    }

    @When("The user clicks on the logout button")
    public void theUserClicksOnTheLogoutButton() {
        logoutPage.clickLogoutButton(driver);
    }

    @And("A success message should be displayed \\(logout)")
    public void aSuccessMessageShouldBeDisplayedLogout() {
        boolean aSuccessMessage = logoutPage.clickLogoutButtonStatus(driver);
        logoutPage.assertTrueFunc("Logout Success",aSuccessMessage);
    }

    //2.Senaryo
    @Given("The user is on the login page \\(logout)")
    public void theUserIsOnTheLoginPageLogout() {
        logoutPage.navigateToLoginPage(driver);
    }

    @When("The user navigates to the logout URL manually")
    public void theUserNavigatesToTheLogoutURLManually() {
        logoutPage.navigateToLogoutUrl(driver);

    }

    @Then("The user should see an error message indicating no active session")
    public void theUserShouldSeeAnErrorMessageIndicatingNoActiveSession() {
        boolean isErrorMessageDisplayed = logoutPage.isNoActiveSessionErrorMessageDisplayed(driver);
        logoutPage.assertTrueFunc("Error message is not displayed", isErrorMessageDisplayed);
    }

    @And("The user should remain on the login page")
    public void theUserShouldRemainOnTheLoginPage() {
        boolean isOnLoginPage = loginPage.clickLoginButtonStatus(driver);
        loginPage.assertTrueFunc("User is not on the login page", isOnLoginPage);
    }

    //3.Senaryo
    @Given("The user is on the booking page and logged in")
    public void theUserIsOnTheBookingPageAndLoggedIn() {
        logoutPage.navigateToBookingPage(driver);
        logoutPage.isUserLoggedIn(driver);
    }

    @And("The session should be terminated")
    public void theSessionShouldBeTerminated() {
        boolean isSessionTerminated = logoutPage.isNoActiveSessionErrorMessageDisplayed(driver);
        logoutPage.assertTrueFunc("Session is not terminated", isSessionTerminated);
    }

    //4.Senaryo
    @And("The user tries to navigate back using the browser's back button")
    public void theUserTriesToNavigateBackUsingTheBrowserSBackButton() {
        logoutPage.navigateBack(driver);
    }

    @And("The session should not be active")
    public void theSessionShouldNotBeActive() {
        boolean isSessionActive = logoutPage.isSessionActive(driver);
        logoutPage.assertTrueFunc("Session is still active", isSessionActive);
    }

    //5.Senaryo
    @When("The user logs out")
    public void theUserLogsOut() {
        logoutPage.clickLogoutButton(driver);

    }

    @And("The user attempts to access a restricted page \\(e.g., dashboard)")
    public void theUserAttemptsToAccessARestrictedPageEGDashboard() {
        logoutPage.navigateToRestrictedPage(driver);

    }

    @And("The user should see an access denied message")
    public void theUserShouldSeeAnAccessDeniedMessage() {
        boolean isAccessDenied = logoutPage.isAccessDeniedMessageDisplayed(driver);
        logoutPage.assertTrueFunc("Access denied message is not displayed", isAccessDenied);
    }

}
