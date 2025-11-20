import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

import static constants.Constants.*;

public class RegistrationTest extends BaseTest {

    @Test
    public void registrationSuccess(){
        HomePage objHomePage = new HomePage(driver);

        objHomePage.waitUntilLoginButtonHomePageVisible();
        LoginPage objLoginPage = objHomePage.loginButtonHomePagePress();
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        objLoginPage.waitUntilRegistrationButtonVisible();
        RegistrationPage objRegistrationPage = objLoginPage.registrationButtonPress();
        Assert.assertTrue("Переход на страницу Регистрации не совершен", objRegistrationPage.isRegistrationPageHeaderDisplayed());

        objRegistrationPage.fillRegistrationForm(NAME, PASSWORD, EMAIL);
        objRegistrationPage.waitUntilRegistrationButtonVisible();
        objLoginPage = objRegistrationPage.registrationButtonOnRegistrationPagePress();
        objLoginPage.waitUntilLoginPageHeaderVisible();
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

        objRegistrationPage.fillRegistrationForm(NAME, INVALID_PASSWORD, EMAIL);
        objRegistrationPage.waitUntilRegistrationButtonVisible();
        objRegistrationPage.registrationButtonOnRegistrationPagePress();
        Assert.assertTrue("Сообщение о невалидном пароле не появилось", objRegistrationPage.isInvalidPasswordWarningDisplayed());
    }

}
