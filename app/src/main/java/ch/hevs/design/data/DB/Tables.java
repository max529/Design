package ch.hevs.design.data.DB;

import android.provider.BaseColumns;

/**
 * Created by hugo on 19.04.2017.
 */

public final class Tables {

    private Tables(){

    }

    //---------- CEPAGE TABLE ----------
    public static abstract class TableCepage implements BaseColumns{

        //Table name
        public static final String TABLE_CEPAGE = "cepages";

        //Cepages Column names
        public static final String key_id = "idCepage";
        public static final String nameCepage ="name";

        //Database creation sql statement product
        public static final String CREATE_TABLE_CEPAGE = "CREATE TABLE"
                + TABLE_CEPAGE + "(" + key_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + nameCepage + " TEXT NOT NULL" +" );";
    }

    //---------- COMMAND TABLE ----------
    public static abstract class TableCommand implements BaseColumns{

        //Table name
        public static final String TABLE_COMMAND = "command";

        //Command Column names
        public static final String key_id = "idCommand";
        public static final String idProvider = "idProvider";
        public static final String date = "date";
        public static final String etat = "etat";

        //Database creation sql statement product
        public static final String CREATE_TABLE_COMMAND= "CREATE TABLE"
                + TABLE_COMMAND + "(" + key_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + idProvider + " INTEGER, "
                + date + " DATE, "
                + etat + " TEXT NOT NULL" +" );";
    }

    //---------- PROVIDER TABLE ----------
    public static abstract class TableProvider implements BaseColumns{

        //Table name
        public static final String TABLE_PROVIDER= "provider";

        //Provider Column names
        public static final String key_id = "idProvider";
        public static final String nameProvider = "name";
        public static final String surnameProvider = "Surname";
        public static final String address = "address";
        public static final String email = "email";

        //Database creation sql statement product
        public static final String CREATE_TABLE_PROVIDER = "CREATE TABLE"
                + TABLE_PROVIDER + "(" + key_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + nameProvider + " TEXT NOT NULL, "
                + surnameProvider + " TEXT NOT NULL, "
                + address + " TEXT NOT NULL, "
                + email + " VARCHAR" +" );";
    }
    //---------- MOVEMENT TABLE ----------
    public static abstract class TableMovemement implements BaseColumns{

        //Table name
        public static final String TABLE_MOVEMENT= "movement";

        //Movement Column names
        public static final String key_id = "idMovement";
        public static final String key_vin = "idWine";
        public static final String dateMovement = "date";
        public static final String key_boolean = "in";
        public static final String quantity = "quantity";
        public static final String wording = "wording";

        //Database creation sql statement product
        public static final String CREATE_TABLE_MOVEMENT = "CREATE TABLE"
                + TABLE_MOVEMENT + "(" + key_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + key_vin + " INTEGER, "
                + dateMovement + " DATE, "
                + key_boolean + " BOOLEAN, "
                + quantity + " INTEGER, "
                + wording + " TEXT NOT NULL" +" );";
    }
    //---------- COUNTRY TABLE ----------
    public static abstract class TableCountry implements BaseColumns{

        //Table name
        public static final String TABLE_COUNTRY= "country";

        //Country Column names
        public static final String key_id = "idMovement";
        public static final String nameCountry = "name";
        public static final String initial = "initial of country";

        //Database creation sql statement product
        public static final String CREATE_TABLE_COUNTRY = "CREATE TABLE"
                + TABLE_COUNTRY + "(" + key_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + nameCountry + " TEXT NOT NULL, "
                + initial + " TEXT NOT NULL" +" );";
    }
    //---------- REGION TABLE ----------
    public static abstract class TableRegion implements BaseColumns{

        //Table name
        public static final String TABLE_REGION= "region";

        //Region Column names
        public static final String key_id = "idMovement";
        public static final String idCountry= "idCountry";
        public static final String region = "name";

        //Database creation sql statement product
        public static final String CREATE_TABLE_REGION = "CREATE TABLE"
                + TABLE_REGION + "(" + key_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + idCountry + " INTEGER, "
                + region + " TEXT NOT NULL" +" );";
    }
    //---------- Wine TABLE ----------
    public static abstract class TableWine implements BaseColumns{

        //Table name
        public static final String TABLE_WINE= "wine";

        //Wine Column names
        public static final String key_id = "idMovement";
        public static final String years = "years";
        public static final String idRegion = "ID Region";
        public static final String quantity = "quantity";
        public static final String price = "Price";

        //Database creation sql statement product
        public static final String CREATE_TABLE_WINE = "CREATE TABLE"
                + TABLE_WINE + "(" + key_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + years + " INTEGER, "
                + idRegion + " INTEGER, "
                + quantity + " INTEGER, "
                + price + " DECIMAL" +" );";
    }




}
