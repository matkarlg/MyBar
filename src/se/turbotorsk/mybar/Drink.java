/**
 * 
 */
package se.turbotorsk.mybar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Drink class
 * 
 * @author karlgren
 *
 */
public class Drink {
	private int ID = 0;
	private String name = "";
	private String ingredient = "";
	private int rating = 0;
	// IMG blob;
	
	Calendar calendar = Calendar.getInstance();
	private Date date = new Date(calendar.getTime().getTime());
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
     * 
     * @return returns the date of the event as a string. 
     */
    public String getDate() {
		return dateFormat.format(date);
	}
    
    /**
     * This method is used to set the date when the event was bought.
     * 
     * @param year should be an Integer
     * @param month should be an Integer
     * @param day should be an Integer
     */
    public void setDate(int year, int month, int day) {
		try {
			this.date = dateFormat.parse("" + year + "-" + month + "-" + day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
    
    /**
     * The in-parameter of this class should be an Date object.
     * 
     * @param date should be an Date object.
     */
    public void setDate(Date date) {
		try {
			this.date = dateFormat.parse(date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ingredient
	 */
	public String getIngredient() {
		return ingredient;
	}

	/**
	 * @param ingredient the ingredient to set
	 */
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
}
