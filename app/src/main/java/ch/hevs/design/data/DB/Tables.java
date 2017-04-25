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
        public static final String CREATE_TABLE_CEPAGE = "CREATE TABLE "
                + TABLE_CEPAGE + "(" + key_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + nameCepage + " TEXT NOT NULL" +" );";
    }





    //---------- COMMAND TABLE ----------
    public static abstract class TableCommand implements BaseColumns{

        //Table name
        public static final String TABLE_COMMAND = "command";

        //Command Column names
        public static final String key_id = "idCommand";
        public static final String idWine = "idWine";
        public static final String qte = "qte";
        public static final String state = "state";

        //Database creation sql statement product
        public static final String CREATE_TABLE_COMMAND= "CREATE TABLE "
                + TABLE_COMMAND + "(" + key_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + idWine + " INTEGER, "
                + qte + " INTEGER, "
                + state + " INTEGER" +" );";
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
        public static final String CREATE_TABLE_PROVIDER = "CREATE TABLE "
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
        public static final String key_in = "isIn";
        public static final String quantity = "quantity";

        //Database creation sql statement product
        public static final String CREATE_TABLE_MOVEMENT = "CREATE TABLE "
                + TABLE_MOVEMENT + "(" + key_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + key_vin + " INTEGER, "
                + key_in + " BOOLEAN, "
                + quantity + " INTEGER"
                +" );";
    }





    //---------- COUNTRY TABLE ----------
    public static abstract class TableCountry implements BaseColumns{

        //Table name
        public static final String TABLE_COUNTRY= "country";

        //Country Column names
        public static final String key_id = "idCountry";
        public static final String nameCountry = "nameCountry";
        public static final String initial = "initialOfCountry";

        //Database creation sql statement product
        public static final String CREATE_TABLE_COUNTRY = "CREATE TABLE "
                + TABLE_COUNTRY + "(" + key_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + nameCountry + " TEXT NOT NULL, "
                + initial + " TEXT NOT NULL" +" );";
        public static final String DEFAULT_INSERTION = "INSERT INTO "+ TABLE_COUNTRY+" ("+nameCountry+","+initial+") VALUES ('Suisse','CH'), ('France','FR'), ('Italie','IT')";
    }





    //---------- REGION TABLE ----------
    public static abstract class TableRegion implements BaseColumns{

        //Table name
        public static final String TABLE_REGION= "region";

        //Region Column names
        public static final String key_id = "idRegion";
        public static final String idCountry= "idCountry";
        public static final String region = "name";

        //Database creation sql statement product
        public static final String CREATE_TABLE_REGION = "CREATE TABLE "
                + TABLE_REGION + "(" + key_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + idCountry + " INTEGER, "
                + region + " TEXT NOT NULL" +" );";

    }





    //---------- Wine TABLE ----------
    public static abstract class TableWine implements BaseColumns{

        //Table name
        public static final String TABLE_WINE= "wine";

        //Wine Column names
        public static final String key_id = "idWine";
        public static final String imgPath = "imgPath";
        public static final String name = "nameWine";
        public static final String description = "description";
        public static final String years = "years";
        public static final String color = "color";
        public static final String idRegion = "idRegion";
        public static final String quantity = "quantity";
        public static final String price = "price";
        public static final String idProvider = "idProvider";

        //Database creation sql statement product
        public static final String CREATE_TABLE_WINE = "CREATE TABLE "
                + TABLE_WINE + "(" + key_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + imgPath + " TEXT, "
                + name + " TEXT, "
                + description + " TEXT, "
                + years + " INTEGER, "
                + color + " INTEGER, "
                + idRegion + " INTEGER, "
                + quantity + " INTEGER, "
                + price + " DECIMAL, "
                + idProvider + " INTEGER" +" );";
    }





    //---------- Wine-Cepage TABLE ----------
    public static abstract class TableWineCepage implements BaseColumns{

        //Table name
        public static final String TABLE_NAME= "wine_cepage";

        //Wine Column names
        public static final String idWine = "idWine";
        public static final String idCepage = "idCepage";

        //Database creation sql statement product
        public static final String CREATE_TABLE = "CREATE TABLE "
                + TABLE_NAME + "(" + idWine +" INTEGER , "
                + idCepage + " INTEGER , "
                + "PRIMARY KEY ("+idWine+","+idCepage+"));";
    }




}
