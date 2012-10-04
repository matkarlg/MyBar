package se.turbotorsk.mybar;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


//Dont know how to use fragments yet
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		
		TabHost tabHost = getTabHost(); 
		
		// Favorites tab
		// We should rename MyFavorites to Favorites, but thats not a big issue.
		Intent intentFavorites = new Intent().setClass(this, MyFavorites.class);
		TabSpec tabSpecFavorites = tabHost
			.newTabSpec("Favorites")
			.setIndicator("Favorites")
			.setContent(intentFavorites);
		
		// MyBar tab
		Intent intentMyBar = new Intent().setClass(this, DrinksList.class);
		TabSpec tabSpecMyBar = tabHost
			.newTabSpec("MyBar")
			.setIndicator("MyBar")
			.setContent(intentMyBar);
		
		// MyBar tab
		Intent intentCollection = new Intent().setClass(this, CollectionActivity.class);
		TabSpec tabSpecCollection = tabHost
			.newTabSpec("Collection")
			.setIndicator("Colleciton")
			.setContent(intentCollection);
	
		// add all tabs 
		tabHost.addTab(tabSpecFavorites);
		tabHost.addTab(tabSpecMyBar);
		tabHost.addTab(tabSpecCollection);
		
		
		//Set Mybar as default tab (the middle tab)
		tabHost.setCurrentTab(1);
	}
    
}