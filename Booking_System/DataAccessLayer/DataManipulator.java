package Booking_System.DataAccessLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Booking_System.BusinessLogicLayer.Account;
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
            Customer cs = new Customer(rs.getString("Title"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Email"), rs.getInt("Phone"));
            customers.add(cs);
        }

        return customers;
    }

    public Map<String,Booking> GetBookings() throws SQLException, Exception {
        Map<String,Booking> bk = new HashMap<String,Booking>();
        ResultSet rs =dh.GetBookings();

        while (rs.next()) {
            Booking book = new Booking(rs.getString("EventType"), rs.getString("EventDate"), rs.getString("EventTime"), rs.getString("Venue"), rs.getInt("NumOfPeople"),"","",dh.FindDecor(rs.getInt("Decorations")));
            bk.put(rs.getString("BookingID"), book);
        }

        return bk;
    }

    public void CreateBooking(Booking book,String email) throws Exception {
        
        if(dh.CheckDecor(book.getDecoration()) == true){
            MakeBooking(book, email);
        }
        else{
            dh.AddDecor(book.getDecoration());
            MakeBooking(book, email);
        }
    }

    public void MakeBooking(Booking book,String email) throws Exception {
        int id;
        id = dh.GetDecorID(book.getDecoration());
        dh.insertBooking(book, FindCustomerID(email), id);

        int bID = 0;
        ResultSet rs = dh.GetBookings();

        while (rs.next()) {
            if(rs.last()){
                bID = rs.getInt("BookingID");
            }
        }

        dh.AddBookingFood(SeperateMenuItems(book), seperateFoodQty(book), bID);
    }

    public int FindCustomerID(String email) throws Exception {
        int cid = 0;
        ResultSet rs =dh.GetCustomers();

        while (rs.next()) {
            if(rs.getString("Email").equalsIgnoreCase(email)){
                cid = rs.getInt("CustomerID");
                break;
            }
        }

        return cid;
    }

    public int[] SeperateMenuItems(Booking book){
        String[] menuItems = book.getFoodMenu().split("|");
        int[] mItemIDs = {0,0,0,0}; 

        for (int i = 0;i<menuItems.length;i++) {
            mItemIDs[i] = Integer.parseInt(menuItems[i]);
        }

        return mItemIDs;
    }

    public int[] seperateFoodQty(Booking book){
        String[] foodQtys = book.getFoodqty().split("|");
        int[] quatities = {0,0,0,0}; 

        for (int i = 0;i<foodQtys.length;i++) {
            quatities[i] = Integer.parseInt(foodQtys[i]);
        }

        return quatities;
    }

    public Map<String,String> GetMenu(int bID) throws SQLException {

        Map<String,String> Menu = new HashMap<String,String>();

        ResultSet rs = dh.GetBookingFoodMenu(bID);
        while (rs.next()) {
            Menu.put(dh.GetFoodNames(rs.getInt(("MenuID"))), rs.getString("Quantity"));
        }
        return Menu;
    }

    public void CreateAccountinDB(Account acc){
        //to be completed
    }

    public void UpdateBalance(int cID,double amount,String paymentDate){
        //to be completed
    }
}
