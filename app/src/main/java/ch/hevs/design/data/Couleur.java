package ch.hevs.design.data;

import java.io.Serializable;

/**
 * Created by hugo on 06.04.2017.
 */

public class Couleur implements Serializable{
    private String couleur;

    public Couleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return this.couleur;
    }
}
