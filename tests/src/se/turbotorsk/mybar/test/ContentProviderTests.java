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

package se.turbotorsk.mybar.test;

import se.turbotorsk.mybar.model.Drink;
import se.turbotorsk.mybar.model.database.DrinkTable;
import se.turbotorsk.mybar.model.database.MyBarContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

/**
 * Tests MyBar's ContentProvider.
 * 
 * @author Mathias Karlgren (<a
 *         href="mailto:mathias.karlgren@gmail.com">email</a>)
 */
public class ContentProviderTests extends ProviderTestCase2<MyBarContentProvider> {

    // Framework for testing Content Providers.
    public ContentProviderTests() {
        super(MyBarContentProvider.class, "se.turbotorsk.mybar.model.database");
    }

    // Define duplicate literals.
    private static final String DEFAULT_URL = "http://repro.mybar.turbotorsk.se/img/no_img.png";
    private static final String DEFAULT_INGREDIENTS = "ingredients here";
    private static final int DEFAULT_RATING = 5;
    private static final int DEFAULT_FAVORITE = 0;

    // Reference to the mocked ContentResolver for MyBarContentProvider.
    private MockContentResolver mMockResolver;

    // Database used for testing
    private SQLiteDatabase testDB;

    // Contains the test data, as an array of Drinks.
    private final Drink[] testDrinks = {
            new Drink(1, "Margarita", DEFAULT_URL, "Martini Glass", DEFAULT_INGREDIENTS,
                    "Margarita instructions", DEFAULT_RATING, DEFAULT_FAVORITE),
            new Drink(2, "Tequila", DEFAULT_URL, "Shot Glass", DEFAULT_INGREDIENTS,
                    "Pour Tequila in shot glass", DEFAULT_RATING, DEFAULT_FAVORITE),
            new Drink(3, "Cosmopolitan", DEFAULT_URL, "Martini Glass", DEFAULT_INGREDIENTS,
                    "Cosmopolitan instructions", DEFAULT_RATING, DEFAULT_FAVORITE),
            new Drink(4, "Cuba Libre", DEFAULT_URL, "Highball Glass", DEFAULT_INGREDIENTS,
                    "Cuba Libre instructions", DEFAULT_RATING, DEFAULT_FAVORITE),
            new Drink(5, "Martini", DEFAULT_URL, "Martini Glass", DEFAULT_INGREDIENTS,
                    "Pour Martini in glass", DEFAULT_RATING, DEFAULT_FAVORITE),
            new Drink(6, "Irish Coffee", DEFAULT_URL, "Coffee Glass", DEFAULT_INGREDIENTS,
                    "Irish Coffee instructions", DEFAULT_RATING, DEFAULT_FAVORITE) };

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Gets the resolver.
        mMockResolver = getMockContentResolver();
        // Gets the database.
        testDB = getProvider().getDatabaseHandle().getWritableDatabase();
    }

    private void insertTestData() {
        // Sets up test data
        for (int index = 0; index < testDrinks.length; index++) {
            testDB.insertOrThrow(DrinkTable.TABLE_DRINK, null, testDrinks[index].getContentValues());
        }
    }

    public void testDeletes() {
        final String selection = DrinkTable.COLUMN_NAME + " = ? ";
        final String[] selectionArgs = { "Tequila" };

        // Delete Test 1. Delete an empty record.
        int rowsDeleted = mMockResolver.delete(MyBarContentProvider.CONTENTURI_DRINK, selection,
                selectionArgs);
        assertEquals(0, rowsDeleted);

        // Delete Test 2. Delete an existing record.
        insertTestData();
        rowsDeleted = mMockResolver.delete(MyBarContentProvider.CONTENTURI_DRINK, selection,
                selectionArgs);

        // The number of deleted rows should be 1.
        assertEquals(1, rowsDeleted);

        // Delete Test 3. Check if row was actually deleted.
        assertEquals(
                0,
                mMockResolver.query(MyBarContentProvider.CONTENTURI_DRINK, null, selection,
                        selectionArgs, null).getCount());
    }

    public void testInserts() {
        // Create a new Drink.
        final Drink testOneDrink = new Drink(1, "Vodka", DEFAULT_URL, "Shot Glass",
                DEFAULT_INGREDIENTS, "Pour Vodka in shot glass", DEFAULT_RATING, DEFAULT_FAVORITE);

        // Insert Test 1. Inserts one Drink into DrinkTable.
        Uri rowUri = null;
        try {
            rowUri = mMockResolver.insert(MyBarContentProvider.CONTENTURI_DRINK,
                    testOneDrink.getContentValues());
        } catch (Exception e) {
            fail("Inserting one row failed.");
        }

        // Gets the URI _id of the single Drink.
        long drinkID = ContentUris.parseId(rowUri);

        // Insert Test 2. Should contain only one Drink.
        Cursor cursor = mMockResolver.query(MyBarContentProvider.CONTENTURI_DRINK, null, null,
                null, null);
        assertEquals(1, cursor.getCount());

        // Insert Test 3. Moves to the first (and only) record.
        assertTrue(cursor.moveToFirst());

        // Insert Test 4. Compare data returned with data inserted and check if
        // it's correct.
        // Get the _id value from test 1 URI to get the correct _id even if
        // autoincrement is on.
        assertEquals((int) drinkID, cursor.getInt(cursor.getColumnIndex(DrinkTable.COLUMN_ID)));
        assertEquals(testOneDrink.getName(),
                cursor.getString(cursor.getColumnIndex(DrinkTable.COLUMN_NAME)));
        assertEquals(testOneDrink.getUrl(),
                cursor.getString(cursor.getColumnIndex(DrinkTable.COLUMN_URL)));
        assertEquals(testOneDrink.getGlass(),
                cursor.getString(cursor.getColumnIndex(DrinkTable.COLUMN_GLASS)));
        assertEquals(testOneDrink.getIngredient(),
                cursor.getString(cursor.getColumnIndex(DrinkTable.COLUMN_INGREDIENT)));
        assertEquals(testOneDrink.getDescription(),
                cursor.getString(cursor.getColumnIndex(DrinkTable.COLUMN_DESCRIPTION)));
        assertEquals(testOneDrink.getRating(),
                cursor.getInt(cursor.getColumnIndex(DrinkTable.COLUMN_RATING)));
        assertEquals(testOneDrink.getFavorite(),
                cursor.getInt(cursor.getColumnIndex(DrinkTable.COLUMN_FAVORITE)));

        // Insert Test 5. Insert a record whose _id value already exists.
        // Get the _id value from test 1 URI to get the correct _id even if
        // autoincrement is on.
        ContentValues values = testOneDrink.getContentValues();
        values.put(DrinkTable.COLUMN_ID, (int) drinkID);

        // Test if you can overwrite existing row.
        try {
            rowUri = mMockResolver.insert(MyBarContentProvider.CONTENTURI_DRINK, values);
            fail("Insert Test 5 Failed. Able to overwrite existing record.");
        } catch (Exception e) {
            // Insert couldn't overwrite existing record.
        }
    }

    public void testUpdates() {
        final String selection = DrinkTable.COLUMN_NAME + " = ? ";
        final String[] selectionArgs = { "Cuba Libre" };
        ContentValues values = new ContentValues();

        // Update Test 1. Update empty table.
        values.put(DrinkTable.COLUMN_NAME, "Test new name");
        int rowsUpdated = mMockResolver.update(MyBarContentProvider.CONTENTURI_DRINK, values,
                selection, selectionArgs);

        // The number of updated rows should be 0.
        assertEquals(0, rowsUpdated);

        // Update Test 2. Same test as Test 1 with testData in the table.
        insertTestData();
        rowsUpdated = mMockResolver.update(MyBarContentProvider.CONTENTURI_DRINK, values,
                selection, selectionArgs);

        // The number of updated rows should be 1.
        assertEquals(1, rowsUpdated);
    }
}
