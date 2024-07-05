package services;

import models.AuthRequest;
import models.AuthResponse;
import models.BookingResponse;
import models.Booking;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingService {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    public BookingService() {
        RestAssured.baseURI = BASE_URL;
    }

    public BookingResponse createBooking(Booking booking) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");

        response.then().statusCode(200);
        return response.as(BookingResponse.class);
    }

    public void deleteBooking(int bookingId, String token) {
        given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .statusCode(201);
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
