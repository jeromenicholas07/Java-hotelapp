package miniproject;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
public class Customer implements java.io.Serializable{
    
    private int noOfPeople;
    private int cID;
    //String emailID,address;
    private String emailID;
    //String address;
    private String address;
    private Instant checkInTime;
    private Person[] residents ;
    private long phoneNumber;
    List<Bill> bills;

    public Customer(int noOfPeople, String emailID, String address, Instant checkInTime, Person[] residents, long phoneNumber) {
        this.noOfPeople = noOfPeople;
        this.emailID = emailID;
        this.address = address;
        this.checkInTime = checkInTime;
        this.residents = residents;
        this.phoneNumber = phoneNumber;
        IdGenerator s = new IdGenerator();
        this.cID = s.nextInt();
        bills = new ArrayList<>();
    }
    
    public Customer(int noOfPeople, String emailID, String address,Person[] residents, long phoneNumber) {
        this.noOfPeople = noOfPeople;
        this.emailID = emailID;
        this.address = address;
        this.checkInTime = Instant.now();
        this.residents = residents;
        this.phoneNumber = phoneNumber;
        IdGenerator s = new IdGenerator();
        this.cID = s.nextInt();
        bills = new ArrayList<>();
    }

    public boolean addTransaction(Bill b){
        return bills.add(b);
    }
    public int getNoOfPeople() {
        return noOfPeople;
    }

    public int getcID() {
        return cID;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getAddress() {
        return address;
    }

    public Instant getCheckInTime() {
        return checkInTime;
    }

    public Person[] getResidents() {
        return residents;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String displayBills(){
        String msg = "";
        for(Bill x : bills){
            msg = msg + "\n" + x.toString();
        }
        return msg;
    }
    
    public int getAmount(){
        int amt = 0;
        for(Bill x : bills){
            amt += x.getAmount();
        }
        return amt;
    }
    
    public Object[][] getBillData(){
        Object[][] data = new Object[bills.size()][3];
        
        for(int i = 0; i < bills.size(); i++){
            Bill x = bills.get(i);
            
            data[i][0] = x.getDescription();
            data[i][1] = new SimpleDateFormat("dd.MM.yy HH:mm").format(Date.from(x.getTimeOfTransaction()));
            data[i][2] = x.getAmount();
        }
        return data;
    }
}
