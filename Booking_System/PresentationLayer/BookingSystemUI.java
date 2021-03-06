package Booking_System.PresentationLayer;

import java.sql.SQLException;
import java.util.Scanner;

public class BookingSystemUI {
    public static void main(String[] args) throws SQLException, Exception {
        MainMenu menu = new MainMenu();
        int selection = 0;

        while (MainMenuEnum.values()[selection] != MainMenuEnum.EXIT) {
            System.out.println(menu.Menu());
            selection = new Scanner(System.in).nextInt();
            if(MainMenuEnum.values()[selection] == MainMenuEnum.MANAGE_CLIENTS){
                menu.CallSubMenu(0);
            }
            else if(MainMenuEnum.values()[selection] == MainMenuEnum.MANAGE_BOOKINGS){
                menu.CallSubMenu(1);
            }
            else if(MainMenuEnum.values()[selection] == MainMenuEnum.CHECK_NEXT_BOOKING){
                menu.CallSubMenu(2);
            }
            else if(MainMenuEnum.values()[selection] == MainMenuEnum.EXIT){
                
                System.console().flush();
            }
            else{
                System.out.println("Invalid Selection");
            }
        }
        
    }
}
