package View;

import Controller.CategoryList;
import Controller.TaskList;
import Model.Category;
import Model.Exception.CategoryException;
import Model.Exception.TaskException;
import Model.PunctualTask;
import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTaskView extends JPanel {

	private CategoryList categories;
	private final Dimension dimension = new Dimension(500,50);
	private JFrame mainFrame;
	private Task task;
	
	public CreateTaskView(JFrame mainFrame) {
		this.categories = CategoryList.getCategoryList();
		this.mainFrame = mainFrame;
		setLayout(new FlowLayout());
		setupTaskView();
	}

    public CreateTaskView(JFrame mainFrame, Task task) {
        this.categories = CategoryList.getCategoryList();
        this.mainFrame = mainFrame;
        this.task = task;
        setLayout(new FlowLayout());
        setupTaskView();
    }

	public void setupTaskView() {
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setPreferredSize(dimension);
		JLabel titleLabel = new JLabel("Nom de la tache :");
		titlePanel.add(titleLabel);
		JTextField titleTextField = new JTextField(20);
		titlePanel.add(titleTextField);
		add(titlePanel);
		if (task != null) {
            titleTextField.setText(task.getTitle());
        }
		
		JPanel punctualPanel = new JPanel();
		punctualPanel.setLayout(new FlowLayout());
		punctualPanel.setPreferredSize(dimension);
		JLabel punctualLabel = new JLabel("Tache ponctuelle ?");
		punctualPanel.add(punctualLabel);
		JCheckBox isPunctual = new JCheckBox();
		punctualPanel.add(isPunctual);
		add(punctualPanel);
        if (task != null) {
            isPunctual.setSelected(task instanceof PunctualTask);
        }
		
		JPanel categoryPanel = new JPanel();
		categoryPanel.setLayout(new FlowLayout());
		categoryPanel.setPreferredSize(dimension);
		JLabel categoryLabel = new JLabel("Categorie :");
		categoryPanel.add(categoryLabel);
		JComboBox categoryList = new JComboBox<>(categories.getAllCategories().toArray());
		categoryPanel.add(categoryList);
		add(categoryPanel);
        if (task != null) {
            categoryList.setSelectedItem(task.getCategory());
        }
		
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new FlowLayout());
		datePanel.setPreferredSize(dimension);
		JLabel dateLabel = new JLabel("Date de fin (dd/mm/yy) :");
		datePanel.add(dateLabel);
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yy");
		JFormattedTextField dateTextField = new JFormattedTextField(format);
		dateTextField.setColumns(8);
		datePanel.add(dateTextField);
		add(datePanel);
        if (task != null) {
            dateTextField.setText(format.format(task.getBeginDate()));
        }
		
		JPanel progressPanel = new JPanel();
		progressPanel.setLayout(new FlowLayout());
		progressPanel.setPreferredSize(dimension);
		JLabel progresLabel = new JLabel("Progression :");
		progressPanel.add(progresLabel);
        JSlider progressBar = new  JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        progressBar.setPaintTicks(true);
        progressBar.setPaintLabels(true);
        progressBar.setMinorTickSpacing(5);
        progressBar.setMajorTickSpacing(25);
        progressBar.setLabelTable(progressBar.createStandardLabels(25));
        progressPanel.add(progressBar);
		add(progressPanel);
        if (task != null) { // TODO
            //progressBar.setValue();
        }
		
		JPanel confirmPanel = new JPanel();
		confirmPanel.setLayout(new FlowLayout());
		confirmPanel.setPreferredSize(dimension);
		JButton undoButton = new JButton("Annuler");
		confirmPanel.add(undoButton);
		JButton confirmButton = new JButton("Confirmer");
		confirmPanel.add(confirmButton);
		add(confirmPanel);
		undoButton.addActionListener(e -> {
            mainFrame.setContentPane(new MainWindow(mainFrame));
            mainFrame.revalidate();
        });
		confirmButton.addActionListener(e -> {
            mainFrame.setContentPane(new MainWindow(mainFrame));
            mainFrame.revalidate();
            Task task;
            if (isPunctual.isSelected()) {
                String title = titleTextField.getText();
                String endDate = dateTextField.getText();
                Category category = (Category) categoryList.getSelectedItem();
                try {
                    task = new PunctualTask(title, new Date(), endDate, category);
                    TaskList.getTaskList().addNewTask(task);
                } catch (TaskException e1) {
                    e1.printStackTrace();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                } catch (CategoryException e1) {
                    e1.printStackTrace();
                }
            } else { // TODO

            }

        });
		isPunctual.addActionListener(e -> {
            if(isPunctual.isSelected()) {
                remove(progressPanel);
            } else {
                add(progressPanel);
            }
            mainFrame.repaint();
        });
	}
}


