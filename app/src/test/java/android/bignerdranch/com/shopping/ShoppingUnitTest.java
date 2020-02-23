package android.bignerdranch.com.shopping;

import org.junit.Test;
import android.content.Context;

import static org.junit.Assert.*;


public class ShoppingUnitTest {
    public ItemsDB itemsDB;
    Item t;

    @Test
    public void CheckTest(){
        assertEquals("aa","aa");
        System.out.println("test working");
    }

    @Test
    public void ItemsDBtest(){

        t = new Item("aa", "bb");
        assertEquals(t.getWhat(),"aa");
        assertEquals(t.getWhere(),"bb");
        t.setWhat("aaa"); t.setWhere("bbb");
        assertEquals(t.getWhat(),"aaa");
        assertEquals(t.getWhere(),"bbb");
        assertEquals(t.toString(),"aaa in: bbb");

        Context c = null;
        itemsDB = ItemsDB.get(c);

        assertEquals(itemsDB.getSize(), 5);
        Item k = new Item("x", "y");
        itemsDB.addItem(k);

        assertEquals(itemsDB.getSize(), 6);
    }
}