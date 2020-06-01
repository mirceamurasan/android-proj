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

    private List<Item> itemsDB;
    private static SQLiteDatabase mDatabase;
    private static ItemsDB sItemsDB;
    private Context mContext;


    public static synchronized ItemsDB get(Context context) { if (sItemsDB == null) {
        sItemsDB = new ItemsDB(context);
        sItemsDB.fillItemsDB();
    }
        return sItemsDB;
    }
    private ItemsDB(Context context) {

        mContext = context.getApplicationContext();
        mDatabase = new ItemBaseHelper(mContext)
                .getWritableDatabase();
        itemsDB= new ArrayList<>();
    }

    public synchronized String listItems() {
        String r= "";
        for (int i= 0; i<itemsDB.size(); i++) {
            r= r+"\n Buy "+itemsDB.get(i).toString();
        }
        return r;
    }
    public void fillItemsDB() {
        itemsDB.add(new Item("coffee", "Irma"));
        itemsDB.add(new Item("carrots", "Netto"));
        itemsDB.add(new Item("milk", "Netto"));
        itemsDB.add(new Item("bread", "bakery"));
        itemsDB.add(new Item("butter", "Irma"));

        addItem(new Item("coffee", "Irma"));
        addItem(new Item("carrots", "Netto"));
        addItem(new Item("bread", "bakery"));

        this.setChanged();
        notifyObservers();
    }

    public synchronized void  addItem(Item item) {
//        itemsDB.add(item);
        ContentValues values = getContentValues(item);
        mDatabase.insert(ItemDbSchema.ItemsTable.NAME, null, values);

        this.setChanged();
        notifyObservers();
    }

    public synchronized void deleteLastItem() {

//        mDatabase.delete(...)



//        if (itemsDB.size()>0) {
//            itemsDB.remove(itemsDB.size() - 1);
//        this.setChanged();
//        notifyObservers();
//        }
    }

//    we need item.getId().toString()
    public synchronized void deleteItemFromDb(String id) {
        mDatabase.delete(ItemDbSchema.ItemsTable.NAME, ItemDbSchema.ItemsTable.Cols.UUID + " = ?",
                new String[] { id });
        this.setChanged();
        notifyObservers();
    }


    public int getSize() {
        return itemsDB.size();
    }

    public synchronized List<Item> getItemsDB() {
        return itemsDB;
    }

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

    public synchronized ArrayList<Item> getItems() {
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