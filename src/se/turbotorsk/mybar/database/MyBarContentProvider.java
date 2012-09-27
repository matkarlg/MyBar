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
 * Some code may be similar to the Android Documentation of ContentProviders (developer.android.com)
 * 
 * @author Karlgren
 *
 */
public class MyBarContentProvider extends ContentProvider{
	private MyBarDatabaseHelper database;
	public static final String AUTHORITY = "se.turbotorsk.mybar.database";
	private static final int DRINK = 1;
	private static final int DRINK_ID = 2;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
    	/*
	     * Sets the integer value for multiple rows in DrinkTable to DRINK. Notice that no wildcard is used
	     * in the path
	     */
	    sUriMatcher.addURI(AUTHORITY, "drink", DRINK);
	
	    /*
	     * Sets the code for a single row to DRINK_ID. In this case, the "#" wildcard is
	     * used. "content://se.turbotorsk.mybar.database/drink/1" matches, but
	     * "content://se.turbotorsk.mybar.database/drink doesn't.
	     */
	    sUriMatcher.addURI(AUTHORITY, "drink/#", DRINK_ID);
    }
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public boolean onCreate() {
		database = new MyBarDatabaseHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {		

		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(DrinkTable.TABLE_DRINK);
		
		/*
         * Choose the table to query and a sort order based on the code returned for the incoming
         * URI. Here, too, only the statements for table 3 are shown.
         */
		// Type of URI (sUriMatcher.match(uri))
        switch (sUriMatcher.match(uri)) {
            // If the incoming URI was for the whole table
            case DRINK:
            	
                if (TextUtils.isEmpty(sortOrder)) sortOrder = "_ID ASC";
                break;
                
            // If the incoming URI was for a single row
            case DRINK_ID:
            	
                /*
                 * Because this URI was for a single row, the _ID value part is
                 * present. Get the last path segment from the URI; this is the _ID value.
                 * Then, append the value to the WHERE clause for the query
                 */
                selection = selection + "_ID = " + uri.getLastPathSegment();
                // Adding the ID to the original query
                queryBuilder.appendWhere(DrinkTable.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
                
            default:
                // Error handling
            	throw new IllegalArgumentException("IllegalArgumentException URI: " + uri);
        }
        
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        
        // call the code to actually do the query
        
        Cursor cursor = queryBuilder.query(sqlDB, projection, selection,
            selectionArgs, null, null, sortOrder);
        
        // Make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        
        return cursor;
    }

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}
}
