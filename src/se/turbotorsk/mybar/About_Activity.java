package se.turbotorsk.mybar;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class About_Activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_about);
        
        TextView tv = (TextView) findViewById(R.id.textAbout);
        tv.setText("This app is made by group 13" );
    }

}
