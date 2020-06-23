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
        Cursor cursor = database.rawQuery("SELECT * FROM Food", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Food food = new Food();
            food.setIdFood(cursor.getInt(0));
            food.setTitle(cursor.getString(1));
            food.setCategory(cursor.getString(2));
            food.setDescription(cursor.getString(3));
            food.setThumbnail(cursor.getString(4));
            food.setAddress(cursor.getString(5));
            food.setProvince(cursor.getString(6));
            food.setTypeStore(cursor.getString(7));
            food.setPrice(cursor.getString(8));
            food.setLatiTude(cursor.getDouble(9));
            food.setLongiTude(cursor.getDouble(10));

            list.add(food);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<Food> getListSearchFood(String pro){
        this.database = openHelper.getReadableDatabase();
        List<Food> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Food WHERE province = ?", new String[]{pro});
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Food food = new Food();
            food.setIdFood(cursor.getInt(0));
            food.setTitle(cursor.getString(1));
            food.setCategory(cursor.getString(2));
            food.setDescription(cursor.getString(3));
            food.setThumbnail(cursor.getString(4));
            food.setAddress(cursor.getString(5));
            food.setProvince(cursor.getString(6));
            food.setTypeStore(cursor.getString(7));
            food.setPrice(cursor.getString(8));
            food.setLatiTude(cursor.getDouble(9));
            food.setLongiTude(cursor.getDouble(10));

            list.add(food);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<MenuGroup> getListMenuGroup(int id){
        this.database = openHelper.getReadableDatabase();
        List<MenuGroup> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT MenuGroup.idFood AS idGroup,namegroup,image,Food.idFood,MenuGroup.idMenuGroup AS idFoods  FROM MenuGroup INNER JOIN Food ON MenuGroup.idFood = Food.idFood AND Food.idFood = ?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            MenuGroup menuGroup = new MenuGroup();
            menuGroup.setNameGroup(cursor.getString(1));
            menuGroup.setImage(cursor.getString(2));
            menuGroup.setIdFood(cursor.getInt(3));
            menuGroup.setIdMenuGroup(cursor.getInt(4));

            list.add(menuGroup);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public  List<Menu> getListMenuItem(int id){
        this.database = openHelper.getReadableDatabase();
        List<Menu> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT nameitem,MenuItem.price,MenuItem.idMenuItem AS idMenuItems,MenuGroup.idMenuGroup AS idMenuGroups FROM MenuItem INNER JOIN MenuGroup ON MenuItem.idMenuGroup = MenuGroup.idMenuGroup AND MenuGroup.idMenuGroup = ?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Menu menu = new Menu();
            menu.setNameItem(cursor.getString(0));
            menu.setPrice(cursor.getString(1));

            list.add(menu);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
