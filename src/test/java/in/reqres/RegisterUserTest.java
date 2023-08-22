package in.reqres;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RegisterUserTest {
    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Tag("negative")
    void sucsessfulRegisterUserTest() {
        String regData = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(regData)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("email", is("eve.holt@reqres.in"))
                .body("password", is("pistol"));
    }
}
