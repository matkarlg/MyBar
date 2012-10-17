/*
New BSD License
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se

Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the following conditions are met:
*	Redistributions of source code must retain the above copyright notice,
 	this list of conditions and the following disclaimer.
*	Redistributions in binary form must reproduce the above copyright notice,
 	this list of conditions and the following disclaimer in the documentation
 	and/or other materials provided with the distribution.
*	Neither the name of the MyBar nor the names of its contributors may be 
	used to endorse or promote products derived from this software without
	specific prior written permission.
	
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY 
OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package se.turbotorsk.mybar;

import se.turbotorsk.mybar.controller.Controller;
import se.turbotorsk.mybar.model.Data;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity push the information about a drink to the GUI.
 */

public class ViewDrinkActivity extends Activity implements
		OnRatingBarChangeListener {

	TextView dName;
	TextView dDescription;
	TextView dIngredients;
	RatingBar dRating;
	ImageView dImage;
	CheckBox checkBox;
	int id;
	String name, description, ingredients;
	int rating;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_drink);

		dName = (TextView) findViewById(R.id.drinkName);
		dDescription = (TextView) findViewById(R.id.drinkDescription);
		dIngredients = (TextView) findViewById(R.id.drinkIngredients);
		dRating = (RatingBar) findViewById(R.id.ratingBar1);
		dImage = (ImageView) findViewById(R.id.drinkImage);
		checkBox = (CheckBox) findViewById(R.id.drinkFav);
		setDrinkInfo();
		dRating.setEnabled(true);
		dRating.setOnRatingBarChangeListener(this);
		checkBoxListener();

		if (Controller.isFavorite(id) == 1) {
			checkBox.setChecked(true);
		}
		dRating.setRating(Controller.rating(id));

	}

	/**
	 * This method collects all the set-methods for the information of the
	 * drink.
	 */
	public void setDrinkInfo() {

		// Receiving intents from activity.
		Bundle bundle = getIntent().getExtras();
		name = bundle.getString("drinkname");
		rating = bundle.getInt("rating");
		description = bundle.getString("descrip");
		ingredients = bundle.getString("ingredients");
		String url = bundle.getString("url");
		id = bundle.getInt("id");

		// Set all the information about the drink.
		setDrinkName();
		setDrinkRating();
		setDrinkDescription();
		setDrinkIngredients();
		setDrinkImage();
		dImage.setImageResource(R.drawable.no_img);

	}

	/**
	 * This method sets the name of the drink.
	 */
	public void setDrinkName() {
		dName.setText(name);
	}

	/**
	 * This method sets the rating of the drink.
	 */
	public void setDrinkRating() {
		dRating.setRating(rating);
	}

	/**
	 * This method sets the description of the drink.
	 */
	public void setDrinkDescription() {
		dDescription.setText(description);
	}

	/**
	 * This method sets the ingredients of the drink.
	 */
	public void setDrinkIngredients() {
		dIngredients.setText(ingredients);
	}

	/**
	 * This method sets the image of the drink.
	 */
	public void setDrinkImage() {
		dImage.setImageResource(R.drawable.ic_drinkicon);
	}

	/**
	 * This method handle the checkbox which is used to mark the drink as
	 * favorite.
	 */
	public void checkBoxListener() {

		checkBox.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// is the checkbox checked?
				if (((CheckBox) v).isChecked()) {
					Data.setDrink(name, "favorite", 1);
					Toast.makeText(ViewDrinkActivity.this,
							"Added to Favorites", Toast.LENGTH_SHORT).show();
				} else {
					Data.setDrink(name, "favorite", 0);
					Toast.makeText(ViewDrinkActivity.this,
							"Removed from Favorites", Toast.LENGTH_SHORT)
							.show();
				}
				// MyFavorites.updateList();

			}
		});
	}

	public void onRatingChanged(RatingBar ratingBar, float rating,
			boolean fromUser) {
		dRating.setRating((int) rating);
		Data.setDrink(name, "rating", (int) rating);
	}
}
