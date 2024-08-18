package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class LogoutPage {

    private final By clickLogoutButtonLoc = By.id("");
    public final String navigateLoginLoc = "";
    public final String navigateLogoutLoc = "";
    private final By sessionCheckLoc = By.id("");
    private final By profileIconLoc = By.id("");
    private final By bookinPageLoc = By.id("");
    private final By isSessionLoc = By.id("");
    public final String navigateToRestrictedPageLoc = "";
    private final By isAccessDeniedLoc = By.id("");



    @org.jetbrains.annotations.Nullable
    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void launchAppiumDriverAndInstallApkLogout(AppiumDriver driver) {
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

    private WebElement clickLogoutButtonEl(AppiumDriver driver){
        return driver.findElement(clickLogoutButtonLoc);
    }

    private WebElement isNoActiveSessionErrorMessageDisplayedEl(AppiumDriver driver){
        return driver.findElement((sessionCheckLoc));
    }

    private WebElement profileIconCheck(AppiumDriver driver){
        return  driver.findElement(profileIconLoc);
    }

    private void navigateToBookingPageEl(AppiumDriver driver){
        driver.findElement(bookinPageLoc);
    }

    private WebElement isSessionActiveEl(AppiumDriver driver){
        return driver.findElement(isSessionLoc);
    }

    private WebElement isAccessDeniedMessageDisplayedEl(AppiumDriver driver){
        return driver.findElement(isAccessDeniedLoc);
    }

    public void clickLogoutButton(AppiumDriver driver){
        clickLogoutButtonEl(driver).click();
    }

    public boolean clickLogoutButtonStatus(AppiumDriver driver) {
        return clickLogoutButtonEl(driver).isDisplayed();
    }

    public void assertTrueFunc(String message,boolean isRedirected) {
        assertTrue(message,isRedirected);
    }

    public void navigateToLoginPage(AppiumDriver driver) {
        driver.navigate().to(navigateLoginLoc);
    }

    public void navigateToLogoutUrl(AppiumDriver driver) {
        driver.navigate().to(navigateLogoutLoc);
    }

    public boolean isNoActiveSessionErrorMessageDisplayed(AppiumDriver driver) {
        return isNoActiveSessionErrorMessageDisplayedEl(driver).isDisplayed();
    }

    public void isUserLoggedIn(AppiumDriver driver) {
        try {
            profileIconCheck(driver).isDisplayed();
        } catch (NoSuchElementException ignored) {
        }
    }

    public void navigateToBookingPage(AppiumDriver driver) {
        navigateToBookingPageEl(driver);
    }

    public void navigateBack(AppiumDriver driver) {
        driver.navigate().back();
    }

    public boolean isSessionActive(AppiumDriver driver) {
        try {
            return isSessionActiveEl(driver).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public void navigateToRestrictedPage(AppiumDriver driver) {
        driver.navigate().to(navigateToRestrictedPageLoc);
    }

    public boolean isAccessDeniedMessageDisplayed(AppiumDriver driver) {
        return isAccessDeniedMessageDisplayedEl(driver).isDisplayed();
    }


}
