package ch.hevs.design.data;

/**
 * Created by hugo on 06.04.2017.
 */

public class Mouvement {
    private int _id;
    private Vin vin;
    private boolean in;
    private int qte;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Vin getVin() {
        return vin;
    }

    public void setVin(Vin vin) {
        this.vin = vin;
    }

    public boolean isIn() {
        return in;
    }

    public void setIn(boolean in) {
        this.in = in;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Mouvement(){}
}
