package Model;

import Model.Exception.CategoryException;
import Model.Exception.TaskException;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class LongTask extends Task implements Serializable {

    private int advancement;

    public LongTask(String title, String beginDate, String endDate, String category) throws TaskException, ParseException, CategoryException
    {
        super(title, beginDate, endDate, category);
        this.advancement = 0;
    }

    @Override
    public boolean isLate()
    {
        long timeBetweenBeginAndEnd = this.getEndDate().getTime() - this.getBeginDate().getTime();
        long timeBetweenTodayAndEnd = this.getEndDate().getTime() - new Date().getTime();
        long timeElapsed = timeBetweenBeginAndEnd / timeBetweenTodayAndEnd;

        if(timeElapsed < 0.25)
        {
            return false;
        }
        else if (timeElapsed < 0.5)
        {
            return advancement >= 25;
        }
        else if (timeElapsed < 0.75)
        {
            return advancement >= 50;
        }
        else if (timeElapsed < 1)
        {
            return advancement >= 75;
        }
        else //(timeElapsed == 1)
        {
            return advancement >= 100;
        }
    }

    @Override
    public boolean isAccomplished()
    {
        return advancement >= 100;
    }

    public void setAdvancement(int newAdvancement)
    {
        this.advancement = newAdvancement;
    }
}
