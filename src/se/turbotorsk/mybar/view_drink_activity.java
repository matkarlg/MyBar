package se.turbotorsk.mybar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class view_drink_activity extends Activity {
	
	TextView dName;
	TextView dDescription;
	TextView dIngredients;
	TextView dRating;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_drink);
        
        dName = (TextView) findViewById(R.id.drinkName);
        dDescription = (TextView) findViewById(R.id.drinkDescription);
        dIngredients = (TextView) findViewById(R.id.drinkIngredients);
        dRating = (TextView) findViewById(R.id.drinkRating);
        
        getDrinkInfo();
        
       
    }
    
    public void getDrinkInfo() {
    	dName.setText("Drink name");
    	dDescription.setText("Drink description");
    	dIngredients.setText("Drink ingredients");
    	dRating.setText("100");
    }
    

}
