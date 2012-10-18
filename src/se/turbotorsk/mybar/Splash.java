package se.turbotorsk.mybar;

import se.turbotorsk.mybar.controller.Controller;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;


public class Splash extends Activity {

    private final int SPLASH_LENGHT = 1200;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable(){
          
        	public void run() {
        		Controller.deleteTables();
        		Controller.dataSync();
        		Intent intent = new Intent(Splash.this,MainActivity.class);
                Splash.this.startActivity(intent);
        		Splash.this.finish();
            }
        }, SPLASH_LENGHT);
    }
}
