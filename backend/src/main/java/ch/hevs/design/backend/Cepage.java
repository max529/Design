package ch.hevs.design.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by maxim on 03.05.2017.
 */

@Entity
public class Cepage {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long _id;
    private String nom;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
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
