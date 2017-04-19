package ch.hevs.design.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 06.04.2017.
 */

public class Vin implements Serializable{
    private String img = "";
    private String name;
    private int annee;
    private Couleur couleur;
    private Region region;
    private int qte;
    private double prix;
    private List<Cepage> cepage = new ArrayList<Cepage>();

    public List<Cepage> getCepage(){
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Vin(String img, String name, int annee){
        this.img = img;
        this.name = name;
        this.annee = annee;
    }

    public Vin(String img, String name, int annee, Couleur couleur, Region region, int qte, double prix, List cepage) {
        this.img = img;
        this.name = name;
        this.annee = annee;
        this.couleur = couleur;
        this.region = region;
        this.qte = qte;
        this.prix = prix;
        this.cepage = cepage;
    }
}
