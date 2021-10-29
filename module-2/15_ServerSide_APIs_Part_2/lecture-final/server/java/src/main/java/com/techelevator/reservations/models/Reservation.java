package com.techelevator.reservations.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Reservation {
    private int id;

    @Positive(message = "hotelID must be a positive integer value.")
    private int hotelID;

    @NotBlank(message = "Full name is required.")
    private String fullName;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate checkinDate;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate checkoutDate;

    @Min(value = 1, message = "The minimum number of guests is 1.")
    @Max(value = 5, message = "The maximum numbers of guests is 5.")
    private int guests;

    @AssertTrue(message = "Check-in date is not prior to check-out date.")
    private boolean isCheckinDaterPriorToCheckoutDate() {
        return checkinDate.compareTo(checkoutDate) < 0;
    }

    public Reservation() {

    }

    public Reservation(int id, int hotelID, String fullName, String checkinDate, String checkoutDate, int guests) {
        SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
        this.id = id;
        this.hotelID = hotelID;
        this.fullName = fullName;

        try {
            this.checkinDate = LocalDate.parse(checkinDate);
        } catch (DateTimeParseException e) { this.checkinDate = null; }
        try {
            this.checkoutDate = LocalDate.parse(checkinDate);
        } catch (DateTimeParseException e) { this.checkinDate = null; }

        this.guests = guests;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    @Override
    public String toString() {
        return "\n--------------------------------------------" + "\n Reservation Details"
                + "\n--------------------------------------------" + "\n Id: " + id + "\n Hotel Id: " + hotelID
                + "\n Full Name: " + fullName + "\n Checkin Date: " + checkinDate + "\n Checkout Date: " + checkoutDate
                + "\n Guests: " + guests;
    }
}
