
package miniproject;

import java.time.Instant;

public interface Service {
    static int baseServiceFare = 10;
    
    Instant getTime();
    String getDescription();
    int getAmount();
    Bill getBill();
}
