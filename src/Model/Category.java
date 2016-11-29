package Model;

import Model.Exception.CategorieException;
import org.jetbrains.annotations.Contract;

import java.util.*;

public class Category {

    private String name;
    private static HashMap<Category, List<Task>> categories = new HashMap<>();

    private Category(String name) throws CategorieException
    {
        this.name = name;
        categories.put(this, new ArrayList<>());
    }

    public String getName()
    {
        return this.name;
    }

    public static Category exist(String name)
    {
        for (Category category : categories.keySet())
        {
            if (category.getName().equals(name))
            {
                return category;
            }
        }
        return null;
    }

    public void modify(String newName) throws CategorieException
    {
        if(newName == null)
        {
            throw new CategorieException("Le nouveau name est null");
        }
        else
        {
            this.name = newName;
        }
    }

    @Contract("null -> fail")
    public static Category create(String name) throws CategorieException
    {
        if(name == null)
        {
            throw new CategorieException("Le nouveau name est null");
        }
        else
        {
            Category categoryToCreate = exist(name);
            if(categoryToCreate != null)
            {
                return categoryToCreate;
            }
            else
            {
                return new Category(name);
            }
        }
    }

    public static void delete(String oldCat) throws CategorieException
    {
        int index;

        if(oldCat == null)
        {
            throw new CategorieException("L'argument est null");
        } else
        {
            Category categoryToDelete = exist(oldCat);

            if(categoryToDelete == null)
            {
                throw new CategorieException("La categorie n'existe pas");

            } else
            {

                for(Task task : categories.get(categoryToDelete))
                {
                    task.setCategory("SansCategory");
                }
                categories.remove(categoryToDelete);
            }
        }
    }

    public String toString()
    {
        return this.getName();
    }
}
