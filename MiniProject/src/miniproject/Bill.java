
package miniproject;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
public class Bill implements java.io.Serializable{
    private Instant timeOfTransaction;
    private int amount;
    private String description;

    public Bill(String description,Instant timeOfTransaction, int amount) {
        this.timeOfTransaction = timeOfTransaction;
        this.amount = amount;
        this.description = description;
    }

    public Instant getTimeOfTransaction() {
        return timeOfTransaction;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return description + "\t" + amount + "\t" + new SimpleDateFormat("HH:mm:ss").format(Date.from(timeOfTransaction));
    }  
}
