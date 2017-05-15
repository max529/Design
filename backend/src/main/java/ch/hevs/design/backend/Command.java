package ch.hevs.design.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by maxim on 03.05.2017.
 */
@Entity
public class Command {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long _id;
    private int state;
    private int qte;
    private Vin vin;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
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
