/*
New BSD License
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se
Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
�	Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
�	Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
�	Neither the name of the MyBar nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

/**
 * A drink object contains information about a drink.
 * 
 * @author Dag Frid�n
 * @version 0.1
 * 
 */

package se.turbotorsk.mybar.model;

import java.util.LinkedList;

import se.turbotorsk.mybar.model.database.DrinkTable;
import se.turbotorsk.mybar.model.database.MyBarContentProvider;
import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class Data extends Application {
	
	private final boolean SQLITE = true;
	private final boolean EDATA = false;
	private final boolean FAKE = false;
	private Drink exampleDrink1 = null, exampleDrink2 = null;
	private LinkedList<Ingredient> fakeIngredientList = null;
	private LinkedList<Drink> fakeDrinkList = null;
	private Ingredient fakeIngredient1 = null, fakeIngredient2 = null, fakeIngredient3 = null;
	//private XXXXXXXX sqlite;
	//private XXXXXXXX jsonParser;
	//private XXXXXXXX httpGet;
	
	/**
	 * How to query database
	 */
//	Data data = new Data(getContentResolver()); //Creates the model object.
//	
//	for (Drink drinks : data.getAllDrinks(getContentResolver())) {
//		Log.d(this.getClass().getName(), "" + drinks.get_id() + " " + drinks.getName());
//	}
	
    
	public Data(ContentResolver contentResolver)
	{
		if(SQLITE) {
			Uri myBarUri = null;
	        
			// SQLite uses autoincrement in the _id field
	        Drink[] testDrinks = {
	        		new Drink(0, "Margarita", "/margarita.jpg", "Martini Glass", "Rom", "Mix rom and ice", 5), 
	        		new Drink(0, "Tequila", "/tequila.jpg", "Shot Glass", "Tequila", "Pour Tequila in glass", 5)
	        };
	        
	        // Insert testDrinks
	        for (Drink testDrink : testDrinks) {
	        	ContentValues values = testDrink.getContentValues();
	        	myBarUri = contentResolver.insert(MyBarContentProvider.CONTENT_URI, values);
	        	Log.d(this.getClass().getName(), "Test insert. Created row: " + myBarUri.toString());
	        }
		}
		if(EDATA);
		if(FAKE){ //Creates "fake" data to be used when FAKE is true (for testing purpose).
			fakeDrinkList = new LinkedList<Drink>();
			fakeDrinkList.add(exampleDrink1 = new Drink(1, "1;2;2;1", "http://www.google.se", "test1","ingredient1", "description1",3));
			fakeDrinkList.add(exampleDrink2 = new Drink(2, "1;3;3;1", "http://www.google.se", "test2","ingredient2", "description2",2));
			fakeIngredientList= new LinkedList<Ingredient>();
			fakeIngredientList.add(fakeIngredient1 = new Ingredient(1, "Vodka", "http://www.google.se", 2, "Nice Vodka!"));
			fakeIngredientList.add(fakeIngredient2 = new Ingredient(2, "Dark Rom", "http://www.google.se", 2, "Nice Rom!"));
			fakeIngredientList.add(fakeIngredient3 = new Ingredient(3, "Lime", "http://www.google.se", 2, "Nice Lime!"));
		}
	}
	
	/**
	 * This method return all Drinks from the local SQLite database.
	 * @return Drink object.
	 */
	public LinkedList<Drink> getAllDrinks(ContentResolver contentResolver)
	{
		if(SQLITE) {
			
			/**
	         * SQLITE getAllDrinks()
	         */
			LinkedList<Drink> drinkList = new LinkedList<Drink>();
	        
		    // Choose which columns you want to query. null queries all columns
		    //String[] projection = { DrinkTable.COLUMN_NAME,	DrinkTable.COLUMN_DESCRIPTION, DrinkTable.COLUMN_RATING };
	        
		    // Query database
		    Cursor cursor = contentResolver.query(MyBarContentProvider.CONTENT_URI, null, null, null, null);
		    
		    // Successful query?
		    if (cursor != null) {
		    	
		    	// Is there any data from the requested Query
		    	if (cursor.moveToFirst()) {
		    		
			        do {
			            int _id = cursor.getInt(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_ID));
			            String name = cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_NAME));
			            String url = cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_URL));
			            String glass = cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_GLASS));
			            String ingredient = cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_INGREDIENT));
			            String description = cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_DESCRIPTION));
			            int rating = cursor.getInt(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_RATING));
			            drinkList.add(new Drink(_id, name, url, glass, ingredient, description, rating));
			        }while (cursor.moveToNext());
		    		
		    		return drinkList;
		    	}
		    	
		    	// Close the cursor
		    	cursor.close();
		    }
		    /**
	         * End of SQLite getAllDrinks()
	         */
		}
		if(EDATA);
		if(FAKE){return fakeDrinkList;}; 
		return null; 
	}
	
	public String[] getDrinkNameArray()
	{
		String[] drinks; 
		if(SQLITE);
		if(EDATA);
		if(FAKE){
			drinks = new String[20];
			drinks[0] ="Cola och tonic";
			drinks[1] ="Cola lime";
			drinks[2] ="Cola gin";
			drinks[3] ="Cola citrus";
			drinks[4] ="Cola hallon";
			drinks[5] ="Cola light";
			drinks[6] ="Cola sun";
			drinks[7] ="Cola looka";
			drinks[8] ="Cola with ice";
			drinks[9] ="Rom and cocke";
			
			return drinks;
		}
		return null; 
	}
		
	/**
	 * This method return a Drink (thumbnail, name, rating, description) 
	 * SQL Query: SELECT * WHERE _id = id
	 * @param ID
	 * @return
	 */
	public Drink getDrinkByID(int ID, ContentResolver contentResolver)
	{
		if(SQLITE){
		
		/**
         * SQLITE getDrinkByID()
         */
	    // Choose which columns you want to query. null queries all columns
	    //String[] projection = { DrinkTable.COLUMN_NAME,	DrinkTable.COLUMN_DESCRIPTION, DrinkTable.COLUMN_RATING };

	    // Query database
	    Cursor cursor = contentResolver.query(MyBarContentProvider.CONTENT_URI, null, DrinkTable.COLUMN_ID + "=" + ID, null, null);

	    // Successful query?
	    if (cursor != null) {

	    	// Is there any data from the requested Query
	    	if (cursor.moveToFirst()) {

	    		// Test Print
	    		//Log.d(this.getClass().getName(), cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_NAME)));
	    		//Log.d(this.getClass().getName(), cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_DESCRIPTION)));
	    		//Log.d(this.getClass().getName(), cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_RATING)));
	    		
	    		int _id = cursor.getInt(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_ID));
	    		String name = cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_NAME));
	    		String url = cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_URL));
	    		String glass = cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_GLASS));
	    		String ingredient = cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_INGREDIENT));
	    		String description = cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_DESCRIPTION));
	    		int rating = cursor.getInt(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_RATING));
	    		Drink drink = new Drink(_id, name, url, glass, ingredient, description, rating);
	    		return drink;
	    	}

	    	// Close the cursor
	    	cursor.close();
	    }
        /**
         * End of SQLite getDrinkByID()
         */
		}
		if(EDATA);
		if(FAKE){ return exampleDrink1;};
		
		return null;
	}
	
	/**
	 * Returns a LinkedList with the current users favorit drinks. 
	 * SQL Query: SELECT * from Favorites;
	 * @return
	 */
	public LinkedList<Drink> getFavoritDrinks()
	{
		if(SQLITE);
		if(EDATA);
		if(FAKE){ return fakeDrinkList; }
		return null; 
	}
	
	/**
	 * Search for Drinks in the database.
	 * 
	 * @param searchName
	 * @return A LinkedList with the Drink containing the searchName string
	 */
	public LinkedList<Drink> searchDrinkName(String searchName)
	{
		if(FAKE){ return fakeDrinkList; }
		return null;
	}
	/**
	 * Search for ingredients in the database.
	 * @param searchName
	 * @return A LinkedList with the ingredients containing the searchName string
	 */
	public LinkedList<Drink> searchIngredientName(String searchName)
	{
		if(FAKE) {return fakeDrinkList;};
		return null;
	}
	
	public LinkedList<Ingredient> getIngredientList()
	{
		if(FAKE){ return fakeIngredientList; }
		return null;
	}
}
