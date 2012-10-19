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

package se.turbotorsk.mybar.model;

import android.content.ContentValues;

/**
 * Drink is a simple class that contains variables describing, among other
 * attributes the name and the type of glass used when making a drink.
 * 
 * @author Mathias Karlgren (<a
 *         href="mailto:mathias.karlgren@gmail.com">email</a>)
 */
public class Drink {
	private int id = 0;
	private String name = "";
	private String url = "";
	private String glass = "";
	private String ingredient = "";
	private String description = "";
	private int rating = 0;
	private int favorite = 0;
	private ContentValues values = null;

	public Drink(int id, String name, String url, String glass,
			String ingredient, String description, int rating, int favorite) {
		this.id = id;
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

	/**
	 * Return a representation of the ingredients in the drink for the the drink
	 * view activity.
	 * 
	 * @return
	 */
	public String getIngredientPreViewString() {
		int maxStringLength = (40 - 4);
		StringBuffer nameBuff = new StringBuffer();
		String nameString = "";
		String[] array = ingredient.split(";"); 
		nameBuff.append("| ");
		int i = 0;
		while( i < array.length -1){
				nameString = Data.getIngredientByID(Integer.parseInt(array[i])).getName();
				if ((maxStringLength - nameString.length()) > 0){
					nameBuff.append(nameString + " | ");
					maxStringLength = maxStringLength - nameString.length();
				}
				i = i + 2; 
		}
		return nameBuff.toString(); 
	}
	
	public String getIngredientString() {
		StringBuffer nameBuff = new StringBuffer();
		String nameString = "";
		String[] array = ingredient.split(";"); 
		int i = 0;
		while( i < array.length -1){
				nameString = Data.getIngredientByID(Integer.parseInt(array[i])).getName();
				i++;
				nameBuff.append(nameString +" " + array[i++] + " cl\n");
		}
		return nameBuff.toString(); 
	}

	/**
	 * Gets the ContentValues.
	 * 
	 * @return values
	 */
	public ContentValues getContentValues() {
		return values;
	}

	/**
	 * Gets the id.
	 * 
	 * @return id
	 */
	public int get_id() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 */
	public void set_id(int id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the ingredient.
	 * 
	 * @return ingredient
	 */
	public String getIngredient() {
		return ingredient;
	}

	/**
	 * Sets the ingredient.
	 * 
	 * @param ingredient
	 */
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	/**
	 * Gets the rating.
	 * 
	 * @return rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Sets the rating.
	 * 
	 * @param rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Gets the glass.
	 * 
	 * @return glass
	 */
	public String getGlass() {
		return glass;
	}

	/**
	 * Sets the glass.
	 * 
	 * @param glass
	 */
	public void setGlass(String glass) {
		this.glass = glass;
	}

	/**
	 * Gets the description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the URL.
	 * 
	 * @return URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the URL.
	 * 
	 * @param URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the favorite.
	 * 
	 * @return favorite
	 */
	public int getFavorite() {
		return favorite;
	}

	/**
	 * Sets the favorite.
	 * 
	 * @param favorite
	 */
	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}
}
