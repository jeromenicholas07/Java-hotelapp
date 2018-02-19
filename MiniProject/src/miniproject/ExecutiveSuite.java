/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

/**
 *
 * @author DELL
 */
public class ExecutiveSuite extends Room{

    public ExecutiveSuite() {
        //super(roomNo);
        this.amount = 5000;
        this.sqrft = 1200;
    }

    @Override
    int getSqrft() {
        return sqrft;
    }
}
