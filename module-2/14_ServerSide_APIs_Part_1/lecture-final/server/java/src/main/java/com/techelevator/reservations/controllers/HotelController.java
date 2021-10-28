package com.techelevator.reservations.controllers;

import com.techelevator.reservations.dao.HotelDAO;
import com.techelevator.reservations.dao.MemoryHotelDAO;
import com.techelevator.reservations.dao.MemoryReservationDAO;
import com.techelevator.reservations.dao.ReservationDAO;
import com.techelevator.reservations.models.Hotel;
import com.techelevator.reservations.models.Reservation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelController {

    private HotelDAO hotelDAO;
    private ReservationDAO reservationDAO;

    public HotelController() {
        this.hotelDAO = new MemoryHotelDAO();
        this.reservationDAO = new MemoryReservationDAO(hotelDAO);
    }

    /**
     * Return All Hotels
     *
     * @return a list of all hotels in the system
     */
    @RequestMapping(path = "/hotels", method = RequestMethod.GET)
    public List<Hotel> list(@RequestParam(required = false, defaultValue = "") String state,
                            @RequestParam(required = false, defaultValue = "") String city) {
        List<Hotel> allHotels = hotelDAO.list();

        if((state == null || state.isEmpty()) && (city == null || city.isEmpty())) {
            return allHotels;
        }

        List<Hotel> results = new ArrayList<>();

        for(Hotel hotel : allHotels) {
            if(state != null && !state.isEmpty() && hotel.getAddress().getState().equals(state)
               && city != null && !city.isEmpty() && hotel.getAddress().getCity().equals(city)) {
                results.add(hotel);
            }
            else if((city == null || city.isEmpty()) && (hotel.getAddress().getState().equals(state))) {
                results.add(hotel);
            }
            else if((state == null || state.isEmpty()) && (hotel.getAddress().getCity().equals(city))) {
                results.add(hotel);
            }
        }

        return results;
    }

    /**
     * Return hotel information
     *
     * @param id the id of the hotel
     * @return all info for a given hotel
     */
    @RequestMapping(path = "/hotels/{id}", method = RequestMethod.GET)
    public Hotel get(@PathVariable int id) {
        return hotelDAO.get(id);
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<Reservation> getAllReservations() {
        return reservationDAO.findAll();
    }

    @RequestMapping(path = "/hotels/{hotelId}/reservations", method = RequestMethod.GET)
    public List<Reservation> getReservationsForHotel(@PathVariable int hotelId) {
        return reservationDAO.findByHotel(hotelId);
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.POST)
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationDAO.create(reservation, reservation.getHotelID());
    }

    @RequestMapping(path = "/hotels/{hotelId}/reservations", method = RequestMethod.POST)
    public Reservation addReservation_version2(@RequestBody Reservation reservation, @PathVariable int hotelId) {
        return reservationDAO.create(reservation, hotelId);
    }


}
