package se.turbotorsk.mybar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MyFavorites extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorites);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_my_favorites, menu);
        return true;
    }
}
