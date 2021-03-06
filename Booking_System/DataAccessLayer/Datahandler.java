package Booking_System.DataAccessLayer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import Booking_System.BusinessLogicLayer.Booking;
import Booking_System.BusinessLogicLayer.Customer;

public class Datahandler {

    private Connection connect;
    private Statement statement;
    private ResultSet resultSet;
    private String sqlQ;   

    //Default constructor for the datahandler class
    public Datahandler() throws SQLException {
        this.connect = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookingSys;integratedSecurity=true");
        this.statement =  connect.createStatement();
        this.resultSet = null;
        this.sqlQ = null;
    }

    //Registers a new client to the database
    public void RegisterCustomer(Customer cust)throws Exception{
        try 
        {                
            sqlQ = "INSERT INTO Customers VALUES('"+ cust.GetTitle() +"','"+ cust.GetName() +"','" + cust.GetSName() +"'," + cust.GetNum() +",'" + cust.GetMail() +"')";
            statement.executeUpdate(sqlQ);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }            
    }  
    
    //Fetches all existing clients from the database
    public ResultSet GetCustomers() throws Exception {      
        try{
            sqlQ = "SELECT * FROM Customers";
            resultSet = statement.executeQuery(sqlQ); 
            return resultSet;          
        } 
        catch (Exception e) {
            System.out.println(e.toString());
        }       

        return null;
    }

    //Method to insert booking details into the Bookings table
    public void insertBooking(Booking book, int cID,int decorID) throws Exception {
        try{
            String sDate1=book.getEventDate();
            java.util.Date date1=new SimpleDateFormat("d/M/yyyy").parse(sDate1);
            sqlQ = "INSERT INTO Bookings VALUES ("+ cID + ",'" + book.getEventType() + "','" + date1 + "','" + book.getVenueAddress() + "','" + book.getNumOfPeople() + "',"+ book.getTotal() +","+ book.getBalance() +",'"+ book.getStatus()+"','"+ decorID+"')";
            statement.executeUpdate(sqlQ);
        }
        catch (Exception e){
            System.out.println(e.toString());
        } 
    }

    //Checks if a decoration profile exists
    public boolean CheckDecor(String string) throws SQLException
    {
        try{
            sqlQ = "SELECT DecorDescription FROM Decor";
            resultSet = statement.executeQuery(sqlQ); 
            
            while (resultSet.next()) {
                if(resultSet.getString("DecorDescription").equalsIgnoreCase(string)){
                    return true;
                }
            }
        } 
        catch (Exception e) {
            System.out.println(e.toString());
        } 
        return false;
    }

    //Adds a decoration to the database and then returns its newely generated id
    public void AddDecor(String decor) throws SQLException {
        try {
            sqlQ = "INSERT INTO Decor VALUES('"+ decor +"')";
            statement.executeUpdate(sqlQ);            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
    }

    public int GetDecorID(String decor) throws SQLException{
        try {
            sqlQ = "SELECT DecorID FROM Decor WHERE DecorDescription = '"+ decor +"'";
            resultSet = statement.executeQuery(sqlQ);
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("DecorID");
            }
            return id;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }

    public String FindDecor(int decorID) throws SQLException{
       
        String desc = "";        
        try {
            sqlQ = "SELECT DecorDescription FROM Decor WHERE DecorID = "+ decorID +"";
            resultSet = statement.executeQuery(sqlQ);
            
            while (resultSet.next()) {
                desc = resultSet.getString("DecorDescription");
            }
            return desc;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return desc;
    }

    //Method to get bookings
    public ResultSet GetBookings() throws Exception {      
        try{
            sqlQ = "SELECT * FROM Bookings";
            ResultSet rs = statement.executeQuery(sqlQ);
            
            return rs;          
        } 
        catch (Exception e) {
            System.out.println(e.toString());
        }

        return null;
    }
     
    //Method to search bookings
    public ResultSet SearchBookings(int bID) throws Exception {      
        try{
            sqlQ = "SELECT * FROM dbo.Bookings WHERE BookingID = "+bID+"";
            resultSet = statement.executeQuery(sqlQ); 
            return resultSet;          
        } 
        catch (Exception e) {
            System.out.println(e.toString());
        }

        return null;
    }

    //Method to update the FoodMenu table before booking confirmation
    public void updateFoodMenu(int mID,int qty,int bID) throws Exception{
        try{
            sqlQ = "UPDATE BookingFood SET MenuID = " + mID + ",Quantity = "+qty+" WHERE = " + bID;
            statement.executeUpdate(sqlQ);
        }
        catch (Exception e){
            System.out.println(e.toString());
        } 
    }

    //Adds the food items for a booking menu
    public void AddBookingFood(int menuItemIDs[],int menuItemQtys[],int bID) throws SQLException {
        for (int i = 0;i<menuItemIDs.length;i++) {
            try 
            {  
                if(menuItemIDs[i] != 0) {
                    sqlQ = "INSERT INTO BookingFood VALUES ("+bID+","+menuItemIDs[i]+","+menuItemQtys[i]+")";
                    statement.executeUpdate(sqlQ);
                }    
                
            }
            catch(Exception e){
                System.out.println(e.toString());
            }    
        }
    }

    //Searches and returns the menu items for a given booking
    public ResultSet GetBookingFoodMenu(int bID) throws SQLException {
        try{
            sqlQ = "SELECT * FROM BookingFood WHERE BookingID = "+bID+"";
            resultSet = statement.executeQuery(sqlQ); 
            return resultSet;          
        } 
        catch (Exception e) {
            System.out.println(e.toString());
        } 
        return null;   
    } 
    
    public String GetFoodNames(int menuID){
        String items = "";
        try{
            sqlQ = "SELECT MenuName FROM FoodMenu WHERE MenuID = "+menuID+"";
            resultSet = statement.executeQuery(sqlQ);
            ResultSet rs = resultSet;
            while (rs.next()) {
                items = rs.getString("MenuName");
            }
             
            return items;        
        } 
        catch (Exception e) {
            System.out.println(e.toString());
        } 
        return items;
    }

    public void DeleteM(int bid)throws SQLException{
        try 
        {                
            sqlQ = "DELETE FROM BookingFood WHERE BookingID = " + bid;
            statement.executeUpdate(sqlQ);
        }
        catch(Exception e){
            System.out.println(e.toString());
        } 
    }

    public double GetBalance(int bid)throws SQLException{
        double balance = 0;
        try{
            sqlQ = "SELECT Balance FROM Bookings WHERE BookingID= "+bid+"";
            resultSet = statement.executeQuery(sqlQ);
            ResultSet rs = resultSet;
            while (rs.next()) {
                balance = rs.getDouble("Balance");
            }
             
            return balance;        
        } 
        catch (Exception e) {
            System.out.println(e.toString());
        } 

        return balance;  
    }

    public void UpdateBal(int bid,double amount)throws SQLException{
        try 
        {                
            sqlQ = "UPDATE Bookings SET Balance = " + amount +" WHERE BookingID = " +bid+"";
            statement.executeUpdate(sqlQ);
        }
        catch(Exception e){
            System.out.println(e.toString());
        } 
    }

    public void UpdateStat(int bid)throws SQLException{
        try 
        {                
            sqlQ = "UPDATE Bookings SET Confirmation = 'Confirmed' WHERE BookingID = " +bid+"";
            statement.executeUpdate(sqlQ);
        }
        catch(Exception e){
            System.out.println(e.toString());
        } 
    }

    public double GetTotal(int bid)throws SQLException{
        double balance = 0;
        try{
            sqlQ = "SELECT TotalDue FROM Bookings WHERE BookingID= "+bid+"";
            resultSet = statement.executeQuery(sqlQ);
            ResultSet rs = resultSet;
            while (rs.next()) {
                balance = rs.getDouble("TotalDue");
            }
             
            return balance;        
        } 
        catch (Exception e) {
            System.out.println(e.toString());
        } 

        return balance;  
    }
}
