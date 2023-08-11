import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import service.CustomerService;

import java.util.Collection;

public class Driver {
    public static AdminResource adminResource = AdminResource.getInstance();
    public static HotelResource hotelResource = HotelResource.getInstance();

    public static void addRoomsTest(){
        //Add few rooms for testing
        adminResource.addRoom("101",100.0D,1);
        adminResource.addRoom("102",120.0D,2);
        adminResource.addRoom("103",150.0D,1);
        adminResource.addRoom("104",180.0D,2);
        adminResource.addRoom("105",210.0D,1);
        adminResource.addRoom("106",240.0D,2);
        adminResource.addRoom("107",270.0D,1);
        adminResource.addRoom("108",300.0D,2);
        adminResource.addRoom("109",350.0D,1);
        adminResource.addRoom("110",400.0D,2);
        Collection<IRoom> roomsList = adminResource.getAllRooms();
        System.out.println("Test Pass: "+ (roomsList.size() == 10));

    }

    public static void addCustomersTest(){
        //create few customers for testing
        hotelResource.createACustomer("michael","clarke","michaelclarke@gmail.com");
        hotelResource.createACustomer("john","kirby","johnkirby@gmail.com");
        hotelResource.createACustomer("james","taylor","jamestaylor@gmail.com");
        hotelResource.createACustomer("sheldon","cooper","sheldoncooper@gmail.com");
        hotelResource.createACustomer("steven","page","stevenpage@gmail.com");
        Collection<Customer> customersList = adminResource.getAllCustomers();
        System.out.println("Test Pass: "+ (customersList.size() == 5));
    }

    public static void customerExistsTest(){
        hotelResource.createACustomer("billy","clarke","billyclarke@gmail.com");
        Customer customer = hotelResource.getCustomer("billyclarke@gmail.com");
        System.out.println("Test Pass: "+ (customer != null));
    }

    public static void customerNotExistsTest(){
        Customer customer = hotelResource.getCustomer("abc@gmail.com");
        System.out.println("Test Pass: "+ (customer == null));
    }

    public static void customerEmailValidTest(){
        try{
            Customer customer = new Customer("abc","xyz","abc@xyz.com");
            System.out.println("Test Pass : true");
        }
        catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void customerEmailNotValidTest(){
        try{
            Customer customer = new Customer("abc","xyz","abcxyz.com");
        }
        catch(IllegalArgumentException ex){
            System.out.println("Test Pass : true");
        }
    }


    public static void main(String[] args) {
        addCustomersTest();
        addRoomsTest();
        customerExistsTest();
        customerNotExistsTest();
        customerEmailValidTest();
        customerEmailNotValidTest();
    }

}
