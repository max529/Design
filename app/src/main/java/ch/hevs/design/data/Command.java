package ch.hevs.design.data;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by hugo on 06.04.2017.
 */

public class Command {
    private Fournisseur fournisseur;
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
