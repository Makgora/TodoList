package Controller;

import Model.Category;

import java.io.*;
import java.util.ArrayList;

public class CategoryList implements Serializable {

    private ArrayList<Category> categories;
    private static CategoryList categoryList;

    private CategoryList()
    {
        if(!new File("CategoryList.ser").exists())
        {
            this.categories = new ArrayList<>();
        }
        else
        {
            categoryList = deserialize();
        }
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
        file.createNewFile();//Make a file if doesn't exist

        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream outStream = new ObjectOutputStream(fileOut);

        outStream.writeObject(this);

        outStream.close();
        fileOut.close();
    }

    private static CategoryList deserialize()
    {
        try
        {
            FileInputStream fileIn = new FileInputStream(new File("CategoryList.ser"));
            ObjectInputStream inStream = new ObjectInputStream(fileIn);

            CategoryList categoryList = (CategoryList)inStream.readObject();

            inStream.close();
            fileIn.close();

            return categoryList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public Category getCategory(String name)
    {
        for(Category category : getCategoryList().categories)
        {
            if(category.getName().equals(name))
            {
                return category;
            }
        }
        return null;
    }

    public void addNewCategory(Category newCategory)
    {
        this.categories.add(newCategory);
    }

    public ArrayList getAllCategories() {
        return this.categories;
    }

    public String toString()
    {
        String list = "Category List:";
        for(Category category : getCategoryList().categories)
        {
            list = list + "\n" + category.toString();
        }

        return list;
    }
}