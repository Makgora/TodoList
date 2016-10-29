import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;

import static org.testng.Assert.*;

/**
 * Created by osiris on 29/10/16.
 */
public class TacheTest {

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
    public void testTache() throws Exception {

        Reporter.log("Test : testTache", true);
        
        String titre = "TestTache";
        String dateDebut = "28/08/16";
        String dateFin = "30/08/16";

        Reporter.log("Création d'une tache avec les paramètres (titre: " + titre + ", dateDebut: " + dateDebut + ", dateFin: " + dateFin + ")", true);
        Tache testTache = new Tache(titre, dateDebut, dateFin);
        Reporter.log("OK", true);

        Reporter.log("\nLancement des tests:", true);
        Reporter.log("--> tache non nulle", true);
        assertNotNull(testTache);
        Reporter.log("--> tache.titre() == titre", true);
        assertEquals(testTache.getTitre(), titre);
        Reporter.log("--> tache.dateDebut() == dateDebut", true);
        assertEquals(testTache.getDateDebut(), new SimpleDateFormat("dd/mm/yy").parse(dateDebut));
        Reporter.log("--> tache.dateFin() == dateFin", true);
        assertEquals(testTache.getDateFin(), new SimpleDateFormat("dd/mm/yy").parse(dateFin));
        Reporter.log("--> tache.dateDebut < tache.dateFin", true);
        assertTrue(testTache.getDateDebut().before(testTache.getDateFin()));
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