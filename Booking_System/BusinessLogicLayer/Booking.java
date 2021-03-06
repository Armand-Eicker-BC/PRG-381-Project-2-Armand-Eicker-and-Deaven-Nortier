package Booking_System.BusinessLogicLayer;

public class Booking {
    //Declare encapsulated attributes
    private String eventType;
    private String eventDate;
    private String eventTime;
    private String venueAddress;
    private int numOfPeople;
    private String foodMenu;
    private String foodQty;
    private String decoration;

    //Constructor
    public Booking(String ETP, String ED, String ETM, String VA, int NOP, String FM,String fQ,String D) {
        this.eventType = ETP;
        this.eventDate = ED;
        this.eventTime = ETM;
        this.venueAddress = VA;
        this.numOfPeople = NOP;
        this.foodMenu = FM;
        this.foodQty = fQ;
        this.decoration = D;
    }

    //Creating Get and Set Methods
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setFoodQuatity(String fq) {
        this.foodQty = fq;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getFoodqty() {
        return this.foodQty;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
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

    public String getFoodMenu() {
        return foodMenu;
    }

    public void setFoodMenu(String foodMenu) {
        this.foodMenu = foodMenu;
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
       
}
