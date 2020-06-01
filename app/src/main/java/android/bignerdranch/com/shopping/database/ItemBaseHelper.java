package android.bignerdranch.com.shopping.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemBaseHelper extends SQLiteOpenHelper {


    private static final int VERSION = 2;

    private static final String DATABASE_NAME = "itemBase.db";

    public ItemBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        SQLiteDatabase db=this.getWritableDatabase();
    }


    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ItemDbSchema.ItemsTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ItemDbSchema.ItemsTable.Cols.UUID + ", " +
                ItemDbSchema.ItemsTable.Cols.NAME + ", " +
                ItemDbSchema.ItemsTable.Cols.WHERE_TO +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // we use it in case we want to do some migrations

    }
}
