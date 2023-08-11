package service;


import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService;
    private Map<String,IRoom> roomsMap;
    private List<Reservation> reservationList;

    private ReservationService() {
        roomsMap = new HashMap<>();
        reservationList = new ArrayList<>();
    }

    public static ReservationService getInstance(){
        if(reservationService == null){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room){
            try{
                if(RoomIDAlreadyExists(room.getRoomNumber())){
                    throw new Exception("A Room with the given number "+ room.getRoomNumber()+" already exists");
                }
                roomsMap.put(room.getRoomNumber(),room);
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
    }

    public IRoom getARoom(String roomId){
        return roomsMap.get(roomId);
    }

    public Collection<IRoom> getAllRooms(){
        return roomsMap.values();
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkoutDate){
        Collection<IRoom> availableRooms = getAvailableRoomsForReservation(checkInDate,checkoutDate);
        return availableRooms;
    }

    public Collection<IRoom> getAvailableRoomsForReservation(Date checkInDate, Date checkoutDate){
        List<IRoom> availableRooms = new ArrayList<>();
        for(IRoom room : roomsMap.values()){
            Collection<Reservation> roomsWithSameNumber = getRoomsReservedWithSameNumber(room.getRoomNumber());
            if(isRoomFree(checkInDate,checkoutDate,roomsWithSameNumber)){
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }


    private Collection<Reservation> getRoomsReservedWithSameNumber(String roomNumber){
        List<Reservation> rooms = new ArrayList<>();
        for(Reservation reservation : reservationList){
            if(reservation.getRoom().getRoomNumber().equals(roomNumber)){
                rooms.add(reservation);
            }
        }
        return rooms;
    }

    private boolean isRoomFree(Date checkInDate, Date checkoutDate,Collection<Reservation> reservedRooms) {
        for(Reservation reservation : reservedRooms){
            if(reservation.getCheckInDate().before(checkoutDate) && reservation.getCheckOutDate().after(checkInDate)){
                /*System.out.println(reservation.getCheckInDate()+" "+reservation.getCheckOutDate());
                System.out.println(checkInDate+" "+checkoutDate);*/
                return false;
            }
            else if(reservation.getCheckInDate().compareTo(checkInDate) == 0 || reservation.getCheckOutDate().compareTo(checkoutDate) == 0) {
                /*System.out.println(reservation.getCheckInDate()+" "+reservation.getCheckOutDate());
                System.out.println(checkInDate+" "+checkoutDate);*/
                return false;
            }
        }
        return true;
    }

    public Reservation reserveARoom(Customer customer,IRoom room,Date checkInDate,Date checkoutDate){
        Reservation newReservation = new Reservation(customer,room,checkInDate,checkoutDate);
        reservationList.add(newReservation);
        return newReservation;
    }

    public List<Reservation> getCustomersReservation(Customer customer){
        List<Reservation> customerReservations = new ArrayList<>();
        for(Reservation reservation : reservationList){
            if(reservation.getCustomer().getEmail().equals(customer.getEmail())){
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    public void printAllReservation(){
        if(reservationList.isEmpty()){
            System.out.println("There are no reservations at the moment. Please check some time later");
            return;
        }
        int numReservations = 1;
        for(Reservation reservation : reservationList){
           System.out.println(numReservations + ". " +reservation);
           numReservations ++;
        }
    }

     boolean RoomIDAlreadyExists(String roomID){
        return roomsMap.containsKey(roomID);
    }
}
