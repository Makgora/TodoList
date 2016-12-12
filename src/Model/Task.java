package Model;

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
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy");
    public static final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
    private Priority priority;

    public enum Priority {
        HIGH, MEDIUM, LOW;
    }

    /**
     * Constructor
     * @param title, the title of the task
     * @param beginDate, the begin date of the task
     * @param endDate, the date the task should be done for
     * @param category, the category related to this task
     * @param priority, the priority of this task
     * @throws TaskException, can be throw if the task is already accomplished, or if some invalid value are passed
     * @throws ParseException, can be throw if the date are invalid
     */
    public Task(String title, String beginDate, String endDate, Category category, Priority priority) throws TaskException, ParseException
    {
        this.setTitle(title);
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        this.setCategory(category);
        this.setPriority(priority);
    }

    /**
     * Getter
     * @return the title
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     * Getter
     * @return the begin date
     */
    public Date getBeginDate() {
        return this.beginDate;
    }

    /**
     * Getter
     * @return the end date
     */
    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * Getter
     * @return the category
     */
    public Category getCategory()
    {
        return this.category;
    }

    /**
     * Getter
     * @return the priority
     */
    public Priority getPriority() {return this.priority;}

    /**
     * Setter
     * @param newPriority, the new priority of the task
     */
    public void setPriority(Priority newPriority)
    {
        this.priority = newPriority;
    }

    /**
     * Setter
     * @param newTitle, the new title of the task
     * @throws TaskException, if the new title is an empty string
     */
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

    /**
     * Setter
     * @param newBeginDate, the new begin date
     * @throws TaskException, if the task is already accomplished or the begin date is after end date
     * @throws ParseException, if the date is invalid
     */
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

    /**
     * Setter
     * @param newEndDate, the new end date
     * @throws TaskException, if the task is already accomplished or the end date is before the begin date
     * @throws ParseException, if the date is invalid
     */
    public void setEndDate(String newEndDate) throws TaskException, ParseException
    {
        Date newEndDateD = DATE_FORMAT.parse(newEndDate);

        if(this.isAccomplished())
        {
            throw new TaskException("Task already accomplished");
        }
        else
        {
            if (this.beginDate == null || newEndDateD.after(this.beginDate))
            {
                this.endDate = newEndDateD;
            }
            else
            {
                throw new TaskException("New EndDate has to be after BeginDate");
            }
        }
    }

    /**
     * Setter
     * @param newCategory, the new category
     * @throws TaskException
     */
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

    /**
     * @return whether the task is accomplished or not
     */
    public abstract boolean isAccomplished();

    /**
     * @return whether the task is late or not
     */
    public abstract boolean isLate();

    @Override
    public String toString()
    {
        long dayToGo = ((getEndDate().getTime() - new Date().getTime()) / MILLISECONDS_PER_DAY) + 1;

        String isAcc = this.isAccomplished() ? " is finished" : " is not finished";
        String isLate = this.isLate() ? ", is late" : ", isn't late";
        return "[" + this.title + "]" + isAcc + ", finish in " + dayToGo + " days, " + this.category.getName() + ", " + isLate ;
    }
}
