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
import ch.hevs.design.LoadingActivity;
import ch.hevs.design.components.SerializeList;
import ch.hevs.design.data.Cepage;
import ch.hevs.design.data.Command;
import ch.hevs.design.data.Couleur;
import ch.hevs.design.data.Mouvement;
import ch.hevs.design.data.Pays;
import ch.hevs.design.data.Provider;
import ch.hevs.design.data.Region;
import ch.hevs.design.data.Vin;
import ch.hevs.design.data.converter.CepageConverter;
import ch.hevs.design.data.converter.CommandConverter;
import ch.hevs.design.data.converter.MouvementConverter;
import ch.hevs.design.data.converter.PaysConverter;
import ch.hevs.design.data.converter.ProviderConverter;
import ch.hevs.design.data.converter.RegionConverter;
import ch.hevs.design.data.converter.VinConverter;
import ch.hevs.design.data.endPoint.CepageEndPoint;
import ch.hevs.design.data.endPoint.CommandEndPoint;
import ch.hevs.design.data.endPoint.MouvementEndPoint;
import ch.hevs.design.data.endPoint.PaysEndPoint;
import ch.hevs.design.data.endPoint.ProviderEndPoint;
import ch.hevs.design.data.endPoint.RegionEndPoint;
import ch.hevs.design.data.endPoint.VinEndPoint;

import static ch.hevs.design.data.DB.Tables.TableCommand.qte;
import static ch.hevs.design.data.DB.Tables.TableCountry.TABLE_COUNTRY;
import static ch.hevs.design.data.DB.Tables.TableCountry.initial;
import static ch.hevs.design.data.DB.Tables.TableCountry.nameCountry;
import static ch.hevs.design.data.DB.Tables.TableProvider.address;
import static ch.hevs.design.data.DB.Tables.TableProvider.email;
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

    public void SqlToCloud(LoadingActivity load) {
        SqlToCloudProvider(load);
        SqlToCloudCepages(load);
        SqlToCloudCommand(load);
        SqlToCloudCountry(load);
        SqlToCloudMouvement(load);
        SqlToCloudRegion(load);
        SqlToCloudVin(load);
    }






    //-- Insert -- / -- Delete -- / -- Update -- / -- PROVIDER --

    public void insertProvider (String namee, String surnamee, String addresss, String emaill){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Tables.TableProvider.nameProvider, namee);
        values.put(Tables.TableProvider.surnameProvider, surnamee);
        values.put(Tables.TableProvider.address, addresss);
        values.put(Tables.TableProvider.email, emaill);
        db.insert(Tables.TableProvider.TABLE_PROVIDER, null, values);
        db.close();
        SqlToCloudProvider();
    }
    public Provider getProvider(int _id){
        SQLiteDatabase db = this.getReadableDatabase();

        String table = Tables.TableProvider.TABLE_PROVIDER;
        String[] columns = {
                Tables.TableProvider.key_id,
                Tables.TableProvider.nameProvider,
                Tables.TableProvider.surnameProvider,
                email,
                address
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
                email,
                address
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

    public void SqlToCloudProvider(LoadingActivity load){
        List<Provider> ps = getProviders();
        for (Provider p: ps) {
            ch.hevs.design.backend.providerApi.model.Provider pTemp = ProviderConverter.LocalToCloud(p);
            new ProviderEndPoint(pTemp,load).execute();
        }
        Log.e("debugCloud","all data saved");
    }
    public void SqlToCloudProvider(){
        List<Provider> ps = getProviders();
        for (Provider p: ps) {
            ch.hevs.design.backend.providerApi.model.Provider pTemp = ProviderConverter.LocalToCloud(p);
            new ProviderEndPoint(pTemp).execute();
        }
        Log.e("debugCloud","all data saved");
    }
    public void CloudToSqlProvider(List<ch.hevs.design.backend.providerApi.model.Provider> items){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="DELETE FROM "+Tables.TableProvider.TABLE_PROVIDER;
        db.execSQL(sql);

        for (ch.hevs.design.backend.providerApi.model.Provider p : items) {
            Provider res = ProviderConverter.CloudToLocal(p);

            ContentValues values = new ContentValues();
            values.put(Tables.TableProvider.key_id, res.get_id());
            values.put(Tables.TableProvider.nameProvider, res.getName());
            values.put(Tables.TableProvider.surnameProvider, res.getSurname());
            values.put(Tables.TableProvider.address, res.getAdress());
            values.put(Tables.TableProvider.email, res.getEmail());
            db.insert(Tables.TableProvider.TABLE_PROVIDER, null, values);
        }
        db.close();
        Log.e("debugCloud","all data getted");
    }






    //-- Insert -- / -- Delete -- / -- Update -- / -- CEPAGE --

    public void insertCepage(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Tables.TableCepage.nameCepage, name);
        db.insert(Tables.TableCepage.TABLE_CEPAGE, null, values);
        db.close();
        SqlToCloudCepages();
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

    public void SqlToCloudCepages(LoadingActivity load){
        List<Cepage> ps = getCepages();
        for (Cepage c: ps) {
            ch.hevs.design.backend.cepageApi.model.Cepage pTemp = CepageConverter.LocalToCloud(c);
            new CepageEndPoint(pTemp,load).execute();
        }
    }
    public void SqlToCloudCepages(){
        List<Cepage> ps = getCepages();
        for (Cepage c: ps) {
            ch.hevs.design.backend.cepageApi.model.Cepage pTemp = CepageConverter.LocalToCloud(c);
            new CepageEndPoint(pTemp).execute();
        }
    }
    public void CloudToSqlCepages(List<ch.hevs.design.backend.cepageApi.model.Cepage> items){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="DELETE FROM "+Tables.TableCepage.TABLE_CEPAGE;
        db.execSQL(sql);

        for (ch.hevs.design.backend.cepageApi.model.Cepage c : items) {
            Cepage res = CepageConverter.CloudToLocal(c);

            ContentValues values = new ContentValues();
            values.put(Tables.TableCepage.key_id, res.get_id());
            values.put(Tables.TableCepage.nameCepage, res.getNom());
            db.insert(Tables.TableCepage.TABLE_CEPAGE, null, values);
        }
        db.close();
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
        SqlToCloudCommand();
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
        new CommandEndPoint(_id).execute();
    }
    public void receivedCommand(int _id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE "+ Tables.TableCommand.TABLE_COMMAND+" SET "+ Tables.TableCommand.state+"=1 WHERE "+ Tables.TableCommand.key_id+"="+_id;
        db.execSQL(sql);
        Command c = getCommand(_id);
        sql = "UPDATE "+ Tables.TableWine.TABLE_WINE+" SET "+Tables.TableWine.quantity+"="+Tables.TableWine.quantity+"+"+c.getQte()+" WHERE "+ Tables.TableWine.key_id+"="+c.getVin().get_id();
        Log.e("debug",sql);
        db.execSQL(sql);
        SqlToCloudCommand();
        SqlToCloudVin();
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

    public void SqlToCloudCommand(LoadingActivity load){
        List<Command> ps = getCommands();
        for (Command c: ps) {
            ch.hevs.design.backend.commandApi.model.Command pTemp = CommandConverter.LocalToCloud(c);
            new CommandEndPoint(pTemp,load).execute();
        }
    }
    public void SqlToCloudCommand(){
        List<Command> ps = getCommands();
        for (Command c: ps) {
            ch.hevs.design.backend.commandApi.model.Command pTemp = CommandConverter.LocalToCloud(c);
            new CommandEndPoint(pTemp).execute();
        }
    }
    public void CloudToSqlCommand(List<ch.hevs.design.backend.commandApi.model.Command> items){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="DELETE FROM "+Tables.TableCommand.TABLE_COMMAND;
        db.execSQL(sql);

        for (ch.hevs.design.backend.commandApi.model.Command c : items) {
            Command res = CommandConverter.CloudToLocal(c);

            ContentValues values = new ContentValues();
            values.put(Tables.TableCommand.key_id, res.get_id());
            values.put(Tables.TableCommand.qte, res.getQte());
            values.put(Tables.TableCommand.idWine, res.getVin().get_id());
            values.put(Tables.TableCommand.state, res.getState());
            db.insert(Tables.TableCommand.TABLE_COMMAND, null, values);
        }
        db.close();
    }






    //-- Insert -- / -- Delete -- / -- Update -- / -- COUNTRY --

    public void insertCountry(String name, String initial){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Tables.TableCountry.nameCountry, name);
        values.put(initial, initial);


        db.insert(Tables.TableCountry.CREATE_TABLE_COUNTRY,null, values);
        SqlToCloudCountry();
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
    public void SqlToCloudCountry(LoadingActivity load){
        List<Pays> ps = getCountries();
        for (Pays c: ps) {
            ch.hevs.design.backend.paysApi.model.Pays pTemp = PaysConverter.LocalToCloud(c);
            new PaysEndPoint(pTemp,load).execute();
        }
    }
    public void SqlToCloudCountry(){
        List<Pays> ps = getCountries();
        for (Pays c: ps) {
            ch.hevs.design.backend.paysApi.model.Pays pTemp = PaysConverter.LocalToCloud(c);
            new PaysEndPoint(pTemp).execute();
        }
    }
    public void CloudToSqlCountry(List<ch.hevs.design.backend.paysApi.model.Pays> items){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="DELETE FROM "+Tables.TableCountry.TABLE_COUNTRY;
        db.execSQL(sql);

        for (ch.hevs.design.backend.paysApi.model.Pays c : items) {
            Pays res = PaysConverter.CloudToLocal(c);

            ContentValues values = new ContentValues();
            values.put(Tables.TableCountry.key_id, res.get_id());
            values.put(Tables.TableCountry.initial, res.getInitial());
            values.put(Tables.TableCountry.nameCountry, res.getNom());
            db.insert(Tables.TableCountry.TABLE_COUNTRY, null, values);
        }
        db.close();
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
        SqlToCloudMouvement();
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
        List<Mouvement> mvts = getMovements();
        for (Mouvement m: mvts) {
            new MouvementEndPoint(m.get_id()).execute();
        }
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM "+ Tables.TableMovemement.TABLE_MOVEMENT;
        db.execSQL(sql);

    }
    public void SqlToCloudMouvement(LoadingActivity load){
        List<Mouvement> ps = getMovements();
        for (Mouvement c: ps) {
            ch.hevs.design.backend.mouvementApi.model.Mouvement pTemp = MouvementConverter.LocalToCloud(c);
            new MouvementEndPoint(pTemp,load).execute();
        }
    }
    public void SqlToCloudMouvement(){
        List<Mouvement> ps = getMovements();
        for (Mouvement c: ps) {
            ch.hevs.design.backend.mouvementApi.model.Mouvement pTemp = MouvementConverter.LocalToCloud(c);
            new MouvementEndPoint(pTemp).execute();
        }
    }
    public void CloudToSqlMouvement(List<ch.hevs.design.backend.mouvementApi.model.Mouvement> items){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="DELETE FROM "+Tables.TableMovemement.TABLE_MOVEMENT;
        db.execSQL(sql);

        for (ch.hevs.design.backend.mouvementApi.model.Mouvement c : items) {
            Mouvement res = MouvementConverter.CloudToLocal(c);

            ContentValues values = new ContentValues();
            values.put(Tables.TableMovemement.key_id, res.get_id());
            values.put(Tables.TableMovemement.key_in, res.isIn());
            values.put(Tables.TableMovemement.key_vin, res.getVin().get_id());
            values.put(Tables.TableMovemement.quantity, res.getQte());
            db.insert(Tables.TableMovemement.TABLE_MOVEMENT, null, values);
        }
        db.close();
    }







    //-- Insert -- / -- Delete -- / -- Update -- / -- REGION --

    public void insertRegion(int idCountry, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Tables.TableRegion.region, name);
        values.put(Tables.TableRegion.idCountry, idCountry);
        db.insert(Tables.TableRegion.TABLE_REGION, null, values);
        db.close();
        SqlToCloudRegion();
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
    public void SqlToCloudRegion(LoadingActivity load){
        List<Region> ps = getRegions();
        for (Region c: ps) {
            ch.hevs.design.backend.regionApi.model.Region pTemp = RegionConverter.LocalToCloud(c);
            new RegionEndPoint(pTemp,load).execute();
        }
    }
    public void SqlToCloudRegion(){
        List<Region> ps = getRegions();
        for (Region c: ps) {
            ch.hevs.design.backend.regionApi.model.Region pTemp = RegionConverter.LocalToCloud(c);
            new RegionEndPoint(pTemp).execute();
        }
    }
    public void CloudToSqlRegion(List<ch.hevs.design.backend.regionApi.model.Region> items){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="DELETE FROM "+Tables.TableRegion.TABLE_REGION;
        db.execSQL(sql);

        for (ch.hevs.design.backend.regionApi.model.Region c : items) {
            Region res = RegionConverter.CloudToLocal(c);

            ContentValues values = new ContentValues();
            values.put(Tables.TableRegion.key_id, res.get_id());
            values.put(Tables.TableRegion.idCountry, res.getPays().get_id());
            values.put(Tables.TableRegion.region, res.getNom());
            db.insert(Tables.TableRegion.TABLE_REGION, null, values);
        }
        db.close();
    }






    //-- Insert -- / -- Delete -- / -- Update -- / -- WINE --

    public void insertWine(String imgPaths,String name, String description,int years, int idColor, int idRegion, double price, int quantity, int idProvider, List<Cepage> cepages ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Tables.TableWine.imgPath, imgPaths);
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
        SqlToCloudVin();
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
        SqlToCloudVin();
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
            v.setImg(c.getString(c.getColumnIndex(Tables.TableWine.imgPath)));
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
        SqlToCloudVin();
    }

    public void SqlToCloudVin(LoadingActivity load){
        List<Vin> ps = getAllWines();
        for (Vin c: ps) {
            ch.hevs.design.backend.vinApi.model.Vin pTemp = VinConverter.LocalToCloud(c);
            new VinEndPoint(pTemp,load).execute();
        }
    }
    public void SqlToCloudVin(){
        List<Vin> ps = getAllWines();
        for (Vin c: ps) {
            ch.hevs.design.backend.vinApi.model.Vin pTemp = VinConverter.LocalToCloud(c);
            new VinEndPoint(pTemp).execute();
        }
    }
    public void CloudToSqlVin(List<ch.hevs.design.backend.vinApi.model.Vin> items){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="DELETE FROM "+Tables.TableWine.TABLE_WINE;
        db.execSQL(sql);

        for (ch.hevs.design.backend.vinApi.model.Vin c : items) {
            Vin res = VinConverter.CloudToLocal(c);

            ContentValues values = new ContentValues();
            values.put(Tables.TableWine.key_id, res.get_id());
            values.put(Tables.TableWine.idProvider, res.getProvider().get_id());
            values.put(Tables.TableWine.imgPath, res.getImg());
            values.put(Tables.TableWine.color, res.getCouleur().get_id());
            values.put(Tables.TableWine.description, res.getDescription());
            values.put(Tables.TableWine.idRegion, res.getRegion().get_id());
            values.put(Tables.TableWine.name, res.getName());
            values.put(Tables.TableWine.price, res.getPrix());
            values.put(Tables.TableWine.quantity, res.getQte());
            values.put(Tables.TableWine.years, res.getAnnee());
            db.insert(Tables.TableWine.TABLE_WINE, null, values);

            sql = "DELETE FROM "+ Tables.TableWineCepage.TABLE_NAME+" WHERE "+ Tables.TableWineCepage.idWine+"="+res.get_id();
            db.execSQL(sql);

            for(ch.hevs.design.backend.vinApi.model.Cepage cep : c.getCepage()) {
                Cepage cepageLocal = CepageConverter.CloudToLocal(cep);

                sql = "INSERT INTO " + Tables.TableWineCepage.TABLE_NAME + " (" +
                        Tables.TableWineCepage.idWine + "," +
                        Tables.TableWineCepage.idCepage +
                        ") VALUES ('" +
                        res.get_id() + "','" +
                        cepageLocal.get_id() +
                        "')";
                db.execSQL(sql);
            }

        }
        db.close();
    }





}
