package ch.hevs.design.data;

/**
 * Created by hugo on 06.04.2017.
 */

public class Command {
    private int _id;
    private int state;
    private int qte;
    private Vin vin;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Vin getVin() {
        return vin;
    }

    public void setVin(Vin vin) {
        this.vin = vin;
    }

    public String getName() {
        return this.vin.getName();
    }

    public int getQte() {
        return this.qte;
    }

}
