package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;

    private final By nameInput = By.xpath("//input[@name='name']");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//input[@name='Пароль']");
    private final By loginOnRegistrationPageButton = By.xpath("//a[@href='/login' and text()='Войти']");
    private final By registrationButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By registrationPageHeader = By.xpath("//h2[text()='Регистрация']");
    private final By invalidPasswordWarning = By.xpath("//p[text()='Некорректный пароль']");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение формы регистрации")
    public void fillRegistrationForm(String name, String password, String email) {
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }
    @Step("Ожидание появления кнопки Зарегистрироваться на странице регистрации")
    public void waitUntilRegistrationButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(registrationButton));
    }
    @Step("Нажатие на кнопку Зарегистрироваться на странице регистрации")
    public LoginPage registrationButtonOnRegistrationPagePress() {
        driver.findElement(registrationButton).click();
        return new LoginPage(driver);
    }
    @Step("Нажатие на кнопку Войти на странице регистрации")
    public LoginPage loginOnRegistrationPageButtonPress(){
        driver.findElement(loginOnRegistrationPageButton).click();
        return new LoginPage(driver);
    }
    @Step("Ожидание появления кнопки Войти на странице регистрации")
    public void waitUntilLoginButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(loginOnRegistrationPageButton));
    }
    @Step("Ожидание появления заголовка Регистрация страницы регистрации")
    public boolean isRegistrationPageHeaderDisplayed(){
        return driver.findElement(registrationPageHeader).isDisplayed();
    }
    @Step("Сообщение о некорректном пароле отображается в форме регистрации (true/false)")
    public boolean isInvalidPasswordWarningDisplayed(){
        return driver.findElement(invalidPasswordWarning).isDisplayed();
    }

}




