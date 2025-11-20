package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    private final By registrationButton = By.xpath("//a[text()='Зарегистрироваться']");
    private final By emailInput = By.xpath("//input[@name='name']");
    private final By passwordInput = By.xpath("//input[@name='Пароль']");
    private final By recoverPasswordButton = By.xpath("//a[@href='/forgot-password' and text()='Восстановить пароль']");
    private final By loginPageHeader = By.xpath("//h2[text()='Вход']");
    private final By loginButtonOnLoginPage = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoginPageHeaderDisplayed(){
        return driver.findElement(loginPageHeader).isDisplayed();
    }

    public void waitUntilLoginPageHeaderVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(loginPageHeader));
    }

    public void waitUntilRegistrationButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(registrationButton));
    }

    public RegistrationPage registrationButtonPress(){
        driver.findElement(registrationButton).click();
        return new RegistrationPage(driver);
    }

    public void fillLoginForm(String email, String password) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public HomePage loginButtonOnLoginPagePress(){
        driver.findElement(loginButtonOnLoginPage).click();
        return new HomePage(driver);

    }

    public RecoverPasswordPage recoverPasswordButtonPress(){
        driver.findElement(recoverPasswordButton).click();
        return new RecoverPasswordPage(driver);
    }

    public void waitUntilRecoverPasswordButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(recoverPasswordButton));
    }

}
