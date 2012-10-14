package se.turbotorsk.mybar;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Profiles extends ListActivity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] profiles = {"Home","Dag's Place", "School"};
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 	android.R.layout.simple_list_item_1, profiles);
	    setListAdapter(adapter);
        }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	  //Bundle bundle = new Bundle();
	  String item = (String) getListAdapter().getItem(position);
	  Intent data = new Intent(Profiles.this, SettingsActivity.class);
	 // bundle.putString("profile",item);
	  data.putExtra("profile",item);
      setResult(RESULT_OK, data);
	  Toast.makeText(this, "Changed to " + item, Toast.LENGTH_LONG).show();
	  finish();
	}

}
