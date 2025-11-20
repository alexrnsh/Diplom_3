import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import model.UserModel;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RecoverPasswordPage;
import pages.RegistrationPage;

import static constants.Constants.*;
import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginTest extends BaseTest {
    private final static String USER_CREATION_API = "/api/auth/register";
    private final static String USER_DELETION_API ="/api/auth/user";

    HomePage homePage;

    static String userToken;

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

    public void testLoginWithPrivateAccountButton(){
        HomePage objHomePage = new HomePage(driver);
        LoginPage objLoginPage;
        objHomePage.waitUntilPrivateAccountButtonVisible();
        objLoginPage = objHomePage.privateAccountButtonPress();
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        objLoginPage.fillLoginForm(EMAIL, PASSWORD);
        objHomePage = objLoginPage.loginButtonOnLoginPagePress();
        objHomePage.waitUntilCreateOrderButtonVisible();
        Assert.assertTrue("Вход в аккаунт не совершен", objHomePage.isCreateOrderButtonDisplayed());

    }

    @Test

    public void testLoginWithLoginButtonOnHomePage(){
        HomePage objHomePage = new HomePage(driver);
        LoginPage objLoginPage;
        objHomePage.waitUntilLoginButtonHomePageVisible();
        objLoginPage= objHomePage.loginButtonHomePagePress();
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        objLoginPage.fillLoginForm(EMAIL, PASSWORD);
        objHomePage = objLoginPage.loginButtonOnLoginPagePress();
        objHomePage.waitUntilCreateOrderButtonVisible();
        Assert.assertTrue("Вход в аккаунт не совершен", objHomePage.isCreateOrderButtonDisplayed());
    }

    @Test

    public void testLoginWithLoginButtonOnRegistrationPage(){
        HomePage objHomePage = new HomePage(driver);
        LoginPage objLoginPage;
        RegistrationPage objRegistrationPage;

        objHomePage.waitUntilLoginButtonHomePageVisible();
        objLoginPage= objHomePage.loginButtonHomePagePress();
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        objLoginPage.waitUntilRegistrationButtonVisible();
        objRegistrationPage = objLoginPage.registrationButtonPress();
        Assert.assertTrue("Переход на страницу Регистрации не совершен", objRegistrationPage.isRegistrationPageHeaderDisplayed());

        objRegistrationPage.waitUntilLoginButtonVisible();
        objLoginPage = objRegistrationPage.loginOnRegistrationPageButtonPress();
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        objLoginPage.fillLoginForm(EMAIL, PASSWORD);
        objHomePage = objLoginPage.loginButtonOnLoginPagePress();
        objHomePage.waitUntilCreateOrderButtonVisible();
        Assert.assertTrue("Вход в аккаунт не совершен", objHomePage.isCreateOrderButtonDisplayed());

    }
    @Test

    public void testLoginWithLoginButtonOnPasswordRecoveryPage(){
        HomePage objHomePage = new HomePage(driver);
        LoginPage objLoginPage;
        RecoverPasswordPage objRecoverPasswordPage;

        objHomePage.waitUntilLoginButtonHomePageVisible();
        objLoginPage= objHomePage.loginButtonHomePagePress();
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        objLoginPage.waitUntilRecoverPasswordButtonVisible();
        objRecoverPasswordPage = objLoginPage.recoverPasswordButtonPress();
        objRecoverPasswordPage.waitUntilLoginButtonOnRecoveryPageVisible();
        Assert.assertTrue("Переход на страницу восстановления пароля не совершен", objRecoverPasswordPage.loginButtonOnRecoverPasswordPageDisplayed());

        objLoginPage = objRecoverPasswordPage.loginButtonOnRecoverPasswordPagePress();
        Assert.assertTrue("Переход на страницу Входа не совершен", objLoginPage.isLoginPageHeaderDisplayed());

        objLoginPage.fillLoginForm(EMAIL, PASSWORD);
        objHomePage = objLoginPage.loginButtonOnLoginPagePress();
        objHomePage.waitUntilCreateOrderButtonVisible();
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

}

