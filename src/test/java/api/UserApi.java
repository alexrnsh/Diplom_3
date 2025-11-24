package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import model.UserModel;
import org.hamcrest.Matchers;

import static constants.Constants.*;
import static constants.Constants.NAME;
import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserApi {

    private final static String USER_CREATION_API = "/api/auth/register";
    private final static String USER_DELETION_API ="/api/auth/user";
    static String userToken;

    @Step("Регистрация через " + USER_CREATION_API)
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
    @Step ("Удаление юзера через" + USER_DELETION_API )
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
