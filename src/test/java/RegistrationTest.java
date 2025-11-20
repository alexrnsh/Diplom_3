import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

import static constants.Constants.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;

public class RegistrationTest extends BaseTest {

    @Test

    public void registrationSuccess(){
        HomePage objHomePage = new HomePage(driver);
        LoginPage objLoginPage;
        RegistrationPage objRegistrationPage;

        objHomePage.waitUntilLoginButtonHomePageVisible();
        objLoginPage= objHomePage.loginButtonHomePagePress();
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        objLoginPage.waitUntilRegistrationButtonVisible();
        objRegistrationPage = objLoginPage.registrationButtonPress();
        Assert.assertTrue("Переход на страницу Регистрации не совершен", objRegistrationPage.isRegistrationPageHeaderDisplayed());
        objRegistrationPage.fillRegistrationForm(EMAIL, PASSWORD, NAME);
        objRegistrationPage.waitUntilRegistrationButtonVisible();
        objLoginPage = objRegistrationPage.registrationOnRegistrationButtonPress();
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());
    }

    @Test

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
        objRegistrationPage.fillRegistrationForm(EMAIL, INVALIDPASSWORD, NAME);
        objRegistrationPage.waitUntilRegistrationButtonVisible();
        objRegistrationPage.registrationOnRegistrationButtonPress();
        Assert.assertTrue("Сообщение о невалидном пароле не появилось", objRegistrationPage.isInvalidPasswordWarningDisplayed());
    }

}
