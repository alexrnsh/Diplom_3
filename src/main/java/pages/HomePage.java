package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class HomePage {

    private final WebDriver driver;

    private final By loginButtonHomePage = By.xpath("//button[text()='Войти в аккаунт']");
    private final By privateAccountButton = By.xpath("//p[text()='Личный Кабинет' or text()='Личный кабинет']");
    private final By createOrderButton = By.xpath("//button[text()='Оформить заказ']");

    private static final By SAUCE_TAB = By.xpath("//div[contains(@class,'tab_tab')][.//span[text()='Соусы']]");
    private static final By BUNS_TAB = By.xpath("//div[contains(@class,'tab_tab')][.//span[text()='Булки']]");
    private static final By FILLINGS_TAB = By.xpath("//div[contains(@class,'tab_tab')][.//span[text()='Начинки']]");

    private static final String ACTIVE_CLASS = "tab_tab_type_current__2BEPc";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step ("Нажатие на кнопку Войти в аккаунт")
    public LoginPage loginButtonHomePagePress(){
        driver.findElement(loginButtonHomePage).click();
        return new LoginPage(driver);
    }

    @Step ("Нажатие на кнопку Личный кабинет")
    public LoginPage privateAccountButtonPress(){
        driver.findElement(privateAccountButton).click();
        return new LoginPage(driver);
    }

    @Step ("Ожидание появления кнопки Войти в аккаунт")
    public void waitUntilLoginButtonHomePageVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(loginButtonHomePage));
    }

    @Step ("Ожидание появления кнопки Личный кабинет")
    public void waitUntilPrivateAccountButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(privateAccountButton));
    }

    @Step ("Кнопка Создать заказ отображается (true/false)")
    public boolean isCreateOrderButtonDisplayed(){
        return driver.findElement(createOrderButton).isDisplayed();
    }

    @Step ("Ожидание появления кнопки Создать заказ")
    public void waitUntilCreateOrderButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
    }

    @Step("Нажатие на вкладку Соусы")
    public void clickSauceTab() {
        WebElement tab = driver.findElement(SAUCE_TAB);
        if (!Objects.requireNonNull(tab.getAttribute("class")).contains(ACTIVE_CLASS)) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
        }
    }

    @Step("Нажатие на вкладку Булки")
    public void clickBunsTab() {
        WebElement tab = driver.findElement(BUNS_TAB);
        if (!Objects.requireNonNull(tab.getAttribute("class")).contains(ACTIVE_CLASS)) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
        }
    }

    @Step("Нажатие на вкладку Начинки")
    public void clickFillingsTab() {
        WebElement tab = driver.findElement(FILLINGS_TAB);
        if (!Objects.requireNonNull(driver.findElement(FILLINGS_TAB).getAttribute("class")).contains(ACTIVE_CLASS)) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
        }
    }

    @Step("Вкладка Соусы активна true/false")
    public boolean isSauceTabActive() {
        return Objects.requireNonNull(driver.findElement(SAUCE_TAB).getAttribute("class")).contains(ACTIVE_CLASS);
    }

    @Step("Вкладка Булки активна true/false")
    public boolean isBunsTabActive() {
        return Objects.requireNonNull(driver.findElement(BUNS_TAB).getAttribute("class")).contains(ACTIVE_CLASS);
    }

    @Step("Вкладка Начинки активна true/false")
    public boolean isFillingsTabActive() {
        return Objects.requireNonNull(driver.findElement(FILLINGS_TAB).getAttribute("class")).contains(ACTIVE_CLASS);
    }

}
