/*
New BSD License
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se

Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the following conditions are met:
*	Redistributions of source code must retain the above copyright notice,
 	this list of conditions and the following disclaimer.
*	Redistributions in binary form must reproduce the above copyright notice,
 	this list of conditions and the following disclaimer in the documentation
 	and/or other materials provided with the distribution.
*	Neither the name of the MyBar nor the names of its contributors may be 
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

package se.turbotorsk.mybar.test;

import se.turbotorsk.mybar.AboutBox;
import se.turbotorsk.mybar.AddIngredientActivity;
import se.turbotorsk.mybar.CollectionActivity;
import se.turbotorsk.mybar.MainActivity;
import se.turbotorsk.mybar.SettingsActivity;
import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;


//Written by Adam Clark
//It seems like the tests are evaluated alphabetically, therefore im adding a number in each
//test, to have a good overview and so that it is easier to manage the code. 

public class MyBarRobotiumTest extends ActivityInstrumentationTestCase2<MainActivity> {
	
	private Solo solo;

	public MyBarRobotiumTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
//In some way this method fu**s the tests up.
//	public void tearDown() throws Exception {
//		try {
//			solo.finalize();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		getActivity().finish();
//		super.tearDown();
//		System.exit(0);
//	}
	

//This basic test tries to start the application and checks weather it started
//or not.
	public void test1_StartApplication(){
		solo.assertCurrentActivity("Nu blev det fel activity", MainActivity.class);
	}
	
//This test will press an emulated right button, it is not a clean or good way to try this
//but i haven't managed to get robotium to press the COLLECTION tab. This has to do for now.
	public void test2_ChangeTabToCollection(){
		solo.assertCurrentActivity("First activity", MainActivity.class);
		solo.sendKey(solo.RIGHT);
		solo.assertCurrentActivity("Ska ha bytt activity", CollectionActivity.class);
	}
	
	//WORKS
//	public void testOpenAddButton(){
//		solo.clickOnMenuItem("Add");
//		solo.assertCurrentActivity("Ska vara add aktiviteten", AddIngredientActivity.class);
//		solo.goBack();
//	}
//	
	//WORKS
//	public void testOpenSettingsButton(){
//		solo.clickOnMenuItem("Settings");
//		solo.assertCurrentActivity("Ska vara settings aktiviteten", SettingsActivity.class);
//		solo.goBack();
//	}

	//Messes up the other tests at the moment
	/*
	public void testOpenShareButton(){
		solo.clickOnMenuItem("Share");
		solo.goBack();
		solo.goBack();
		solo.clickOnText("OK");
	}*/
	
	//"WORKS"
//	public void testOpenAboutBox(){
//		solo.clickOnMenuItem("About");
//		solo.assertCurrentActivity("Ska vara about aktiviteten", AboutBox.class);
//		solo.goBack();
//	}
//	
	//WORKS
//	public void testCheckMemory(){
//		solo.assertMemoryNotLow();
//	}
//	
//	public void testChangeToCollection(){
//		solo.clickOnButton("COLLECTION");
//	}
}
