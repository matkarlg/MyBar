package se.turbotorsk.mybar;

//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.text.ParseException;
//import android.util.Log;

/**
 * A drink object contains information about a drink.
 * 
 * @author Karlgren
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
	
	public Drink(int _id, String name, String url, String glass,
			String ingredient, String description, int rating) {
		this._id = _id;
		this.name = name;
		this.url = url;
		this.glass = glass;
		this.ingredient = ingredient;
		this.description = description;
		this.rating = rating;
	}
	
	/*private Calendar calendar = Calendar.getInstance();
	private Date date = new Date(calendar.getTime().getTime());
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public String getDate() {
		return dateFormat.format(date);
	}

	public void setDate(int year, int month, int day) {
		try {
			this.date = dateFormat.parse("" + year + "-" + month + "-" + day);
		} catch (ParseException e) {
			Log.e(e.getClass().getName(), "ParseException: " + e.getMessage(),
					e);
		}
	}

	public void setDate(Date date) {
		try {
			this.date = dateFormat.parse(date.toString());
		} catch (ParseException e) {
			Log.e(e.getClass().getName(), "ParseException: " + e.getMessage(),
					e);
		}
	}*/
	
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getGlass() {
		return glass;
	}

	public void setGlass(String glass) {
		this.glass = glass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
