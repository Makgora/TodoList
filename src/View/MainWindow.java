package View;

import Controller.TaskList;
import Model.Task;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JPanel {

    private TaskList tasks;
	private JList<Object> taskJList;
    private JFrame mainFrame;

    public MainWindow(JFrame mainFrame) {
        this.tasks = TaskList.getTaskList();
		this.mainFrame = mainFrame;
		setLayout(new FlowLayout());
		setupScrollList();
		setupTaskButtonPanel();
		setupAppButtonPanel();
	}

    private void setupScrollList() {
		taskJList = new JList<>(tasks.getAllTasks().toArray());
		taskJList.setFixedCellHeight(30);
		JScrollPane taskListPanel = new JScrollPane(taskJList);
		taskListPanel.setPreferredSize(new Dimension(500, 300));

   		add(taskListPanel);
	}

    private void setupTaskButtonPanel() {
  		JPanel buttonPanel = new JPanel();
  		buttonPanel.setLayout(new FlowLayout());
  		
		JButton createButton = new JButton("Créer une tâche");
		createButton.addActionListener(e -> {
            mainFrame.setContentPane(new CreateTaskView(mainFrame));
            mainFrame.revalidate();
        });

		JButton editButton = new JButton("Editer la tâche");
		editButton.addActionListener(e -> {
		    Task task = (Task) taskJList.getSelectedValue();
            mainFrame.setContentPane(new CreateTaskView(mainFrame, task));
            mainFrame.revalidate();
        });
		editButton.setEnabled(false);
		
		JButton deleteButton = new JButton("Supprimer la tâche");
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

		JButton createButton = new JButton("Trier par Date de fin");
		createButton.addActionListener(e -> {
			tasks.sortByEndDate();
            taskJList.setListData(tasks.getAllTasks().toArray());
        });

		JButton editButton = new JButton("Trier par ?");
		editButton.addActionListener(e -> {
            tasks.sortByEndDate();
            taskJList.setListData(tasks.getAllTasks().toArray());
        });

        buttonPanel.add(createButton);
        buttonPanel.add(editButton);
        add(buttonPanel);
	}
	

	public static void main(String s[]) {
		JFrame mainView = new JFrame("TodoList");
        mainView.setLocationRelativeTo(null);
		mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainView.setContentPane(new MainWindow(mainView));
		mainView.setSize(500, 500);
		mainView.setVisible(true);
		mainView.setResizable(false);


	}
}


