package Model;

import Controller.TaskList;
import Model.Exception.CategoryException;
import Model.Exception.TaskException;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Task implements Serializable {

    private String title;
    private Date beginDate;
    private Date endDate;
    private Category category;
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/mm/yy");

    public Task(String title, String beginDate, String endDate, Category category) throws TaskException, CategoryException, ParseException
    {
        this.setTitle(title);
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        this.setCategory(category);
    }

    public String getTitle()
    {
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
        if(this.isAccomplished())
        {
            throw new TaskException("Task already accomplished");
        }
        else
        {
            if (newTitle.equals(""))
            {
                throw new TaskException("New title is invalid");
            }
            else
            {
                this.title = newTitle;
            }
        }
    }

    public void setBeginDate(String newBeginDate) throws TaskException, ParseException
    {
        Date newBeginDateD = DATE_FORMAT.parse(newBeginDate);
        if(this.isAccomplished())
        {
            throw new TaskException("Task already accomplished");
        }
        else
        {
            if (this.endDate == null || newBeginDateD.before(this.endDate))
            {
                this.beginDate = newBeginDateD;
            }
            else
            {
                throw new TaskException("New BeginDate has to be before EndDate");
            }
        }
    }
    
    public void setEndDate(String newEndDate) throws TaskException, ParseException
    {
        Date newEndDateD = DATE_FORMAT.parse(newEndDate);

        if(this.isAccomplished())
        {
            throw new TaskException("Task already accomplished");
        }
        else
        {
            if (this.endDate == null || newEndDateD.after(this.beginDate))
            {
                this.endDate = newEndDateD;
            }
            else
            {
                throw new TaskException("La nouvelle date de fin de la tache est anterieure à sa date de debut");
            }
        }
    }

    public void setCategory(Category newCategory) throws TaskException
    {
        if(this.isAccomplished())
        {
            throw new TaskException("Task already accomplished");
        }
        else
        {
            this.category = newCategory;
        }
    }

    public boolean isLate()
    {
        return this.endDate.before(new Date());
    }

    public abstract boolean isAccomplished();

    @Override
    public String toString()
    {
        String endDate = DATE_FORMAT.format(getEndDate());
        String isAcc = this.isAccomplished() ? ", est Terminée" : ", à terminer";
        return this.title + isAcc + ", a finir pour " + endDate + ", " + this.category.getName();
    }
}
