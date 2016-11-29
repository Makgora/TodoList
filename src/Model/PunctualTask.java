package Model;

import Model.Exception.CategoryException;
import Model.Exception.TaskException;

import java.text.ParseException;

public class PunctualTask extends Task {

    private boolean isAccomplished;

    public PunctualTask(String title, String beginDate, String endDate, String category) throws TaskException, ParseException, CategoryException
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
