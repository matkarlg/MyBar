package se.turbotorsk.mybar;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class DrinksList extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_drinks_list);
        
        String[] values = new String[] { "Drink1", "Drink2", "Drink3", "Drink4", "Drink5",
        			"Drink6", "Drink7", "Drink8", "Drink9", "Drink10", "Drink11", "Drink12", 
        			"Drink13", "Drink14", "Drink15" };
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_drinks_list, menu);
        return true;
    }
}
