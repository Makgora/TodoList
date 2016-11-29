package Controller;

import Model.Category;
import Model.Exception.CategoryException;
import Model.Task;

import java.util.ArrayList;

/**
 * Created by osiris on 29/11/16.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private static TaskList taskList;

    private TaskList()
    {
        //TODO SERIALISER
    }

    public static TaskList getTaskList()
    {
        if(taskList == null)
        {
            return new TaskList();
        }
        else
        {
            return taskList;
        }
    }

    public void addNewTask(Task newTask)
    {
        this.tasks.add(newTask);
    }

    public ArrayList getAllTasks()
    {
        return this.tasks;
    }

    public void removeAllCategory(Category cat) throws CategoryException
    {
        for(Task task : this.tasks)
        {
            if(task.getCategory() == cat)
            {
                task.setCategory("SansCategorie");
            }
        }
    }
}