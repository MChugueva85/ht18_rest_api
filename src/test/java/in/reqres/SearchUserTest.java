package in.reqres;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SearchUserTest {
    @Test
    @DisplayName("Успешный поиск пользователя")
    @Tag("positive")
    void sucsessfulSearchUserTest() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"))
                .body("support.url", is("https://reqres.in/#support-heading"))
                .body("support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"));

    }

    @Test
    @DisplayName("Безуспешный поиск пользователя по id")
    @Tag("negative")
    void userNotFoundTest() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404)
                .body(is("{}"));
    }
}

