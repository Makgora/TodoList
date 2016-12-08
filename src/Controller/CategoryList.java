package Controller;

import Model.Category;
import Model.Exception.CategoryException;
import Model.Exception.TaskException;
import Model.Task;
import sun.misc.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CategoryList implements Serializable {

    private ArrayList<Category> categories;
    private static CategoryList categoryList;

    private CategoryList()
    {
        if(new File("CategoryList.ser").exists())
        {
            this.categories = deserialize().getAllCategories();
        } else {
            this.categories = new ArrayList<>();
        }
        categoryList = this;
    }

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

    public void serialize() throws IOException
    {
        File file = new File("CategoryList.ser");
        file.createNewFile(); //Make a file if doesn't exist

        FileOutputStream fileOut = new FileOutputStream(file);
        byte[] data = SerializeHelper.serialize(this).getBytes();
        fileOut.write(data);
        fileOut.close();
    }

    private static CategoryList deserialize()
    {
        try
        {
            String data = new String(Files.readAllBytes(Paths.get("CategoryList.ser")));
            CategoryList categoryList = (CategoryList) SerializeHelper.deserialize(data);
            return categoryList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

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

    public void removeCategory(int i) throws TaskException {
        Category c = categories.get(i);
        TaskList.getTaskList().removeACategoryFromAllTask(c);
        categories.remove(i);
    }

    public void addNewCategory(Category newCategory)
    {
        this.categories.add(newCategory);
    }

    public ArrayList<Category> getAllCategories() {
        return this.categories;
    }
}