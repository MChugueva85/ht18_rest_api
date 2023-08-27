package in.reqres.tests;

import in.reqres.models.LoginResponseModel;
import in.reqres.models.LoginBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.AuthSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.*;

public class AuthApiTest extends TestBase {
    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Tag("negative")
    void sucsessfulRegisterUserTest() {

        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");

        LoginResponseModel loginResponse = step("Make request", () ->
                given(AuthRequestSpec)
                        .body(authData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(SucsessAuthResponseSpec)
                        .extract().as(LoginResponseModel.class));

        step("Make response", () -> {
                    assertThat(loginResponse.getEmail()).isEqualTo("eve.holt@reqres.in");
                    assertThat(loginResponse.getPassword()).isEqualTo("pistol");
                }
        );
    }
}
