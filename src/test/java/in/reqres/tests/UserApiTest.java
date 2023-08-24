package in.reqres.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class UserApiTest  extends TestBase{
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
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("LARRY"))
                .body("job", is("QA"));
    }

    @Test
    @DisplayName("Успешное редактирование пользователя")
    @Tag("positive")
    void sucsessfulUpdateUserTest() {
        String updateData = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(updateData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    @DisplayName("Успешное удаление пользователя")
    @Tag("positive")
    void sucsessfulDeletingUserTest() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .delete("/users/10954")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    @DisplayName("Успешный поиск пользователя")
    @Tag("positive")
    void sucsessfulSearchUserTest() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("/users/2")
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
                .get("/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404)
                .body(is("{}"));
    }
}
