package hcmute.edu.vn.foody_17;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;


    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the databases connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the databases connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    /**
     * Read all quotes from the databases.
     *
     * @return a List of quotes
     */
    public List<Food> getListFood() {
        this.database = openHelper.getReadableDatabase();
        List<Food> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM 'Food'", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Food food = new Food();
            food.setTitle(cursor.getString(1));
            food.setCategory(cursor.getString(2));
            food.setDescription(cursor.getString(3));
            food.setThumbnail(cursor.getString(4));
            food.setAddress(cursor.getString(5));
            food.setProvince(cursor.getString(6));
            food.setTypeStore(cursor.getString(7));
            food.setPrice(cursor.getString(8));
            food.setAccountWifi(cursor.getString(9));
            food.setPassWifi(cursor.getString(10));

            list.add(food);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
