package android.bignerdranch.com.shopping;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class ItemsDB extends Observable {
    private List<Item> itemsDB;
//    private mItemsDB = new ArrayList<>();

    private static ItemsDB sItemsDB;
    public static synchronized ItemsDB get(Context context) { if (sItemsDB == null) {
        sItemsDB = new ItemsDB(context);
        sItemsDB.fillItemsDB();
    }
        return sItemsDB;
    }
    private ItemsDB(Context context) {
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
        this.setChanged();
        notifyObservers();
    }

    public synchronized void  addItem(Item item) {
        itemsDB.add(item);
        this.setChanged();
        notifyObservers();
    }

    public synchronized void deleteLastItem() {
        if (itemsDB.size()>0) {
            itemsDB.remove(itemsDB.size() - 1);
        this.setChanged();
        notifyObservers();
        }
    }

    public int getSize() {
        return itemsDB.size();
    }

    public synchronized List<Item> getItemsDB() {
        return itemsDB;
    }
}