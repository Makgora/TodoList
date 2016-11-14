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

    public void setTitle(String newTitle) throws TaskException
    {
        if(newTitle == null)
        {
            throw new TaskException("Le nouveau titre est null");
        }
        else
        {
            this.title = newTitle;
        }
    }

    public void setBeginDate(String newBeginDate) throws TaskException, ParseException
    {
        if(newBeginDate == null)
        {
            throw new TaskException("La nouvelle date de debut est null");
        }
        else
        {
            Date newBeginDateD = new SimpleDateFormat("dd/mm/yy").parse(newBeginDate);

            if(this.endDate == null || !newBeginDateD.after(this.endDate))
            {
                this.beginDate = newBeginDateD;
            }
            else
            {
                throw new TaskException("La date de debut de la tache est ulterieure à sa date de fin");
            }
        }
    }
    
    public void setEndDate(String newEndDate) throws TaskException, ParseException
    {
        if(newEndDate == null)
        {
            throw new TaskException("La nouvelle date de fin est null");
        }
        else
        {
            Date newEndDateD = new SimpleDateFormat("dd/mm/yy").parse(newEndDate);

            if(this.beginDate.after(newEndDateD))
            {
                throw new TaskException("La date de debut de la tache est ulterieure à sa date de fin");
            }
            else
            {
                this.endDate = newEndDateD;
            }
        }
    }

    public void setCategory(String newCategory) throws CategorieException
    {
        this.category = Category.create(newCategory);
    }

    public boolean isLate() {
        return this.endDate.before(new Date());
    }

    public boolean isFinished() {
        return isFinished;
    }

    public static Task create(String title, String beginDate, String endDate, String category) throws TaskException, ParseException, CategorieException
    {
        Task task = new Task();
        task.setTitle(title);
        task.setBeginDate(beginDate);
        task.setEndDate(endDate);
        task.setCategory(category);
        return task;
    }

    public void modify(String newTitle, String newEndDate, String newCategory) throws TaskException, ParseException, CategorieException
    {
        if(this.isFinished())
        {
            throw new TaskException("La tâche est déjà terminée");
        }
        else
        {
            this.setTitle(newTitle);
            this.setEndDate(newEndDate);
            this.setCategory(newCategory);
        }

    }

    public String toString()
    {
        return "Titre : " + this.title + ", Date de debut : " + this.beginDate.toString() + ", Date de fin : " + this.endDate.toString() + ", Categorie : " + this.category;
    }
}
