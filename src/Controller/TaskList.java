package Controller;

import Model.Category;
import Model.Exception.TaskException;
import Model.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TaskList implements Serializable {

    private ArrayList<Task> tasks;
    private static TaskList taskList;

    /**
     * Constructor
     * Deserialize from the file TaskList.ser if it exist
     */
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

    /**
     * Getter
     * @return the current instance of TaskList so that they are all in sync
     */
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

    /**
     * Serialize the current instance of TaskList
     * @throws IOException, related to file manipulation
     */
    public void serialize() throws IOException
    {
        File file = new File("TaskList.ser");
        file.createNewFile(); //Make a file if doesn't exist

        FileOutputStream fileOut = new FileOutputStream(file);
        byte[] data = SerializeHelper.serialize(this).getBytes();
        fileOut.write(data);
        fileOut.close();
    }

    /**
     * Deserialize the TaskList instance from TaskList.ser
     * @return TaskList saved from a previous session
     */
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

    /**
     * Add a new Task
     * @param newTask, the new task
     */
    public void addNewTask(Task newTask)
    {
        this.tasks.add(newTask);
    }

    /**
     * Getter
     * @return al the current tasks
     */
    public ArrayList<Task> getAllTasks()
    {
        return this.tasks;
    }

    /**
     * Remove the category from all the task it is in.
     * Set the category of thos task to null
     * @param cat, the category we should remove
     * @throws TaskException, cannot happen
     */
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

    /**
     * Allow to sort the task by End Date
     */
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