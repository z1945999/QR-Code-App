package edu.niu.android.qrcodereader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "recordDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_RECORD = "qrList";
    private static final String ID = "id";
    private static final String FORMAT = "format";
    private static final String CONTENT = "content";

    public DatabaseManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }//Default constructor of the database.

    public void onCreate(SQLiteDatabase db)
    {
        String sqlCreate = "create table " + TABLE_RECORD + "(" + ID;
        sqlCreate += " integer primary key autoincrement, " + FORMAT;
        sqlCreate += " TEXT, " + CONTENT + " TEXT)";

        db.execSQL(sqlCreate);//Creates the SQL table
    }//Create the table with the variables needed for the ID, Format, and the content of the scanned code

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_RECORD);
        onCreate(db);
    }//Delete the table and it's data when changes are made to the table (Columns, version)

    public ArrayList<Codes> selectAll()
    {
        String sqlQuery = "Select * from " + TABLE_RECORD;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Codes> codes = new ArrayList<>();

        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
            String format = cursor.getString(cursor.getColumnIndexOrThrow(FORMAT));
            String content = cursor.getString(cursor.getColumnIndexOrThrow(CONTENT));
            Codes currentCode = new Codes(id, format, content);

            codes.add(currentCode);
        }//This loops goes over all the data in the database and extracts the id, format and content
        //then adds the data to an object of the Code class. This loops until there is no data
        //to set.
        cursor.close();
        db.close();
        //It is useful to close the use of the database and cursor in order to prevent data leaks.
        return codes;
    }


    public void insert(Codes codes)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(FORMAT, codes.getFormat());
        content.put(CONTENT, codes.getContent());
        db.insert(TABLE_RECORD, null, content);
        db.close();
    }//Place the data into the DB table. Closes the table to prevent data leaks.


}
