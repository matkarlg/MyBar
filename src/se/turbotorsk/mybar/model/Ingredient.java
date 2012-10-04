package se.turbotorsk.mybar.model;

public class Ingredient {
	
	private int id = 0;
	private String name = "";
	private String url = "";
	private int type = 0;
	private String description = "";
	
	public Ingredient(int id, String name, String url, int glass, String description)
	{
		this.id = id;
		this.name = name;
		this.url = url;
		this.description = description;
	}

	public int getId() {
		return id;
	}

}
