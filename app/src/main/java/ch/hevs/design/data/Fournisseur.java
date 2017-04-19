package ch.hevs.design.data;

import java.io.Serializable;

/**
 * Created by hugo on 06.04.2017.
 */

public class Fournisseur implements Serializable{
    private String nom;
    private String prenom;
    private String adresse;
    private String email;


    public Fournisseur(String nom, String prenom, String adresse, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
    }

    @Override
    public String toString() {
        return this.prenom+" "+this.nom;
    }
}
