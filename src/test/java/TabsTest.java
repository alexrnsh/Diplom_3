
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

@RunWith(Parameterized.class)
public class TabsTest extends BaseTest {

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Object[][] browsers() {
        return new Object[][] {
                { DriverType.YANDEX },
                { DriverType.CHROME }

        };
    }

    public TabsTest(DriverType driverType) {
        this.driverType = driverType;
    }

    @Test
    @DisplayName("Переключение между вкладками раздела Соберите бургер")
    @Description("Осуществляет переключение между вкладками Соусы, Булки, Начинки")
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


