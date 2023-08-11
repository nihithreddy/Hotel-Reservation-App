import java.util.Scanner;

public class CustomerInput {

    public static String processCustomerFirstName(){
        boolean isInputValid = false;
        String firstName = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your first name");
            while(!isInputValid){
                try{
                    firstName = scanner.nextLine().trim();
                    //System.out.println(firstName);
                    isInputValid = true;
                }
                catch (Exception e){
                    System.out.println("Error: Invalid Input! Try Again");
                }
            }
        return firstName;
    }

    public static String processCustomerLastName(){
        boolean isInputValid = false;
        String lastName = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your last name");
            while(!isInputValid){
                try{
                    lastName = scanner.nextLine().trim();
                    //System.out.println(lastName);
                    isInputValid = true;
                }
                catch (Exception e){
                    System.out.println("Error: Invalid Input! Please Enter Again");
                }
            }
        return lastName;
    }

    public static String processCustomerEmail(){
        boolean isInputValid = false;
        String email = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your email in the format: name@domain.com");
            while(!isInputValid){
                try{
                    email = scanner.nextLine().trim();
                    //System.out.println(email);
                    isInputValid = true;
                }
                catch (Exception e){
                    System.out.println("Error: Invalid Input Use the email format: name@domain.com");
                }
            }
        return email;
    }

    public static String processRoomNumber(){
        boolean isInputValid = false;
        String roomNumber = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Room Number");
        while(!isInputValid){
            try{
                roomNumber = scanner.nextLine().trim();
                //System.out.println(roomNumber);
                isInputValid = true;
            }
            catch (Exception e){
                System.out.println("Error: Invalid Input! Please Enter Again");
            }
        }
        return roomNumber;
    }

    public static int processRoomType(){
        boolean isInputValid = false;
        String roomType = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter 1 for Single Bed Room 2 for Double Bed Room");
        while(!isInputValid){
            try{
                roomType = scanner.nextLine().trim();
                //System.out.println(roomType);
                if(!roomType.equals("1") && !roomType.equals("2")){
                    throw new Exception("Error: Invalid Input! Please Enter Again");
                }
                isInputValid = true;
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return Integer.parseInt(roomType);
    }

    public static double processRoomPrice(){
        boolean isInputValid = false;
        double roomPrice = 0D;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the price of the room");
        while(!isInputValid){
            try{
                String inputPrice = scanner.nextLine().trim();
                roomPrice = Double.parseDouble(inputPrice);
                //System.out.println(roomPrice);
                isInputValid = true;
            }
            catch (Exception e){
                System.out.println("Error: Invalid Input! Please Enter Again");
            }
        }
        return roomPrice;
    }

    public static boolean processCustomerChoice(){
        boolean isInputValid = false;
        String customerChoice = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to book a room? y/n");
        while(!isInputValid){
            try{
                customerChoice = scanner.nextLine().trim().toLowerCase();
                if(!customerChoice.equals("y") && !customerChoice.equals("n")){
                    throw new Exception("Error: Invalid Input! Please Enter y for yes and n for no");
                }
                isInputValid = true;
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return customerChoice.equals("y");
    }

    public static String processCustomerRoomNumber(){
        boolean isInputValid = false;
        String roomNumber = null;
        System.out.println("What room number would you like to reserve");
        Scanner scanner = new Scanner(System.in);
        while(!isInputValid){
            try{
                roomNumber = scanner.nextLine().trim();
                isInputValid = true;
            }
            catch (Exception e){
                System.out.println("Error: Invalid Input Please Enter Again");
            }
        }
        return roomNumber;
    }

    public static boolean customerHasAnExistingAccount() {
        boolean isInputValid = false;
        String customerChoice = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you have an account? y/n");
        while (!isInputValid) {
            try {
                customerChoice = scanner.nextLine().trim().toLowerCase();
                if(!customerChoice.equals("y") && !customerChoice.equals("n")){
                    throw new Exception("Error: Invalid Input! Please Enter y for yes and n for no");
                }
                isInputValid = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return customerChoice.equals("y");
    }

    public static int processNumberOfDays() {
        boolean isInputValid = false;
        Scanner scanner = new Scanner(System.in);
        int numDays = 0;
        System.out.println("There are no rooms available in the mentioned dates. Please enter the number of days between 1 and 365 to add to search for rooms ");
        while(!isInputValid){
            try{
                String inputDays = scanner.nextLine().trim();
                numDays = Integer.parseInt(inputDays);
                //System.out.println(numDays);
                if(numDays<0 || numDays>365){
                    throw new Exception("Error: Invalid Input! Please Enter Again");
                }
                isInputValid = true;
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return numDays;
    }
}
