package ch.hevs.design.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by maxim on 03.05.2017.
 */
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long _id;
    private Pays pays;
    private String nom;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Region(){}
    public Region(String nom){
        this.nom = nom;
        this.pays = new Pays("Unknow","UN");
    }
    public Region(String nom, Pays pays){
        this.pays = pays;
        this.nom = nom;
    }

    @Override
    public String toString() {
        String ini = this.pays.getInitial();
        if(ini.equals("")){
            return this.nom;
        }
        return this.nom + " ("+this.pays.getInitial()+")";
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Region)){
            return false;
        }
        Region r = (Region)obj;
        if(this.nom.equals(r.getNom()) && this.pays.equals(r.getPays())){
            return true;
        }
        return false;
    }
}
