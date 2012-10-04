package se.turbotorsk.mybar;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Add_Ingredient_Activity extends ListActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] ingredients = this.getResources().getStringArray(R.array.ingredient_list);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 	android.R.layout.simple_list_item_1, ingredients);
	    setListAdapter(adapter);  
        }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	  String item = (String) getListAdapter().getItem(position);
	  Intent data = new Intent();
	  data.putExtra("Add",item);
	  Toast.makeText(this, "Added ingredient", Toast.LENGTH_LONG).show();
	  setResult(RESULT_OK, data);
	  finish();
	}
	
	
}
