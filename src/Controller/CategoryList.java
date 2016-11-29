package Controller;

import Model.Category;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by osiris on 29/11/16.
 */
public class CategoryList {

    private ArrayList<Category> categories;
    private static CategoryList categoryList;

    private CategoryList() {
        //TODO SERIALISER
    }

    public static CategoryList getCategoryList() {
        if (categoryList == null) {
            return new CategoryList();
        } else {
            return categoryList;
        }
    }

    public Category getCategory(String name)
    {
        Iterator<Category> categoryIterator = this.categories.iterator();
        Category currentCategory;

        while(categoryIterator.hasNext())
        {
            currentCategory = categoryIterator.next();
            if(currentCategory.getName() == name)
            {
                return currentCategory;
            }
        }
        return null;
    }

    public ArrayList getAllCategories() {
        return this.categories;
    }

    public void addNewCategory(Category newCategory)
    {
        this.categories.add(newCategory);
    }

}