package se.turbotorsk.mybar;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DrinksList extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_drinks_list);
        
        //Storing some random variables
        String[] values = new String[] { "Drink1", "Drink2", "Drink3", "Drink4", "Drink5",
        			"Drink6", "Drink7", "Drink8", "Drink9", "Drink10", "Drink11", "Drink12", 
        			"Drink13", "Drink14", "Drink15" };
        
        //Adds everything to an adapter. We are also chosing which layout the program should use. This is where we're going to choose our own layout when that works.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        
        //Sets the adapter that we just did
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_drinks_list, menu);
        return true;
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
      String item = (String) getListAdapter().getItem(position);
      //We will replace this to start another activity instead. Dont know how to do that yet.
      Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }
}
