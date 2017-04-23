package ch.hevs.design.data;

import java.io.Serializable;

/**
 * Created by hugo on 06.04.2017.
 */

public class Cepage implements Serializable{
    private int _id;
    private String nom;

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

    public Cepage(){}
    public Cepage(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Cepage)){
            return false;
        }
        Cepage c = (Cepage)obj;
        if(this.nom.equals(c.getNom())){
            return true;
        }
        return false;
    }
}
