package se.turbotorsk.mybar.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite helper object to manage our database. Uses DrinkTable.java (and in the future maybe more)
 * as a template for every table that should be in the database.
 * Uses onUpgrade() to update the database if a newer version of the database is out.
 * (a software update may change the DATABASE_VERSION to a higher number calling onUpgrade())
 * Uses onCreate() to create the database first time the application runs.
 * 
 * @author Karlgren
 * 
 */
public class MyBarDatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "turbotorsk_mybar.db";
	private static final int DATABASE_VERSION = 1;

	public MyBarDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Method is called during creation of the database
	@Override
	public void onCreate(SQLiteDatabase database) {
		// Populate the database with tables using our templates
		DrinkTable.onCreate(database);
	}

	// Method is called during an upgrade of the database,
	// e.g. if you increase the database version
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		// Upgrade our database with the new table templates
		DrinkTable.onUpgrade(database, oldVersion, newVersion);
	}
}
