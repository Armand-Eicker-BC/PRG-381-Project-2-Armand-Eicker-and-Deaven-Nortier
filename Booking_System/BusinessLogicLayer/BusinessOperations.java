package Booking_System.BusinessLogicLayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Booking_System.DataAccessLayer.DataManipulator;

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

        for (int i : MenuItemID) {
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

    public void CreateBooking(Booking book, String email) throws Exception {
        dm.CreateBooking(book, email);
        Account acc = new Account(dm.FindCustomerID(email), calculatePrice(dm.SeperateMenuItems(book),
                dm.seperateFoodQty(book), book.getNumOfPeople(), book.getEventType()));
        dm.CreateAccountinDB(acc);
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
}
