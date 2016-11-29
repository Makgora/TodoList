import org.testng.Reporter;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;

import static org.testng.Assert.*;

/**
 * Created by osiris on 29/10/16.
 */
public class TaskTest {

    @BeforeClass
    public void setUpBeforeClass() throws Exception {
        Reporter.log("\n==================================================================================================", true);
        Reporter.log(" - Start of " + this.getClass() + " -", true);
        Reporter.log("==================================================================================================", true);
    }

    @AfterClass
    public void tearDownAfterClass() {
        Reporter.log("\n==================================================================================================", true);
        Reporter.log(" - End of " + this.getClass() + " -", true);
        Reporter.log("==================================================================================================", true);

    }

    @BeforeMethod
    public void setUpBeforeMethod() throws Exception {
        Reporter.log("--------------------------------------------------------------------------------------------------", true);
    }

    @AfterMethod
    public void tearDownAfterMethod() throws Exception {
        Reporter.log("--------------------------------------------------------------------------------------------------", true);
    }

    @Test
    public void testCreate() throws Exception {
        Reporter.log("Création des tâches", true);
    }

}