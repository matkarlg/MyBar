package se.mybar.turbotorsk.se.controller;
import java.util.LinkedList;
import se.turbotorsk.mybar.model.Drink;
import se.turbotorsk.mybar.model.Ingredient;


public class DrinkManager {
	private LinkedList<Drink> myBar = null; 

	public DrinkManager(){;}

	public LinkedList<Drink> getMyBar(LinkedList<Ingredient> ingredientList, LinkedList<Drink> drinkList)
	{
		String[] drinks;
		int ingredientID = 0, count = 0;
		boolean found = false; 
		for(Drink drink : drinkList ){
			drinks = drink.getIngredient().split(";");
			for(int countID = 0;  countID <= drinks.length; countID+=2){
				ingredientID = Integer.parseInt(drinks[count]);
				found = false; 
				for(Ingredient ingredient : ingredientList){
					if(ingredient.getId() == ingredientID) {
						found = true; 
						break;
					}
				}
				if (found == false) {
					break;
				}
			myBar.add(drink);
			}	
		}
			return myBar;
	}


}
