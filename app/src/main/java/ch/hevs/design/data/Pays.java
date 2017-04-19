package ch.hevs.design.data;

import java.io.Serializable;

/**
 * Created by hugo on 06.04.2017.
 */

public class Pays implements Serializable {
    private String nom;
    private String initial;

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

    public Pays(String nom, String initial){
        this.initial = initial;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
