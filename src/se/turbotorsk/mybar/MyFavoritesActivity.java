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

package se.turbotorsk.mybar;

import se.turbotorsk.mybar.controller.Controller;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * This activity handles the MyFavorites-section.
 */
public class MyFavoritesActivity extends ListActivity {
    // Create a new DrinkAdapter object to dynamically
    // populate list views.
    private DrinkAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create a new DrinkAdapter object to dynamically
        // populate list views.
        setAdapter(new DrinkAdapter(this, R.layout.rowlayout, Controller.getAllFavorites()));

        // Sets the adapter that we just created.
        setListAdapter(getAdapter());
    }

    /**
     * This method handles what happens when pressing a item in the list.
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // Create a new intent to send to the ViewDrikActivity class.
        Intent intent = new Intent(this, ViewDrinkActivity.class);
        // Add drinkname, rating, ingredients, description, url, and id
        // of the list item clicked to the intent.
        intent.putExtra("drinkname", getAdapter().getDrinkName(position));
        intent.putExtra("rating", getAdapter().getRating(position));
        intent.putExtra("ingredients", getAdapter().getIngredients(position));
        intent.putExtra("descrip", getAdapter().getDescrip(position));
        intent.putExtra("url", getAdapter().getUrl(position));
        intent.putExtra("id", getAdapter().getId(position));
        // Start the ViewDrinkActivity with the created intent.
        startActivity(intent);
    }

    /**
     * This method updates the list in MyFavorites.
     */
    @Override
    public void onResume() {
        // Call the extended onResume method.
        super.onResume();
        // Reset the adapter, load in everything again.
        setAdapter(new DrinkAdapter(this, R.layout.rowlayout, Controller.getAllFavorites()));
        // Set the adapter that we just re-created.
        setListAdapter(getAdapter());
    }

    /**
     * This method gets the DrinkAdapter.
     * 
     * @return adapter
     */
    public DrinkAdapter getAdapter() {
        return adapter;
    }

    /**
     * This method sets the DrinkAdapter.
     * 
     * @param adapter
     */
    public void setAdapter(DrinkAdapter adapter) {
        this.adapter = adapter;
    }
}
