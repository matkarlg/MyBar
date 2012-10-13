/*
New BSD License
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se
Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
�	Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
�	Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
�	Neither the name of the MyBar nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/


package se.turbotorsk.mybar;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import se.turbotorsk.mybar.model.Data;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity push the information about a drink to the GUI
 *
 */

public class View_Drink_Activity extends Activity {
	
	TextView dName;
	TextView dDescription;
	TextView dIngredients;
	TextView dRating;
	ImageView dImage;
	CheckBox checkBox;
	String name;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_drink);
        
        dName = (TextView) findViewById(R.id.drinkName);
        dDescription = (TextView) findViewById(R.id.drinkDescription);
        dIngredients = (TextView) findViewById(R.id.drinkIngredients);
        dRating = (TextView) findViewById(R.id.drinkRating);
        dImage = (ImageView) findViewById(R.id.drinkImage);
        checkBox = (CheckBox) findViewById(R.id.drinkFav);
        
        getDrinkInfo();
        checkBoxListener();
       
    }
    
    /**
     * This method inserts the information to the Views in this activity's XML-file
     */
    public void getDrinkInfo() {
    	
    	//Receiving intents from activity
    	Bundle bundle = getIntent().getExtras();
    	name = bundle.getString("drinkname");
    	String rating = bundle.getString("rating");
    	String description = bundle.getString("descrip");
    	String ingredients = bundle.getString("ingredients");
    	String url = bundle.getString("url");
    	
    	dName.setText(name);
    	dRating.setText(rating);
    	dDescription.setText(description);
    	dIngredients.setText(ingredients);
    	//dImage.setImageResource(R.drawable.ic_drinkicon);
    	
    	URL url2;
		try {
			url2 = new URL(url);
			Bitmap bmp = BitmapFactory.decodeStream(url2.openConnection().getInputStream());
        	dImage.setImageBitmap(bmp);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void checkBoxListener() {
     
    	checkBox.setOnClickListener(new OnClickListener() {
     
    	  public void onClick(View v) {
                    //is the checkbox checked?
    		if (((CheckBox) v).isChecked()) {
    			Data.setFavoriteByName(name);
    			Toast.makeText(View_Drink_Activity.this,"Added to Favorites", Toast.LENGTH_LONG).show();
    		}
    		
    	  }
    	});
    }
}
