
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

            /* WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

// --- Соусы ---
            WebElement saucesTab = driver.findElement(By.xpath("//span[text()='Соусы']/parent::div"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saucesTab);
            wait.until(ExpectedConditions.textToBePresentInElement(saucesTab, "Соусы"));

// --- Булки ---
            WebElement bunsTab = driver.findElement(By.xpath("//span[text()='Булки']/parent::div"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", bunsTab);
            wait.until(ExpectedConditions.textToBePresentInElement(bunsTab, "Булки"));

// --- Начинки ---
            WebElement fillingsTab = driver.findElement(By.xpath("//span[text()='Начинки']/parent::div"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fillingsTab);
            wait.until(ExpectedConditions.textToBePresentInElement(fillingsTab, "Начинки"));*/
        }

    }


