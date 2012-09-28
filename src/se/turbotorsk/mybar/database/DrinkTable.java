package se.turbotorsk.mybar.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * A table template. Creates the database table structure for the drinks
 * (mirrored in remote database).
 * 
 * @author Karlgren
 * 
 */
public class DrinkTable {
	// Database table template
	public static final String TABLE_DRINK = "drink";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_URL = "url";
	public static final String COLUMN_GLASS = "glass";
	public static final String COLUMN_INGREDIENT = "ingredient";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_RATING = "rating";
	
	// Database table creation SQL statement
	private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_DRINK
			+ "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_NAME + " TEXT NOT NULL, "
			+ COLUMN_URL + " TEXT NOT NULL, "
			+ COLUMN_GLASS + " TEXT NOT NULL, "
			+ COLUMN_INGREDIENT + " TEXT NOT NULL, "
			+ COLUMN_DESCRIPTION + " TEXT NOT NULL, "
			+ COLUMN_RATING + " INTEGER NOT NULL" + ");";
	
	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}
	
	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		// Print upgrade warning to LogCat
		Log.w(DrinkTable.class.getName(), "Upgrading " + TABLE_DRINK
				+ " table from version "+ oldVersion
				+" to " + newVersion	+ ", which will destroy all old data");
		
		// Kills the table and existing data
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_DRINK);
		
		// Recreates the database with a new version
		onCreate(database);
	}
}
