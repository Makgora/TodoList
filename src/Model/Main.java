package Model;

public class Main {
    public static void main(String[] arg) throws Exception {
        Task t = new PunctualTask("Test", "28/06/95", "29/06/95", "TestCat");
        System.out.println(t.toString());

        Category.delete("TestCat");

        System.out.println(t.toString());

        Integer f = new Integer(1);
        Integer g = f;

        System.out.println(f);
        System.out.println(g);

        f++;

        System.out.println(f);
        System.out.println(g);
    }
}
