package se.turbotorsk.mybar.test;

/**
 * 
 * 
 * @author Karlgren
 *
 */
import junit.framework.Assert;
import android.test.AndroidTestCase;

public class SimpleTest extends AndroidTestCase {

    public void testMath() throws Throwable {
       Assert.assertTrue(1 + 1 == 2);
    }

    public void testWrongMath() throws Throwable {
       Assert.assertTrue(1 + 1 == 3);
    }
}