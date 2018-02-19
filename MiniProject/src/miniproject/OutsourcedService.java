
package miniproject;

import java.time.Instant;


public class OutsourcedService implements Service{
String nameOfCompany,contactNumber,description;
Instant t;
int amount,cid;
Bill b;

    public OutsourcedService(String description, int amount,int cid) {
        nameOfCompany = "Outsourcer pvt. Ltd.";
        contactNumber = "9876558713";
        this.description = description;
        this.amount = amount + baseServiceFare;
        this.cid = cid;
        t = Instant.now();
        System.out.println("Calling : "+ nameOfCompany);
        
        b = new Bill(description, t, amount);
    }



    @Override
    public Instant getTime() {
        return t;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    public Bill getBill() {
        return b;
    }

}
