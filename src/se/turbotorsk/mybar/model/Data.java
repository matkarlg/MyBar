/**
 * A drink object contains information about a drink.
 * 
 * @author Dag Fridén
 * 
 */

/**
 * @copywright Dag Fridén
 * 
 */



package se.turbotorsk.mybar.model;

import java.util.LinkedList;

public class Data {

	private final boolean SQLITE = false;
	private final boolean EDATA = false;
	private final boolean FAKE = true;
	//private XXXXXXXX sqlite;
	//private XXXXXXXX jsonParser;
	//private XXXXXXXX httpGet;
	
	public Data()
	{
		if(SQLITE); //creates SQlite objects.
		if(EDATA);
		if(FAKE);
		
	}
	
	/**
	 * This method return a preview (thmbnail, name, rating, decprition) of the information in the datasource.
	 * @return DrinkPreview object.
	 */
	public LinkedList<Drink> getDrinksPreview()
	{
		if(SQLITE);
		if(EDATA);
		if(FAKE);
		
		return null; 
	}
	
	public String[] getDrinkNameArray()
	{
		String[] drinks; 
		if(SQLITE);
		if(EDATA);
		if(FAKE){
			drinks = new String[5];
			drinks[0] ="Cola och tonic";
			drinks[1] ="Cola lime";
			drinks[2] ="Cola gin";
			drinks[3] ="Cola citrus";
			drinks[4] ="Cola hallon";
			drinks[5] ="Cola light";
			drinks[6] ="Cola sun";
			drinks[7] ="Cola looka";
			drinks[8] ="Cola with ice";
			drinks[9] ="Rom and cocke";
		}
		return null; 
	}
	
	/**
	 * This method return a Drink (thmbnail, name, rating, decprition) 
	 * @param ID
	 * @return
	 */
	public Drink getDrinkByID(int ID)
	{
		if(SQLITE);
		if(EDATA);
		if(FAKE);
		
		return null; 	
	}
	
	public LinkedList<Drink> getFavoritDrinks()
	{
		if(SQLITE);
		if(EDATA);
		if(FAKE);
		
		return null; 
	}
}
