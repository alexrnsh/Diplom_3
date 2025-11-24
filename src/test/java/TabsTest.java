
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.HomePage;

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
    @DisplayName("Провека переключения на вкладку Соусы")
    @Description("Осуществляет переключение на вкладку Соусы и проверку что она становится активной")
    public void saucesTabBecomesActive() {
        HomePage homePage = new HomePage(driver);

        homePage.clickSauceTab();
        Assert.assertTrue(homePage.isSauceTabActive());
    }
    @Test
    @DisplayName("Провека переключения на вкладку Булки")
    @Description("Осуществляет переключение на вкладку Булки и проверку что она становится активной")
    public void bunsTabBecomesActive() {
        HomePage homePage = new HomePage(driver);

        homePage.clickBunsTab();
        Assert.assertTrue(homePage.isBunsTabActive());
    }
    @Test
    @DisplayName("Провека переключения на вкладку Начинки")
    @Description("Осуществляет переключение на вкладку Начинки и проверку что она становится активной")
    public void fillingsTabBecomesActive() {
        HomePage homePage = new HomePage(driver);

        homePage.clickFillingsTab();
        Assert.assertTrue(homePage.isFillingsTabActive());
    }

}


