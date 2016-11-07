import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private String title;
    private Date beginDate;
    private Date endDate;
    private Category category;

    public Task() {}

    private Task(String title, Date beginDate, Date endDate, Category category)
    {
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.category = category;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public Date getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(Date newBeginDate) {
        this.beginDate = newBeginDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date newDateFin) {
        this.endDate = newDateFin;
    }

    public boolean isLate() {
        return this.endDate.before(new Date());
    }

    public static Task create(String titre, String beginDate, String endDate, String category) throws TaskException, ParseException, CategorieException
    {
        if(titre == null || beginDate == null || endDate == null || category == null)
        {
            throw new TaskException("Un des parametres est null");
        } else
        {
            Date newBeginDate = new SimpleDateFormat("dd/mm/yy").parse(beginDate);
            Date newDateFin = new SimpleDateFormat("dd/mm/yy").parse(endDate);

            if(newBeginDate.after(newDateFin))
            {
                throw new TaskException("La date de debut de la tache est ulterieure à sa date de fin");
            } else
            {
                return new Task(titre, newBeginDate, newDateFin, Category.create(category));
            }
        }
    }

    public String toString()
    {
        return "Titre : " + this.title + ", Date de debut : " + this.beginDate.toString() + ", Date de fin : " + this.endDate.toString();
    }
}
