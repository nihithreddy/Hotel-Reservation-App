import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class MainMenu {
    private static HotelResource hotelResource = HotelResource.getInstance();
    private static int DAYS_TO_ADD = 7;
    public static void printMainMenuOptions(){
        boolean exitMainMenu = false;
        try(Scanner scanner = new Scanner(System.in)) {
            while (!exitMainMenu) {
                System.out.println("Welcome to the Hotel Reservation Application");
                System.out.println("--------------------------------------------");
                System.out.println("1. Find and reserve a room");
                System.out.println("2. See my reservations");
                System.out.println("3. Create an account");
                System.out.println("4. Admin");
                System.out.println("5. Exit");
                System.out.println("--------------------------------------------");
                System.out.println("Please select a number for the menu option");
                try {
                    String userChoice = scanner.nextLine();
                    int userInput = Integer.parseInt(userChoice);
                    System.out.println("userInput : " + userInput);
                    switch (userInput) {
                        case 1:
                            handleCustomerReservation();
                            break;
                        case 2:
                            displayCustomerReservations();
                            break;
                        case 3:
                            createNewAccount();
                            break;
                        case 4:
                            AdminMenu.printAdminMenuOptions();
                            break;
                        case 5:
                            exitMainMenu = true;
                            break;
                        default:
                            System.out.println("Please Select a Number from 1 to 5\n");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Error: Invalid Input");
                }
            }
        }
    }

    private static void handleCustomerReservation() {
        Date checkInDate = RoomInput.processCheckInDate();
        Date checkOutDate = RoomInput.processCheckOutDate();
        Collection<IRoom> foundRooms = hotelResource.findARoom(checkInDate,checkOutDate);
        if(foundRooms.isEmpty()){
            DAYS_TO_ADD = CustomerInput.processNumberOfDays();
            checkInDate = addSevenDaysToDate(checkInDate);
            checkOutDate = addSevenDaysToDate(checkOutDate);
            /*System.out.println(checkInDate);
            System.out.println(checkOutDate);*/
            foundRooms = hotelResource.findARoom(checkInDate,checkOutDate);
        }
        if(foundRooms.isEmpty()){
            System.out.println("There are no rooms available between "+ checkInDate+" and "+checkOutDate);
            return;
        }
        System.out.println("These are the rooms available between "+ checkInDate+" and "+checkOutDate);
        for(IRoom foundRoom : foundRooms){
            System.out.println(foundRoom);
        }
        bookARoomForCustomer(foundRooms,checkInDate,checkOutDate);
    }

    private static Date addSevenDaysToDate(Date inputDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        calendar.add(Calendar.DATE, DAYS_TO_ADD);
        return calendar.getTime();
    }

    private static void bookARoomForCustomer(Collection<IRoom> freeRooms,Date checkInDate,Date checkOutDate){
        boolean customerWantsToReserve = CustomerInput.processCustomerChoice();
        if(!customerWantsToReserve){
            return;
        }
        boolean customerAccountExists = CustomerInput.customerHasAnExistingAccount();
        String customerEmail = null;
        if(customerAccountExists){
            customerEmail = CustomerInput.processCustomerEmail();
        }
        else{
            customerEmail = createNewAccount();
        }
        Customer customerAccount = hotelResource.getCustomer(customerEmail);
        if(customerAccount == null){
            return;
        }
        String roomNumberToBook = CustomerInput.processCustomerRoomNumber();
        if(isRoomAvailableToBook(roomNumberToBook,freeRooms)){
            IRoom roomToBook = hotelResource.getRoom(roomNumberToBook);
            Reservation customerReservation = hotelResource.bookARoom(customerEmail,roomToBook,checkInDate,checkOutDate);
            printCustomerReservationDetails(customerReservation);
        }
        else{
            System.out.println("The room "+ roomNumberToBook+" is not available at the moment. Please choose a room number from the rooms shown above");
        }
    }

    private static void printCustomerReservationDetails(Reservation customerReservation) {
        System.out.println("The following room has been booked for you. Here are your reservation details");
        System.out.println("Customer Name: "+customerReservation.getCustomer().getFirstName()+" "+customerReservation.getCustomer().getLastName());
        System.out.println("Room: "+customerReservation.getRoom().getRoomNumber()+" - "+customerReservation.getRoom().getRoomType()+" BED");
        System.out.println("Price: $"+customerReservation.getRoom().getRoomPrice()+" price per night");
        System.out.println("Checkin Date: "+customerReservation.getCheckInDate().toString());
        System.out.println("Checkout Date: "+customerReservation.getCheckOutDate().toString());
    }

    public static boolean isRoomAvailableToBook(String roomNumber,Collection<IRoom> freeRooms){
        for(IRoom freeRoom : freeRooms){
            if(freeRoom.getRoomNumber().equals(roomNumber)){
                return true;
            }
        }
        return false;
    }

    public static String createNewAccount(){
        String email = CustomerInput.processCustomerEmail();
        String firstName = CustomerInput.processCustomerFirstName();
        String lastName = CustomerInput.processCustomerLastName();

        hotelResource.createACustomer(firstName,lastName,email);
        return email;
    }

    public static void displayCustomerReservations(){
        String email = CustomerInput.processCustomerEmail();

        List<Reservation> customerReservationsList = hotelResource.getCustomersReservation(email);

        if(customerReservationsList == null || customerReservationsList.isEmpty()){
            System.out.println("Sorry, It seems like there are no rooms reserved for "+email);
            return;
        }
        System.out.println("Here are the reservations made by you");
        int numReservations = 1;
        for(Reservation customerReservation : customerReservationsList){
            System.out.println(numReservations+". "+customerReservation);
            numReservations++;
        }
    }
}
