package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PrivateAccountPage {

    private final WebDriver driver;
    private final By nameOnPrivateAccountPage =By.xpath("//input[@name='Name']");

    public PrivateAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilNameVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(nameOnPrivateAccountPage));
    }
}
