package android.bignerdranch.com.shopping;

import java.util.UUID;

// todo: we should really add two more attributes: created_at and updated_at
public class Item {
    private String mWhat = null;
    private String mWhere = null;
    private UUID mId;


    public Item(String what, String where) {
        mWhat= what;
        mWhere= where;

        //it took me quite a while to realize this was missing!!!!
        mId = UUID.randomUUID();
    }

    public Item(UUID id) {
        mId = id;
    }

    public Item() {
        this(UUID.randomUUID());
    }

    public UUID getId() {
        return mId;
    }
/*    public Item(UUID id) {
        mId = id;
    }*/
    @Override
    public String toString() { return oneLine(""," in: "); }

    public String getWhat() { return mWhat; }

    public void setWhat(String what) { mWhat = what; }

    public String getWhere() { return mWhere; }

    public void setWhere(String where) { mWhere = where; }

    public String oneLine(String pre, String post) {
        return pre+mWhat + post + mWhere;
    }

}