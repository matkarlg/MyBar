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

package se.turbotorsk.mybar.controller;

import java.util.LinkedList;

import se.turbotorsk.mybar.model.Data;
import se.turbotorsk.mybar.model.Drink;
import se.turbotorsk.mybar.model.Ingredient;
import se.turbotorsk.mybar.model.MyBar;

/**
 * This activity handles the Controller.
 * @author Dag Fridén (<a
 *         href="mailto:dag@daysoft.se">email</a>)
 */
public class Controller {

	/**
	 * Gets method return the drinks that you can make with
	 * the ingredients in your bar.
	 * @return LinkedList with drinks that you can make.
	 */
	public static LinkedList<Drink> getMyBarDrinks() {
		return DrinkManager.getMyBar();
	}

	/**
	 * Gets a ingredient by the id.
	 * @param id
	 * @return
	 */
	public static Ingredient getIngredientById(int id) {
		return Data.getIngredientByID(id); 
	}

	// ---------- Methods for get and set favorites ----------

	/**
	 * Returns if a given drink is set as a favorite. 
	 * @param id of drink.
	 * @return true if the drinks is in the favorite database.
	 */
	public static int isFavorite(int id) {
		Drink drink = Data.getDrinkByID(id);
		return drink.getFavorite();
	}

	/**
	 * Returns the rating for a given drink. 
	 * @param id of a drink.
	 * @return the rating of the the given drink. 
	 */
	public static int rating(int id) {
		Drink drink = Data.getDrinkByID(id);
		return drink.getRating();
	}
	
	/**
	 * Gets the ingredients that the user has in the MyBar table. 
	 * @return 
	 */
	public static LinkedList<Ingredient> getMyIngredients(){
		LinkedList<Ingredient> list = new LinkedList<Ingredient>(); 
		for(MyBar mybar: Data.getAllMyBar()){
			list.add( Data.getIngredientByID(mybar.getIngredientID()));	
		}
		return list;
	}
	
	/**
	 * Sync the database with info from the external database.  
	 * @return true if the sync was okej. False if a error happened. 
	 */
	public static boolean dataSync()
	{
		return Data.syncDatabase();
	}
	
	/**
	 * Gets all the drinks form data.
	 * @return a linkeList with all drinks. 
	 */
	public static LinkedList<Drink> getAllDrinks()
	{
		return (LinkedList<Drink>) Data.getAllDrinks();
	}
	
	/**
	 * Gets all the favorites from the data.
	 * @return a linkedList with all the drinks that is favorites. 
	 */
	public static LinkedList<Drink> getAllFavorites()
	{
		return (LinkedList<Drink>) Data.getAllFavorites();
	}
	
	/**
	 * Sets the rating in data.
	 * @param name of the drink to be updated. 
	 * @param rating 
	 * @return
	 */
	public static int setRatingByName(String name, int rating)
	{
		return Data.setDrink(name, "rating", rating);
	}

	/**
	 * Sets the drink as a favorite.  
	 * @param name of the drink. 
	 */
	public static void setFavorite(String name)
	{
		Data.setDrink(name, "favorite", 1);
	}
	
	/**
	 * Removes a drink from favorites.
	 * @param name of the drink. 
	 */
	public static void setNotFavorite(String name)
	{
		Data.setDrink(name, "favorite", 0);
	}
	
	/**
	 * Gets all ingredients from data.
	 * @return a linkedList of Drink-object. 
	 */
	public static LinkedList<Ingredient> getAllIngredients()
	{
		return (LinkedList<Ingredient>) Data.getAllIngredients();
		
	}
	
	/**
	 * Add an ingredient to the myBar table given the id of the ingredient. 
	 * @param id of the ingredient. 
	 */
	public static void addMyBarIngredient(int id){
		Data.addMyBar(id, "home");
	}
	
	/**
	 * Removes an ingredient from the myBar
	 * table given the id of the ingredient.
	 * @param id of the ingredient.
	 * @param location of the bar.
	 */
	public static void removeMyBarIngredient(int id, String location){
		Data.dropMyBar(id, location);
	}

	/**
	 * Checks if an ingredient is in the myBar table. 
	 * @param id of the ingredient. 
	 * @return true if it is in the table. 
	 */
	public static boolean isInMyBar(int id){
		return (Data.searchMyBar(id).size() != 0);
	}
	
	/**
	 * Deletes all the ingredients and drinks from the database. 
	 * Muse be run before a sync can be performed.
	 */
	public static void deleteTables(){
		Data.deleteData();
	}
	
}
