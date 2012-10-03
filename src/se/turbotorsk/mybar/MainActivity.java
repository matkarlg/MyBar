package se.turbotorsk.mybar;

//import android.app.ActionBar;
//import android.app.FragmentTransaction;
import se.turbotorsk.mybar.database.DrinkTable;
import se.turbotorsk.mybar.database.MyBarContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.NavUtils;
//import android.view.Gravity;
//import android.view.MenuItem;
//import android.view.ViewGroup;
//import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * sections. We use a {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will
     * keep every loaded fragment in memory. If this becomes too memory intensive, it may be best
     * to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter();


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);
        
        /**
         * Test Code SQLITE
         */
        Uri myBarUri = null;
        // SQLite uses autoincrement in the _id field
        Drink[] testDrinks = {
        		new Drink(0, "Margarita", "/margarita.jpg", "Martini Glass", "Rom", "Mix rom and ice", 5), 
        		new Drink(0, "Tequila", "/tequila.jpg", "Shot Glass", "Tequila", "Pour Tequila in glass", 5)
        };
        
        // Insert test
        for (Drink testDrink : testDrinks) {
        	ContentValues values = testDrink.getContentValues();
        	myBarUri = getContentResolver().insert(MyBarContentProvider.CONTENT_URI, values);
        	Log.d(this.getClass().getName(), "Test insert. Created row: " + myBarUri.toString());
        }
        
	    // Choose which columns you want to query. null queries all columns
	    String[] projection = { DrinkTable.COLUMN_NAME,	DrinkTable.COLUMN_DESCRIPTION, DrinkTable.COLUMN_RATING };
	    
	    // Query database
	    Cursor cursor = getContentResolver().query(MyBarContentProvider.CONTENT_URI, projection, null, null, null);
	    
	    // Successful query?
	    if (cursor != null) {
	    	
	    	// Is there any data from the requested Query
	    	if (cursor.moveToFirst()) {
	    		
	    		// Test Print
	    		Log.d(this.getClass().getName(), cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_NAME)));
	    		Log.d(this.getClass().getName(), cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_DESCRIPTION)));
	    		Log.d(this.getClass().getName(), cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_RATING)));
	    	}
	    	
	    	// Close the cursor
	    	cursor.close();
	    }
        /**
         * End of SQLite Test Code
         */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public class SectionsPagerAdapter extends PagerAdapter {

        public int getCount() {
            return 3;
        }

        
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return getString(R.string.title_section1).toUpperCase();
                case 1: return getString(R.string.title_section2).toUpperCase();
                case 2: return getString(R.string.title_section3).toUpperCase();
            }
            return null;
        }
        
        public Object instantiateItem(View collection, int position) {
       	 
            LayoutInflater inflater = (LayoutInflater) collection.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            int layoutId = 0;
            switch (position) {
            case 0:
            	layoutId = R.layout.menu_myfavorites;
                break;
            case 1:
            	layoutId = R.layout.menu_mybar;
                break;
            case 2:
            	layoutId = R.layout.menu_thecollection;
                break;
            }

            View view = inflater.inflate(layoutId, null);

            ((ViewPager) collection).addView(view, 0);

            return view;
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == ((View) arg1);

        }
        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
 
        }
        @Override
        public Parcelable saveState() {
            return null;
        }

    }
     
}