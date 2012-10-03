

package se.turbotorsk.mybar.test;

/**
 * This class tests the MyBarContentProvider and SQLite
 * 
 * @author Mathias Karlgren (matkarlg)
 *
 */

import se.turbotorsk.mybar.controller.MyBarContentProvider;
import se.turbotorsk.mybar.model.Drink;
import android.database.sqlite.SQLiteDatabase;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

public class SQLiteTests extends ProviderTestCase2<MyBarContentProvider> {

    public SQLiteTests() {
		super(MyBarContentProvider.class, "se.turbotorsk.mybar.model.database");
	}

    private MockContentResolver mMockResolver;

    private SQLiteDatabase testDB;

    private final Drink[] testDrinks = {
    		new Drink(1, "Margarita", "/margarita.jpg", "Martini Glass", "Rom", "Mix rom and ice", 5), 
    		new Drink(2, "Tequila", "/tequila.jpg", "Shot Glass", "Tequila", "Pour Tequila", 5)
    };

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMockResolver = getMockContentResolver();
        testDB = getProvider().getDatabaseHandle().getWritableDatabase();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}