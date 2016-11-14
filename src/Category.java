import java.util.ArrayList;

public class Category {

    private static ArrayList<Category> categories;
    private String name;

    private Category(String name) throws CategorieException
    {
        if(name == null)
        {
            throw new CategorieException("Le nouveau name est null");
        } else
        {
            if(categories.indexOf(name) == -1)
            {
                this.name = name;
                categories.add(this);
            }
            else
            {
                this.name = name;
            }
        }
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
        }
        else
        {
            this.name = newName;
        }
    }

    public static Category create(String name) throws CategorieException
    {
        if(name == null)
        {
            throw new CategorieException("Le nouveau name est null");
        } else
        {
            int i = categories.indexOf(name);

            if(i != -1)
            {
                return categories.get(i);
            }
            else
            {
                Category newCat = new Category(name);
                categories.add(newCat);
                return newCat;
            }
        }
    }

    public void delete(String oldCat) throws CategorieException
    {
        int index;

        if(oldCat == null)
        {
            throw new CategorieException("L'argument est null");
        } else
        {
            index = categories.indexOf(oldCat);

            if(index == -1)
            {
                throw new CategorieException("La categorie n'existe pas");

            } else
            {
                categories.remove(index);
            }
        }
    }
}
