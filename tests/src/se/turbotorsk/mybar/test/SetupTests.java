package se.turbotorsk.mybar.test;

/**
 * A test suite containing tests for MyBar
 * 
 * @author Mathias Karlgren (matkarlg)
 *
 */
import junit.framework.Test;
import junit.framework.TestSuite;
import android.test.suitebuilder.TestSuiteBuilder;

public class SetupTests extends TestSuite {
    public static Test suite() {
        return new TestSuiteBuilder(SetupTests.class).includeAllPackagesUnderHere().build();
    }
}
