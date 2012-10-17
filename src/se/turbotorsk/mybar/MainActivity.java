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

import se.turbotorsk.mybar.model.Data;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

/**
 * This activity handles the main-window in the application.
 * 
 * You could say that this activity is the only one that is never shown. This is
 * the file that starts the application. The application is build on tabs, even
 * though tabs are depreciated.
 * 
 * We have planned to refactor the application to use fragments instead and will
 * do that if we have got time. As of now we are not missing any features due to
 * this, the only other feature we would have used if the application were to be
 * based on fragments is the ability to slide between tabs, or fragments.
 */

// We supress warnings to remove the warnings that tells us to use fragments
// instead of tabs
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		this.deleteDatabase("turbotorsk_mybar.db");
		Data.syncDatabase();
		Data.insertTestData();

		// String[] lista = data.getDrinkNameArray(); tabHost = getTabHost();
		TabHost tabHost = getTabHost();
		// Favorites tab
		// We should rename MyFavorites to Favorites, but thats not a big issue.
		Intent intentFavorites = new Intent().setClass(this, MyFavorites.class);
		TabSpec tabSpecFavorites = tabHost.newTabSpec("Favorites")
				.setIndicator("Favorites").setContent(intentFavorites);

		// MyBar tab.
		Intent intentMyBar = new Intent().setClass(this, MyBarActivity.class);
		TabSpec tabSpecMyBar = tabHost.newTabSpec("MyBar")
				.setIndicator("MyBar").setContent(intentMyBar);

		// MyBar tab.
		Intent intentCollection = new Intent().setClass(this,
				CollectionActivity.class);
		TabSpec tabSpecCollection = tabHost.newTabSpec("Collection")
				.setIndicator("Collection").setContent(intentCollection);

		// Add all tabs.
		tabHost.addTab(tabSpecFavorites);
		tabHost.addTab(tabSpecMyBar);
		tabHost.addTab(tabSpecCollection);

		// Set Mybar as default tab (the middle tab).
		tabHost.setCurrentTab(1);
	}

	/**
	 * This method creates the Options-menu.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/**
	 * This method generates the options-menu in the main window.
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_add_drink:
			startActivity(new Intent(this, AddIngredientActivity.class));
			return true;
		case R.id.menu_settings:
			startActivity(new Intent(this, SettingsActivity.class));
			return true;
		case R.id.menu_about:
			AboutBox.Show(MainActivity.this);
			return true;
		case R.id.menu_share:
			startActivity(new Intent(this, Share.class));
			return true;
		default:
			return true;
		}
	}
}
