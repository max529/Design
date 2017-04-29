package ch.hevs.design.data.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.design.HomeActivity;
import ch.hevs.design.components.SerializeList;
import ch.hevs.design.data.Cepage;
import ch.hevs.design.data.Command;
import ch.hevs.design.data.Couleur;
import ch.hevs.design.data.Mouvement;
import ch.hevs.design.data.Pays;
import ch.hevs.design.data.Provider;
import ch.hevs.design.data.Region;
import ch.hevs.design.data.Vin;

import static ch.hevs.design.data.DB.Tables.TableCommand.qte;
import static ch.hevs.design.data.DB.Tables.TableCountry.TABLE_COUNTRY;
import static ch.hevs.design.data.DB.Tables.TableCountry.initial;
import static ch.hevs.design.data.DB.Tables.TableCountry.nameCountry;
import static ch.hevs.design.data.DB.Tables.TableRegion.idCountry;
import static ch.hevs.design.data.DB.Tables.TableWine.idProvider;
import static ch.hevs.design.data.DB.Tables.TableWine.imgPath;


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
        db.execSQL(Tables.TableWineCepage.CREATE_TABLE);

        //insert default values
        db.execSQL(Tables.TableCountry.DEFAULT_INSERTION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Tables.TableCepage.TABLE_CEPAGE);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.TableCommand.TABLE_COMMAND);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.TableProvider.TABLE_PROVIDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRY);
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
        values.put(Tables.TableProvider.nameProvider, name);
        values.put(Tables.TableProvider.surnameProvider, surname);
        values.put(Tables.TableProvider.address, address);
        values.put(Tables.TableProvider.email, email);
        db.insert(Tables.TableProvider.TABLE_PROVIDER, null, values);
        db.close();
    }
    public Provider getProvider(int _id){
        SQLiteDatabase db = this.getReadableDatabase();

        String table = Tables.TableProvider.TABLE_PROVIDER;
        String[] columns = {
                Tables.TableProvider.key_id,
                Tables.TableProvider.nameProvider,
                Tables.TableProvider.surnameProvider,
                Tables.TableProvider.email,
                Tables.TableProvider.address
        };
        String selection = Tables.TableProvider.key_id+" = ?";
        String[] selectionArgs = {_id+""};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        cursor.moveToFirst();

        Provider p = new Provider();
        p.set_id(cursor.getInt(0));
        p.setName(cursor.getString(1));
        p.setSurname(cursor.getString(2));
        p.setEmail(cursor.getString(3));
        p.setAdress(cursor.getString(4));

        return p;
    }
    public List<Provider> getProviders(){
        SQLiteDatabase db = this.getReadableDatabase();

        String table = Tables.TableProvider.TABLE_PROVIDER;
        String[] columns = {
                Tables.TableProvider.key_id,
                Tables.TableProvider.nameProvider,
                Tables.TableProvider.surnameProvider,
                Tables.TableProvider.email,
                Tables.TableProvider.address
        };
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = Tables.TableProvider.nameProvider;
        String limit = null;

        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

        List<Provider> res = new ArrayList<>();

        while(cursor.moveToNext()){
            Provider p = new Provider();
            p.set_id(cursor.getInt(0));
            p.setName(cursor.getString(1));
            p.setSurname(cursor.getString(2));
            p.setEmail(cursor.getString(3));
            p.setAdress(cursor.getString(4));
            res.add(p);
        }
        return res;
    }







    //-- Insert -- / -- Delete -- / -- Update -- / -- CEPAGE --

    public void insertCepage(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Tables.TableCepage.nameCepage, name);
        db.insert(Tables.TableCepage.TABLE_CEPAGE, null, values);
        db.close();
    }
    public List<Cepage> getCepagesFromWine(int idWine){
        SQLiteDatabase db = this.getReadableDatabase();
        String table1 = Tables.TableWineCepage.TABLE_NAME;
        String table2 = Tables.TableCepage.TABLE_CEPAGE;

        String MY_QUERY = "SELECT * FROM "+table1+" a INNER JOIN "+table2+" b ON a."+ Tables.TableWineCepage.idCepage+"=b."+ Tables.TableCepage.key_id+
                " WHERE "+ Tables.TableWineCepage.idWine +" = "+idWine+
                " ORDER BY "+ Tables.TableCepage.nameCepage;

        Cursor cursor = db.rawQuery(MY_QUERY, null);
        List<Cepage> res = new ArrayList<Cepage>();
        while(cursor.moveToNext()){
            Cepage c = new Cepage();
            c.set_id(cursor.getInt(cursor.getColumnIndex(Tables.TableCepage.key_id)));
            c.setNom(cursor.getString(cursor.getColumnIndex(Tables.TableCepage.nameCepage)));
            res.add(c);
        }
        return res;
    }
    public List<Cepage> getCepages(){
        SQLiteDatabase db = this.getReadableDatabase();

        String table = Tables.TableCepage.TABLE_CEPAGE;
        String[] columns = {
                Tables.TableCepage.key_id,
                Tables.TableCepage.nameCepage
        };
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = Tables.TableCepage.nameCepage;
        String limit = null;

        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        List<Cepage> res = new ArrayList<Cepage>();
        while(cursor.moveToNext()){
            Cepage c = new Cepage();
            c.set_id(cursor.getInt(0));
            c.setNom(cursor.getString(1));
            res.add(c);
        }
        return res;
    }







    //-- Insert -- / -- Delete -- / -- Update -- / -- COMMAND --

    public void insertCommand(int idWine, int qte, int state){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Tables.TableCommand.idWine, idWine);
        values.put(Tables.TableCommand.qte, qte);
        values.put(Tables.TableCommand.state, state);
        db.insert(Tables.TableCommand.TABLE_COMMAND, null, values);
        db.close();
    }
    public List<Command> getCommands(){
        SQLiteDatabase db = this.getReadableDatabase();
        String table = Tables.TableCommand.TABLE_COMMAND;
        String[] columns = {
                Tables.TableCommand.key_id,
                Tables.TableCommand.idWine,
                qte,
                Tables.TableCommand.state

        };
        String selection = Tables.TableCommand.state+"=?";
        String[] selectionArgs = {"0"};
        String groupBy = null;
        String having = null;
        String orderBy = Tables.TableCommand.key_id;
        String limit = null;

        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        List<Command> res = new ArrayList<Command>();
        while(cursor.moveToNext()){
            Command c = new Command();
            c.set_id(cursor.getInt(0));
            c.setVin(getWine(cursor.getInt(1)));
            c.setQte(cursor.getInt(2));
            c.setState(cursor.getInt(3));
            res.add(c);
        }
        return res;
    }
    public void deleteCommand(int _id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM "+ Tables.TableCommand.TABLE_COMMAND+" WHERE "+ Tables.TableCommand.key_id+"="+_id;
        db.execSQL(sql);
    }
    public void receivedCommand(int _id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE "+ Tables.TableCommand.TABLE_COMMAND+" SET "+ Tables.TableCommand.state+"=1 WHERE "+ Tables.TableCommand.key_id+"="+_id;
        db.execSQL(sql);
        Command c = getCommand(_id);
        sql = "UPDATE "+ Tables.TableWine.TABLE_WINE+" SET "+Tables.TableWine.quantity+"="+Tables.TableWine.quantity+"+"+c.getQte()+" WHERE "+ Tables.TableWine.key_id+"="+c.getVin().get_id();
        Log.e("debug",sql);
        db.execSQL(sql);
    }
    public Command getCommand(int _id){
        SQLiteDatabase db = this.getReadableDatabase();

        String table = Tables.TableCommand.TABLE_COMMAND;
        String[] columns = {
                Tables.TableCommand.key_id,
                Tables.TableCommand.idWine,
                Tables.TableCommand.state,
                qte
        };
        String selection = Tables.TableCommand.key_id+"=?";
        String[] selectionArgs = {_id+""};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        cursor.moveToFirst();
        Command c = new Command();
        c.set_id(cursor.getInt(0));
        c.setVin(getWine(cursor.getInt(1)));
        c.setState(cursor.getInt(2));
        c.setQte(cursor.getInt(3));
        return c;
    }






    //-- Insert -- / -- Delete -- / -- Update -- / -- COUNTRY --

    public void insertCountry(String name, String initial){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Tables.TableCountry.nameCountry, name);
        values.put(initial, initial);


        db.insert(Tables.TableCountry.CREATE_TABLE_COUNTRY,null, values);
    }
    public List<Pays> getCountries(){
        SQLiteDatabase db = this.getReadableDatabase();

        String table = Tables.TableCountry.TABLE_COUNTRY;
        String[] columns = {
                Tables.TableCountry.key_id,
                Tables.TableCountry.nameCountry,
                Tables.TableCountry.initial
        };
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = Tables.TableCountry.nameCountry;
        String limit = null;

        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        List<Pays> res = new ArrayList<Pays>();
        while(cursor.moveToNext()){
            Pays p = new Pays();
            p.set_id(cursor.getInt(0));
            p.setNom(cursor.getString(1));
            p.setInitial(cursor.getString(2));
            res.add(p);
        }
        return res;
    }







    //-- Insert -- / -- Delete -- / -- Update -- / -- MOVEMENT --

    public void insertMovement(int vinId,int isIn,int qte){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Tables.TableMovemement.key_vin, vinId);
        values.put(Tables.TableMovemement.key_in, isIn);
        values.put(Tables.TableMovemement.quantity, qte);
        db.insert(Tables.TableMovemement.TABLE_MOVEMENT, null, values);
        db.close();
    }
    public List<Mouvement> getMovements(){
        SQLiteDatabase db = this.getReadableDatabase();

        String table = Tables.TableMovemement.TABLE_MOVEMENT;
        String[] columns = {
                Tables.TableMovemement.key_id,
                Tables.TableMovemement.key_vin,
                Tables.TableMovemement.key_in,
                Tables.TableMovemement.quantity
        };
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = Tables.TableMovemement.key_id+" DESC";
        String limit = null;

        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        List<Mouvement> res = new ArrayList<Mouvement>();
        while(cursor.moveToNext()){
            Mouvement m = new Mouvement();
            m.set_id(cursor.getInt(0));
            m.setVin(getWine(cursor.getInt(1)));
            m.setIn(true);
            if(cursor.getInt(2)!=1){
                m.setIn(false);
            }
            m.setQte(cursor.getInt(3));
            res.add(m);
        }
        return res;
    }
    public void clearMovements(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM "+ Tables.TableMovemement.TABLE_MOVEMENT;
        db.execSQL(sql);
    }







    //-- Insert -- / -- Delete -- / -- Update -- / -- REGION --

    public void insertRegion(int idCountry, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Tables.TableRegion.region, name);
        values.put(Tables.TableRegion.idCountry, idCountry);
        db.insert(Tables.TableRegion.TABLE_REGION, null, values);
        db.close();
    }
    public Region getRegion(int _id){
        SQLiteDatabase db = this.getReadableDatabase();
        String table = Tables.TableRegion.TABLE_REGION;
        String country = Tables.TableCountry.TABLE_COUNTRY;

        String MY_QUERY = "SELECT * FROM "+table+" a INNER JOIN "+country+" b ON a."+ idCountry+"=b."+ Tables.TableCountry.key_id+
                            " WHERE "+ Tables.TableRegion.key_id+" = "+_id+
                            " ORDER BY "+ Tables.TableRegion.region;

        Cursor c = db.rawQuery(MY_QUERY, null);
        c.moveToFirst();

        Region r = new Region();
        r.set_id(c.getInt(c.getColumnIndex(Tables.TableRegion.key_id)));
        r.setNom(c.getString(c.getColumnIndex(Tables.TableRegion.region)));

        Pays p = new Pays();
        p.set_id(c.getInt(c.getColumnIndex(Tables.TableCountry.key_id)));
        p.setNom(c.getString(c.getColumnIndex(nameCountry)));
        p.setInitial(c.getString(c.getColumnIndex(initial)));

        r.setPays(p);

        return r;
    }
    public List<Region> getRegions(){
        SQLiteDatabase db = this.getReadableDatabase();
        String table = Tables.TableRegion.TABLE_REGION;
        String country = Tables.TableCountry.TABLE_COUNTRY;

        String MY_QUERY = "SELECT * FROM "+table+" a INNER JOIN "+country+" b ON a."+ idCountry+"=b."+ Tables.TableCountry.key_id + " ORDER BY "+ Tables.TableCountry.nameCountry;

        Cursor c = db.rawQuery(MY_QUERY, null);
        List<Region> res = new ArrayList<Region>();
        while(c.moveToNext()){
            Region r = new Region();
            r.set_id(c.getInt(c.getColumnIndex(Tables.TableRegion.key_id)));
            r.setNom(c.getString(c.getColumnIndex(Tables.TableRegion.region)));

            Pays p = new Pays();
            p.set_id(c.getInt(c.getColumnIndex(Tables.TableCountry.key_id)));
            p.setNom(c.getString(c.getColumnIndex(nameCountry)));
            p.setInitial(c.getString(c.getColumnIndex(initial)));

            r.setPays(p);

            res.add(r);
        }
        return res;
    }






    //-- Insert -- / -- Delete -- / -- Update -- / -- WINE --

    public void insertWine(String imgPath,String name, String description,int years, int idColor, int idRegion, double price, int quantity, int idProvider, List<Cepage> cepages ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(imgPath, imgPath);
        values.put(Tables.TableWine.name, name);
        values.put(Tables.TableWine.description, description);
        values.put(Tables.TableWine.years, years);
        values.put(Tables.TableWine.color, idColor);
        values.put(Tables.TableWine.idRegion, idRegion);
        values.put(Tables.TableWine.quantity, quantity);
        values.put(Tables.TableWine.price, price);
        values.put(Tables.TableWine.idProvider, idProvider);
        db.insert(Tables.TableWine.TABLE_WINE, null, values);



        String sql = "SELECT MAX("+ Tables.TableWine.key_id+") id FROM "+ Tables.TableWine.TABLE_WINE;
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        int idWine = c.getInt(c.getColumnIndex("id"));

        for(Cepage cep : cepages){
            sql = "INSERT INTO "+ Tables.TableWineCepage.TABLE_NAME+" ("+
                    Tables.TableWineCepage.idWine+","+
                    Tables.TableWineCepage.idCepage+
                    ") VALUES ('"+
                    idWine+"','"+
                    cep.get_id()+
                    "')";
            db.execSQL(sql);
        }
        db.close();
    }
    public void updateWine(int _id,String imgpath,String name, String description,int years, int idColor, int idRegion, double price, int quantity, int idProvider, List<Cepage> cepages){
        SQLiteDatabase db = this.getWritableDatabase();
        String strFilter = Tables.TableWine.key_id+"=" + _id;
        ContentValues values = new ContentValues();
        values.put(Tables.TableWine.imgPath, imgpath);
        values.put(Tables.TableWine.name, name);
        values.put(Tables.TableWine.description, description);
        values.put(Tables.TableWine.years, years);
        values.put(Tables.TableWine.color, idColor);
        values.put(Tables.TableWine.idRegion, idRegion);
        values.put(Tables.TableWine.quantity, quantity);
        values.put(Tables.TableWine.price, price);
        values.put(Tables.TableWine.idProvider, idProvider);
        db.update(Tables.TableWine.TABLE_WINE, values, strFilter, null);

        String sql = "DELETE FROM "+ Tables.TableWineCepage.TABLE_NAME+" WHERE "+ Tables.TableWineCepage.idWine+"="+_id;
        db.execSQL(sql);

        for(Cepage cep : cepages) {
            sql = "INSERT INTO " + Tables.TableWineCepage.TABLE_NAME + " (" +
                    Tables.TableWineCepage.idWine + "," +
                    Tables.TableWineCepage.idCepage +
                    ") VALUES ('" +
                    _id + "','" +
                    cep.get_id() +
                    "')";
            db.execSQL(sql);
        }
    }
    public Vin getWine(int _id){
        SQLiteDatabase db = this.getReadableDatabase();
        String table = Tables.TableWine.TABLE_WINE;
        List<Couleur> colors = HomeActivity.colors;

        String MY_QUERY = "SELECT * FROM "+table+" WHERE "+ Tables.TableWine.key_id+"="+_id+" ORDER BY "+ Tables.TableWine.name;
        Cursor c = db.rawQuery(MY_QUERY, null);
        c.moveToFirst();
        Vin v = new Vin();
        v.set_id(c.getInt(c.getColumnIndex(Tables.TableWine.key_id)));
        v.setImg(c.getString(c.getColumnIndex(imgPath)));
        v.setName(c.getString(c.getColumnIndex(Tables.TableWine.name)));
        v.setAnnee(c.getInt(c.getColumnIndex(Tables.TableWine.years)));
        v.setDescription(c.getString(c.getColumnIndex(Tables.TableWine.description)));
        v.setCouleur(colors.get(c.getInt(c.getColumnIndex(Tables.TableWine.color))));

        Region r = getRegion(c.getInt(c.getColumnIndex(Tables.TableWine.idRegion)));
        v.setRegion(r);

        v.setQte(c.getInt(c.getColumnIndex(Tables.TableWine.quantity)));
        v.setPrix(c.getDouble(c.getColumnIndex(Tables.TableWine.price)));

        Provider p = getProvider(c.getInt(c.getColumnIndex(idProvider)));
        v.setProvider(p);

        v.setCepage(getCepagesFromWine(c.getInt(c.getColumnIndex(Tables.TableWine.key_id))));



        return v;
    }
    public SerializeList<Vin> getWines(){
        SQLiteDatabase db = this.getReadableDatabase();
        String table = Tables.TableWine.TABLE_WINE;
        List<Couleur> colors = HomeActivity.colors;

        String MY_QUERY = "SELECT * FROM "+table+" WHERE "+ Tables.TableWine.quantity +"> 0 ORDER BY "+ Tables.TableWine.name;
        Cursor c = db.rawQuery(MY_QUERY, null);
        SerializeList<Vin> res = new SerializeList<Vin>();
        while (c.moveToNext()){
            Vin v = new Vin();
            v.set_id(c.getInt(c.getColumnIndex(Tables.TableWine.key_id)));
            v.setImg(c.getString(c.getColumnIndex(imgPath)));
            v.setName(c.getString(c.getColumnIndex(Tables.TableWine.name)));
            v.setAnnee(c.getInt(c.getColumnIndex(Tables.TableWine.years)));
            v.setDescription(c.getString(c.getColumnIndex(Tables.TableWine.description)));
            v.setCouleur(colors.get(c.getInt(c.getColumnIndex(Tables.TableWine.color))));

            Region r = getRegion(c.getInt(c.getColumnIndex(Tables.TableWine.idRegion)));
            v.setRegion(r);

            v.setQte(c.getInt(c.getColumnIndex(Tables.TableWine.quantity)));
            v.setPrix(c.getDouble(c.getColumnIndex(Tables.TableWine.price)));

            Provider p = getProvider(c.getInt(c.getColumnIndex(idProvider)));
            v.setProvider(p);

            v.setCepage(getCepagesFromWine(c.getInt(c.getColumnIndex(Tables.TableWine.key_id))));

            res.add(v);
        }
        return res;
    }
    public SerializeList<Vin> getAllWines(){
        SQLiteDatabase db = this.getReadableDatabase();
        String table = Tables.TableWine.TABLE_WINE;
        List<Couleur> colors = HomeActivity.colors;

        String MY_QUERY = "SELECT * FROM "+table+" ORDER BY "+ Tables.TableWine.name;
        Cursor c = db.rawQuery(MY_QUERY, null);
        SerializeList<Vin> res = new SerializeList<Vin>();
        while (c.moveToNext()){
            Vin v = new Vin();
            v.set_id(c.getInt(c.getColumnIndex(Tables.TableWine.key_id)));
            //v.setImg(c.getString(c.getColumnIndex(Tables.TableWine.imgPath)));
            v.setName(c.getString(c.getColumnIndex(Tables.TableWine.name)));
            v.setAnnee(c.getInt(c.getColumnIndex(Tables.TableWine.years)));
            v.setDescription(c.getString(c.getColumnIndex(Tables.TableWine.description)));
            v.setCouleur(colors.get(c.getInt(c.getColumnIndex(Tables.TableWine.color))));

            Region r = getRegion(c.getInt(c.getColumnIndex(Tables.TableWine.idRegion)));
            v.setRegion(r);

            v.setQte(c.getInt(c.getColumnIndex(Tables.TableWine.quantity)));
            v.setPrix(c.getDouble(c.getColumnIndex(Tables.TableWine.price)));

            Provider p = getProvider(c.getInt(c.getColumnIndex(idProvider)));
            v.setProvider(p);

            v.setCepage(getCepagesFromWine(c.getInt(c.getColumnIndex(Tables.TableWine.key_id))));

            res.add(v);
        }
        return res;
    }
    public void getOutWine(int qte,int idWine){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "UPDATE "+ Tables.TableWine.TABLE_WINE+" SET "+Tables.TableWine.quantity+"="+Tables.TableWine.quantity+"-"+qte+" WHERE "+ Tables.TableWine.key_id+"="+idWine;
        db.execSQL(sql);
    }





}
