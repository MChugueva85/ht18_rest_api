package in.reqres.tests;

import in.reqres.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UserApiSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;

public class UserApiTest extends TestBase {
    @Test
    @DisplayName("Успешное создание нового пользователя")
    @Tag("positive")
    void sucsessfulCreatingUserTest() {

        CreateUserBodyModel userData = new CreateUserBodyModel();
        userData.setName("LARRY");
        userData.setJob("QA");

        CreateUserResponseModel createUserResponse = step("Make request", () ->
                given(UserApiRequestSpec)
                        .body(userData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(SucsessCreateUserResponse201Spec)
                        .extract().as(CreateUserResponseModel.class));

        step("Make response", () -> {
                    assertThat(createUserResponse.getName().equals("LARRY"));
                    assertThat(createUserResponse.getJob().equals("QA"));
                }
        );
    }

    @Test
    @DisplayName("Успешное редактирование пользователя")
    @Tag("positive")
    void sucsessfulUpdateUserTest() {
        UpdateUserModel userData = new UpdateUserModel();
        userData.setName("morpheus");
        userData.setJob("zion resident");

        UpdateUserResponseModel updateUserResponse = step("Make request", () ->
                given(UserApiRequestSpec)
                        .body(userData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(sucsessUpdateUserResponse201Spec)
                        .extract().as(UpdateUserResponseModel.class));
        step("Make response", () -> {
                    assertThat(updateUserResponse.getName().equals("morpheus"));
                    assertThat(updateUserResponse.getJob().equals("zion resident"));
                }
        );
    }

    @Test
    @DisplayName("Успешное удаление пользователя")
    @Tag("positive")
    void sucsessfulDeletingUserTest() {
        step("Check user was deleted", () -> {
                    given(UserWithoutJSONRequestSpec)
                            .when()
                            .delete("/users/10954")
                            .then()
                            .spec(sucsessDeleteUserResponse204Spec);
                }
        );
    }

    @Test
    @DisplayName("Успешный поиск пользователя")
    @Tag("positive")
    void sucsessfulSearchUserTest() {

        SearchUserResponseModel searchUserResponse = step("Make request", () ->
                given(UserWithoutJSONRequestSpec)
                        .when()
                        .get("/users/2")
                        .then()
                        .spec(sucsessSearchUserResponse200Spec)
                        .extract().as(SearchUserResponseModel.class));

        step("Make response", () -> {
                    assertThat(searchUserResponse.getData().getId().equals("2"));
                    assertThat(searchUserResponse.getData().getEmail().equals("janet.weaver@reqres.in"));
                    assertThat(searchUserResponse.getData().getFirst_name().equals("Janet"));
                    assertThat(searchUserResponse.getData().getLast_name().equals("Weaver"));
                    assertThat(searchUserResponse.getData().getAvatar().equals("https://reqres.in/img/faces/2-image.jpg"));
                    assertThat(searchUserResponse.getSupport().getUrl().equals("https://reqres.in/#support-heading"));
                }
        );
    }

    @Test
    @DisplayName("Безуспешный поиск пользователя по id")
    @Tag("negative")
    void userNotFoundTest() {
        step("Check user not found", () -> {
                    given(UserWithoutJSONRequestSpec)
                            .when()
                            .get("/users/23")
                            .then()
                            .spec(AuthResponse404Spec)
                            .body(is("{}"));
                }
        );
    }
}
