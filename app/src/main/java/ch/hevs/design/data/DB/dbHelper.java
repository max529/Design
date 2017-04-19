package ch.hevs.design.data.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



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
}
