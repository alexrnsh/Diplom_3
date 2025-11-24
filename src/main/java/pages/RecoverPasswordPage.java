package pages;

import io.qameta.allure.Step;
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

    @Step("Кнопка Войти отображается на странице Восстановления пароля (true/false)")
    public boolean loginButtonOnRecoverPasswordPageDisplayed(){
        return driver.findElement(loginButtonOnRecoverPasswordPage).isDisplayed();
    }
    @Step("Ожидание появления кнопки Войти на странице Восстановить пароль")
    public void waitUntilLoginButtonOnRecoveryPageVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(loginButtonOnRecoverPasswordPage));
    }
    @Step("Нажатие на кнопку Войти на странице восстановления пароля")
    public LoginPage loginButtonOnRecoverPasswordPagePress() {
        driver.findElement(loginButtonOnRecoverPasswordPage).click();
        return new LoginPage(driver);
    }

}
