package android.bignerdranch.com.shopping;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ItemsDB {
    private List<Item> ItemsDB;

    private static ItemsDB sItemsDB;
    public static ItemsDB get(Context context) { if (sItemsDB == null) {
        sItemsDB = new ItemsDB(context);
        sItemsDB.fillItemsDB();
    }
        return sItemsDB;
    }
    private ItemsDB(Context context) {
        ItemsDB= new ArrayList<>();
    }

    public String listItems() {
        String r= "";
        for (int i= 0; i<ItemsDB.size(); i++) {
            r= r+"\n Buy "+ItemsDB.get(i).toString();
        }
        return r;
    }
    public void fillItemsDB() {
        ItemsDB.add(new Item("coffee", "Irma"));
        ItemsDB.add(new Item("carrots", "Netto"));
        ItemsDB.add(new Item("milk", "Netto"));
        ItemsDB.add(new Item("bread", "bakery"));
        ItemsDB.add(new Item("butter", "Irma"));
    }

    public void addItem(Item item) {
        ItemsDB.add(item);
    }

    public void deleteLastItem() {
        if (ItemsDB.size()>0) {
            ItemsDB.remove(ItemsDB.size() - 1);
        }
    }
}