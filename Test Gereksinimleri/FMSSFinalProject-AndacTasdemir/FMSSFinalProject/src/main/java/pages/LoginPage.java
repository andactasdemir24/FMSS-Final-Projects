package pages;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.remote.options.BaseOptions;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static org.junit.Assert.assertTrue;


public class LoginPage {

    private final By usernameLoc = By.id("");
    private final By passwordLoc = By.id("");
    public final String navigateLoginLoc = "app://login-pagurle-";
    private final By clickLoginButtonLoc = By.id("");
    private final By rememberCheckBoxLoc = By.id("");


    @org.jetbrains.annotations.Nullable
    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void launchAppiumDriverAndInstallApk(AppiumDriver driver) {
        var options = new BaseOptions()
                .amend("platformName", "Android")
                .amend("appium:deviceName", "Pixel 6")
                .amend("appium:automationName", "UiAutomator2")
                .amend("appium:udid", "Android Emulator")
                .amend("appium:avd", "Pixel_6_Pro_API_32")
                .amend("appium:fastReset", true)
                .amend("appium:newCommandTimeout", 5)
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("appium:connectHardwareKeyboard", true)
                .amend("appium:app", "");


        driver = new AppiumDriver(Objects.requireNonNull(this.getUrl()), options);
    }

    private WebElement usernameEl(AppiumDriver driver){
        return driver.findElement(usernameLoc);
    }

    private WebElement passwordEl(AppiumDriver driver){
        return driver.findElement(passwordLoc);
    }

    private WebElement clickLoginButtonEl(AppiumDriver driver){
        return driver.findElement(clickLoginButtonLoc);
    }

    private WebElement rememberCheckboxEl(AppiumDriver driver){
        return driver.findElement(rememberCheckBoxLoc);
    }


    public void fillUsername(String username, AppiumDriver driver){
        usernameEl(driver).sendKeys(username);
    }

    public void fillPassword(String password, AppiumDriver driver){
        passwordEl(driver).sendKeys(password);
    }

    public void navigateToLoginPage(AppiumDriver driver) {
        driver.navigate().to(navigateLoginLoc);
    }

    public void clickLoginButton(AppiumDriver driver){
        clickLoginButtonEl(driver).click();
    }

    public boolean clickLoginButtonStatus(AppiumDriver driver) {
        return clickLoginButtonEl(driver).isDisplayed();
    }

    public void assertTrueFunc(String message,boolean isRedirected) {
        assertTrue(message,isRedirected);
    }

    public void selectRememberMeCheckbox(AppiumDriver driver) {
        rememberCheckboxEl(driver).click();
    }

    public boolean isAccountLockedOut() {
        return true;

    }
}
