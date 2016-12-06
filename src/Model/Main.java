package Model;

import Controller.CategoryList;
import Controller.TaskList;

public class Main {
    public static void main(String[] arg) throws Exception
    {

        Task t1 = new PunctualTask("Test", "22/05/20", "23/05/20", "Travail");
        Task t2 = new PunctualTask("Test2", "24/05/18", "25/05/18", "Travail");
        Task t4 = new PunctualTask("Test2", "24/05/96", "25/05/96", "Travail");
        Task t5 = new PunctualTask("Test2", "24/05/99", "25/05/99", "Travail");
        Task t3 = new PunctualTask("Test2", "24/05/19", "25/05/19", "Ludique");

        System.out.println(TaskList.getTaskList());

        TaskList.getTaskList().sortByEndDate();

        System.out.println(TaskList.getTaskList());

    }
}
