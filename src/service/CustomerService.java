package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CustomerService {
    private static CustomerService customerService;
    private Map<String,Customer> customersMap;

    private CustomerService(){
        customersMap = new HashMap<String,Customer>();
    }

    public static CustomerService getInstance(){
        if(customerService == null){
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void addCustomer(String firstName,String lastName,String email){
        try{
            if(customerEmailExists(email)){
                throw new Exception("A Customer Already Exists with the given email "+ email);
            }
            customersMap.put(email,new Customer(firstName,lastName,email));
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Customer getCustomer(String customerEmail){
         return customersMap.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers(){
        return customersMap.values();
    }

    public boolean customerEmailExists(String email){
        return customersMap.containsKey(email);
    }
}
