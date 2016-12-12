package Model;

import Controller.CategoryList;
import Model.Exception.CategoryException;

import java.io.Serializable;

public class Category implements Serializable {

    private String name;
    public static final int WORK = 1;
    public static final int PERSONAL = 2;

    /**
     * @param name, the name of the category
     * @throws CategoryException, when the name is an empty string
     */
    public Category(String name) throws CategoryException
    {
        this.setName(name);
    }

    public Category(int i) {
        if (i == WORK) {
            this.name = "Work";
        } else if (i == PERSONAL) {
            this.name = "Personal";
        }
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
        else if (CategoryList.getCategoryList().exists(newName))
        {
            throw new CategoryException("The category already exists");
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
