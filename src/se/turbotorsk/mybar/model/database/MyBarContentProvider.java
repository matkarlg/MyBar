/*
New BSD License
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se

Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the following conditions are met:
* Redistributions of source code must retain the above copyright notice,
  this list of conditions and the following disclaimer.
* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.
* Neither the name of the MyBar nor the names of its contributors may be 
  used to endorse or promote products derived from this software without
  specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY 
OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package se.turbotorsk.mybar.model.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * MyBarContentProvider gets a database object from MyBarDatabaseHelper, changes
 * the queries into database SQL language and executes the query.
 * 
 * @author Mathias Karlgren (<a
 *         href="mailto:mathias.karlgren@gmail.com">email</a>)
 */
public class MyBarContentProvider extends ContentProvider {
	public static final String AUTHORITY = "se.turbotorsk.mybar.model.database";
	public static final Uri CONTENTURI_DRINK = Uri.parse("content://"
			+ AUTHORITY + "/" + DrinkTable.TABLE_DRINK);
	public static final Uri CONTENTURI_INGREDIENT = Uri.parse("content://"
			+ AUTHORITY + "/" + IngredientTable.TABLE_INGREDIENT);
	public static final Uri CONTENTURI_MYBAR = Uri.parse("content://"
			+ AUTHORITY + "/" + MyBarTable.TABLE_MYBAR);

	private MyBarDatabaseHelper database;
	private static final int DRINK = 1;
	private static final int DRINK_ID = 2;
	private static final int INGREDIENT = 3;
	private static final int INGREDIENT_ID = 4;
	private static final int MYBAR = 5;
	private static final int MYBAR_ID = 6;
	// private static final String DEFAULT_SORT_ORDER = "_id" + " ASC";

	// Create the URI matcher
	private static final UriMatcher sUriMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
	static {
		// Sets the integer value for multiple rows in DrinkTable to DRINK.
		sUriMatcher.addURI(AUTHORITY, DrinkTable.TABLE_DRINK, DRINK);

		// Sets the code for a single row to DRINK_ID.
		sUriMatcher.addURI(AUTHORITY, DrinkTable.TABLE_DRINK + "/#", DRINK_ID);

		// Sets the integer value for multiple rows in IngredientTable to
		// INGREDIENT.
		sUriMatcher.addURI(AUTHORITY, IngredientTable.TABLE_INGREDIENT,
				INGREDIENT);

		// Sets the code for a single row to INGREDIENT_ID.
		sUriMatcher.addURI(AUTHORITY, IngredientTable.TABLE_INGREDIENT + "/#",
				INGREDIENT_ID);

		// Sets the integer value for multiple rows in MyBarTable to MYBAR.
		sUriMatcher.addURI(AUTHORITY, MyBarTable.TABLE_MYBAR, MYBAR);

		// Sets the code for a single row to MYBAR_ID.
		sUriMatcher.addURI(AUTHORITY, MyBarTable.TABLE_MYBAR + "/#", MYBAR_ID);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		// Get read/write permissions to the database
		SQLiteDatabase sqlDB = database.getWritableDatabase();

		int rowsAffected = 0;

		// Choose the table to query based on the code returned for the incoming
		// URI
		switch (sUriMatcher.match(uri)) {
		// If the incoming URI was for the whole table
		case DRINK:
			rowsAffected = sqlDB.delete(DrinkTable.TABLE_DRINK, selection,
					selectionArgs);
			break;

		// If the incoming URI was for a single row
		case DRINK_ID:
			// Add ID to query statement
			selection += DrinkTable.COLUMN_ID + "=" + uri.getLastPathSegment();

			// Add rows that should be updated
			// Example: SELECT * FROM drink WHERE name='Margarita' AND
			// glass='Whiskey Glass'
			selection += TextUtils.isEmpty(selection) ? "" : " AND ("
					+ selection + ")";

			// Call the code to actually do the query
			rowsAffected = sqlDB.delete(DrinkTable.TABLE_DRINK, selection,
					selectionArgs);
			break;

		case INGREDIENT:
			rowsAffected = sqlDB.delete(IngredientTable.TABLE_INGREDIENT,
					selection, selectionArgs);
			break;

		case INGREDIENT_ID:
			selection += IngredientTable.COLUMN_ID + "="
					+ uri.getLastPathSegment();
			selection += TextUtils.isEmpty(selection) ? "" : " AND ("
					+ selection + ")";
			rowsAffected = sqlDB.delete(IngredientTable.TABLE_INGREDIENT,
					selection, selectionArgs);
			break;

		case MYBAR:
			rowsAffected = sqlDB.delete(MyBarTable.TABLE_MYBAR, selection,
					selectionArgs);
			break;

		case MYBAR_ID:
			selection += MyBarTable.COLUMN_ID + "=" + uri.getLastPathSegment();
			selection += TextUtils.isEmpty(selection) ? "" : " AND ("
					+ selection + ")";
			rowsAffected = sqlDB.delete(MyBarTable.TABLE_MYBAR, selection,
					selectionArgs);
			break;
		default:
			// Error handling
			throw new IllegalArgumentException("IllegalArgumentException URI: "
					+ uri);
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

		// Choose the table to query based on the code returned for the incoming
		// URI
		switch (sUriMatcher.match(uri)) {
		// If the incoming URI was for the whole table
		case DRINK:
			rowAffected = sqlDB.insert(DrinkTable.TABLE_DRINK, null, values);
			break;

		// If the incoming URI was for the whole table
		case INGREDIENT:
			rowAffected = sqlDB.insert(IngredientTable.TABLE_INGREDIENT, null,
					values);
			break;

		// If the incoming URI was for the whole table
		case MYBAR:
			rowAffected = sqlDB.insert(MyBarTable.TABLE_MYBAR, null, values);
			break;
		default:
			// Error handling
			throw new IllegalArgumentException("IllegalArgumentException URI: "
					+ uri);
		}

		// Notify registered observers
		getContext().getContentResolver().notifyChange(uri, null);

		// Insert method should return the new rows _id value
		switch (sUriMatcher.match(uri)) {
		case DRINK:
			return Uri.parse(DrinkTable.TABLE_DRINK + "/" + rowAffected);
		case INGREDIENT:
			return Uri.parse(IngredientTable.TABLE_INGREDIENT + "/"
					+ rowAffected);
		case MYBAR:
			return Uri.parse(MyBarTable.TABLE_MYBAR + "/" + rowAffected);
		default:
			return Uri.parse("Unrecognized Table" + "/" + rowAffected);
		}
	}

	@Override
	public boolean onCreate() {
		database = new MyBarDatabaseHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		// Create convenience class to help with creation of our SQL queries
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		switch (sUriMatcher.match(uri)) {
		case DRINK:
		case DRINK_ID:
			queryBuilder.setTables(DrinkTable.TABLE_DRINK);
			break;
		case INGREDIENT:
		case INGREDIENT_ID:
			queryBuilder.setTables(IngredientTable.TABLE_INGREDIENT);
			break;
		case MYBAR:
		case MYBAR_ID:
			queryBuilder.setTables(MyBarTable.TABLE_MYBAR);
			break;
		}

		// Choose the table to query based on the code returned for the incoming
		// URI
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
			queryBuilder.appendWhere(DrinkTable.COLUMN_ID + "="
					+ uri.getLastPathSegment());
			break;

		case INGREDIENT:
			break;
		case INGREDIENT_ID:
			queryBuilder.appendWhere(IngredientTable.TABLE_INGREDIENT + "="
					+ uri.getLastPathSegment());
			break;

		case MYBAR:
			break;
		case MYBAR_ID:
			queryBuilder.appendWhere(MyBarTable.TABLE_MYBAR + "="
					+ uri.getLastPathSegment());
			break;
		default:
			// Error handling
			throw new IllegalArgumentException("IllegalArgumentException URI: "
					+ uri);
		}

		// Get read/write permissions to the database
		SQLiteDatabase sqlDB = database.getWritableDatabase();

		// Call the code to actually do the query
		Cursor cursor = queryBuilder.query(sqlDB, projection, selection,
				selectionArgs, null, null, sortOrder);

		// Tell the cursor which URI to watch over, to make sure it catches
		// changes in source data
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {

		// Get read/write permissions to the database
		SQLiteDatabase sqlDB = database.getWritableDatabase();

		int rowsAffected = 0;

		// Choose the table to query based on the code returned for the incoming
		// URI
		switch (sUriMatcher.match(uri)) {
		// If the incoming URI was for the whole table
		case DRINK:
			rowsAffected = sqlDB.update(DrinkTable.TABLE_DRINK, values,
					selection, selectionArgs);
			break;
		// If the incoming URI was for a single row
		case DRINK_ID:

			// Add ID to query statement
			selection += DrinkTable.COLUMN_ID + "=" + uri.getLastPathSegment();

			// Add rows that should be updated
			// Example: SELECT * FROM drink WHERE name='Margarita' AND
			// glass='Whiskey Glass'
			selection += TextUtils.isEmpty(selection) ? "" : " AND ("
					+ selection + ")";

			// Call the code to actually do the query
			rowsAffected = sqlDB.update(DrinkTable.TABLE_DRINK, values,
					selection, selectionArgs);
			break;

		case INGREDIENT:
			rowsAffected = sqlDB.update(IngredientTable.TABLE_INGREDIENT,
					values, selection, selectionArgs);
			break;
		case INGREDIENT_ID:
			selection += IngredientTable.COLUMN_ID + "="
					+ uri.getLastPathSegment();
			selection += TextUtils.isEmpty(selection) ? "" : " AND ("
					+ selection + ")";
			rowsAffected = sqlDB.update(IngredientTable.TABLE_INGREDIENT,
					values, selection, selectionArgs);
			break;

		case MYBAR:
			rowsAffected = sqlDB.update(MyBarTable.TABLE_MYBAR, values,
					selection, selectionArgs);
			break;
		case MYBAR_ID:
			selection += MyBarTable.COLUMN_ID + "=" + uri.getLastPathSegment();
			selection += TextUtils.isEmpty(selection) ? "" : " AND ("
					+ selection + ")";
			rowsAffected = sqlDB.update(MyBarTable.TABLE_MYBAR, values,
					selection, selectionArgs);
			break;

		default:
			// Error handling
			throw new IllegalArgumentException("IllegalArgumentException URI: "
					+ uri);
		}

		// Notify registered observers
		getContext().getContentResolver().notifyChange(uri, null);

		// The number of rows affected
		return rowsAffected;
	}

	// For JUNIT testing. Gets handle to the database.
	public MyBarDatabaseHelper getDatabaseHandle() {
		return database;
	}
}
