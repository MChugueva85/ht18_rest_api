package in.reqres.tests;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class TestBase {

    @BeforeAll
    static public void setup() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

}

