import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import model.UserModel;
import org.hamcrest.Matchers;
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
import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {
    
    private final static String USER_CREATION_API = "/api/auth/register";
    private final static String USER_DELETION_API ="/api/auth/user";

    static String userToken;

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
    public static void userRegistration(){
        RestAssured.baseURI = PAGE_URL;
        UserModel user = new UserModel(EMAIL, PASSWORD, NAME);
        ValidatableResponse response = given()
                .log().all()
                .header("Content-type", "application/json")
                .body(user)
                .post(USER_CREATION_API)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .body("success", equalTo(true));

        userToken = response.extract().path("accessToken");
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
    public static void createdUserDataDeletion(){
        if (userToken != null) {
            given()
                    .log().all()
                    .header("Authorization", userToken) // Добавляем токен в заголовок
                    .when()
                    .delete(USER_DELETION_API)  // Путь запроса на удаление пользователя
                    .then()
                    .log().all()
                    .statusCode(SC_ACCEPTED)
                    .body("success", Matchers.equalTo(true))  // Ожидаем, что success будет true
                    .body("message", Matchers.equalTo("User successfully removed"));
        }
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

