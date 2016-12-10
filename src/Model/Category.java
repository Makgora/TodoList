package Model;

import Controller.CategoryList;
import Model.Exception.CategoryException;

import java.io.Serializable;

public class Category implements Serializable {

    private String name;

    /**
     * @param name, the name of the category
     * @throws CategoryException, when the name is an empty string
     */
    public Category(String name) throws CategoryException
    {
        this.setName(name);
    }

    /**
     * Getter
     * @return the name of the category
     */
    public String getName()
    {
        return this.name;
    }


    /**
     * Setter
     * @param newName, the new name of the category
     * @throws CategoryException, when the name is an empty string
     */
    public void setName(String newName) throws CategoryException
    {
        if(newName.equals(""))
        {
            throw new CategoryException("the new name is invalid");
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
