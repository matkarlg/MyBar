/*
New BSD License
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se

Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the following conditions are met:
�	Redistributions of source code must retain the above copyright notice,
 	this list of conditions and the following disclaimer.
�	Redistributions in binary form must reproduce the above copyright notice,
 	this list of conditions and the following disclaimer in the documentation
 	and/or other materials provided with the distribution.
�	Neither the name of the MyBar nor the names of its contributors may be 
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

package se.turbotorsk.mybar.model;

import se.turbotorsk.mybar.controller.Controller;
import android.content.ContentValues;
import android.util.Log;

/**
 * A drink object contains information about a drink.
 * 
 * @author Mathias Karlgren (matkarlg)
 * 
 */
public class Drink {

	private int _id = 0;
	private String name = "";
	private String url = "";
	private String glass = "";
	private String ingredient = "";
	private String description = "";
	private int rating = 0;
	private int favorite = 0;
	private ContentValues values = null;

	public Drink(int _id, String name, String url, String glass, String ingredient,
			String description, int rating, int favorite) {
		this._id = _id;
		this.name = name;
		this.url = url;
		this.glass = glass;
		this.ingredient = ingredient;
		this.description = description;
		this.rating = rating;
		this.favorite = favorite;

		values = new ContentValues();
		values.put("name", name);
		values.put("url", url);
		values.put("glass", glass);
		values.put("ingredient", ingredient);
		values.put("description", description);
		values.put("rating", rating);
		values.put("favorite", favorite);
	}

	/*
	 * private Calendar calendar = Calendar.getInstance(); private Date date =
	 * new Date(calendar.getTime().getTime()); private SimpleDateFormat
	 * dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 * 
	 * public String getDate() { return dateFormat.format(date); }
	 * 
	 * public void setDate(int year, int month, int day) { try { this.date =
	 * dateFormat.parse("" + year + "-" + month + "-" + day); } catch
	 * (ParseException e) { Log.e(e.getClass().getName(), "ParseException: " +
	 * e.getMessage(), e); } }
	 * 
	 * public void setDate(Date date) { try { this.date =
	 * dateFormat.parse(date.toString()); } catch (ParseException e) {
	 * Log.e(e.getClass().getName(), "ParseException: " + e.getMessage(), e); }
	 * }
	 */

	/**
	 * Return a representation of the ingredients in the drink for the the drink
	 * view activity.
	 * 
	 * @return
	 */
	public String getIngredientPreViewString() {
		int arrayCount = 0, maxStringLength = (40 - 4);
		StringBuffer nameBuff = new StringBuffer();
		String name = "";
		String[] array = ingredient.split(";"); 
		nameBuff.append("| ");
		int i = 0;
		while( i < array.length -1){
				
				Log.d("Searcing for", array[i]);
				name = Controller.getIngredientById(Integer.parseInt(array[i])).getName();
				if ((maxStringLength - name.length()) > 0){
					nameBuff.append(name + " | ");
					maxStringLength = maxStringLength - name.length();
				}
				i = i + 2; 
		}
		return nameBuff.toString(); 
	}
	
	public String getIngredientString() {

		int arrayCount = 0, maxStringLength = (26 - 4);
		StringBuffer nameBuff = new StringBuffer();
		String name = "";
		String[] array = ingredient.split(";"); 
		int i = 0;
		while( i < array.length -1){
				
				Log.d("Searcing for", array[i]);
				name = Controller.getIngredientById(Integer.parseInt(array[i])).getName();
				nameBuff.append(name + "\n");
				i = i + 2; 
		}
		return nameBuff.toString(); 
	}

	/**
	 * This method gets the values for the content.
	 * 
	 * @return values
	 */
	public ContentValues getContentValues() {
		return values;
	}

	/**
	 * This method gets the id.
	 * 
	 * @return _id
	 */
	public int get_id() {
		return _id;
	}

	/**
	 * This method sets the id.
	 * 
	 * @param _id
	 */
	public void set_id(int _id) {
		this._id = _id;
	}

	/**
	 * This method gets the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets the name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method gets the ingredient.
	 * 
	 * @return ingredient
	 */
	public String getIngredient() {
		return ingredient;
	}

	/**
	 * This method sets the ingredient.
	 * 
	 * @param ingredient
	 */
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	/**
	 * This method gets the rating.
	 * 
	 * @return rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * This method sets the rating.
	 * 
	 * @param rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * This method gets the glass.
	 * 
	 * @return glass
	 */
	public String getGlass() {
		return glass;
	}

	/**
	 * This method sets the glass.
	 * 
	 * @param glass
	 */
	public void setGlass(String glass) {
		this.glass = glass;
	}

	/**
	 * This method gets the description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method sets the description.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This method gets the url.
	 * 
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * This method sets the url.
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * This method gets the favorite.
	 * 
	 * @return
	 */
	public int getFavorite() {
		return favorite;
	}

	/**
	 * This method sets the favorite.
	 * 
	 * @param favorite
	 */
	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}
}
