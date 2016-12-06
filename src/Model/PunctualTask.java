package Model;

import Model.Exception.CategoryException;
import Model.Exception.TaskException;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class PunctualTask extends Task implements Serializable {

    private boolean isAccomplished;

    public PunctualTask(String title, Date beginDate, String endDate, Category category) throws TaskException, ParseException, CategoryException
    {
        super(title, beginDate, endDate, category);
        this.isAccomplished = false;
    }

    public void finish()
    {
        this.isAccomplished = true;
    }

    @Override
    public boolean isAccomplished()
    {
        return this.isAccomplished;
    }

    @Override
    public String toString()
    {
        return super.toString() + ", estAccomplie : " + this.isAccomplished();
    }
}
