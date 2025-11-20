import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.HomePage;

import static constants.Constants.*;

public class BaseTest {
    WebDriver driver;
   // private final static String USER_CREATION_API = "/api/auth/register";
   // private final static String USER_DELETION_API ="/api/auth/user";

    HomePage homePage;

   // String userToken;

    @Before
    public void setUp() {
        driver = getDriver(DriverType.CHROME);
       // homePage = new HomePage(driver);
        driver.get(PAGE_URL);

       // UserModel user = new UserModel(EMAIL, PASSWORD, NAME);
        //RestAssured.baseURI = PAGE_URL;

       /* ValidatableResponse response = given()
                .log().all()
                .header("Content-type", "application/json")
                .body(user)
                .post(USER_CREATION_API)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .body("success", equalTo(true));

        userToken = response.extract().path("accessToken");*/
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
