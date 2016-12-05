package Model;

import Controller.CategoryList;
import Controller.TaskList;

public class Main {
    public static void main(String[] arg) throws Exception
    {

        Task t1 = new PunctualTask("Test", "22/05/95", "23/05/95", "Travail");
        Task t2 = new PunctualTask("Test2", "24/05/95", "25/05/95", "Travail");
        Task t3 = new PunctualTask("Test2", "24/05/95", "25/05/95", "Ludique");

        TaskList.getTaskList().serialize();
        CategoryList.getCategoryList().serialize();

        System.out.println(TaskList.getTaskList());
    }
}
