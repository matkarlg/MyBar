/*
New BSD License
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se

Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice,
  this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.
 * Neither the name of the MyBar nor the names of its contributors may be 
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

	private TextView dName;
	private TextView dDescription;
	private TextView dIngredients;
	private RatingBar dRating;
	private ImageView dImage;
	private CheckBox checkBox;
	private int id;
	private String name, description, ingredients;
	private int rating;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_drink);

		setdName((TextView) findViewById(R.id.drinkName));
		setdDescription((TextView) findViewById(R.id.drinkDescription));
		setdIngredients((TextView) findViewById(R.id.drinkIngredients));
		setdRating((RatingBar) findViewById(R.id.ratingBar1));
		setdImage((ImageView) findViewById(R.id.drinkImage));
		setCheckBox((CheckBox) findViewById(R.id.drinkFav));
		setDrinkInfo();
		getdRating().setEnabled(true);
		getdRating().setOnRatingBarChangeListener(this);
		checkBoxListener();

		if (Controller.isFavorite(getId()) == 1) {
			getCheckBox().setChecked(true);
		}
		getdRating().setRating(Controller.rating(getId()));

	}

	/**
	 * This method collects all the set-methods for the information of the
	 * drink.
	 */
	public void setDrinkInfo() {

		// Receiving intents from activity.
		Bundle bundle = getIntent().getExtras();
		setName(bundle.getString("drinkname"));
		setRating(bundle.getInt("rating"));
		setDescription(bundle.getString("descrip"));
		setIngredients(bundle.getString("ingredients"));
		// url will be used later on, not deleted because its a part of a
		// drink-object
		String url = bundle.getString("url");
		setId(bundle.getInt("id"));

		// Set all the information about the drink.
		setDrinkName();
		setDrinkRating();
		setDrinkDescription();
		setDrinkIngredients();
		setDrinkImage();
		getdImage().setImageResource(R.drawable.no_img);

	}

	/**
	 * This method sets the name of the drink.
	 */
	public void setDrinkName() {
		getdName().setText(getName());
	}

	/**
	 * This method sets the rating of the drink.
	 */
	public void setDrinkRating() {
		getdRating().setRating(getRating());
	}

	/**
	 * This method sets the description of the drink.
	 */
	public void setDrinkDescription() {
		getdDescription().setText(getDescription());
	}

	/**
	 * This method sets the ingredients of the drink.
	 */
	public void setDrinkIngredients() {
		getdIngredients().setText(getIngredients());
	}

	/**
	 * This method sets the image of the drink.
	 */
	public void setDrinkImage() {
		getdImage().setImageResource(R.drawable.no_img);
	}

	/**
	 * This method handle the checkbox which is used to mark the drink as
	 * favorite.
	 */
	public void checkBoxListener() {

		getCheckBox().setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// is the checkbox checked?
				if (((CheckBox) v).isChecked()) {
					Controller.setFavorite(getName());
					Toast.makeText(ViewDrinkActivity.this,
							"Added to Favorites", Toast.LENGTH_SHORT).show();
				} else {
					Controller.setNotFavorite(getName());
					Toast.makeText(ViewDrinkActivity.this,
							"Removed from Favorites", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
	}

	/**
	 * This method sets the new rating when changed by the user.
	 */
	public void onRatingChanged(RatingBar ratingBar, float rating,
			boolean fromUser) {
		getdRating().setRating((int) rating);
		Controller.setRatingByName(getName(), (int) rating);
	}

	// Getters and setters. We need these to follow java standard. Even though
	// they aren't used.

	/**
	 * This method gets dname.
	 */
	public TextView getdName() {
		return dName;
	}

	/**
	 * This method sets dname.
	 * 
	 * @param dName
	 */
	public void setdName(TextView dName) {
		this.dName = dName;
	}

	/**
	 * This method gets dDescription.
	 */
	public TextView getdDescription() {
		return dDescription;
	}

	/**
	 * This method sets dDescription.
	 * 
	 * @param dDescription
	 */
	public void setdDescription(TextView dDescription) {
		this.dDescription = dDescription;
	}

	/**
	 * This method gets dIngredients.
	 */
	public TextView getdIngredients() {
		return dIngredients;
	}

	/**
	 * This method sets dIngredients.
	 * 
	 * @param dIngredients
	 */
	public void setdIngredients(TextView dIngredients) {
		this.dIngredients = dIngredients;
	}

	/**
	 * This method gets dRating.
	 */
	public RatingBar getdRating() {
		return dRating;
	}

	/**
	 * This method sets dRating.
	 * 
	 * @param dRating
	 */
	public void setdRating(RatingBar dRating) {
		this.dRating = dRating;
	}

	/**
	 * This method gets dImage.
	 */
	public ImageView getdImage() {
		return dImage;
	}

	/**
	 * This method sets dImage.
	 * 
	 * @param dImage
	 */
	public void setdImage(ImageView dImage) {
		this.dImage = dImage;
	}

	/**
	 * This method gets checkBox.
	 */
	public CheckBox getCheckBox() {
		return checkBox;
	}

	/**
	 * This method sets checkBox.
	 * 
	 * @param checkBox
	 */
	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}

	/**
	 * This method gets id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * This method sets id.
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * This method gets name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method gets description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method sets description.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This method gets ingredients.
	 */
	public String getIngredients() {
		return ingredients;
	}

	/**
	 * This method sets ingredients.
	 * 
	 * @param ingredients
	 */
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * This method gets rating.
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * This method sets rating.
	 * 
	 * @param rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
}
