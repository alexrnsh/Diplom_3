package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoverPasswordPage {

    private final WebDriver driver;
    private final By loginButtonOnRecoverPasswordPage = By.xpath("//a[@href='/login' and text()='Войти']");

    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean loginButtonOnRecoverPasswordPageDisplayed(){
        return driver.findElement(loginButtonOnRecoverPasswordPage).isDisplayed();
    }
    public void waitUntilLoginButtonOnRecoveryPageVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(loginButtonOnRecoverPasswordPage));
    }
    public LoginPage loginButtonOnRecoverPasswordPagePress() {
        driver.findElement(loginButtonOnRecoverPasswordPage).click();
        return new LoginPage(driver);
    }

}
