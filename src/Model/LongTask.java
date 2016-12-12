package Model;

import Model.Exception.TaskException;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.PrimitiveIterator;

public class LongTask extends Task implements Serializable {

    private int advancement;

    /**
     * Constructor
     * @param title, the title of the task
     * @param beginDate, the begin date of the task
     * @param endDate, the date the task should be done for
     * @param category, the category related to this task
     * @throws TaskException, can be throw if the task is already accomplished, or if some invalid value are passed
     * @throws ParseException, can be throw if the date are invalid
     */
    public LongTask(String title, String beginDate, String endDate, Category category, Priority priority) throws TaskException, ParseException
    {
        super(title, beginDate, endDate, category, priority);
        this.advancement = 0;
    }

    /**
     * Getter
     * @return whether the task is late or not
     */
    public boolean isLate()
    {
        long timeBetweenBeginAndEnd = this.getEndDate().getTime() - this.getBeginDate().getTime();
        long timeBetweenTodayAndEnd = this.getEndDate().getTime() - new Date().getTime();
        double timeElapsed = timeBetweenBeginAndEnd / timeBetweenTodayAndEnd;

        if(timeElapsed < 0.25)
        {
            return false;
        }
        else if (timeElapsed < 0.5)
        {
            return this.advancement < 25;
        }
        else if (timeElapsed < 0.75)
        {
            return this.advancement < 50;
        }
        else if (timeElapsed < 1)
        {
            return this.advancement < 75;
        }
        else
        {
            return this.advancement < 100;
        }
    }

    public int nextQuartToDo()
    {
        if(this.advancement < 25)
        {
            return 25;
        }
        else if(this.advancement < 50)
        {
            return 50;
        }
        else if(this.advancement < 75)
        {
            return 75;
        }
        else
        {
            return 100;
        }
    }

    /**
     * Getter
     * @return whether the task is accomplished or not
     */
    @Override
    public boolean isAccomplished()
    {
        return advancement >= 100;
    }

    /**
     * Getter
     * @return the advancement of the task
     */
    public int getAdvancement() {
        return advancement;
    }

    /**
     * Setter
     * @param newAdvancement, the new advancement
     */
    public void setAdvancement(int newAdvancement)
    {
        this.advancement = newAdvancement;
    }
}
