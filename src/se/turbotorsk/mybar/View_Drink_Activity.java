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

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

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
	CheckBox dFavorite;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_drink);

		dName = (TextView) findViewById(R.id.drinkName);
		dDescription = (TextView) findViewById(R.id.drinkDescription);
		dIngredients = (TextView) findViewById(R.id.drinkIngredients);
		dRating = (TextView) findViewById(R.id.drinkRating);
		dImage = (ImageView) findViewById(R.id.drinkImage);
		dFavorite = (CheckBox) findViewById(R.id.drinkFav);

		getDrinkInfo();

		dFavorite.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (dFavorite.isChecked()) {
					// this_drink.setFavorite(true);
					dName.setText("Favorite");
				} else
					dName.setText("No favorite"); // this_drink.setFavorite(false);
			}
		});

	}

	/**
	 * This method inserts the information to the Views in this activity's
	 * XML-file
	 */
	public void getDrinkInfo() {

		// Receiving intents from activity
		Bundle bundle = getIntent().getExtras();
		String name = bundle.getString("drinkname");
		String rating = bundle.getString("rating");
		String description = bundle.getString("descrip");
		String ingredients = bundle.getString("ingredients");

		dName.setText(name);
		dRating.setText(rating);
		dDescription.setText(description);
		dIngredients.setText(ingredients);

		dImage.setImageResource(R.drawable.ic_drinkicon);

	}

}
