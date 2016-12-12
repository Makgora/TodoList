package Model;

import Model.Exception.TaskException;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.Date;

import static org.testng.Assert.*;


public class TaskTest {


    private PunctualTask t;
    private final String title = "title";
    private final Date now = new Date();
    private final Date tomorow = new Date(new Date().getTime() + Task.MILLISECONDS_PER_DAY);
    private final Category category = new Category(Category.TRAVAIL);


    @BeforeClass
    public void setUpBeforeClass() throws Exception {
        Reporter.log("\n==================================================================================================", true);
        Reporter.log(" - Start of " + this.getClass() + " -", true);
        Reporter.log("==================================================================================================", true);
        this.t = new PunctualTask(title, Task.DATE_FORMAT.format(now), Task.DATE_FORMAT.format(tomorow), category);
        t.setAccomplished(false);
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
    public void testGetTitle() throws Exception {
        assertEquals(t.getTitle(), title);
    }

    @Test
    public void testGetBeginDate() throws Exception {
        assertEquals(Task.DATE_FORMAT.format(t.getBeginDate()), Task.DATE_FORMAT.format(now));
    }

    @Test
    public void testGetEndDate() throws Exception {
        assertEquals(Task.DATE_FORMAT.format(t.getEndDate()), Task.DATE_FORMAT.format(tomorow));
    }

    @Test
    public void testGetCategory() throws Exception {
        assertEquals(t.getCategory(), category);
    }

    @Test
    public void testSetTitle() throws Exception {
        t.setTitle(title + "n");
        assertEquals(t.getTitle(), title + "n");
    }

    @Test(expectedExceptions = TaskException.class)
    public void testSetBeginDate() throws Exception {
        t.setBeginDate(Task.DATE_FORMAT.format(tomorow));
    }

    @Test(expectedExceptions = TaskException.class)
    public void testSetEndDate() throws Exception {
        t.setEndDate(Task.DATE_FORMAT.format(new Date(now.getTime() - Task.MILLISECONDS_PER_DAY)));
    }

}