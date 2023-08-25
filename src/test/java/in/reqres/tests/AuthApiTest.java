package in.reqres.tests;

import models.LoginBodyModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthApiTest extends TestBase {
    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Tag("negative")
    void sucsessfulRegisterUserTest() {

        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");

        LoginResponseModel loginResponse = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(LoginResponseModel.class);
        assertThat(loginResponse.getEmail()).contains("eve.holt@reqres.in");
        assertThat(loginResponse.getPassword()).contains("pistol");
    }
}
