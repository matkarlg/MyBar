package se.turbotorsk.mybar.controller;

import se.turbotorsk.mybar.model.Data;

public class Controller {
	
	public final static Controller controller = new Controller();
	private Data data = null;
	
	public Controller()
	{
		data = new Data();
	}
	
	public Data getData()
	{
		return data;
	}
}
