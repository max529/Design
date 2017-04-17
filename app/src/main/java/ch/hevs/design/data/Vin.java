package ch.hevs.design.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

import ch.hevs.design.R;

/**
 * Created by hugo on 06.04.2017.
 */

public class Vin {
    private String img = "";
    private String name;
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
}
