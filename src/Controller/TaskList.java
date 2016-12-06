package Controller;

import Model.Category;
import Model.Exception.CategoryException;
import Model.Exception.TaskException;
import Model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class TaskList implements Serializable {

    private ArrayList<Task> tasks;
    private static TaskList taskList;

    private TaskList()
    {
        if(!new File("TaskList.ser").exists())  //File doesn't exist (first execution)
        {
            this.tasks = new ArrayList<>();
            taskList = this;
        }
        else                //Deserialise taskList from file
        {
            taskList = deserialize();
        }
    }

    public static TaskList getTaskList()    //return the static object taskList or create one if first execution
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

    public void serialize() throws IOException
    {
        File file = new File("TaskList.ser");
        file.createNewFile(); //Make a file if doesn't exist

        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream outStream = new ObjectOutputStream(fileOut);

        outStream.writeObject(this);

        outStream.close();
        fileOut.close();
    }

    private static TaskList deserialize()
    {
        try
        {
            FileInputStream fileIn = new FileInputStream(new File("TaskList.ser"));
            ObjectInputStream inStream = new ObjectInputStream(fileIn);

            TaskList taskList = (TaskList)inStream.readObject();

            inStream.close();
            fileIn.close();

            return taskList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
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

    public void removeACategoryFromAllTask(Category cat) throws CategoryException, TaskException
    {
        for(Task task : this.tasks)
        {
            if(task.getCategory() == cat)
            {
                task.setCategory("SansCategorie");
            }
        }
    }

    public void sortByEndDate()
    {
        this.tasks.sort((task1, task2) -> {
            Date today = new Date();
            if(task1.getEndDate().before(task2.getEndDate()))
            {
                return -1;
            }
            else if(task1.getEndDate().after(task2.getEndDate()))
            {
                return 1;
            }
            else
            {
                return 0;
            }
        });
    }

    public String toString()
    {
        String list = "Task List:";
        for(Task task : getTaskList().tasks)
        {
            list = list + "\n" + task.toString();
        }
        return list;
    }
}