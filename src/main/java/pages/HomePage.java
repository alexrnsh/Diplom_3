package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;

    private final By loginButtonHomePage = By.xpath("//button[text()='Войти в аккаунт']");
    private final By privateAccountButton = By.xpath("//p[text()='Личный Кабинет' or text()='Личный кабинет']");
    private final By createOrderButton = By.xpath("//button[text()='Оформить заказ']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoginButtonHomePageDisplayed() {
        return driver.findElement(loginButtonHomePage).isDisplayed();
    }
    public LoginPage loginButtonHomePagePress(){
        driver.findElement(loginButtonHomePage).click();
        return new LoginPage(driver);
    }
    public boolean isPrivateAccountButtonDisplayed() {
        return driver.findElement(privateAccountButton).isDisplayed();
    }

    public LoginPage privateAccountButtonPress(){
        driver.findElement(privateAccountButton).click();
        return new LoginPage(driver);
    }
    public void waitUntilLoginButtonHomePageVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(loginButtonHomePage));
    }
    public void waitUntilPrivateAccountButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(privateAccountButton));
    }

    public boolean isCreateOrderButtonDisplayed(){
        return driver.findElement(createOrderButton).isDisplayed();
    }

    public void waitUntilCreateOrderButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
    }
}
