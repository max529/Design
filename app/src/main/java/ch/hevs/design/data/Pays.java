package ch.hevs.design.data;

import java.io.Serializable;

/**
 * Created by hugo on 06.04.2017.
 */

public class Pays implements Serializable {
    private int _id;
    private String nom;
    private String initial;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public Pays(){}
    public Pays(String nom, String initial){
        this.initial = initial;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Pays)){
            return false;
        }
        Pays p = (Pays)obj;
        if(this.nom.equals(p.getNom())){
            return true;
        }
        return false;
    }
}
