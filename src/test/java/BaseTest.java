import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static constants.Constants.*;

public class BaseTest {

    protected WebDriver driver;
    protected DriverType driverType;

    @Before
    public void setUp() {
        driver = getDriver(driverType);
        driver.get(PAGE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private WebDriver getDriver(DriverType driverType) {
        switch (driverType) {

            case YANDEX:
                System.setProperty("webdriver.chrome.driver",
                        "src/test/resources/chromedriver.exe"); // chromedriver 138
                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.setBinary("C:/Users/alexa/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
                return new ChromeDriver(yandexOptions);
            case CHROME:
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }

    public enum DriverType {
        CHROME, YANDEX
    }
}
