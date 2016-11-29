package Model;

import Model.Exception.CategorieException;
import Model.Exception.TaskException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Task {

    private String title;
    private Date beginDate;
    private Date endDate;
    private Category category;

    public Task(String title, String beginDate, String endDate, String category) throws TaskException, CategorieException, ParseException
    {
        this.setTitle(title);
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        this.setCategory(category);
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

    public Category getCategory()
    {
        return this.category;
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
        Date newBeginDateD = new SimpleDateFormat("dd/mm/yy").parse(newBeginDate);

        if(this.endDate == null || newBeginDateD.before(this.endDate))
        {
            this.beginDate = newBeginDateD;
        }
        else
        {
            throw new TaskException("La nouvelle date de debut de la tache est ulterieure à sa date de fin");
        }
    }
    
    public void setEndDate(String newEndDate) throws TaskException, ParseException
    {
        Date newEndDateD = new SimpleDateFormat("dd/mm/yy").parse(newEndDate);

        if(this.endDate == null || newEndDateD.after(this.beginDate))
        {
            this.endDate = newEndDateD;
        }
        else
        {
            throw new TaskException("La nouvelle date de fin de la tache est anterieure à sa date de debut");
        }
    }

    public void setCategory(String newCategory) throws CategorieException
    {
        this.category = Category.create(newCategory);
    }

    public boolean isLate() {
        return this.endDate.before(new Date());
    }

    public void modify(String newTitle, String newCategory) throws TaskException, ParseException, CategorieException
    {
        if(this.isAccomplished())
        {
            throw new TaskException("La tâche est déjà terminée");
        }
        else
        {
            this.setTitle(newTitle);
            this.setCategory(newCategory);
        }
    }

    public abstract boolean isAccomplished();

    public String toString()
    {
        return "Titre : " + this.title + ", Date de debut : " + this.beginDate.toString() + ", Date de fin : " + this.endDate.toString() + ", Categorie : " + this.category;
    }
}
