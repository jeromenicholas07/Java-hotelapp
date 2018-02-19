
package miniproject;
import java.util.*;

public class IdGenerator {
    List<Integer> ints = new ArrayList<>();
    int i = 0;

    IdGenerator() {
        for (int i = 0; i < 10000; i++) {
            ints.add(i);
        }
        Collections.shuffle(ints);
    }

    int nextInt() {
        return ints.get(i++);
    }
}
