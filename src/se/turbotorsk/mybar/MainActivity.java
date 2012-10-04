package se.turbotorsk.mybar;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }  
    
    /**
     *  This method generates the options-menu in the main window
     */
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        	case R.id.menu_add_drink:
        		startActivity(new Intent(this, Add_Ingredient_Activity.class));
        		return true;
        	case R.id.menu_settings:
        		startActivity(new Intent(this, Settings_Activity.class));
        		return true;
        	case R.id.menu_about:
        		AboutBox.Show(MainActivity.this);
        		return true;
        	default:
        		return true;
    	}
    }
     
}