
package miniproject;

/**
 *
 * @author DELL
 */
public class PremierSuite extends Room{

    public PremierSuite() {
        //super(roomNo);
        amount = 2000;
        sqrft = 650;
    }

    @Override
    int getSqrft() {
        return sqrft;
    }
    
}
