import api.UserApi;
import constants.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.UserLoginResponse;
import model.UserModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

import static constants.Constants.*;

public class RegistrationTest extends BaseTest {

    private static String userToken;

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
        objRegistrationPage.registrationButtonOnRegistrationPagePress();


        UserLoginResponse userLoginResponse = UserApi.userLogin(new UserModel(email, PASSWORD, NAME));
        userToken = userLoginResponse.getAccessToken();
        Assert.assertEquals("Имя не совпадает", NAME, userLoginResponse.getUser().getName());

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

    @After
    public void testCleanup(){
        if (userToken != null) {
            UserApi.userDeletion(userToken);
        }
    }

}
