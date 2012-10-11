package se.turbotorsk.mybar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MyBarActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_my_bar, menu);
        return true;
    }
}
