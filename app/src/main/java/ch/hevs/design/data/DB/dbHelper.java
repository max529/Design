package ch.hevs.design.data.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;
import android.support.design.widget.TabLayout;

import ch.hevs.design.data.Provider;


/**
 * Created by hugo on 19.04.2017.
 */

public class dbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Design.db";
    private static final int DATABASE_VERSION = 1;

    public dbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tables.TableCepage.CREATE_TABLE_CEPAGE);
        db.execSQL(Tables.TableCommand.CREATE_TABLE_COMMAND);
        db.execSQL(Tables.TableCountry.CREATE_TABLE_COUNTRY);
        db.execSQL(Tables.TableMovemement.CREATE_TABLE_MOVEMENT);
        db.execSQL(Tables.TableProvider.CREATE_TABLE_PROVIDER);
        db.execSQL(Tables.TableRegion.CREATE_TABLE_REGION);
        db.execSQL(Tables.TableWine.CREATE_TABLE_WINE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Tables.TableCepage.TABLE_CEPAGE);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.TableCommand.TABLE_COMMAND);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.TableProvider.TABLE_PROVIDER);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.TableCountry.TABLE_COUNTRY);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.TableMovemement.TABLE_MOVEMENT);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.TableRegion.TABLE_REGION);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.TableWine.TABLE_WINE);

        //CREATE NEW TABLE
        onCreate(db);
    }
    //-- Insert -- / -- Delete -- / -- Update -- / -- PROVIDER --

    public void insertProvider (String name, String surname, String address, String email){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Tables.TableProvider.nameProvider, name );
        values.put(Tables.TableProvider.surnameProvider, surname);
        values.put(Tables.TableProvider.address, address);
        values.put(Tables.TableProvider.email, email);

        db.insert(Tables.TableProvider.CREATE_TABLE_PROVIDER,null, values);
    }

    //-- Insert -- / -- Delete -- / -- Update -- / -- CEPAGE --

    public void insertCepage(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Tables.TableCepage.nameCepage, name);

        db.insert(Tables.TableCepage.CREATE_TABLE_CEPAGE,null, values);
    }

    //-- Insert -- / -- Delete -- / -- Update -- / -- COMMAND --

    public void insertCommand(String idProvider, String date, String etat){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Tables.TableCommand.idProvider, idProvider);
        values.put(Tables.TableCommand.date, date);
        values.put(Tables.TableCommand.etat, etat);

        db.insert(Tables.TableCommand.CREATE_TABLE_COMMAND,null, values);
    }

    //-- Insert -- / -- Delete -- / -- Update -- / -- COUNTRY --

    public void insertCountry(String name, String initial){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Tables.TableCountry.nameCountry, name);
        values.put(Tables.TableCountry.initial, initial);


        db.insert(Tables.TableCountry.CREATE_TABLE_COUNTRY,null, values);
    }

    //-- Insert -- / -- Delete -- / -- Update -- / -- MOVEMENT --

    public void insertMovement(String idWine, String date, String status, String quantity , String wording){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Tables.TableMovemement.key_vin, idWine);
        values.put(Tables.TableMovemement.dateMovement, date);
        values.put(Tables.TableMovemement.key_boolean, status);
        values.put(Tables.TableMovemement.quantity, quantity);
        values.put(Tables.TableMovemement.wording, wording);

        db.insert(Tables.TableMovemement.CREATE_TABLE_MOVEMENT,null, values);
    }

    //-- Insert -- / -- Delete -- / -- Update -- / -- REGION --

    public void insertRegion(String idCountry, String name){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Tables.TableRegion.idCountry, idCountry);
        values.put(Tables.TableRegion.region, name);

        db.insert(Tables.TableRegion.CREATE_TABLE_REGION, null, values);
    }

    //-- Insert -- / -- Delete -- / -- Update -- / -- WINE --

    public void insertWine(String years, String idRegion, String quantity, String price){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Tables.TableWine.years, years);
        values.put(Tables.TableWine.idRegion, idRegion);
        values.put(Tables.TableWine.quantity, quantity);
        values.put(Tables.TableWine.price, price);

        db.insert(Tables.TableWine.CREATE_TABLE_WINE, null, values);
    }




}
