import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static AdminResource adminResource = AdminResource.getInstance();
    private static HotelResource hotelResource = HotelResource.getInstance();
    public static void printAdminMenuOptions(){
        try(Scanner scanner = new Scanner(System.in)){
            while(true){
                System.out.println("Admin Menu");
                System.out.println("--------------------------------------------");
                System.out.println("1. See all Customers");
                System.out.println("2. See all Rooms");
                System.out.println("3. See all Reservations");
                System.out.println("4. Add a Room");
                System.out.println("5. Populate Test Rooms");
                System.out.println("6. Populate Test Customers");
                System.out.println("7. Back to Main Menu");
                System.out.println("--------------------------------------------");
                System.out.println("Please select a number for the menu option");
                try{
                    String userChoice = scanner.nextLine();
                    int userInput = Integer.parseInt(userChoice);
                    switch (userInput){
                        case 1:
                            displayAllCustomers();
                            break;
                        case 2:
                            displayAllRooms();
                            break;
                        case 3:
                            adminResource.printAllReservation();
                            break;
                        case 4:
                            handleAdminInput();
                            break;
                        case 5:
                            populateTestRooms();
                            break;
                        case 6:
                            populateTestCustomers();
                            break;
                        case 7:
                            MainMenu.printMainMenuOptions();
                            break;
                        default:
                            System.out.println("Please Select a Number from 1 to 7\n");
                            break;
                    }
                }
                catch(Exception e){
                    System.out.println("Error: Invalid Input");
                }
            }
        }
    }

    public static void handleAdminInput(){
        String roomNumber = CustomerInput.processRoomNumber();
        double roomPrice = CustomerInput.processRoomPrice();
        int roomType = CustomerInput.processRoomType();
        adminResource.addRoom(roomNumber,roomPrice,roomType);
    }

    private static void displayAllRooms() {
        Collection<IRoom> roomsCollection = adminResource.getAllRooms();
        if(roomsCollection == null || roomsCollection.isEmpty()){
            System.out.println("There are no rooms available at the moment. Please check some time later");
        }
        int numRooms = 1;
        for(IRoom room : roomsCollection){
            System.out.println(numRooms + ". " +room);
            numRooms++;
        }
    }

    private static void displayAllCustomers(){
        Collection<Customer> customerCollection = adminResource.getAllCustomers();
        if(customerCollection == null || customerCollection.isEmpty()){
            System.out.println("There are no registered customers at the moment. Please check some time later");
            return;
        }
        int numCustomers = 1;
        for(Customer customer : customerCollection){
            System.out.println(numCustomers + ". " + customer);
            numCustomers++;
        }
    }

    public static void populateTestRooms(){
        adminResource.addRoom("101",100.0D,1);
        adminResource.addRoom("102",120.0D,2);
        /*adminResource.addRoom("103",150.0D,1);
        adminResource.addRoom("104",180.0D,2);
        adminResource.addRoom("105",210.0D,1);
        adminResource.addRoom("106",240.0D,2);
        adminResource.addRoom("107",270.0D,1);
        adminResource.addRoom("108",300.0D,2);
        adminResource.addRoom("109",350.0D,1);
        adminResource.addRoom("110",400.0D,2);*/
    }

    public static void populateTestCustomers(){
        hotelResource.createACustomer("michael","clarke","michaelclarke@gmail.com");
        hotelResource.createACustomer("john","kirby","johnkirby@gmail.com");
        hotelResource.createACustomer("james","taylor","jamestaylor@gmail.com");
        hotelResource.createACustomer("sheldon","cooper","sheldoncooper@gmail.com");
        hotelResource.createACustomer("steven","page","stevenpage@gmail.com");
    }
}
