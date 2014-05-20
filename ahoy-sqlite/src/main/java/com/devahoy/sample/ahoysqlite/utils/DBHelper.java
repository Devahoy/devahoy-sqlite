package com.devahoy.sample.ahoysqlite.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.devahoy.sample.ahoysqlite.model.Friend;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();

    //Database
    private static final String DATABASE_NAME = "devahoy_friends.db";
    private static final int DATABASE_VERSION = 1;

    //Table & Column
    public static final String TABLE_NAME = "friend";
    public static final String COL_ID = "_id";
    public static final String COL_FIRSTNAME = "first_name";
    public static final String COL_LASTNAME = "last_name";
    public static final String COL_TEL = "tel";
    public static final String COL_EMAIL = "email";
    public static final String COL_DESCRIPTION = "description";

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_FRIEND_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FIRSTNAME + " TEXT, " +
                COL_LASTNAME + " TEXT, " +
                COL_TEL + " TEXT, " +
                COL_EMAIL + " TEXT, " +
                COL_DESCRIPTION + " TEXT )";

        Log.i(TAG, CREATE_FRIEND_TABLE);

        // create friend table
        db.execSQL(CREATE_FRIEND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        db.execSQL(DROP_FRIEND_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);
    }



    //CRUD ( CREATE, READ, UPDATE, DELETE )

    //CREATE
    public void addFriend(Friend friend) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
//        values.put(COL_ID, friend.getId());
        values.put(COL_FIRSTNAME, friend.getFirstName());
        values.put(COL_LASTNAME, friend.getLastName());
        values.put(COL_TEL, friend.getTel());
        values.put(COL_EMAIL, friend.getEmail());
        values.put(COL_DESCRIPTION, friend.getDescription());

        sqLiteDatabase.insert(TABLE_NAME, null, values);

        sqLiteDatabase.close();
    }

    //READ
    public Friend getFriend(String id) {

        sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query( TABLE_NAME,
                null,
                COL_ID + " = ? ",
                new String[] { id },
                null,
                null,
                null,
                null);


        if (cursor != null) {
            cursor.moveToFirst();
        }

        Friend friend = new Friend();

        friend.setId((int) cursor.getLong(0));
        friend.setFirstName(cursor.getString(1));
        friend.setLastName(cursor.getString(2));
        friend.setTel(cursor.getString(3));
        friend.setEmail(cursor.getString(4));
        friend.setDescription(cursor.getString(5));

        return friend;
    }
    public List<String> getFriendList() {
        String QUERY_ALL_FRIEND = "SELECT * FROM " + TABLE_NAME;

        List<String> friends = new ArrayList<String>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(QUERY_ALL_FRIEND, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while(!cursor.isAfterLast()) {

            friends.add(cursor.getLong(0) + " " +
                    cursor.getString(1) + " " +
                    cursor.getString(2));

            cursor.moveToNext();
        }

        sqLiteDatabase.close();

        return friends;
    }

    /**
     * Call when need List<Friend> instead of List<String>
     * @return List of friends.
     */
    public List<Friend> getAllFriend() {

        String QUERY_ALL_FRIEND = "SELECT * FROM " + TABLE_NAME;

        List<Friend> friends = new ArrayList<Friend>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(QUERY_ALL_FRIEND, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {

            Friend friend = new Friend();
            friend.setId((int) cursor.getLong(0));
            friend.setFirstName(cursor.getString(1));
            friend.setLastName(cursor.getString(2));
            friend.setEmail(cursor.getString(3));
            friend.setDescription(cursor.getString(4));

            friends.add(friend);

            cursor.moveToNext();
        }

        sqLiteDatabase.close();


        return friends;
    }


    //UPDATE
    public int updateFriend(Friend friend) {

        sqLiteDatabase  = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_ID, friend.getId());
        values.put(COL_FIRSTNAME, friend.getFirstName());
        values.put(COL_LASTNAME, friend.getLastName());
        values.put(COL_EMAIL, friend.getEmail());
        values.put(COL_DESCRIPTION, friend.getDescription());

        int row = sqLiteDatabase.update(TABLE_NAME,
                values,
                COL_ID + " = ? ",
                new String[] { String.valueOf(friend.getId()) });

        sqLiteDatabase.close();

        return row;
    }

    //DELETE
    public void deleteFriend(String id) {

        sqLiteDatabase = this.getWritableDatabase();

/*        sqLiteDatabase.delete(TABLE_NAME, COL_ID + " = ? ",
                new String[] { String.valueOf(friend.getId()) });*/
        sqLiteDatabase.delete(TABLE_NAME, COL_ID + " = " + id, null);

        sqLiteDatabase.close();
    }
}
