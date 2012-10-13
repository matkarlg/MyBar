package se.turbotorsk.mybar;

import se.turbotorsk.mybar.model.Data;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyBarActivity extends ListActivity {

	IngredientAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		IngredientAdapter adapter = new IngredientAdapter(this, R.layout.rowlayout, Data.getAllIngredients());

		setListAdapter(adapter);

	}
	/*@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	  String item = (String) getListAdapter().getItem(position);
	  Toast.makeText(this, "Deleted " + item, Toast.LENGTH_LONG).show();
	  
	}*/

}
