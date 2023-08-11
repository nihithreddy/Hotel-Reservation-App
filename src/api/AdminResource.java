package api;

import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource adminResource;
    private CustomerService customerService;
    private ReservationService reservationService;

    private AdminResource(){
        customerService = CustomerService.getInstance();
        reservationService = ReservationService.getInstance();
    }

    public static AdminResource getInstance(){
        if(adminResource == null){
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }

    public void addRoom(String roomNumber,Double roomPrice,int roomType){
        if(roomType == 1 && roomPrice == 0.0D){
            reservationService.addRoom(new FreeRoom(roomNumber, RoomType.SINGLE));
        }
        else if(roomType == 1 && roomPrice != 0.0D){
            reservationService.addRoom(new Room(roomNumber, roomPrice,RoomType.SINGLE));
        }
        else if(roomType == 2 && roomPrice == 0.0D){
            reservationService.addRoom(new FreeRoom(roomNumber, RoomType.DOUBLE));
        }
        else{
            reservationService.addRoom(new Room(roomNumber,roomPrice,RoomType.DOUBLE));
        }
    }

    public void printAllReservation(){
        reservationService.printAllReservation();
    }
}
