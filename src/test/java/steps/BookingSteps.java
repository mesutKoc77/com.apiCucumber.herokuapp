package steps;

import models.Booking;
import models.BookingDates;
import models.BookingResponse;
import services.BookingService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookingSteps {

    private BookingService bookingService;
    private BookingResponse bookingResponse;
    private Booking booking;

    public BookingSteps() {
        bookingService = new BookingService();
    }

    @Given("user creates a new reservation")
    public void user_creates_a_new_reservation() {
        bookingResponse = null;
    }

    @Given("the user provides the information required for the reservation")
    public void user_provides_information_for_reservation() {
        BookingDates bookingDates = new BookingDates("2018-01-01", "2019-01-01");
        booking = new Booking("Jim", "Brown", 111, true, bookingDates, "Breakfast");
    }

    @When("User creates hotel reservation")
    public void user_creates_hotel_reservation() {
        bookingResponse = bookingService.createBooking(booking);
    }

    @Then("the reservation has been created successfully")
    public void the_reservation_has_been_created_successfully() {
        assertNotNull(bookingResponse);
        assertNotNull(bookingResponse.getBookingid());
    }

    @Then("User cancels the created reservation")
    public void user_cancels_the_created_reservation() {
        String token = bookingService.getAuthToken();
        bookingService.deleteBooking(bookingResponse.getBookingid(), token);
    }
}
