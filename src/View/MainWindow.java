package View;

import Controller.CategoryList;
import Controller.TaskList;
import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MainWindow extends JPanel {

    private TaskList tasks;
	private JList<Object> taskJList;
    private JFrame mainFrame;

    public MainWindow(JFrame mainFrame) {
        this.tasks = TaskList.getTaskList();
		System.out.println(tasks);
		this.mainFrame = mainFrame;
		setLayout(new FlowLayout());
		setupScrollList();
		setupTaskButtonPanel();
		setupAppButtonPanel();
	}

    private void setupScrollList() {
		taskJList = new JList<>(tasks.getAllTasks().toArray());
		taskJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskJList.setFixedCellHeight(30);
		JScrollPane taskListPanel = new JScrollPane(taskJList);
		taskListPanel.setPreferredSize(new Dimension(500, 300));

   		add(taskListPanel);
	}

    private void setupTaskButtonPanel() {
  		JPanel buttonPanel = new JPanel();
  		buttonPanel.setLayout(new FlowLayout());
  		
		JButton createButton = new JButton("New task");
		createButton.addActionListener(e -> {
            mainFrame.setContentPane(new CreateTaskView(mainFrame));
            mainFrame.revalidate();
        });

		JButton editButton = new JButton("Edit task");
		editButton.addActionListener(e -> {
		    Task task = (Task) taskJList.getSelectedValue();
            mainFrame.setContentPane(new CreateTaskView(mainFrame, task));
            mainFrame.revalidate();
        });
		editButton.setEnabled(false);
		
		JButton deleteButton = new JButton("Delete task");
		deleteButton.addActionListener(e -> {
            int index = taskJList.getSelectedIndex();
            tasks.getAllTasks().remove(index);
            taskJList.setListData(tasks.getAllTasks().toArray());
        });
		
		deleteButton.setEnabled(false);
		
		taskJList.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                boolean state = !taskJList.isSelectionEmpty();
                editButton.setEnabled(state);
                deleteButton.setEnabled(state);
            }
        });

		buttonPanel.add(createButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		add(buttonPanel);
  	}

	private void setupAppButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		JButton sort1Button = new JButton("Sort by end date");
		sort1Button.addActionListener(e -> {
			tasks.sortByEndDate();
            taskJList.setListData(tasks.getAllTasks().toArray());
        });

		JButton sort2Button = new JButton("Sort by intermediary deadlines");
		sort2Button.addActionListener(e -> {
            tasks.sortByIntermediaryDeadlines();
            taskJList.setListData(tasks.getAllTasks().toArray());
        });

		JButton sort3Button = new JButton("Sort by ?");
		sort3Button.addActionListener(e -> {
			//TODO Replace .sortByEndDate() by the third sort
			tasks.sortByEndDate();
			taskJList.setListData(tasks.getAllTasks().toArray());
		});

        buttonPanel.add(sort1Button);
        buttonPanel.add(sort2Button);
		buttonPanel.add(sort3Button);
        add(buttonPanel);
	}
	

	public static void main(String s[]) {
		JFrame mainView = new JFrame("TodoList");
        mainView.setLocationRelativeTo(null);

		mainView.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    TaskList.getTaskList().serialize();
                    CategoryList.getCategoryList().serialize();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
		mainView.setContentPane(new MainWindow(mainView));
		mainView.setSize(500, 500);
		mainView.setVisible(true);
		mainView.setResizable(false);
		mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}


