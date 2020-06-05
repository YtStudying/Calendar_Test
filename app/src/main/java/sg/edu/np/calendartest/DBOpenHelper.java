package sg.edu.np.calendartest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String CREATE_EVENTS_TABLE = "create table " + DBStructure.DB_EVENT_TABLE_NAME
            + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +DBStructure.DB_EVENT+" TEXT, "
            + DBStructure.DB_TIME+" TEXT, " + DBStructure.DB_DATE+" TEXT, "
            + DBStructure.DB_MONTH+" TEXT, " + DBStructure.DB_YEAR+" TEXT)";

    private static final String  DROP_EVENTS_TABLE= "DROP TABLE IF EXISTS " + DBStructure.DB_EVENT_TABLE_NAME;

    public DBOpenHelper(@Nullable Context context) {
        super(context, DBStructure.DB_NAME, null, DBStructure.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_EVENTS_TABLE);
        onCreate(db);

    }

    public void SaveEvent(String event,String time,String date,String month,String year,SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.DB_EVENT,event);
        contentValues.put(DBStructure.DB_TIME,time);
        contentValues.put(DBStructure.DB_DATE,date);
        contentValues.put(DBStructure.DB_MONTH,month);
        contentValues.put(DBStructure.DB_YEAR,year);
        database.insert(DBStructure.DB_EVENT_TABLE_NAME,null,contentValues);

    }

    public Cursor ReadEvents(String date,SQLiteDatabase database){
        String [] Projections = {DBStructure.DB_EVENT,DBStructure.DB_TIME,DBStructure.DB_DATE,DBStructure.DB_MONTH,DBStructure.DB_YEAR};
        String Selection = DBStructure.DB_DATE +"=?";
        String [] SelectionArgs = {date};

        return database.query(DBStructure.DB_EVENT_TABLE_NAME,Projections,Selection,SelectionArgs,null,null,null);
    }

    public Cursor ReadEventsperMonth(String month,String year,SQLiteDatabase database){
        String [] Projections = {DBStructure.DB_EVENT,DBStructure.DB_TIME,DBStructure.DB_DATE,DBStructure.DB_MONTH,DBStructure.DB_YEAR};
        String Selection = DBStructure.DB_MONTH +"=? and "+DBStructure.DB_YEAR+"=?";
        String [] SelectionArgs = {month,year};
        return database.query(DBStructure.DB_EVENT_TABLE_NAME,Projections,Selection,SelectionArgs,null,null,null);
    }

    public void deleteEvent(String event, String date, String time, SQLiteDatabase database){
        String Selection = DBStructure.DB_EVENT +"=? and "+DBStructure.DB_DATE+"=? and "+DBStructure.DB_TIME+"=?";
        String [] SelectionArgs = {event, date, time};
        database.delete(DBStructure.DB_EVENT_TABLE_NAME, Selection, SelectionArgs);
    }

}
