package ch.hevs.design.data;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hugo on 06.04.2017.
 */

public class Command {
    private Provider provider;
    private Date date;
    private int etat;
    private ArrayList vin = new ArrayList<String>();

    public ArrayList getVin(){
        return vin;
    }

    public void setVin(ArrayList vin){
        this.vin = vin;
    }

}
