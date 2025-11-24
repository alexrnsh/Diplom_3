package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import model.UserLoginResponse;
import model.UserModel;
import org.hamcrest.Matchers;

import static constants.Constants.*;
import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserApi {

    static {
        RestAssured.baseURI = PAGE_URL;
    }

    private final static String USER_CREATION_API = "/api/auth/register";
    private final static String USER_DELETION_API ="/api/auth/user";
    private final static String USER_LOGIN_API = "/api/auth/login";

    @Step("Регистрация через " + USER_CREATION_API + "возвращает токен")
    public static String userRegistration(){
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

        return response.extract().path("accessToken");
    }

    @Step("Логин юзера через " + USER_LOGIN_API + " возвращает объект ответа")
    public static UserLoginResponse userLogin(UserModel user) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(user)
                .post(USER_LOGIN_API)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract()
                .as(UserLoginResponse.class);
    }

    @Step ("Удаление юзера через" + USER_DELETION_API )
    public static void userDeletion(String userToken){
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
