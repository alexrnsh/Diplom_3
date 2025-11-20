import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static constants.Constants.*;

public class BaseTest {

    WebDriver driver;

    @Before
    public void setUp() {
        // TODO do this also for yandex browser
        driver = getDriver(DriverType.CHROME);
        driver.get(PAGE_URL);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    private WebDriver getDriver(DriverType driverType) {

        if (driverType == DriverType.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();

    }

    enum DriverType {
        CHROME, FIREFOX
    }
}
