package Controller;

import Controller.Execption.TaskListException;
import Model.Category;
import Model.Exception.TaskException;
import Model.LongTask;
import Model.Task;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

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
     * Set the category of those task to null
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
    public void sortByEndDate() {
        this.tasks.sort((task1, task2) -> {
            if (task1.getEndDate().before(task2.getEndDate())) {
                return -1;
            } else if (task1.getEndDate().after(task2.getEndDate())) {
                return 1;
            } else {
                return 0;
            }
        });
    }

    public void sortByIntermediaryDeadlines()
    {
        this.tasks.sort((task1, task2) -> {

            Date interDeadLT1 = task1.getEndDate();
            Date interDeadLT2 = task2.getEndDate();

            long todayDateTime = new Date().getTime();
            double nextQuartPercent;
            long betweenBeginAndEndTime;

            if(task1 instanceof LongTask)
            {
                nextQuartPercent = ((LongTask)task1).nextQuartToDo()/100.0;
                betweenBeginAndEndTime = task1.getEndDate().getTime() - task1.getBeginDate().getTime();
                interDeadLT1 = new Date((long)(todayDateTime + nextQuartPercent*betweenBeginAndEndTime));
            }
            if(task2 instanceof LongTask)
            {
                nextQuartPercent = ((LongTask)task2).nextQuartToDo()/100.0;
                betweenBeginAndEndTime = task2.getEndDate().getTime() - task2.getBeginDate().getTime();
                interDeadLT2 = new Date((long)(todayDateTime + nextQuartPercent*betweenBeginAndEndTime));
            }

            if(interDeadLT1.before(interDeadLT2))
            {
                return -1;
            }
            else if(interDeadLT1.after(interDeadLT2))
            {
                return 1;
            }
            else
            {
                return 0;
            }
        });
    }

    public void eightTasksToDo() throws TaskListException
    {
        if(TaskList.getTaskList().tasks.size() < 8 || this.getHighPriorityTasks().size() < 1 || this.getMediumPriorityTasks().size() < 3 || this.getLowPriorityTasks().size() < 5)
        {
            throw new TaskListException("Need to have more different tasks to use this function");
        }
        else
        {
            Random random = new Random();
            ArrayList<Task> arrayListReturned = new ArrayList<>();
            ArrayList<Task> arrayListCopied;

            arrayListCopied = TaskList.getTaskList().getHighPriorityTasks();
            for(int i = 0; i < 2; i++)
            {
                int randomNumber = random.nextInt(arrayListCopied.size());
                arrayListReturned.add(arrayListCopied.get(randomNumber));
                arrayListCopied.remove(randomNumber);
            }

            arrayListCopied = TaskList.getTaskList().getMediumPriorityTasks();
            for(int i = 0; i < 3; i++)
            {
                int randomNumber = random.nextInt(arrayListCopied.size());
                arrayListReturned.add(arrayListCopied.get(randomNumber));
                arrayListCopied.remove(randomNumber);
            }

            arrayListCopied = TaskList.getTaskList().getLowPriorityTasks();
            for(int i = 0; i < 5; i++)
            {
                int randomNumber = random.nextInt(arrayListCopied.size());
                arrayListReturned.add(arrayListCopied.get(randomNumber));
                arrayListCopied.remove(randomNumber);
            }
            arrayListReturned.addAll(this.tasks);
            this.tasks = arrayListReturned;
        }
    }

    public void sortByPriority()
    {
        this.tasks.sort((task1, task2) -> {
            if (task1.getPriority().equals(Task.Priority.HIGH) && task2.getPriority().equals(Task.Priority.HIGH))
            {
                return 0;
            }
            else if (task1.getPriority().equals(Task.Priority.HIGH) && task2.getPriority().equals(Task.Priority.MEDIUM))
            {
                return -1;
            }
            else if (task1.getPriority().equals(Task.Priority.HIGH) && task2.getPriority().equals(Task.Priority.LOW))
            {
                return -1;
            }
            else if(task1.getPriority().equals(Task.Priority.MEDIUM) && task2.getPriority().equals(Task.Priority.HIGH))
            {
                return 1;
            }
            else if (task1.getPriority().equals(Task.Priority.MEDIUM) && task2.getPriority().equals(Task.Priority.MEDIUM))
            {
                return 0;
            }
            else if(task1.getPriority().equals(Task.Priority.MEDIUM) && task2.getPriority().equals(Task.Priority.LOW))
            {
                return -1;
            }
            else if(task1.getPriority().equals(Task.Priority.LOW) && task2.getPriority().equals(Task.Priority.HIGH))
            {
                return 1;
            }
            else if(task1.getPriority().equals(Task.Priority.LOW) && task2.getPriority().equals(Task.Priority.MEDIUM))
            {
                return 1;
            }
            else if(task1.getPriority().equals(Task.Priority.LOW) && task2.getPriority().equals(Task.Priority.LOW))
            {
                return 0;
            }
            else
            {
                return 0;
            }
        });
    }

    public ArrayList<Task> getHighPriorityTasks()
    {
        ArrayList<Task> arrayList = new ArrayList<>();

        for(Task task : this.tasks)
        {
            if(task.getPriority().equals(Task.Priority.HIGH))
            {
                arrayList.add(task);
            }
        }
        return arrayList;
    }

    public ArrayList<Task> getMediumPriorityTasks()
    {
        ArrayList<Task> arrayList = new ArrayList<>();

        for(Task task : this.tasks)
        {
            if(task.getPriority().equals(Task.Priority.MEDIUM))
            {
                arrayList.add(task);
            }
        }
        return arrayList;
    }

    public ArrayList<Task> getLowPriorityTasks()
    {
        ArrayList<Task> arrayList = new ArrayList<>();

        for(Task task : this.tasks)
        {
            if(task.getPriority().equals(Task.Priority.LOW))
            {
                arrayList.add(task);
            }
        }
        return arrayList;
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "tasks=" + tasks +
                '}';
    }
}