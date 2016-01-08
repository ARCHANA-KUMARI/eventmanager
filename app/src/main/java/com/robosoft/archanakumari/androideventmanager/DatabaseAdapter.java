package com.robosoft.archanakumari.androideventmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by archanakumari on 27/12/15.
 */
public class DatabaseAdapter {

    public Helper helper;
    public DatabaseAdapter(Context context){

        helper = new Helper(context);
    }

    //INSERT DATA INTO DATABASE

    public long  insertData(String eventname ,String eventvenue,String participantlist,String date,String time){
        SQLiteDatabase sqLiteDatabase  = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Helper.EVENT_NAME,eventname);
        contentValues.put(Helper.VENUE,eventvenue);
        contentValues.put(Helper.PARTICIPANTLIST,participantlist);
        contentValues.put(Helper.EVENT_DATE,date);
        contentValues.put(Helper.EVENT_TIME, time);
        long id = sqLiteDatabase.insert(Helper.TABLE_NAME,null,contentValues);
        return id;
    }
    //Get all data from database
    public Cursor  getAllData(SQLiteDatabase sqLiteDatabase){

        //StringBuffer stringBuffer = new StringBuffer();
        //Select UID,NAME and PASSWORD
        String columns[] = {Helper.UID,Helper.EVENT_NAME,Helper.VENUE,Helper.PARTICIPANTLIST,Helper.EVENT_DATE,Helper.EVENT_TIME};
     //   SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        Cursor cursor =  sqLiteDatabase.query(Helper.TABLE_NAME, columns, null, null, null, null, null);
       /* while(cursor.moveToNext()){

            int id = cursor.getInt(0);
            String eventname = cursor.getString(1);
            String eventvenue = cursor.getString(2);
            String participantlist = cursor.getString(3);
            String eventdate = cursor.getString(4);
            String eventtime = cursor.getString(5);

            stringBuffer.append(id+" "+ eventname+" "+eventvenue+" "+participantlist+" "+eventdate+" "+eventtime+"\n");
        }
        return stringBuffer.toString();*/
         return  cursor;
    }
   public int deleteRow(int id,SQLiteDatabase sqLiteDatabase){

        //Delete * from TABLE_NAME where NAME = "archana"
       // SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
     //   int id = dataProvider.getmId();
      //  String whereArgs = ((Strubg) id);
        //String whereArgs[] = {"archana"};

        int noOfRows = sqLiteDatabase.delete(Helper.TABLE_NAME, Helper.UID + "="+id,null);

        return noOfRows;


    }
    /*
      StringBuffer stringBuffer = new StringBuffer();
      //Select UID,NAME and PASSWORD
      String columns[] = {Helper.UID,Helper.NAME,Helper.PASSWORD};

      Cursor cursor =  sqLiteDatabase.query(Helper.TABLE_NAME, columns, null, null, null, null, null);

    return cursor;
     */
    static public class Helper  extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "databasename";
        private static final String TABLE_NAME = "Employee";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String EVENT_NAME = "Name";
        private static final String VENUE = "Venue";
        private static final String PARTICIPANTLIST = "Participantlist";
        private static final String EVENT_DATE = "Date";
        private static final String EVENT_TIME = "Time";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + EVENT_NAME + " VARCHAR(255)," +  VENUE + " VARCHAR(255),"+ PARTICIPANTLIST +" VARCHAR(255),"+EVENT_DATE+" VARCHAR(255),"+ EVENT_TIME+" VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        Context context;


        public Helper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Message.message(context, "Constructor is called");

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            Message.message(context, "onCreate is called");
            try {
                db.execSQL(CREATE_TABLE);
            } catch (SQLException exception) {
                Message.message(context, "" + exception);
                exception.printStackTrace();
            }


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Message.message(context, "onUpgrade is called");
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException e) {
                Message.message(context, "" + e);
            }
        }
    }
}
