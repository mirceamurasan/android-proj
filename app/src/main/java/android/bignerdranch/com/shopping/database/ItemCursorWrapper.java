package android.bignerdranch.com.shopping.database;

import android.bignerdranch.com.shopping.Item;
import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

public class ItemCursorWrapper extends CursorWrapper {
    public ItemCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    public Item getItem() {
        String uuidString = getString(getColumnIndex(ItemDbSchema.ItemsTable.Cols.UUID));
        String what = getString(getColumnIndex(ItemDbSchema.ItemsTable.Cols.NAME));
        String where = getString(getColumnIndex(ItemDbSchema.ItemsTable.Cols.WHERE_TO));

//        return null;
        // cred ca trebuie sa ii setez cumva si un uuid! ca nu se generaeaza singur
//        Item item = new Item(what, where);
        Item item = new Item(UUID.fromString(uuidString));
        item.setWhat(what);
        item.setWhere(where);

        return item;
    }

}
