package services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AuthRequest;
import models.AuthResponse;
import models.Booking;
import models.BookingResponse;

import static io.restassured.RestAssured.given;

public class ReservationService extends BaseTest{


    public String getAuthToken() {
        AuthRequest authRequest = new AuthRequest("admin", "password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(authRequest)
                .when()
                .post("/auth");

        response.then().statusCode(200);
        AuthResponse authResponse = response.as(AuthResponse.class);
        return authResponse.getToken();
    }

    public BookingResponse createBooking(Booking booking) {
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");

        response.then().statusCode(200);
        return response.as(BookingResponse.class);
    }

    public void deleteBooking(int bookingId, String token) {
        given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .statusCode(201);
    }







}
