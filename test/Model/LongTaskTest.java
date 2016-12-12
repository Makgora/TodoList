package Model;

import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.Date;

import static org.testng.Assert.*;


public class LongTaskTest {

    private LongTask t;
    private final String title = "title";
    private final Date now = new Date();
    private final Date tomorow = new Date(new Date().getTime() + Task.MILLISECONDS_PER_DAY);
    private final Category category = new Category(Category.WORK);
    private final Task.Priority priority = Task.Priority.HIGH;

    @BeforeClass
    public void setUpBeforeClass() throws Exception {
        Reporter.log("\n==================================================================================================", true);
        Reporter.log(" - Start of " + this.getClass() + " -", true);
        Reporter.log("==================================================================================================", true);
        this.t = new LongTask(title, Task.DATE_FORMAT.format(now), Task.DATE_FORMAT.format(tomorow), category, priority);
        t.setAdvancement(50);
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
    public void testIsLate() throws Exception {
        assertTrue(!t.isLate());
    }

    @Test
    public void testNextQuartToDo() throws Exception {
        assertEquals(t.nextQuartToDo(), 75);
    }

    @Test
    public void testIsAccomplished() throws Exception {
        assertTrue(!t.isAccomplished());
    }

    @Test
    public void testGetAdvancement() throws Exception {
        assertEquals(t.getAdvancement(), 50);
    }

    @Test
    public void testSetAdvancement() throws Exception {
        t.setAdvancement(0);
        assertEquals(t.getAdvancement(),0);
    }

}