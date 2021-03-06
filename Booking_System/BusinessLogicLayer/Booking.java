package Booking_System.BusinessLogicLayer;

public class Booking {
    //Declare encapsulated attributes
    private String eventType;
    private String eventDate;
    private String venueAddress;
    private int numOfPeople;
    private String decoration;
    private double total;
    private double balance;
    private String status;

    //Constructor
    public Booking(String ETP, String ED, String VA, int NOP,String D,double t,double b,String s) {
        this.eventType = ETP;
        this.eventDate = ED;
        this.venueAddress = VA;
        this.numOfPeople = NOP;
        this.decoration = D;
        this.total = t;
        this.balance = b;
        this.status = s;
    }

    //Creating Get and Set Methods
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }


    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }


    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public String BookingsToString(Booking book){
        return "Event Type: " + book.eventType + " Event Date: " + book.eventDate + " Venue: " + book.venueAddress + " Number of People: " + book.numOfPeople + " Decor: " + book.decoration;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
       
}
