
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class TabsTest extends BaseTest{

        @Test
        public void checkTabsSwitching() {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement sauces = driver.findElement(By.xpath("//span[text()='Соусы']"));
            sauces.click();
            wait.until(ExpectedConditions.textToBePresentInElement(sauces, "Соусы"));

            WebElement buns = driver.findElement(By.xpath("//span[text()='Булки']"));
            buns.click();
            wait.until(ExpectedConditions.textToBePresentInElement(buns, "Булки"));

            WebElement fillings = driver.findElement(By.xpath("//span[text()='Начинки']"));
            fillings.click();
            wait.until(ExpectedConditions.textToBePresentInElement(fillings, "Начинки"));

        }

    }


