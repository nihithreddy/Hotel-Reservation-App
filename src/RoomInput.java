import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class RoomInput {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public static String processRoomNumber(){
        boolean isInputValid = false;
        String roomNumber = null;
        System.out.println("Enter the room number");
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


    public static Date processCheckInDate(){
        boolean isInputValid = false;
        String checkInDateString = null;
        Date checkInDate = null;
        System.out.println("Enter the checkIn Date in yyyy-MM-dd format Example:2022-01-01");
        Scanner scanner = new Scanner(System.in);
        while(!isInputValid){
            try{
                checkInDateString = scanner.nextLine().trim();
                checkInDate = formatDate(checkInDateString, DATE_FORMAT);
                isInputValid = true;
            }
            catch (Exception e){
                System.out.println("Error: Please Enter the checkIn Date in the above mentioned format");
            }
        }
        //System.out.println(checkInDate.toString());
        return checkInDate;
    }

    public static Date processCheckOutDate(){
        boolean isInputValid = false;
        Date checkOutDate = null;
        String checkOutDateString = null;
        System.out.println("Enter the checkOut Date in yyyy-MM-dd format Example:2022-01-01");
        Scanner scanner = new Scanner(System.in);
        while(!isInputValid){
            try{
                checkOutDateString  = scanner.nextLine().trim();
                checkOutDate = formatDate(checkOutDateString,DATE_FORMAT);
                isInputValid = true;
            }
            catch (Exception e){
                System.out.println("Error: Please Enter the checkOut Date in the above mentioned format");
            }
        }
        //System.out.println(checkOutDate.toString());
        return checkOutDate;
    }

    public static Date formatDate(String dateString, String dateFormat) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
        dateFormatter.setLenient(false);
        return dateFormatter.parse(dateString);
    }

}
