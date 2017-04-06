package ch.hevs.design.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hugo on 06.04.2017.
 */

public class Database extends SQLiteOpenHelper {

    private static final String LOG = "Database";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "DBSTOCKS";

    //TABLE NAMES
    private static final String TABLE_CEPAGE = "cepage";
    private static final String TABLE_COMMAND ="command";
    private static final String TABLE_COULEUR ="couleur";
    private static final String TABLE_FOURNISSEUR ="fournisseur";
    private static final String TABLE_MOUVEMENT ="mouvement";
    private static final String TABLE_PAYS ="pays";
    private static final String TABLE_REGION ="region";
    private static final String TABLE_VIN ="vin";

    //COMMON COLUMN NAMES
    private static String key_id ="id";

    //CEPAGE TABLE - COLUMN NAMES
    private static final String key_nom ="Nom des cépages";
    //COMMAND TABLE - COLUMN NAMES
    private static final String key_fournisseur = "ID Fournisseur";
    private static final String key_date = "ID Date";
    private static final String key_etat = "Etat";
    //COULEUR TABLE - COLUMN NAMES
    private static final String key_couleur = "Couleur";
    //FOURNISSEUR TABLE - COLUMN NAMES
    private static final String key_nomFournisseur = "Nom";
    private static final String key_prenom = "Prénom";
    private static final String key_adresse = "Adresse";
    private static final String key_email = "Email";
    //MOUVEMENT TABLE - COLUMN NAMES
    private static final String key_vin = "ID du Vin";
    private static final String key_dateMouvement = "ID Date";
    private static final String key_boolean = "In";
    private static final String key_int = "Quantité";
    private static final String key_libelle = "Libéllé";
    //PAYS TABLE - COLUMN NAMES
    private static final String key_nomPays = "Nom du Pays";
    private static final String key_inital = "Initial du Pays";
    //REGION TABLE - COLUMN NAMES
    private static final String key_idPays = "ID du pays";
    private static final String key_nomRegion = "Nom des Régions";
    //VIN TABLE - COLUMN NAMES
    private static final String key_annee = "Année";
    private static final String key_region = "ID Region";
    private static final String key_couleurVin = "ID Couleur";
    private static final String key_qte = "Quantité";
    private static final String key_prix = "Prix";

    //TABLE CREATE STATEMENTS
    //TODO TABLE CREATE STATEMENT
    private static final String CREATE_TABLE_CEPAGE = "CREATE TABLE" + TABLE_CEPAGE + "("+ key_id + "INTEGER PRIMARY KEY, " + key_nom + "TEXT" + ")";
    private static final String CREATE_TABLE_COMMAND = "CREATE TABLE" + TABLE_COMMAND + "("+ key_id + "INTEGER PRIMARY KEY, " + key_fournisseur + "INTEGER" + key_date + "DATETIME, " + key_etat + "TEXT"+")";
    private static final String CREATE_TABLE_COULEUR = "CREATE TABLE" + TABLE_COULEUR + "("+ key_id + "INTEGER PRIMARY KEY, "  + key_couleur + "TEXT" +")";
    private static final String CREATE_TABLE_FOURNISSEUR = "CREATE TABLE" + TABLE_FOURNISSEUR + "("+ key_id + "INTEGER PRIMARY KEY, "  + key_nomFournisseur + "TEXT" +  key_prenom + "TEXT" + key_adresse + "TEXT" + key_email + "TEXT"+ ")";
    private static final String CREATE_TABLE_MOUVEMENT = "CREATE TABLE" + TABLE_MOUVEMENT + "("+ key_id + "INTEGER PRIMARY KEY, "  + key_vin + "INTEGER" + key_dateMouvement + "INTEGER" + key_boolean + "TEXT"+ key_int + "INTEGER" + key_libelle + "TEXT" +")";
    private static final String CREATE_TABLE_PAYS = "CREATE TABLE" + TABLE_PAYS + "("+ key_id + "INTEGER PRIMARY KEY, "  + key_nomPays + "TEXT" + key_inital + "TEXT"+")";
    private static final String CREATE_TABLE_REGION = "CREATE TABLE" + TABLE_REGION + "("+ key_id + "INTEGER PRIMARY KEY, "  + key_idPays + "INTEGER" + key_nomRegion + "TEXT" +")";
    private static final String CREATE_TABLE_VIN = "CREATE TABLE" + TABLE_VIN + "("+ key_id + "INTEGER PRIMARY KEY, "  + key_annee + "INTEGER" + key_region + "TEXT" + key_couleurVin + "TEXT" + key_qte + "INTEGER" + key_prix + "INTEGER" +")";



    public Database(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CEPAGE);
        db.execSQL(CREATE_TABLE_COMMAND);
        db.execSQL(CREATE_TABLE_COULEUR);
        db.execSQL(CREATE_TABLE_FOURNISSEUR);
        db.execSQL(CREATE_TABLE_MOUVEMENT);
        db.execSQL(CREATE_TABLE_PAYS);
        db.execSQL(CREATE_TABLE_REGION);
        db.execSQL(CREATE_TABLE_VIN);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //ON UPGRADE DROP OLDER TABLES
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CEPAGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMAND);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COULEUR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOURNISSEUR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOUVEMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIN);

        //CREATE NEW TABLE
        onCreate(db);
    }
}
