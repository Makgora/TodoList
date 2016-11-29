package Model;

import Controller.CategoryList;
import Controller.TaskList;
import Model.Exception.CategoryException;
import Model.Exception.TaskException;
import org.jetbrains.annotations.Contract;

public class Category {

    private String name;

    private Category(String name) throws CategoryException
    {
        this.setName(name);
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

    @Contract("null -> fail")
    public static Category create(String name) throws CategoryException
    {
        Category categoryToCreate = CategoryList.getCategoryList().getCategory("name");

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

            } else
            {
                TaskList.getTaskList().removeAllCategory(categoryToDelete);
            }
        }
    }

    public String toString()
    {
        return this.getName();
    }
}
