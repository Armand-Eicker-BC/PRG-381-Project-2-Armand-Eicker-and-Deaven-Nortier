package Booking_System.PresentationLayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Booking_System.BusinessLogicLayer.BusinessOperations;

public class ManageClientsMenu implements IMenu {
    BusinessOperations BO = new BusinessOperations();

    @Override
    public String Menu() {
        return "Manage Clients: Please Select an Option\n0. Register a New Client\n1. View All Existing Clients\n2. Check if a Client Exists\n3. Back";
    }

    public void RegisterClient() throws Exception {
        Scanner inp = new Scanner(System.in);
        System.out.println("Please Enter title (e.g 'Mr')");
        String t = inp.nextLine();

        System.out.println("Enter Name:");
        String n = inp.nextLine();

        System.out.println("Enter Surname:");
        String s = inp.nextLine();

        System.out.println("Enter Email:");
        String e = inp.nextLine();

        System.out.println("Enter Phone Number");
        int p = inp.nextInt();

        BO.RegisterClient(t, n, s, e, p);
    }

    public void DisplayClients() throws SQLException, Exception {
        ArrayList<String> cs = new ArrayList<String>();
        cs = BO.OutputCustomers();
        for (String customer : cs) {
            System.out.println(customer);
        }
    }
    
    public void SearchClient() throws Exception {
        Scanner inp = new Scanner(System.in);
        System.out.println("Please enter client email:");
        String email = inp.nextLine();
        System.out.println(BO.FindCust(email));
    }
}
