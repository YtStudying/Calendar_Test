package sg.edu.np.calendartest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.FontsContract;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="registerUser";
    public static final String COl_1="ID";
    public static final String COl_2="username";
    public static final String COl_3="password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE registerUser (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("password", password);
        long res = db.insert("registerUser", null, contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String username, String password) {
        String[] columns = { COl_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COl_2 + "=?" + " and " + COl_3 + "=?";
        String[] selectionArg = { username, password};
        Cursor cursor =  db.query(TABLE_NAME,columns,selection,selectionArg,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;
    }
}
