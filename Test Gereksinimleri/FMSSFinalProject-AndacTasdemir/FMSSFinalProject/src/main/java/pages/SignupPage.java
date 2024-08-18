package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SignupPage {

    private final By signupButtonLoc = By.id("");
    private final By usernameFieldLoc = By.id("");
    private final By passwordFieldLoc = By.id("");
    private final By emailFieldLoc = By.id("");
    private final By confirmPasswordFieldLoc = By.id("");
    private final By errorElementLoc = By.id("");


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

    private WebElement navigateToSignupPageEl(AppiumDriver driver){
        return driver.findElement(signupButtonLoc);
    }


    private List<WebElement> enterSignupDetailsEl(AppiumDriver driver) {

        List<WebElement> elements = new ArrayList<>();
        elements.add(driver.findElement(usernameFieldLoc));
        elements.add(driver.findElement(passwordFieldLoc));
        elements.add(driver.findElement(emailFieldLoc));
        elements.add(driver.findElement(confirmPasswordFieldLoc));

        return elements;
    }

    private WebElement clickSignupButtonEl(AppiumDriver driver){
        return driver.findElement(signupButtonLoc);
    }

    public void navigateToSignupPage(AppiumDriver driver) {
        navigateToSignupPageEl(driver).click();
    }

    public void enterSignupDetails(AppiumDriver driver, String username, String password, String email, String confirmPassword) {
        List<WebElement> signupElements = enterSignupDetailsEl(driver);

        signupElements.get(0).sendKeys(username);
        signupElements.get(1).sendKeys(password);
        signupElements.get(2).sendKeys(email);
        signupElements.get(3).sendKeys(confirmPassword);
    }


    public void clickSignupButton(AppiumDriver driver) {
        clickSignupButtonEl(driver).click();
    }

    public boolean isSignupSuccessful(AppiumDriver driver) {
        WebElement successMessage = driver.findElement(signupButtonLoc);
        return successMessage.isDisplayed();
    }

    public boolean isErrorMessageDisplayed(AppiumDriver driver,String errorMessage) {
        WebElement errorElement = driver.findElement(By.id(errorElementLoc + errorMessage));
        return errorElement.isDisplayed();
    }
}

