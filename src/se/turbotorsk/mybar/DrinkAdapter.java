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

import java.util.LinkedList;

import se.turbotorsk.mybar.model.Drink;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This activity handles the drinks.
 * 
 * This is the first version of our DrinkAdapter that is used so that it is
 * easier to manage drinks within the application.
 * 
 */
public class DrinkAdapter extends ArrayAdapter<LinkedList> {

	public DrinkAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	private LinkedList<Drink> items;

	/**
	 * Constructor for DrinkAdapter.
	 * 
	 * @param context
	 * @param resource
	 * @param items
	 */
	public DrinkAdapter(Context context, int resource, LinkedList items) {
		super(context, resource, items);
		this.items = items;

	}

	/**
	 * This method gets the view for all drinks.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;

		if (v == null) {

			LayoutInflater vi;
			vi = LayoutInflater.from(getContext());
			v = vi.inflate(R.layout.rowlayout, null);

		}

		Drink p = items.get(position);

		if (p != null) {

			TextView tt = (TextView) v.findViewById(R.id.drink);
			TextView tt1 = (TextView) v.findViewById(R.id.ingredients);
			TextView tt3 = (TextView) v.findViewById(R.id.rating);
			TextView tt4 = (TextView) v.findViewById(R.id.drinkDescription);
			ImageView iv = (ImageView) v.findViewById(R.id.list_image);

			if (tt != null) {

				tt.setText(p.getName());
			}
			if (tt1 != null) {

				tt1.setText(p.getIngredientPreViewString());
			}
			if (tt3 != null) {

				tt3.setText("Rating: " + Integer.toString(p.getRating()));
			}
			if (tt4 != null) {
				tt4.setText(p.getDescription());
			}
			if (iv != null) {
				iv.setImageResource(R.drawable.no_img);
			}
		}

		return v;
	}

	/**
	 * This method is fetching the name of the drink.
	 * 
	 * @param position
	 * @return name
	 */
	public String getDrinkName(int position) {
		Drink drink = items.get(position);
		return drink.getName();
	}

	/**
	 * This method is fetching the ingredients of the drink.
	 * 
	 * @param position
	 * @return ingredients
	 */
	public String getIngredients(int position) {
		Drink drink = items.get(position);
		return drink.getIngredientString();
	}

	/**
	 * This method is fetching the rating of the drink.
	 * 
	 * @param position
	 * @return rating
	 */
	public int getRating(int position) {
		Drink drink = items.get(position);
		return drink.getRating();
	}

	/**
	 * This method is fetching the description of the drink.
	 * 
	 * @param position
	 * @return description
	 */
	public String getDescrip(int position) {
		Drink drink = items.get(position);
		return drink.getDescription();
	}

	/**
	 * This method gets the url.
	 * 
	 * @param position
	 * @return url
	 */
	public String getUrl(int position) {
		Drink drink = items.get(position);
		return drink.getUrl();
	}

	/**
	 * This method gets the id.
	 * 
	 * @param position
	 * @return drink id
	 */
	public int getId(int position) {
		Drink drink = items.get(position);
		return drink.getId();
	}

}
