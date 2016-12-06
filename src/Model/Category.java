package Model;

import Controller.CategoryList;
import Controller.TaskList;
import Model.Exception.CategoryException;
import Model.Exception.TaskException;

import java.io.Serializable;

public class Category implements Serializable {

    private String name;
    private int id;
    private static int currentID = 0;

    private Category(String name) throws CategoryException
    {
        this.setName(name);
        this.id = currentID++;
        CategoryList.getCategoryList().addNewCategory(this);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String newName) throws CategoryException
    {
        if(newName == null)
        {
            throw new CategoryException("Le nouveau name est null");
        }
        else
        {
            this.name = newName;
        }
    }

    public static Category create(String name) throws CategoryException
    {
        Category categoryToCreate = CategoryList.getCategoryList().getCategory(name);

        if(categoryToCreate != null)
        {
            return categoryToCreate;
        }
        else
        {
            return new Category(name);
        }
    }

    public static void delete(String oldCat) throws CategoryException, TaskException
    {

        if(oldCat == null)
        {
            throw new CategoryException("L'argument est null");
        }
        else
        {
            Category categoryToDelete = CategoryList.getCategoryList().getCategory("name");

            if(categoryToDelete == null)
            {
                throw new CategoryException("La categorie n'existe pas");
            }
            else
            {
                TaskList.getTaskList().removeACategoryFromAllTask(categoryToDelete);
            }
        }
    }

    public String toString()
    {
        return this.getName();
    }
}
