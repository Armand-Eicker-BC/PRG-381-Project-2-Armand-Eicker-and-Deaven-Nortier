package Booking_System.PresentationLayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Booking_System.BusinessLogicLayer.Booking;
import Booking_System.BusinessLogicLayer.BusinessOperations;


public class ManageBookingsMenu implements IMenu {
    BusinessOperations BO = new BusinessOperations();

    @Override
    public String Menu() {
        return "Manage Bookings: Please Select an Option\n0. Make a Booking\n1. Display All Bookings\n2. Find and Manage a Booking\n3. Back";
    }

    public void MakeBooking(String ETP, String ED, String VA, int NOP, String D,String cEmail,int[] Fooditem,int[] Qty) throws Exception {
        Booking book = new Booking(ETP, ED, VA, NOP,D,BO.calculatePrice(Fooditem, Qty, NOP, ETP),BO.calculatePrice(Fooditem, Qty, NOP, ETP),"Unconfirmed");
        BO.CreateBooking(book, cEmail);
        BO.AddFoodMenu(Fooditem, Qty);
    }

    public void DisplayBookings() throws SQLException, Exception {
        ArrayList<String> bookings = BO.OutputBookings();
        for (String string : bookings) {
            System.out.println(string);
        }
    }

    public void FindBooking(int bookingID) throws Exception {
        System.out.println("Booking ID:" + String.valueOf(bookingID) + "\t" + BO.FindBook(bookingID));
    }

    public void UpdateMenu(int bid) throws Exception{
        Scanner inp = new Scanner(System.in);
        String FoodMenu = "1. Adults Meal\n2.Kids Meal\n3. Drinks\n4. Dessert";
        int input = 100;
        int[] FoodM = {0,0,0,0};
        int[] FoodQ = {0,0,0,0};
        int count = 0;
        while (input != 0) {
            System.out.println("Please Select a food option, enter only the item number or 0 to exit");
            System.out.println(FoodMenu);
            input = inp.nextInt();
            if(input == 0){
                break;
            }
            FoodM[count] = input;
            System.out.println("How many would you like:");
            FoodQ[count] = inp.nextInt();
            count++;
            if(count == 4){
                input = 0;
                System.out.println("You have ordered the maximum amount of items");
            }
        }
        BO.DeleteMenu(bid);
        BO.UFoodMenu(FoodM, FoodQ,bid);
    }

    public void DisplayMenu(int bid) throws SQLException{
        ArrayList<String> menu = BO.getMenu(bid);
        for (String string : menu) {
            System.out.println(string);
        }
    }

    public void MakePayment(int bid) throws SQLException{
        Scanner inp = new Scanner(System.in);
        System.out.println("Please enter the amount you would like to pay:");
        double amount = inp.nextDouble();
        System.out.println(BO.MakePayment(bid,amount));
    }
}
