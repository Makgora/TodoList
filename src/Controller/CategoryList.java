package Controller;

import Model.Category;
import Model.Exception.TaskException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 */
public class CategoryList implements Serializable {

    private ArrayList<Category> categories;
    private static CategoryList categoryList;

    /**
     * Constructor
     * Deserialize from the file CategoryList.ser if it exist
     */
    private CategoryList()
    {
        if(new File("CategoryList.ser").exists())
        {
            this.categories = deserialize().getCategories();
        } else {
            this.categories = new ArrayList<>();
            addNewCategory(new Category(Category.WORK));
            addNewCategory(new Category(Category.PERSONAL));
        }
        categoryList = this;
    }


    /**
     * Getter
     * @return the current instance of CategoryList so that they are all in sync
     */
    public static CategoryList getCategoryList()
    {
        if (categoryList == null)
        {
            return new CategoryList();
        }
        else
        {
            return categoryList;
        }
    }

    /**
     * Serialize the current instance of CategoryList
     * @throws IOException, related to file manipulation
     */
    public void serialize() throws IOException
    {
        File file = new File("CategoryList.ser");
        file.createNewFile(); //Make a file if doesn't exist

        FileOutputStream fileOut = new FileOutputStream(file);
        byte[] data = SerializeHelper.serialize(this).getBytes();
        fileOut.write(data);
        fileOut.close();
    }

    /**
     * Deserialize the CategoryList instance from CategoryList.ser
     * @return CategoryList saved from a previous session
     */
    private static CategoryList deserialize()
    {
        try
        {
            String data = new String(Files.readAllBytes(Paths.get("CategoryList.ser")));
            return (CategoryList) SerializeHelper.deserialize(data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Getter
     * @param name, the name of the category
     * @return the category related
     */
    public Category getCategory(String name) {
        for(Category category : categories)
        {
            if(category.getName().equals(name))
            {
                return category;
            }
        }
        return null;
    }

    /**
     * Remove a category at a given index
     * @param i the index
     * @throws TaskException, because we set the category to null to all task that have the category
     */
    public void removeCategory(int i) throws TaskException {
        Category c = categories.get(i);
        TaskList.getTaskList().removeACategoryFromAllTask(c);
        categories.remove(i);
    }

    /**
     * Add a new category
     * @param newCategory, the new category
     */
    public void addNewCategory(Category newCategory)
    {
        this.categories.add(newCategory);
    }

    /**
     * @param name, the name to check
     * @return if the name already exist
     */
    public boolean exists(String name)
    {
        for(Category category : this.categories)
        {
            if(category.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Getter
     * @return all the current categories
     */
    public ArrayList<Category> getCategories() {
        return this.categories;
    }
}