package sg.edu.rp.c346.appthree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.os.Build.ID;
import static android.provider.Contacts.SettingsColumns.KEY;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String TAG = "DatabaseHelper";


    private static final String DATABASENAME = "wedding.db" ;
    private static final String TABLE_NAME = "weddTask";
    private static final String COL1 = "ID";
    private static final String COL2 = "task";

    private static final String TABLE_NAME2 = "weddGuestlist";
    private static final String COL01 = "ID";
    private static final String COL02 = "guestname";

    private static final String TABLE_NAME3 = "weddDuties";
    private static final String COLOne = "ID";
    private static final String COLTwo = "name";
    private static final String COLThree = "duties";


    public DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " task)");

        db.execSQL("create table " + TABLE_NAME2 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " name TEXT)");

        db.execSQL("create table " + TABLE_NAME3 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " name TEXT, duties TEXT)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);

    }

    public boolean addTaskData(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, task);



        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean addGuestData(String guestname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL02, guestname);



        long result = db.insert(TABLE_NAME2, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean addDutyData(String name, String duty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLTwo, name);
        contentValues.put(COLThree, duty);



        long result = db.insert(TABLE_NAME3, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getTaskData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;


    }

    public Cursor getGuestData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME2;
        Cursor data = db.rawQuery(query, null);
        return data;


    }

    public Cursor getDutyData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME3;
        Cursor data = db.rawQuery(query, null);
        return data;


    }







    /**
     * Returns only the ID that matches the name passed in
     * @param task
     * @return
     */
    public Cursor getTaskID(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + task + "'";
        Cursor data = db.rawQuery(query, null);


        return data;
    }


    public Cursor getGuestID(String guest){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL01 + " FROM " + TABLE_NAME2 +
                " WHERE " + COL02 + " = '" + guest + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getDutyID(String duty){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLOne + " FROM " + TABLE_NAME3 +
                " WHERE " + COLTwo + " = '" + duty + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }



    /**
     * Updates the name field
     * @param newTask
     * @param id
     * @param oldTask
     */
    public void updateTask(String newTask, int id, String oldTask){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newTask + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldTask + "'";
        Log.d(TAG, "updateTask: query: " + query);
        Log.d(TAG, "updateTask: Setting task to " + newTask);
        db.execSQL(query);
    }

    public void updateGuestName(String newGuest, int id, String oldGuest){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + TABLE_NAME2 + " SET " + COL02 +
                " = '" + newGuest + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldGuest + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting task to " + newGuest);
        db.execSQL(query);
    }

    public void updateDuty(String newDuty, int id, String oldDuty){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + TABLE_NAME3 + " SET " + COLTwo +
                " = '" + newDuty + "' WHERE " + COLOne + " = '" + id + "'" +
                " AND " + COLTwo + " = '" + oldDuty + "'";
        Log.d(TAG, "updateDuty: query: " + query);
        Log.d(TAG, "updateDuty: Setting duty to " + newDuty);
        db.execSQL(query);
    }


    /**
     * Delete from database
     * @param id
     * @param task
     */
    public void deleteName(int id, String task) {
        SQLiteDatabase db = this.getWritableDatabase();


        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + task + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + task + " from database.");
        db.execSQL(query);
    }

    public void deleteGuestName(int id, String guestname) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME2 + " WHERE "
                + COL01 + " = '" + id + "'" +
                " AND " + COL02 + " = '" + guestname + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + guestname + " from database.");
        db.execSQL(query);
    }

    public void deleteDuty(int id, String duty) {
    SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME3 + " WHERE "
                + COLOne + " = '" + id + "'" +
                " AND " + COLTwo + " = '" + duty + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + duty + " from database.");
        db.execSQL(query);



    }

}
