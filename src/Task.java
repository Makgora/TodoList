import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private String title;
    private Date beginDate;
    private Date endDate;
    private Category category;
    private boolean isFinished;

    public Task() {}

    private Task(String title, Date beginDate, Date endDate, Category category, boolean isFinished)
    {
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.category = category;
        this.isFinished = isFinished;
    }

    public String getTitle() {
        return this.title;
    }

    public Date getBeginDate() {
        return this.beginDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public boolean isLate() {
        return this.endDate.before(new Date());
    }

    public boolean isFinished() {
        return isFinished;
    }

    public static Task create(String titre, String beginDate, String endDate, String category) throws TaskException, ParseException, CategorieException
    {
        if(titre == null || beginDate == null || endDate == null || category == null)
        {
            throw new TaskException("Un des parametres est null");
        }
        else
        {
            Date newBeginDate = new SimpleDateFormat("dd/mm/yy").parse(beginDate);
            Date newDateFin = new SimpleDateFormat("dd/mm/yy").parse(endDate);

            if(newBeginDate.after(newDateFin))
            {
                throw new TaskException("La date de debut de la tache est ulterieure à sa date de fin");
            }
            else
            {
                return new Task(titre, newBeginDate, newDateFin, Category.create(category), false);
            }
        }
    }

    public void modify(String newTitle, Date newEndDate, Category newCategory) throws TaskException
    {
        if(this.isFinished())
        {
            throw new TaskException("La tache est terminee, on ne peut plus la modifier");
        }
        else
        {
            if(newTitle != null) {this.title = newTitle;}

            if(newCategory != null) {this.category = newCategory;}

            if(newEndDate != null)
            {
                if(newEndDate.before(this.beginDate))
                {
                    throw new TaskException("La nouvelle date de fin est antérieure à la date de debut");
                }
                else
                {
                    this.endDate = newEndDate;
                }
            }
        }
    }

    public String toString()
    {
        return "Titre : " + this.title + ", Date de debut : " + this.beginDate.toString() + ", Date de fin : " + this.endDate.toString() + ", Categorie : " + this.category;
    }
}
