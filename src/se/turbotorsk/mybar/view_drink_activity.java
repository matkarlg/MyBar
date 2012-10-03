package se.turbotorsk.mybar;


import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This activity push the information about a drink to the GUI
 * 
 * @author Viktor
 *
 */

public class view_drink_activity extends Activity {
	
	TextView dName;
	TextView dDescription;
	TextView dIngredients;
	TextView dRating;
	ImageView dImage;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_drink);
        
        dName = (TextView) findViewById(R.id.drinkName);
        dDescription = (TextView) findViewById(R.id.drinkDescription);
        dIngredients = (TextView) findViewById(R.id.drinkIngredients);
        dRating = (TextView) findViewById(R.id.drinkRating);
        dImage = (ImageView) findViewById(R.id.drinkImage);
        
        getDrinkInfo();
       
    }
    
    /**
     * This method inserts the information to the Views in this activity's XML-file
     */
    public void getDrinkInfo() {
    	dName.setText("Drink name");
    	dDescription.setText("Drink description");
    	dIngredients.setText("Drink ingredients");
    	dRating.setText("5");
    	
    	dImage.setImageResource(R.drawable.ic_drinkicon);
    }
    
}
