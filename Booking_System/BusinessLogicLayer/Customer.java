package Booking_System.BusinessLogicLayer;

public class Customer {
    //Declaring encapsulated attributes
    private String title;
    private String custName;
    private String custSurname;
    private String email;
    private int phoneNum;

    //Constructor
    public Customer(String T, String N,String S,String E,int Num){
        this.title =T;
        this.custName = N;
        this.custSurname = S;
        this.email =E;
        this.phoneNum = Num;
    }

    //Creating Get Methods
    public String GetTitle(){
        return this.title;
    }

    public String GetName(){
        return this.custName;
    }

    public String GetSName(){
        return this.custSurname;
    }

    public String GetMail(){
        return this.email;
    }

    public int GetNum(){
        return this.phoneNum;
    }

    //Creating Set Methods
    public void SetTitle(String title){
        this.title = title;
    }

    public void SetName(String name){
        this.custName = name;
    }

    public void SetSName(String sname){
        this.custSurname = sname;
    }

    public void SetMail(String mail){
        this.email = mail;
    }

    public void SetNum(int num){
        this.phoneNum = num;
    }

    public String CustomertoString(Customer cs){
        return "Title: " + cs.title + " Name: " + cs.custName + " Surname: " + cs.custSurname + " Email: " + cs.email + " Contact Number: " + cs.phoneNum;
    }
}
