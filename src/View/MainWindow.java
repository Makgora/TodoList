package View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JPanel {

	JList taskList;
	DefaultListModel taskModels;
	int counter = 15;
	JFrame mainFrame;

	public MainWindow(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		setLayout(new FlowLayout());
		setupScrollList();
		setupButtonPanel();
	}

	public void setupScrollList() {
		taskModels = new DefaultListModel();
		taskList = new JList(taskModels);
		taskList.setFixedCellHeight(30);
		JScrollPane taskListPanel = new JScrollPane(taskList);
		taskListPanel.setPreferredSize(new Dimension(500, 400));

		for (int i = 0; i < 15; i++) {
			taskModels.addElement("Tache " + i);
		}

   		add(taskListPanel);
	}

	public void setupButtonPanel() {
  		JPanel buttonPanel = new JPanel();
  		buttonPanel.setLayout(new FlowLayout());
  		
		JButton createButton = new JButton("Créer une tâche");
		createButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				mainFrame.setContentPane(new CreateTaskView(mainFrame));
				mainFrame.revalidate();
			}
		});

		JButton editButton = new JButton("Editer la tâche");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setContentPane(new CreateTaskView(mainFrame));
				mainFrame.revalidate();
			}
		});
		editButton.setEnabled(false);
		
		JButton deleteButton = new JButton("Supprimer la tâche");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taskModels.remove(taskList.getSelectedIndex());
			}
		});
		
		deleteButton.setEnabled(false);
		
		taskList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                	boolean state = !taskList.isSelectionEmpty();
                	editButton.setEnabled(state);
                	deleteButton.setEnabled(state);
                }
            }
        });

		buttonPanel.add(createButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		add(buttonPanel);
  	}
	

	public static void main(String s[]) {
		JFrame mainView = new JFrame("TodoList");
		mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainView.setContentPane(new MainWindow(mainView));
		mainView.setSize(500, 500);
		mainView.setVisible(true);
		mainView.setResizable(false);
	}
}


