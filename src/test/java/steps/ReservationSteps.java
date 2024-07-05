package steps;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Booking;
import models.BookingDates;
import models.BookingResponse;
import org.junit.jupiter.api.Assertions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import services.BaseTest;
import services.ReservationService;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReservationSteps {

    ReservationService reservationService;
    Booking booking;
    BookingResponse bookingResponse;




    @Given("user creates a new reservation")
    public void user_creates_a_new_reservation() {
        reservationService=new ReservationService();
    }

    @Given("the user provides the information required for the reservation")
    public void user_provides_information_for_reservation() {
        BookingDates bookingDates = new BookingDates("2018-01-01", "2019-01-01");
        booking = new Booking("Jim", "Brown", 111, true, bookingDates, "Breakfast");
    }

    @When("User creates hotel reservation")
    public void user_creates_hotel_reservation() {
        bookingResponse = reservationService.createBooking(booking);
    }

    @Then("the reservation has been created successfully")
    public void the_reservation_has_been_created_successfully() {
        assertNotNull(bookingResponse);
        assertNotNull(bookingResponse.getBookingid());
        Assertions.assertEquals("Brown",bookingResponse.getBooking().getLastname());
    }

    @Then("User cancels the created reservation")
    public void user_cancels_the_created_reservation() {
        String token = reservationService.getAuthToken();
        reservationService.deleteBooking(bookingResponse.getBookingid(), token);

    }
}
