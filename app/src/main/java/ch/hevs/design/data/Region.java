package ch.hevs.design.data;

import java.io.Serializable;

/**
 * Created by hugo on 06.04.2017.
 */

public class Region implements Serializable{
    private Pays pays;
    private String nom;

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
}
