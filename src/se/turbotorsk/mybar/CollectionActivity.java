package se.turbotorsk.mybar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CollectionActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_collection, menu);
        return true;
    }
}
