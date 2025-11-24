import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static constants.Constants.*;

public class BaseTest {

    protected WebDriver driver;
    private Properties properties;

    @Before
    public void setUp() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/config.properties"));

        String browser = System.getProperty("browser", "CHROME");

        DriverType driverType = DriverType.valueOf(browser.toUpperCase());
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
                        "src/test/resources/chromedriver.exe");
                String yandexPath = properties.getProperty("yandex.browser.path");
                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.setBinary(yandexPath);
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
