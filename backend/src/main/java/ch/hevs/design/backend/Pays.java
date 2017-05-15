package ch.hevs.design.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by maxim on 03.05.2017.
 */
@Entity
public class Pays {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long _id;
    private String nom;
    private String initial;

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
