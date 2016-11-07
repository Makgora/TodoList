import java.util.ArrayList;

public class Category {

    private static ArrayList<Category> categories;
    private String name;

    public Category(String name)
    {
        this.name = name;
        categories.add(this);
    }

    public String getName()
    {
        return this.name;
    }

    public void modify(String newName) throws CategorieException
    {
        if(newName == null)
        {
            throw new CategorieException("Le nouveau name est null");
        } else
        {
            this.name = newName;
        }
    }

    public Category create(String name) throws CategorieException
    {
        if(name == null)
        {
            throw new CategorieException("Le nouveau name est null");
        } else
        {
            Category newCat = new Category(name);
            categories.add(newCat);
            return newCat;
        }
    }
}
