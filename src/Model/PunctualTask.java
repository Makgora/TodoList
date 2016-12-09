package Model;

import Model.Exception.TaskException;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class PunctualTask extends Task implements Serializable {

    private boolean isAccomplished;

    /**
     * Constructor
     * @param title, the title of the task
     * @param beginDate, the begin date of the task
     * @param endDate, the date the task should be done for
     * @param category, the category related to this task
     * @throws TaskException, can be throw if the task is already accomplished, or if some invalid value are passed
     * @throws ParseException, can be throw if the date are invalid
     */
    public PunctualTask(String title, String beginDate, String endDate, Category category) throws TaskException, ParseException
    {
        super(title, beginDate, endDate, category);
        this.isAccomplished = false;
    }

    /**
     * Getter
     * @return whether the task is late or not
     */
    public boolean isLate()
    {
        return new Date().after(this.getEndDate());
    }

    /**
     * Setter
     * @param accomplished, the accomplished state of the task
     */
    public void setAccomplished(boolean accomplished) {
        isAccomplished = accomplished;
    }

    /**
     * @return whether the task is accomplished or not
     */
    public boolean isAccomplished()
    {
        return this.isAccomplished;
    }
}
