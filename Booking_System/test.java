package Booking_System;

import java.io.IOException;

import Booking_System.BusinessLogicLayer.Customer;
import Booking_System.DataAccessLayer.Datahandler;

public class test {
    public static void main(String[] args) throws Exception {
        
        Customer cs = new Customer("Mr", "Deaven3", "Nortier", "yourself@gmail.com", 234324234);
        Customer cs2 = new Customer("Mr", "Deaven4", "Nortier", "yourself@gmail.com", 234324234);
        Datahandler d = new Datahandler();
       
        try {
            d.RegisterCustomer(cs);
            d.RegisterCustomer(cs2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
