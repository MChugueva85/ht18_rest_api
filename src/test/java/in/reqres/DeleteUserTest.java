package in.reqres;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class DeleteUserTest {
    @Test
    @DisplayName("Успешное удаление пользователя")
    @Tag("positive")
    void sucsessfulCreatingUserTest() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .delete("https://reqres.in/api/users/10954")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
