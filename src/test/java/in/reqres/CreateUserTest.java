package in.reqres;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class CreateUserTest {
    @Test
    @DisplayName("Успешное создание нового пользователя")
    @Tag("positive")
    void sucsessfulCreatingUserTest() {
        String authData = "{ \"name\": \"LARRY\", \"job\": \"QA\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("LARRY"))
                .body("job", is("QA"));
    }
}
