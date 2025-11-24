import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.HomePage;
import pages.LoginPage;
import pages.RecoverPasswordPage;
import pages.RegistrationPage;

import static constants.Constants.*;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Object[][] browsers() {
        return new Object[][] {
                { DriverType.YANDEX },
                { DriverType.CHROME }

        };
    }

    public LoginTest(DriverType driverType) {
        this.driverType = driverType;
    }

    @BeforeClass

    public static void testSetup(){
        UserApi.userRegistration();
    }

    @Test
    @DisplayName("Логин через кнопку Личный кабинет")
    @Description("Переходит на страницу Личного кабинета, находит кнопку Войти и осуществляет успешный вход")
    public void testLoginWithPrivateAccountButton(){
        HomePage objHomePage = new HomePage(driver);

        LoginPage objLoginPage = getLoginPageWithPrivateAccountButton(objHomePage);
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        fillAndSendLoginForm(objLoginPage);
        Assert.assertTrue("Вход в аккаунт не совершен", objHomePage.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Логин через кнопку Войти в аккаунт на стартовой странице")
    @Description("Переходит на страницу входа через кнопку Войти в аккаунт и осуществляет успешный вход")
    public void testLoginWithLoginButtonOnHomePage(){
        HomePage objHomePage = new HomePage(driver);
        LoginPage objLoginPage = getLoginPageWithLoginButton(objHomePage);
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        fillAndSendLoginForm(objLoginPage);
        Assert.assertTrue("Вход в аккаунт не совершен", objHomePage.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Логин через кнопку Войти на странице регистрации")
    @Description("Переходит на страницу регистрации, находит кнопку Войти и осуществляет успешный вход")
    public void testLoginWithLoginButtonOnRegistrationPage(){

        HomePage objHomePage = new HomePage(driver);

        LoginPage objLoginPage = getLoginPageWithLoginButton(objHomePage);
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        objLoginPage.waitUntilRegistrationButtonVisible();
        RegistrationPage objRegistrationPage = objLoginPage.registrationButtonPress();
        Assert.assertTrue("Переход на страницу Регистрации не совершен", objRegistrationPage.isRegistrationPageHeaderDisplayed());

        objRegistrationPage.waitUntilLoginButtonVisible();
        objLoginPage = objRegistrationPage.loginOnRegistrationPageButtonPress();
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        fillAndSendLoginForm(objLoginPage);
        Assert.assertTrue("Вход в аккаунт не совершен", objHomePage.isCreateOrderButtonDisplayed());

    }
    
    @Test
    @DisplayName("Логин через кнопку Войти на странице восстановления пароля")
    @Description("Переходит на страницу восстановления пароля, находит кнопку Войти и осуществляет успешный вход")
    public void testLoginWithLoginButtonOnPasswordRecoveryPage(){
        HomePage objHomePage = new HomePage(driver);


        LoginPage objLoginPage = getLoginPageWithLoginButton(objHomePage);
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        objLoginPage.waitUntilRecoverPasswordButtonVisible();
        RecoverPasswordPage objRecoverPasswordPage = objLoginPage.recoverPasswordButtonPress();
        objRecoverPasswordPage.waitUntilLoginButtonOnRecoveryPageVisible();
        Assert.assertTrue("Переход на страницу восстановления пароля не совершен", objRecoverPasswordPage.loginButtonOnRecoverPasswordPageDisplayed());

        objLoginPage = objRecoverPasswordPage.loginButtonOnRecoverPasswordPagePress();
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        fillAndSendLoginForm(objLoginPage);
        Assert.assertTrue("Вход в аккаунт не совершен", objHomePage.isCreateOrderButtonDisplayed());

    }

    @AfterClass

    public static void testCleanup(){
        UserApi.createdUserDataDeletion();
    }

    @Step("Заполнение формы для логина")
    private static void fillAndSendLoginForm(LoginPage objLoginPage) {
        objLoginPage.fillLoginForm(EMAIL, PASSWORD);
        HomePage objHomePage = objLoginPage.loginButtonOnLoginPagePress();
        objHomePage.waitUntilCreateOrderButtonVisible();
    }
    @Step("Переход на форму входа через кнопку Личный кабинет")
    private static LoginPage getLoginPageWithPrivateAccountButton(HomePage objHomePage) {
        objHomePage.waitUntilPrivateAccountButtonVisible();
        return objHomePage.privateAccountButtonPress();
    }
    @Step("Переход на форму входа через кнопку Войти в аккаунт")
    private static LoginPage getLoginPageWithLoginButton(HomePage objHomePage) {
        objHomePage.waitUntilLoginButtonHomePageVisible();
        return objHomePage.loginButtonHomePagePress();
    }

}

