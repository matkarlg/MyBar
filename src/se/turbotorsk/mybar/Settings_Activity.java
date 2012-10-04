package se.turbotorsk.mybar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;

public class Settings_Activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_settings);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_about_settings, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        	case R.id.menu_settings:
        		startActivity(new Intent(this, Settings_Activity.class));
        		return true;
        	case R.id.menu_about:
        		AboutBox.Show(Settings_Activity.this);
        		return true;
        	default:
        		return true;
    	}
    }

}
