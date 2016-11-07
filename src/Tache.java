import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by osiris on 25/10/16.
 */
public class Tache {

    private String titre;
    private Date dateDebut;
    private Date dateFin;

    public Tache() {}

    private Tache(String titre, Date dateDebut, Date dateFin)
    {
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String newTitre) {
        this.titre = newTitre;
    }

    public Date getDateDebut() {
        return this.dateDebut;
    }

    public void setDateDebut(Date newDateDebut) {
        this.dateDebut = newDateDebut;
    }

    public Date getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(Date newDateFin) {
        this.dateFin = newDateFin;
    }

    public boolean isEnRetard() {
        return this.dateFin.before(new Date());
    }

    public static Tache create(String titre, String dateDebut, String dateFin) throws TacheException, ParseException
    {
        if(titre == null || dateDebut.equals(null) || dateFin.equals(null))
        {
            throw new TacheException("Un des parametres est null");
        } else
        {
            Date newDateDebut = new SimpleDateFormat("dd/mm/yy").parse(dateDebut);
            Date newDateFin = new SimpleDateFormat("dd/mm/yy").parse(dateFin);

            if(newDateDebut.after(newDateFin))
            {
                throw new TacheException("La date de debut de la tache est ulterieure à sa date de fin");
            } else
            {
                return new Tache(titre, newDateDebut, newDateFin);
            }
        }
    }

    public String toString()
    {
        return "Titre : " + this.titre + ", Date de debut : " + this.dateDebut.toString() + ", Date de fin : " + this.dateFin.toString();
    }
}
