import java.text.DateFormat;
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

    public Tache(String titre, String dateDebut, String dateFin) {
        this.titre = titre;
        try {
            this.dateDebut = new SimpleDateFormat("dd/mm/yy").parse(dateDebut);
            this.dateFin = new SimpleDateFormat("dd/mm/yy").parse(dateFin);
        } catch (ParseException e) {
            e.printStackTrace();
        }

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


}
