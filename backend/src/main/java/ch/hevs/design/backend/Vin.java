package ch.hevs.design.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by maxim on 03.05.2017.
 */
@Entity
public class Vin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long _id;
    private String img = "";
    private String name;
    private String description = "";
    private int annee;
    private Couleur couleur;
    private Region region;
    private int qte;
    private double prix;
    private List<Cepage> cepage = new ArrayList<Cepage>();
    private Provider provider = null;

    public long get_id() { return _id; }
    public void set_id(long _id) { this._id = _id; }

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

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Couleur getCouleur() { return couleur; }
    public void setCouleur(Couleur couleur) { this.couleur = couleur; }

    public Region getRegion() { return region; }
    public void setRegion(Region region) { this.region = region; }

    public int getQte() { return qte; }
    public void setQte(int qte) { this.qte = qte; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public List<Cepage> getCepage(){
        return cepage;
    }
    public void setCepage(List<Cepage> cepage){
        this.cepage = cepage;
    }

    public Provider getProvider(){
        return provider;
    }
    public void setProvider(Provider provider){
        this.provider = provider;
    }

    public Vin(){}
    public Vin(String img, String name, int annee){
        this.img = img;
        this.name = name;
        this.annee = annee;
    }



    public Vin(String img, String name, String description, int annee, Couleur couleur, Region region, int qte, double prix, List cepage) {
        this.img = img;
        this.name = name;
        this.description = description;
        this.annee = annee;
        this.couleur = couleur;
        this.region = region;
        this.qte = qte;
        this.prix = prix;
        this.cepage = cepage;
    }
    public Vin(String img, String name, String description, int annee, Couleur couleur, Region region, int qte, double prix, List cepage,Provider provider) {
        this.img = img;
        this.name = name;
        this.description = description;
        this.annee = annee;
        this.couleur = couleur;
        this.region = region;
        this.qte = qte;
        this.prix = prix;
        this.cepage = cepage;
        this.provider = provider;
    }

    @Override
    public String toString() {
        return this.name+" ("+this.provider.toString()+")";
    }


    public String toStringInfo() {
        return "Vin{" +
                "_id=" + _id +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", annee=" + annee +
                ", couleur=" + couleur +
                ", region=" + region +
                ", qte=" + qte +
                ", prix=" + prix +
                ", cepage=" + cepage +
                ", provider=" + provider +
                '}';
    }
}
