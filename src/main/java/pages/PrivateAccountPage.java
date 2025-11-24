package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class PrivateAccountPage {

    private final WebDriver driver;
    private final By nameOnPrivateAccountPage =By.xpath("//input[@name='Name']");
    public static final By nameInTheNameField = By.xpath("//input[@name='Name']");

    public PrivateAccountPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Ожидание появления поля Имя")
    public void waitUntilNameVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(nameOnPrivateAccountPage));
    }
    @Step("Получение имени из поля Имя в личном кабинете")
    public String getNameFieldValue() {
        return driver.findElement(nameInTheNameField).getAttribute("value");
    }

}
