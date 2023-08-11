package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class HotelResource {
    private static HotelResource hotelResource;
    private CustomerService customerService;
    private ReservationService reservationService;

    private HotelResource(){
        customerService = CustomerService.getInstance();
        reservationService = ReservationService.getInstance();
    }

    public static HotelResource getInstance(){
        if(hotelResource == null){
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public Customer getCustomer(String customerEmail){
       return customerService.getCustomer(customerEmail);
    }

    public void createACustomer(String firstName,String lastName,String email){
        customerService.addCustomer(firstName,lastName,email);
    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Customer customer = getCustomer(customerEmail);
        return reservationService.reserveARoom(customer,room,checkInDate,checkOutDate);
    }

    public List<Reservation> getCustomersReservation(String customerEmail){
        Customer customer = getCustomer(customerEmail);
        if(customer == null){
            return null;
        }
        return reservationService.getCustomersReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkout){
        return reservationService.findRooms(checkIn,checkout);
    }

}
