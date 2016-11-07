import org.testng.Reporter;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;

import static org.testng.Assert.*;

/**
 * Created by osiris on 29/10/16.
 */
public class TaskTest {

    @BeforeClass
    public void setUpBeforeClass() throws Exception{
        Reporter.log(" \n - Start of " + this.getClass() + " -", true);
    }

    @AfterClass
    public void tearDownAfterClass() {
        Reporter.log(" \n - End of " + this.getClass() + " -", true);
    }

    @BeforeMethod
    public void setUpBeforeMethod() throws Exception {
        Reporter.log("---------------------------------------------------------------------------------------------------", true);
    }

    @AfterMethod
    public void tearDownAfterMethod() throws Exception {
        Reporter.log("---------------------------------------------------------------------------------------------------", true);
    }

    @Test
    public void testCreate() throws Exception {

        Reporter.log("Test : create()", true);
        
        String titre = "TestTache";
        String dateDebut = "28/08/16";
        String dateFin = "30/08/16";

        Reporter.log("Création d'une tache avec les paramètres (titre: " + titre + ", dateDebut: " + dateDebut + ", dateFin: " + dateFin + ")", true);
        Task testTask = Task.create(titre, dateDebut, dateFin);
        Reporter.log("OK", true);

        Reporter.log("\nLancement des tests:", true);
        Reporter.log("--> tache non nulle", true);
        assertNotNull(testTask);
        Reporter.log("--> tache.titre() == titre", true);
        assertEquals(testTask.getTitle(), titre);
        Reporter.log("--> tache.dateDebut() == dateDebut", true);
        assertEquals(testTask.getBeginDate(), new SimpleDateFormat("dd/mm/yy").parse(dateDebut));
        Reporter.log("--> tache.dateFin() == dateFin", true);
        assertEquals(testTask.getEndDate(), new SimpleDateFormat("dd/mm/yy").parse(dateFin));
        Reporter.log("--> tache.dateDebut < tache.dateFin", true);
        assertTrue(testTask.getBeginDate().before(testTask.getEndDate()));
        Reporter.log("OK", true);
    }

    @Test
    public void testGetTitre() throws Exception {

    }

    @Test
    public void testSetTitre() throws Exception {

    }

    @Test
    public void testGetDateDebut() throws Exception {

    }

    @Test
    public void testSetDateDebut() throws Exception {

    }

    @Test
    public void testGetDateFin() throws Exception {

    }

    @Test
    public void testSetDateFin() throws Exception {

    }

    @Test
    public void testIsEnRetard() throws Exception {

    }

}