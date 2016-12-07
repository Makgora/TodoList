package Model;

import Controller.CategoryList;
import Controller.TaskList;
import Model.Exception.CategoryException;
import Model.Exception.TaskException;

import java.io.Serializable;

public class Category implements Serializable {

    private String name;

    private Category(String name) throws CategoryException
    {
        this.setName(name);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String newName) throws CategoryException
    {
        if(newName.equals(""))
        {
            throw new CategoryException("the new name is null");
        }
        else
        {
            this.name = newName;
        }
    }

    public String toString()
    {
        return this.getName();
    }
}
