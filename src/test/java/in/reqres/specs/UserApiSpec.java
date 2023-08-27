package in.reqres.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserApiSpec {
    public static RequestSpecification UserApiRequestSpec = with()
            .log().uri()
            .log().method()
            .log().body()
            .filter(withCustomTemplates())
            .contentType(JSON);

    public static ResponseSpecification sucsessUpdateUserResponse201Spec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .expectBody((matchesJsonSchemaInClasspath("schemas/sucsess-UpdateUser-response-schema.json")))
            .build();

    public static ResponseSpecification sucsessSearchUserResponse200Spec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody((matchesJsonSchemaInClasspath("schemas/sucsessSearchUser-response-schema.json")))
            .build();

    public static RequestSpecification UserWithoutJSONRequestSpec = with()
            .log().uri()
            .log().method()
            .log().body()
            .filter(withCustomTemplates());

    public static ResponseSpecification sucsessDeleteUserResponse204Spec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(204)
            .build();

    public static ResponseSpecification AuthResponse404Spec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(404)
            .build();
    public static ResponseSpecification SucsessCreateUserResponse201Spec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .expectBody((matchesJsonSchemaInClasspath("schemas/sucsess-createUser-response-schema.json")))
            .build();
}
