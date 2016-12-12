package Model;

import org.testng.Reporter;
import org.testng.annotations.*;

import static org.testng.Assert.*;

/**
 * Created by fly on 12/11/16.
 */
public class CategoryTest {

    private Category c;

    @BeforeClass
    public void setUpBeforeClass() throws Exception {
        Reporter.log("\n==================================================================================================", true);
        Reporter.log(" - Start of " + this.getClass() + " -", true);
        Reporter.log("==================================================================================================", true);
        this.c = new Category("test");
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
    public void testGetName() throws Exception {
        assertEquals(this.c.getName(), "test");
    }

    @Test
    public void testSetName() throws Exception {
        this.c.setName("test2");
        assertEquals(this.c.getName(), "test2");
    }

}