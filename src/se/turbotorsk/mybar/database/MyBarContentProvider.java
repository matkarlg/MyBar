package se.turbotorsk.mybar.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * MyBar's ContentProvider. For increased startup speed the
 * getWriteableDatabase() method is not called in the onCreate() method.
 * The getDatabase methods calls the DatabaseHelper's create and upgrade
 * methods, which in turn creates the database tables.
 * 
 * Done: single row and multiple row support
 * TODO: Add sortOrder support, extensive JUNIT testing!, more javadoc and comments.
 * 
 * TODO: Ta bort? Min kod. Some comments may be similar to Android
 * Documentation about ContentProviders.
 * http://developer.android.com/guide/topics/providers/content-provider
 * -creating.html, which is under the Apache 2.0 License
 * 
 * @author Karlgren
 * 
 */
public class MyBarContentProvider extends ContentProvider {
	private MyBarDatabaseHelper database;
	public static final String AUTHORITY = "se.turbotorsk.mybar.database";
	private static final int DRINK = 1;
	private static final int DRINK_ID = 2;
	// private static final String DEFAULT_SORT_ORDER = "_ID" + " DESC";
	
    // Create the URI matcher
	private static final UriMatcher sUriMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
	static {
		//Sets the integer value for multiple rows in DrinkTable to DRINK.
		sUriMatcher.addURI(AUTHORITY, DrinkTable.TABLE_DRINK, DRINK);

		//Sets the code for a single row to DRINK_ID.
		sUriMatcher.addURI(AUTHORITY, DrinkTable.TABLE_DRINK + "/#", DRINK_ID);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		// Get read/write permissions to the database
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		
		int rowsAffected = 0;
		
		// Choose the table to query based on the code returned for the incoming URI
		switch (sUriMatcher.match(uri)) {
			// If the incoming URI was for the whole table
			case DRINK:
				
				rowsAffected = sqlDB.delete(DrinkTable.TABLE_DRINK, selection, selectionArgs);
				
				break;
			// If the incoming URI was for a single row
			case DRINK_ID:
				
				// Add ID to query statement
				selection += DrinkTable.COLUMN_ID + "=" + uri.getLastPathSegment();
				
				// Add rows that should be updated
				// Example: SELECT * FROM drink WHERE name='margarita' AND glass='Whiskey Glass'
				selection += TextUtils.isEmpty(selection) ? "" : " AND (" + selection + ")";
				
				// Call the code to actually do the query
				rowsAffected = sqlDB.delete(DrinkTable.TABLE_DRINK, selection, selectionArgs);
				
				break;
		default:
			// Error handling
			throw new IllegalArgumentException("IllegalArgumentException URI: " + uri);
		}
		
		// Notify registered observers
		getContext().getContentResolver().notifyChange(uri, null);
		
		// The number of rows affected
		return rowsAffected;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		// Get read/write permissions to the database
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		
		long rowAffected = 0;
		
		// Choose the table to query based on the code returned for the incoming URI
		switch (sUriMatcher.match(uri)) {
			// If the incoming URI was for the whole table
			case DRINK:
				
				rowAffected = sqlDB.insert(DrinkTable.TABLE_DRINK, null, values);
				
				break;
		default:
			// Error handling
			throw new IllegalArgumentException("IllegalArgumentException URI: " + uri);
		}
		
		// Notify registered observers
		getContext().getContentResolver().notifyChange(uri, null);
		
		// Insert method should return the new rows _id value
		return Uri.parse(DrinkTable.TABLE_DRINK + "/" + rowAffected);
	}
	
	@Override
	public boolean onCreate() {
		database = new MyBarDatabaseHelper(getContext());
		return false;
	}
	
   /**
    * This method is called when a client calls
    * {@link android.content.ContentResolver#query(Uri, String[], String, String[], String)}.
    * Queries the database and returns a cursor containing the results.
    *
    * @return A cursor containing the results of the query. The cursor exists but is empty if
    * the query returns no results or an exception occurs.
    * @throws IllegalArgumentException if the incoming URI pattern is invalid.
    */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		// Create convenience class to help with creation of our SQL queries
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(DrinkTable.TABLE_DRINK);

		// Choose the table to query based on the code returned for the incoming URI
		switch (sUriMatcher.match(uri)) {
			// If the incoming URI was for the whole table
			case DRINK:
	
				break;
			// If the incoming URI was for a single row
			case DRINK_ID:
				/*
				 * Alternative without queryBuilder: selection = selection +
				 * "_ID = " + uri.getLastPathSegment();
				 */
				
				// Adding the ID to the original query
				queryBuilder.appendWhere(DrinkTable.COLUMN_ID + "=" + uri.getLastPathSegment());
				break;
		default:
			// Error handling
			throw new IllegalArgumentException("IllegalArgumentException URI: " + uri);
		}

		// Get read/write permissions to the database
		SQLiteDatabase sqlDB = database.getWritableDatabase();

		// Call the code to actually do the query
		Cursor cursor = queryBuilder.query(sqlDB, projection, selection,
				selectionArgs, null, null, sortOrder);

		// Tell the cursor which URI to watch over, to make sure it catches changes in source data
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}
	
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		
		// Get read/write permissions to the database
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		
		int rowsAffected = 0;
		
		// Choose the table to query based on the code returned for the incoming URI
		switch (sUriMatcher.match(uri)) {
			// If the incoming URI was for the whole table
			case DRINK:
				
				rowsAffected = sqlDB.update(DrinkTable.TABLE_DRINK, values, selection, selectionArgs);
				
				break;
			// If the incoming URI was for a single row
			case DRINK_ID:
				
				// Add ID to query statement
				selection += DrinkTable.COLUMN_ID + "=" + uri.getLastPathSegment();
				
				// Add rows that should be updated
				// Example: SELECT * FROM drink WHERE name='margarita' AND glass='Whiskey Glass'
				selection += TextUtils.isEmpty(selection) ? "" : " AND (" + selection + ")";
				
				// Call the code to actually do the query
				rowsAffected = sqlDB.update(DrinkTable.TABLE_DRINK, values, selection, selectionArgs);
				
				break;
		default:
			// Error handling
			throw new IllegalArgumentException("IllegalArgumentException URI: " + uri);
		}
		
		// Notify registered observers
		getContext().getContentResolver().notifyChange(uri, null);
		
		// The number of rows affected
		return rowsAffected;
	}
	
	// For JUNIT testing. A test package can call this to get a handle to the database.
    MyBarDatabaseHelper getOpenHelperForTest() {
        return database;
    }
}
