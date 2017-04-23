package ch.hevs.design.data;

import java.io.Serializable;

/**
 * Created by hugo on 06.04.2017.
 */

public class Provider implements Serializable {
    private int _id;
    private String name;
    private String surname;
    private String adress;
    private String email;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Provider(){}
    public Provider(String name, String surname, String adress, String email) {
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.email = email;
    }

    @Override
    public String toString() {
        return this.surname + " " + this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Provider)){
            return false;
        }
        Provider p = (Provider)obj;
        if(this.name.equals(p.getName()) && this.surname.equals(p.getSurname())){
            return true;
        }
        return false;
    }

    public void insert() {

    }
}

