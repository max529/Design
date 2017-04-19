package ch.hevs.design.data;

import java.io.Serializable;

/**
 * Created by hugo on 06.04.2017.
 */

public class Provider implements Serializable{
    private String name;
    private String surname;
    private String adress;
    private String email;


    public Provider(String name, String surname, String adress, String email) {
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.email = email;
    }

    @Override
    public String toString() {
        return this.surname+" "+this.name;
    }
    }

    public void insert ()
    {
        
    }


