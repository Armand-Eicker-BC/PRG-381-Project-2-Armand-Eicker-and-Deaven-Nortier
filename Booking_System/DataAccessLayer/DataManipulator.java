package Booking_System.DataAccessLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Booking_System.BusinessLogicLayer.Booking;
import Booking_System.BusinessLogicLayer.Customer;

public class DataManipulator {
    Datahandler dh;

    // Default Constructor
    public DataManipulator() {
        try {
            dh = new Datahandler();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Customer> GetCustomers() throws SQLException, Exception {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        ResultSet rs =dh.GetCustomers();

        while (rs.next()) {
            Customer cs = new Customer(rs.getString("Title"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Email_Address"), rs.getInt("Phone"));
            customers.add(cs);
        }

        return customers;
    }

    public Map<String,Booking> GetBookings() throws SQLException, Exception {
        Map<String,Booking> bk = new HashMap<String,Booking>();
        ResultSet rs =dh.GetBookings();
        
        while (rs.next()) {
            Booking book = new Booking(rs.getString("EventType"), rs.getString("Date"), rs.getString("Venue"), rs.getInt("NumOfPeople"),String.valueOf(rs.getInt("Decorations")),rs.getDouble("TotalDue"),rs.getDouble("Balance"),rs.getString("Confirmation"));
            bk.put(rs.getString("BookingID"), book);
        }
    
        for(Map.Entry<String,Booking> entry : bk.entrySet()){
            entry.getValue().setDecoration(dh.FindDecor(Integer.parseInt(entry.getValue().getDecoration())));    
        }
        return bk;
    }

    public void CreateBooking(Booking book,String email) throws Exception {
        
        if(dh.CheckDecor(book.getDecoration()) == true){
            dh.insertBooking(book, FindCustomerID(email),dh.GetDecorID(book.getDecoration()));
        }
        else{
            dh.AddDecor(book.getDecoration());
            dh.insertBooking(book, FindCustomerID(email),dh.GetDecorID(book.getDecoration()));
        }
    }

    

    public int FindCustomerID(String email) throws Exception {
        int cid = 0;
        ResultSet rs =dh.GetCustomers();

        while (rs.next()) {
            if(rs.getString("Email_Address").equalsIgnoreCase(email)){
                cid = rs.getInt("CustomerID");
                break;
            }
        }

        return cid;
    }

    public String FindCustomer(String email) throws Exception {
        String cs = "";
        ResultSet rs =dh.GetCustomers();

        while (rs.next()) {
            if(rs.getString("Email_Address").equalsIgnoreCase(email)){
                Customer cust = new Customer(rs.getString("Title"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Email_Address"), rs.getInt("Phone"));
                cs = cust.CustomertoString(cust);
                break;
            }
        }

        return cs;
    }

    public Map<String,String> GetMenu(int bID) throws SQLException {

        Map<String,String> Menu = new HashMap<String,String>();

        ResultSet rs = dh.GetBookingFoodMenu(bID);
        while (rs.next()) {
            Menu.put(rs.getString("MenuID"), rs.getString("Quantity"));
        }
        return Menu;
    }

    public void RegistCust(String T, String N,String S,String E,int Num) throws Exception {
        Customer cust = new Customer(T, N, S, E, Num);
        dh.RegisterCustomer(cust);
    }

    public void UpdateBalance(int cID,double amount,String paymentDate){
        //to be completed
    }
}
