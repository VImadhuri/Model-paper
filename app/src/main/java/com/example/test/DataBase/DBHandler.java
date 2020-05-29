package com.example.test.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="FeedReader.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES=
            "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " (" +
                    UserProfile.Users._ID + " INTEGER PRIMARY KEY," +
                    UserProfile.Users.COLUMN_NAME_1 + " TEXT," +
                    UserProfile.Users.COLUMN_NAME_2 + " TEXT," +
                    UserProfile.Users.COLUMN_NAME_3 + " TEXT," +
                    UserProfile.Users.COLUMN_NAME_4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES=
            "DROP TABLE IF EXISTS " + UserProfile.Users.TABLE_NAME;

    public long addInfo(String userName, String dateOfBirth, String Password, String Gender) {
        // Gets the data repository in write mode
        SQLiteDatabase db=getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values=new ContentValues();
        values.put(UserProfile.Users.COLUMN_NAME_1, userName);
        values.put(UserProfile.Users.COLUMN_NAME_2, dateOfBirth);
        values.put(UserProfile.Users.COLUMN_NAME_3, Password);
        values.put(UserProfile.Users.COLUMN_NAME_4, Gender);

// Insert the new row, returning the primary key value of the new row
        long newRowId=db.insert(UserProfile.Users.TABLE_NAME, null, values);

        return newRowId;
    }

    public boolean updateInfor(String userName, String dateOfBirth, String Password, String Gender) {

        SQLiteDatabase db=getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(UserProfile.Users.COLUMN_NAME_2, dateOfBirth);
        values.put(UserProfile.Users.COLUMN_NAME_3, Password);
        values.put(UserProfile.Users.COLUMN_NAME_4, Gender);


        String selection=UserProfile.Users.COLUMN_NAME_1 + " LIKE ?";
        String[] selectionArgs={"userName"};

        int count=db.update(
                UserProfile.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        if (count >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteInfo(String s) {
        SQLiteDatabase db=getReadableDatabase();

        // Define 'where' part of query.
        String selection=UserProfile.Users.COLUMN_NAME_1 + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs={"MyTitle"};
// Issue SQL statement.
        int deletedRows=db.delete(UserProfile.Users.TABLE_NAME, selection, selectionArgs);
    }

    public List readAllInfor() {
        String UserName="Maduri";
        SQLiteDatabase db=getReadableDatabase();

        String[] projection={
                BaseColumns._ID,
                UserProfile.Users.COLUMN_NAME_1,
                UserProfile.Users.COLUMN_NAME_2,
                UserProfile.Users.COLUMN_NAME_3,
                UserProfile.Users.COLUMN_NAME_4
        };

        // Filter results WHERE "title" = 'My Title'
        String selection=UserProfile.Users.COLUMN_NAME_1 + " = ?";
        String[] selectionArgs={UserName};

        // How you want the results sorted in the resulting Cursor
        String sortOrder=UserProfile.Users.COLUMN_NAME_1 + " ASC";

        Cursor cursor=db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List userName=new ArrayList<>();
        while (cursor.moveToNext()) {
            String user=String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_1)));
            userName.add(user);
        }
        cursor.close();
        return userName;
    }


        public List readAllInfo(String userName) {
            String UserName="Maduri";
            SQLiteDatabase db=getReadableDatabase();

            String[] projection={
                    BaseColumns._ID,
                    UserProfile.Users.COLUMN_NAME_1,
                    UserProfile.Users.COLUMN_NAME_2,
                    UserProfile.Users.COLUMN_NAME_3,
                    UserProfile.Users.COLUMN_NAME_4
            };

            // Filter results WHERE "title" = 'My Title'
            String selection=UserProfile.Users.COLUMN_NAME_1 + " = LIKE ?";
            String[] selectionArgs={UserName};

            // How you want the results sorted in the resulting Cursor
            String sortOrder=UserProfile.Users.COLUMN_NAME_1 + " ASC";

            Cursor cursor=db.query(
                    UserProfile.Users.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );
            List userInfo=new ArrayList<>();
            while (cursor.moveToNext()) {
                String user=String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_1)));
                String dateofBirthday=String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_2)));
                String password=String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_3)));
                String gender=String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_4)));
                userInfo.add(userName);//0
                userInfo.add(dateofBirthday);//1
                userInfo.add(password);//2
                userInfo.add(gender);//3
            }
            cursor.close();
            return userInfo;
        }
    }
