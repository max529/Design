package ch.hevs.design.data;

import java.util.ArrayList;

/**
 * Created by hugo on 06.04.2017.
 */

public class Vin {
    private int annee;
    private Couleur couleur;
    private Region region;
    private int qte;
    private double prix;
    private ArrayList cepage = new ArrayList<String>();

    public ArrayList getCepage(){
        return cepage;
    }

    public void setCepage(ArrayList cepage){
        this.cepage = cepage;
    }
    private Fournisseur fournisseur;

    public Fournisseur getFournisseur(){
        return fournisseur;
    }
    public void setFournisseur(Fournisseur fournisseur){
        this.fournisseur = fournisseur;
    }
}
