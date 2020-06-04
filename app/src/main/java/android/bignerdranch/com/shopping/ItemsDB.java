package android.bignerdranch.com.shopping;

import android.bignerdranch.com.shopping.database.ItemBaseHelper;
import android.bignerdranch.com.shopping.database.ItemCursorWrapper;
import android.bignerdranch.com.shopping.database.ItemDbSchema;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class ItemsDB extends Observable {

//    private List<Item> itemsDB;
    private static SQLiteDatabase mDatabase;
    private static ItemsDB sItemsDB;
    private Context mContext;


    public static ItemsDB get(Context context) { if (sItemsDB == null) {
        sItemsDB = new ItemsDB(context);
        if (sItemsDB.getItems().isEmpty()) {
            sItemsDB.fillItemsDB();

        }
//        sItemsDB.fillItemsDB();
    }
        return sItemsDB;
    }
    private ItemsDB(Context context) {

        mContext = context.getApplicationContext();
        mDatabase = new ItemBaseHelper(mContext)
                .getWritableDatabase();
    }

    public void fillItemsDB() {

        addItem(new Item("coffee", "Irma"));
        addItem(new Item("carrots", "Netto"));
        addItem(new Item("bread", "bakery"));
        addItem(new Item("butter", "Irma"));
        addItem(new Item("milk", "Netto"));
        addItem(new Item("pretzel", "bakery"));
        addItem(new Item("beer", "Netto"));
        addItem(new Item("soda", "Irma"));



        this.setChanged();
        notifyObservers();
    }

    public void  addItem(Item item) {
//        itemsDB.add(item);
        ContentValues values = getContentValues(item);
        mDatabase.insert(ItemDbSchema.ItemsTable.NAME, null, values);

        this.setChanged();
        notifyObservers();
    }



//    we need item.getId().toString()
    public void deleteItemFromDb(String id) {
        mDatabase.delete(ItemDbSchema.ItemsTable.NAME, ItemDbSchema.ItemsTable.Cols.UUID + " = ?",
                new String[] { id });
        this.setChanged();
        notifyObservers();
    }


//    public int getSize() {
//        return itemsDB.size();
//    }

//    public List<Item> getItemsDB() {
//        return itemsDB;
//    }

    private static ContentValues getContentValues(Item item) {
        ContentValues values = new ContentValues();
        values.put(ItemDbSchema.ItemsTable.Cols.UUID, item.getId().toString());  // error debug!!!
        values.put(ItemDbSchema.ItemsTable.Cols.NAME, item.getWhat());
        values.put(ItemDbSchema.ItemsTable.Cols.WHERE_TO, item.getWhere());
        return values;
    }

    private ItemCursorWrapper queryItems(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
//                CrimeTable.NAME,
//                null, // columns - null selects all columns
//                whereClause,
//                whereArgs,
//                null, // groupBy
//                null, // having
//                null // orderBy
                ItemDbSchema.ItemsTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );

        return new ItemCursorWrapper(cursor);
    }

    public ArrayList<Item> getItemsByShop(String param) {
        ArrayList<Item> items= new ArrayList<>();
        ItemCursorWrapper cursor = queryItems(
                ItemDbSchema.ItemsTable.Cols.WHERE_TO + " = ?",
                new String[] { param } // here should be param
        );

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            items.add(cursor.getItem());
            cursor.moveToNext();
        }
        cursor.close();
        return items;

//        return null;
    }

    public ArrayList<Item> getItems() {
        ArrayList<Item> items= new ArrayList<>();
        ItemCursorWrapper cursor= queryItems(null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            items.add(cursor.getItem());
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }


//    public Crime getCrime(UUID id) {
//        return null;
//        CrimeCursorWrapper cursor = queryCrimes(
//                CrimeTable.Cols.UUID + " = ?",
//                new String[] { id.toString() }
//        );
//        try {
//            if (cursor.getCount() == 0) {
//                return null;
//            }
//            cursor.moveToFirst();
//            return cursor.getCrime();
//        } finally {
//            cursor.close();
//        }
//    }
}