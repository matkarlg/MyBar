package se.turbotorsk.mybar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class view_drink_activity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_drink);
        
        
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_drink_info, menu);
        return true;
    }
    
}
