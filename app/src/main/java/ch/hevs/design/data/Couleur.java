package ch.hevs.design.data;

import java.io.Serializable;

/**
 * Created by hugo on 06.04.2017.
 */

public class Couleur implements Serializable{
    private int _id;
    private String couleur;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
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

    public Couleur(){ }

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
