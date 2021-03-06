package Booking_System.BusinessLogicLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Booking_System.DataAccessLayer.DataManipulator;
import Booking_System.DataAccessLayer.Datahandler;

public class BusinessOperations {
    DataManipulator dm;

    public BusinessOperations() {
        try {
            dm = new DataManipulator();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Checks if discount should be applied
    public boolean checkDiscount(int numOfPeople) {
        boolean check = false;

        if (numOfPeople > 40) {
            check = true;
        } else {
            check = false;
        }

        return check;
    }

    // calculates a discounted price at 15% of the amount
    public double applyDiscount(double amount) {
        double discountAmount = amount - (amount * 0.15);
        return discountAmount;
    }

    public double calculatePrice(int[] MenuItemID, int[] Qty, int numOfPeople, String EventType) {
        double price = 0;

        if (EventType.equalsIgnoreCase("wedding")) {
            price += 5000;
        } else if (EventType.equalsIgnoreCase("baptism")) {
            price += 1000;
        } else if (EventType.equalsIgnoreCase("year end function")) {
            price += 2500;
        } else if (EventType.equalsIgnoreCase("birthday party")) {
            price += 800;
        }

        for (int i =0;i<4;i++) {
            if (MenuItemID[i] == 1) {
                price += (150 * Qty[i]);
            } else if (MenuItemID[i] == 2) {
                price += (50 * Qty[i]);
            } else if (MenuItemID[i] == 3) {
                price += (100 * Qty[i]);
            } else if (MenuItemID[i] == 4) {
                price += (80 * Qty[i]);
            }
        }

        if (checkDiscount(numOfPeople) == true) {
            price = applyDiscount(price);
            return price;
        } else {
            return price;
        }
    }

    public String MakePayment(int bid,double amount) throws SQLException{
   
        Datahandler dh = new Datahandler();
        
        double bal = dh.GetBalance(bid);
        bal = bal - amount;

        if(bal < 0){
            return "The amount payed will result in an overdraft please adjust amount";
        }
        else{
            dh.UpdateBal(bid, bal);
        }

        if(((bal / dh.GetTotal(bid)*100) <= 50)){
            dh.UpdateStat(bid);
            return "Balance Updated Successfully and status changed to 'confirmed' - New balance: " + bal;
        }
        else{
            return "Balance Updated - New balance: " + bal;
        }       
    }

    public void CreateBooking(Booking book, String email) throws Exception {
        dm.CreateBooking(book, email);
    }

    public void AddFoodMenu(int[] food,int[] qty) throws Exception {
        Datahandler dh = new Datahandler();
        int bid = 499;
        ResultSet bookings = dh.GetBookings();
        
        while(bookings.next()){
            bid = bookings.getInt("BookingID");
        }
        
        bookings.close();
        dh.AddBookingFood(food, qty,bid);
    }

    public void MakePayment(int cID, double amount, String paymentDate) {
        dm.UpdateBalance(cID, amount, paymentDate);
    }

    public ArrayList<String> OutputCustomers() throws SQLException, Exception {
        ArrayList<String> customers = new ArrayList<String>();

        for (Customer cs : dm.GetCustomers()) {
            customers.add(cs.CustomertoString(cs));
        }

        return customers;
    }

    public ArrayList<String> OutputBookings() throws SQLException, Exception {
        Map<String,Booking> bookings = new HashMap<String,Booking>();
        bookings = dm.GetBookings();

        ArrayList<String> bookingsOutput = new ArrayList<String>();

        for(Map.Entry<String,Booking> entry : bookings.entrySet()){
            bookingsOutput.add("Booking ID: " + entry.getKey() + " " + entry.getValue().BookingsToString(entry.getValue()));
        }

        return bookingsOutput;
    }

    public void RegisterClient(String T, String N,String S,String E,int Num) throws Exception {
        dm.RegistCust(T,N,S,E,Num);
    }

    public String FindCust(String email) throws Exception {
        return dm.FindCustomer(email);
    }

    public String FindBook(int bID) throws Exception{
        Map<String,Booking> bookings = new HashMap<String,Booking>();
        bookings = dm.GetBookings();
        String bookInfo = "";
        for(Map.Entry<String,Booking> entry : bookings.entrySet()){           
            if(entry.getKey().equalsIgnoreCase(String.valueOf(bID))){
                bookInfo = entry.getValue().BookingsToString(entry.getValue());
            }
        }
        return bookInfo;
    }

    public ArrayList<String> getMenu(int bid) throws SQLException{
        ArrayList<String> men = new ArrayList<String>();
        Map<String,String> menu= new HashMap<String,String>();

        menu = dm.GetMenu(bid);

        for (Map.Entry<String,String> entry : menu.entrySet()) {
            if(entry.getKey().equalsIgnoreCase("1")){
                men.add("Meal:\t Adult Meal\t Quatity:\t " + entry.getValue());
            }
            else if(entry.getKey().equalsIgnoreCase("2")){
                men.add("Meal:\t Kids Meal\t Quatity:\t " + entry.getValue());
            }
            else if(entry.getKey().equalsIgnoreCase("3")){
                men.add("Meal:\t Drinks\t\t Quatity:\t " + entry.getValue());
            }
            else if(entry.getKey().equalsIgnoreCase("4")){
                men.add("Meal:\t Dessert\t Quatity:\t " + entry.getValue());
            }
        }
        return men;
    }
    public void DeleteMenu(int bid) throws SQLException{
        Datahandler dh = new Datahandler();
        dh.DeleteM(bid);
    }

    public void UFoodMenu(int[] food,int[] qty,int bid) throws Exception {
        Datahandler dh = new Datahandler();
        dh.AddBookingFood(food, qty,bid);
    }
}
