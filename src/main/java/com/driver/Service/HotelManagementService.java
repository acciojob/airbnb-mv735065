package com.driver.Service;

import com.driver.Repository.HotelMangementRepo;
import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelManagementService {

    HotelMangementRepo hotelMangementRepo=new HotelMangementRepo();
    public String addHotel(Hotel hotel) {
        return hotelMangementRepo.addHotel(hotel);

    }

    public Integer addUser(User user) {
        return hotelMangementRepo.addUser(user);
    }

    public String getHotelWithMostFacilities() {
        return hotelMangementRepo.getHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking) {

        return hotelMangementRepo.bookARoom(booking);

    }

    public int getBookings(Integer aadharCard) {

        return hotelMangementRepo.getBookings(aadharCard);

    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {

        return hotelMangementRepo.updateFacilities(newFacilities,hotelName);
    }
}
