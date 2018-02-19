
package miniproject;

/**
 *
 * @author DELL
 */
public class DeluxeSuite extends Room{

    public DeluxeSuite() {
        //super(roomNo);
        amount = 3500;
        sqrft = 850;
    }

    @Override
    int getSqrft() {
        return sqrft;
    }
    
    
}
