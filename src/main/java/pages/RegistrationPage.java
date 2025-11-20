package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private final By nameInput = By.xpath("//input[@name='name']");
    private final By emailInput = By.xpath("//input[@name='name']");
    private final By passwordInput = By.xpath("//input[@name='Пароль']");
    private final By loginOnRegistrationPageButton = By.xpath("//a[@href='/login' and text()='Войти']");
    private final By registrationButton = By.xpath("//a[text()='Зарегистрироваться']");
    private final By registrationPageHeader = By.xpath("//h2[text()='Регистрация']");
    private final By invalidPasswordWarning = By.xpath("//p[text()='Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillRegistrationForm(String name, String email, String password) {
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void waitUntilRegistrationButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(registrationButton));
    }
    public LoginPage registrationOnRegistrationButtonPress() {
        driver.findElement(registrationButton).click();
        return new LoginPage(driver);
    }
    public LoginPage loginOnRegistrationPageButtonPress(){
        driver.findElement(loginOnRegistrationPageButton).click();
        return new LoginPage(driver);
    }
    public void waitUntilLoginButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(loginOnRegistrationPageButton));
    }

    public boolean isRegistrationPageHeaderDisplayed(){
        return driver.findElement(registrationPageHeader).isDisplayed();
    }
    public boolean isInvalidPasswordWarningDisplayed(){
        return driver.findElement(invalidPasswordWarning).isDisplayed();
    }

}




