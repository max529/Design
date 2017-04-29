package ch.hevs.design.data;

/**
 * Created by maxim on 30.04.2017.
 */

public class Langue {
    private String name;
    private String initial;
    private  int _id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Langue(String name, String initial, int _id) {
        this.name = name;
        this.initial = initial;
        this._id = _id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
