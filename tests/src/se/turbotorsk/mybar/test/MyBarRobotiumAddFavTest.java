package se.turbotorsk.mybar.test;

import com.jayway.android.robotium.solo.Solo;

import se.turbotorsk.mybar.CollectionActivity;
import se.turbotorsk.mybar.MainActivity;
import se.turbotorsk.mybar.ViewDrinkActivity;
import android.test.ActivityInstrumentationTestCase2;

public class MyBarRobotiumAddFavTest extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	private Solo solo;

	public MyBarRobotiumAddFavTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	//Not done yet. Needs to go to FAVORITES tab and verify that the drink is there there
	//is some issue with changing tabs
	public void testAddFavoriteAndVerify(){
		solo.assertCurrentActivity("First activity", MainActivity.class);
		solo.sendKey(Solo.UP);
		solo.sendKey(Solo.RIGHT);
		solo.assertCurrentActivity("Ska ha bytt activity",
				CollectionActivity.class);
		solo.clickLongOnText("Angel Face");
		solo.assertCurrentActivity("Nu ska vi se drinken", ViewDrinkActivity.class);
		solo.clickOnCheckBox(0); //This is the fav checkbox
		solo.goBack();
		//solo.clickOnText("FAVORITES"); This is what i "want" to do
	}
}
