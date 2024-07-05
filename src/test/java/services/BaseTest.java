package services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.AuthRequest;
import models.AuthResponse;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class BaseTest {
    RequestSpecification spec;
    public BaseTest() {
        spec= new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter(),new ResponseLoggingFilter()))
                .build();
    }

    public String getAuthToken() {
        AuthRequest authRequest = new AuthRequest("admin", "password123");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(authRequest)
                .when()
                .post("/auth");

        response.then().statusCode(200);
        AuthResponse authResponse = response.as(AuthResponse.class);
        return authResponse.getToken();
    }


}
