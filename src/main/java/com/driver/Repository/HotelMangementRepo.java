package com.driver.Repository;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.processing.Generated;
import java.util.*;

@Repository
public class HotelMangementRepo {


//    static  String uuid=Generated.


    Map<String,Hotel> hotelList;

    Map<Integer,User> userList;


    Map<String,Booking> bookingList;

    public HotelMangementRepo() {
        this.hotelList =new HashMap<>();
        this.userList = new HashMap<>();
        this.bookingList = new HashMap<>();
    }



    public String addHotel(Hotel hotel) {

        if(hotel==null) return "FAILURE";

        if(hotel.getHotelName().length()==0) return "FAILURE";


        if(hotelList.containsKey(hotel.getHotelName())) return "FAILURE";

        hotelList.put(hotel.getHotelName(),hotel);

        return "SUCCESS";




    }

    public Integer addUser(User user) {

        userList.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();

    }

    public String getHotelWithMostFacilities() {
        int max=0;

        String res="";

        for(String hotel: hotelList.keySet()){

            int facilities=hotelList.get(hotel).getFacilities().size();

            if(max>0 && max==facilities){
                int comp=res.compareTo(hotel);
                if(comp<0) res=hotel;

            }
            else if(max<facilities){
                max=facilities;
                res=hotel;
            }


        }

        if(max==0) return "";

        return res;


    }

    public int bookARoom(Booking booking) {

        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        Booking booking1=new Booking(uuidString,booking.getBookingAadharCard(),booking.getNoOfRooms(),booking.getBookingPersonName(),booking.getHotelName());

        booking1.setAmountToBePaid(0);

        Hotel hotel=hotelList.get(booking1.getHotelName());

        if(hotel.getAvailableRooms()<booking.getNoOfRooms()) return -1;


        int cost=0;

        int price=hotel.getPricePerNight();

        cost=price*(booking.getNoOfRooms());

        booking1.setAmountToBePaid(cost);

        bookingList.put(uuidString,booking1);

        return cost;

    }

    public int getBookings(Integer aadharCard) {

        int count=0;

        for(Booking booking: bookingList.values()){

            if(booking.getBookingAadharCard()==aadharCard) count++;

        }


        return count;

    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {

       Hotel hotel= hotelList.get(hotelName);

       Set<Facility> set=new HashSet<>();

       for(Facility i: hotel.getFacilities()){
           set.add(i);
       }
        for(Facility i: newFacilities){
            set.add(i);
        }

        List<Facility> list=new ArrayList<>(set);

        hotel.setFacilities(list);

        hotelList.put(hotelName,hotel);

        return hotel;



    }
}
