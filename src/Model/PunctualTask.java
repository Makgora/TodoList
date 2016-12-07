package Model;

import Model.Exception.CategoryException;
import Model.Exception.TaskException;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class PunctualTask extends Task implements Serializable {

    private boolean isAccomplished;

    public PunctualTask(String title, String beginDate, String endDate, Category category) throws TaskException, ParseException, CategoryException
    {
        super(title, beginDate, endDate, category);
        this.isAccomplished = false;
    }

    public void setAccomplished(boolean accomplished) {
        isAccomplished = accomplished;
    }

    @Override
    public boolean isAccomplished()
    {
        return this.isAccomplished;
    }
}
