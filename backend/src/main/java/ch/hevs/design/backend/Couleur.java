package ch.hevs.design.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by maxim on 03.05.2017.
 */

@Entity
public class Couleur {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long _id;
    private String couleur;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Couleur(String couleur) {
        this.couleur = couleur;
    }
    public Couleur(){}

    @Override
    public String toString() {
        return this.couleur;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.couleur.equals(((Couleur)obj).getCouleur())){
            return true;
        }
        return false;
    }

}
