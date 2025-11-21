import constants.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import pages.HomePage;
import pages.LoginPage;
import pages.PrivateAccountPage;
import pages.RegistrationPage;

import static constants.Constants.*;

@RunWith(Parameterized.class)
public class RegistrationTest extends BaseTest {

    public static final By nameInTheNameField = By.xpath("//input[@name='Name']");

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Object[][] browsers() {
        return new Object[][] {
                { DriverType.YANDEX },
                { DriverType.CHROME }

        };
    }

    public RegistrationTest(DriverType driverType) {
        this.driverType = driverType;
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Осуществляет регистрацию, затем вход и проверку успешной регистрации по имени в Личном кабинете")
    public void registrationSuccess(){
        HomePage objHomePage = new HomePage(driver);

        objHomePage.waitUntilLoginButtonHomePageVisible();
        LoginPage objLoginPage = objHomePage.loginButtonHomePagePress();
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        objLoginPage.waitUntilRegistrationButtonVisible();
        RegistrationPage objRegistrationPage = objLoginPage.registrationButtonPress();
        Assert.assertTrue("Переход на страницу Регистрации не совершен", objRegistrationPage.isRegistrationPageHeaderDisplayed());

        String email = Constants.faker.internet().emailAddress();
        objRegistrationPage.fillRegistrationForm(NAME, PASSWORD, email);
        objRegistrationPage.waitUntilRegistrationButtonVisible();
        objLoginPage = objRegistrationPage.registrationButtonOnRegistrationPagePress();
        objLoginPage.waitUntilLoginPageHeaderVisible();
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        objLoginPage.fillLoginForm(email, PASSWORD);
        objHomePage = objLoginPage.loginButtonOnLoginPagePress();
        objHomePage.waitUntilPrivateAccountButtonVisible();
        PrivateAccountPage objPrivateAccountPage = objHomePage.privateAccountButtonPressWhenLoggedIn();
        objPrivateAccountPage.waitUntilNameVisible();
        String actualName = driver.findElement(nameInTheNameField).getAttribute("value");
        Assert.assertEquals("Имя не совпадает", NAME, actualName);

    }

    @Test
    @DisplayName("Вывод ошибки для некорректного пароля")
    @Description("Переходит на страницу регистрации и осуществляет ввод невалидного пароля, ожидается сообщение об ошибке")
    public void registrationPasswordWarning(){
        HomePage objHomePage = new HomePage(driver);
        LoginPage objLoginPage;
        RegistrationPage objRegistrationPage;

        objHomePage.waitUntilLoginButtonHomePageVisible();
        objLoginPage= objHomePage.loginButtonHomePagePress();
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        objLoginPage.waitUntilRegistrationButtonVisible();
        objRegistrationPage = objLoginPage.registrationButtonPress();
        Assert.assertTrue("Переход на страницу Регистрации не совершен", objRegistrationPage.isRegistrationPageHeaderDisplayed());

        objRegistrationPage.fillRegistrationForm(NAME, INVALID_PASSWORD, EMAIL);
        objRegistrationPage.waitUntilRegistrationButtonVisible();
        objRegistrationPage.registrationButtonOnRegistrationPagePress();
        Assert.assertTrue("Сообщение о невалидном пароле не появилось", objRegistrationPage.isInvalidPasswordWarningDisplayed());
    }

}
