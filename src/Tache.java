import java.util.Calendar;
import java.util.Date;

/**
 * Created by osiris on 25/10/16.
 */
public class Tache {

    protected String titre;
    protected DateTime dateDebut;
    protected DateTime dateFin;
    protected boolean enRetard;

    public Tache() {}

    public Tache(String titre, String dateDebut, String dateFin)
    {

        this.titre = titre; 
       // this.dateDebut = dateDebut;
       // this.dateFin = dateFin;
        //this.enRetard =
    }

    public DateTime getDate(String date)
    {
        return null;
    }

    public boolean estEnRetard()
    {
        return true;
    }
}
