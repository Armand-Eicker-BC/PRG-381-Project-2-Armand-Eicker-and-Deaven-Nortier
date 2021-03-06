package Booking_System.PresentationLayer;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu implements IMenu {

    @Override
    public String Menu() {
        return "Home: Please Select an Option to Proceed\n0. Manage Clients\n1. Manage Bookings\n2. Exit Application";
    }

    public void CallSubMenu(int selec) throws SQLException, Exception {
        if(selec == 0){

            ManageClientsMenu menu = new ManageClientsMenu();
            int selection = 0;

            while (ManageClientEnum.values()[selection] != ManageClientEnum.EXIT) {

                System.out.println(menu.Menu());
                selection = new Scanner(System.in).nextInt();
                
                if(ManageClientEnum.values()[selection] == ManageClientEnum.REGISTER){
                    menu.RegisterClient();
                }
                else if(ManageClientEnum.values()[selection] == ManageClientEnum.VIEW){
                    menu.DisplayClients();
                }
                else if(ManageClientEnum.values()[selection] == ManageClientEnum.FIND){
                    menu.SearchClient();
                }               
                else{
                    if(ManageClientEnum.values()[selection] != ManageClientEnum.EXIT){
                        System.out.println("Invalid Selection");
                    }                 
                }
            }
        }
        else if(selec == 1){
            ManageBookingsMenu menu = new ManageBookingsMenu();
            int selection = 0;
            Scanner inp = new Scanner(System.in);
            while (ManageBookingEnum.values()[selection] != ManageBookingEnum.BACK) {
                System.out.println(menu.Menu());
                selection = inp.nextInt();

                if (ManageBookingEnum.values()[selection] == ManageBookingEnum.CREATE_BOOKING) {
                    String ETP,ED, VA, D, cEmail;
                    int NOP;
                    inp =new Scanner(System.in);
                    System.out.println("Please enter customer email:");
                    cEmail = inp.nextLine();

                    System.out.println("Please enter event type:");
                    ETP = inp.nextLine();

                    System.out.println("Please enter event date:");
                    ED = inp.nextLine();

                    System.out.println("Please enter venue address:");
                    VA = inp.nextLine();

                    System.out.println("Please enter decoration description:");
                    D= inp.nextLine();

                    System.out.println("Please enter the number of people that will be attending:");
                    NOP = inp.nextInt();

                    
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

                    menu.MakeBooking(ETP, ED, VA, NOP, D, cEmail,FoodM,FoodQ);
                }
                else if (ManageBookingEnum.values()[selection] == ManageBookingEnum.VIEW) {
                    menu.DisplayBookings();   
                }
                else if (ManageBookingEnum.values()[selection] == ManageBookingEnum.FIND) {
                    int bookingID;
                    System.out.println("Please enter booking id:");
                    bookingID = inp.nextInt();
                    menu.FindBooking(bookingID);
                    System.out.println("Options for BookingID: " + bookingID +"\n1. Update Food Menu\n2. Make Payment\n3. Exit");
                    int input = inp.nextInt();

                    while (input != 3) {
                        if(input==1){
                            menu.DisplayMenu(bookingID);
                            menu.UpdateMenu(bookingID);
                        }
                        else if(input==2){
                           menu.MakePayment(bookingID);
                        }

                        System.out.println("Options for BookingID: " + bookingID +"\n1. Update Food Menu\n2. Make Payment\n3. Exit");
                        input = inp.nextInt();
                    }
                }
                else{
                    if (ManageBookingEnum.values()[selection] != ManageBookingEnum.BACK) {
                        System.out.println("Invalid Selection");
                    }
                }
            }
        }
    }
    
}
