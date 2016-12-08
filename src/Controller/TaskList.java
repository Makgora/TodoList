package Controller;

import Model.Category;
import Model.Exception.CategoryException;
import Model.Exception.TaskException;
import Model.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class TaskList implements Serializable {

    private ArrayList<Task> tasks;
    private static TaskList taskList;

    private TaskList()
    {
        if(new File("TaskList.ser").exists())  // File doesn't exist (first execution)
        {
            this.tasks = deserialize().getAllTasks();
        } else {
            this.tasks = new ArrayList<>();
        }
        taskList = this;
    }

    public static TaskList getTaskList()    // return the static object taskList or create one if first execution
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
        byte[] data = SerializeHelper.serialize(this).getBytes();
        fileOut.write(data);
        fileOut.close();
    }

    private static TaskList deserialize()
    {
        try
        {
            String data = new String(Files.readAllBytes(Paths.get("TaskList.ser")));
            TaskList taskList = (TaskList) SerializeHelper.deserialize(data);

            // Set the deserialized category object
            for (Task task : taskList.getAllTasks()) {
                Category c = CategoryList.getCategoryList().getCategory(task.getCategory().getName());
                task.setCategory(c);
            }
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

    public ArrayList<Task> getAllTasks()
    {
        return this.tasks;
    }

    public void removeACategoryFromAllTask(Category cat) throws TaskException
    {
        for(Task task : this.tasks)
        {
            if(task.getCategory() == cat)
            {
                task.setCategory(null);
            }
        }
    }

    public void sortByEndDate()
    {
        this.tasks.sort((task1, task2) -> {
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

    @Override
    public String toString() {
        return "TaskList{" +
                "tasks=" + tasks +
                '}';
    }
}