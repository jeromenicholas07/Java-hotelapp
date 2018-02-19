
package miniproject;

import java.time.Instant;

/**
 *
 * @author DELL
 */
public class HotelService implements Service{
    String description;
    Instant t;
    int amount,cid;
    Bill b;
    
    public HotelService(String description, int amount, int cid) {
        t = Instant.now();
        this.description = description;
        this.amount = amount + baseServiceFare;
        this.cid = cid;
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
