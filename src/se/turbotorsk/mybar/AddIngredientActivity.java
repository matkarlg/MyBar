/*
New BSD License
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se

Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the following conditions are met:
*	Redistributions of source code must retain the above copyright notice,
 	this list of conditions and the following disclaimer.
*	Redistributions in binary form must reproduce the above copyright notice,
 	this list of conditions and the following disclaimer in the documentation
 	and/or other materials provided with the distribution.
*	Neither the name of the MyBar nor the names of its contributors may be 
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

package se.turbotorsk.mybar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

/**
 * This activity handles the functions that add ingredients.
 */
public class AddIngredientActivity extends Activity {

	SearchView search;

	// Ingredient ingredient;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout); // Use the xml layout
												// search_layout

		/*
		 * Cursor cursor = MyBarApplication.ContentResolver().query(
		 * MyBarContentProvider.CONTENTURI_INGREDIENT, null, null, null, null);
		 * String[] columns = new String[] { ingredient.getName(),
		 * ingredient.getType(), ingredient.getAlcoholcontent() + ""}; int[] to
		 * = new int[] { R.id.drink, R.id.ingredients, R.id.rating };
		 */

		search = (SearchView) findViewById(R.id.searchView1);
		/*
		 * @SuppressWarnings("deprecation") SimpleCursorAdapter adapter = new
		 * SimpleCursorAdapter(this, R.layout.rowlayout, cursor, columns, to);
		 */

		search.setOnQueryTextListener(sListen);
		// search.setSuggestionsAdapter(adapter);

		/**
		 * This method handles what happens when pressing a item in the list.
		 */
		// setOnSuggestionListener(sListen);
	}

	final SearchView.OnQueryTextListener sListen = new SearchView.OnQueryTextListener() {
		public boolean onQueryTextSubmit(String query) {
			Toast.makeText(AddIngredientActivity.this, "Submitted search",
					Toast.LENGTH_SHORT).show();
			return false;
		}

		public boolean onQueryTextChange(String query) {
			if (query.length() >= 3) {
				Toast.makeText(AddIngredientActivity.this, "Wrote some stuff",
						Toast.LENGTH_SHORT).show();
			}
			return false;
		}
	};
}
