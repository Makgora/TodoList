package View;

import Controller.CategoryList;
import Controller.TaskList;
import Model.Category;
import Model.LongTask;
import Model.PunctualTask;
import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;

public class CreateTaskView extends JPanel {

	private CategoryList categories;
	private final Dimension dimension = new Dimension(500,50);
	private JFrame mainFrame;
	private Task task;
    private final static String PUNCTUAL = "punctual";
    private final static String LONG = "long";
	
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
		JLabel titleLabel = new JLabel("Task name :");
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
		JLabel punctualLabel = new JLabel("Punctual task ?");
		punctualPanel.add(punctualLabel);
		JCheckBox isPunctual = new JCheckBox();
		punctualPanel.add(isPunctual);
        if (task == null) {
            add(punctualPanel);
        }
		
		JPanel categoryPanel = new JPanel();
		categoryPanel.setLayout(new FlowLayout());
		categoryPanel.setPreferredSize(dimension);
		JLabel categoryLabel = new JLabel("Category :");
		categoryPanel.add(categoryLabel);
		JComboBox categoryList = new JComboBox<>(categories.getCategories().toArray());
		categoryPanel.add(categoryList);
        JButton editCategory = new JButton("Modify categories");
        categoryPanel.add(editCategory);
		add(categoryPanel);
        if (task != null) {
            categoryList.setSelectedItem(task.getCategory());
        }

		JPanel beginDatePanel = new JPanel();
        beginDatePanel.setLayout(new FlowLayout());
        beginDatePanel.setPreferredSize(dimension);
		JLabel beignDateLabel = new JLabel("Begin date (dd/mm/yy) :");
        beginDatePanel.add(beignDateLabel);
		JFormattedTextField beginDateTextField = new JFormattedTextField(Task.DATE_FORMAT);
        beginDateTextField.setColumns(8);
        beginDatePanel.add(beginDateTextField);
		add(beginDatePanel);
        if (task != null) {
            beginDateTextField.setText(Task.DATE_FORMAT.format(task.getBeginDate()));
        } else {
            beginDateTextField.setText(Task.DATE_FORMAT.format(new Date()));
        }

        JPanel endDatePanel = new JPanel();
        endDatePanel.setLayout(new FlowLayout());
        endDatePanel.setPreferredSize(dimension);
        JLabel endDateLabel = new JLabel("End date (dd/mm/yy) :");
        endDatePanel.add(endDateLabel);
        JFormattedTextField endDateTextField = new JFormattedTextField(Task.DATE_FORMAT);
        endDateTextField.setColumns(8);
        endDatePanel.add(endDateTextField);
        add(endDatePanel);
        if (task != null) {
            endDateTextField.setText(Task.DATE_FORMAT.format(task.getEndDate()));
        } else {
            endDateTextField.setText(Task.DATE_FORMAT.format( new Date(new Date().getTime() + Task.MILLISECONDS_PER_DAY)));
        }

        JPanel donePanel = new JPanel();
        donePanel.setLayout(new FlowLayout());
        donePanel.setPreferredSize(dimension);
        JLabel doneLabel = new JLabel("is accomplished ?");
        donePanel.add(doneLabel);
        JCheckBox isDone = new JCheckBox();
        donePanel.add(isDone);
        if (task != null && task instanceof PunctualTask) {
            isDone.setSelected(task.isAccomplished());
        }

		JPanel progressPanel = new JPanel();
		progressPanel.setLayout(new FlowLayout());
		progressPanel.setPreferredSize(dimension);
		JLabel progresLabel = new JLabel("Advencement :");
		progressPanel.add(progresLabel);
        JSlider progressBar = new  JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        progressBar.setPaintTicks(true);
        progressBar.setPaintLabels(true);
        progressBar.setMinorTickSpacing(5);
        progressBar.setMajorTickSpacing(25);
        progressBar.setLabelTable(progressBar.createStandardLabels(25));
        progressPanel.add(progressBar);
        if (task != null && task instanceof LongTask) {
            progressBar.setValue(((LongTask) task).getAdvancement());
        }

        // Create the panel that contains either a checkbox or a slider
        JPanel switcher = new JPanel(new CardLayout());
        switcher.add(donePanel, PUNCTUAL);
        switcher.add(progressPanel, LONG);
        CardLayout cl = (CardLayout)(switcher.getLayout());
        if (task != null && task instanceof PunctualTask) {
            cl.show(switcher, PUNCTUAL);
        } else {
            cl.show(switcher, LONG);
        }
        add(switcher);
		
		JPanel confirmPanel = new JPanel();
		confirmPanel.setLayout(new FlowLayout());
		confirmPanel.setPreferredSize(dimension);
		JButton undoButton = new JButton("Cancel");
		confirmPanel.add(undoButton);
		JButton confirmButton = new JButton("Confirm");
		if (task == null) {
            confirmPanel.add(confirmButton);
        } else if (!task.isAccomplished()) {
            confirmPanel.add(confirmButton);
        }
		add(confirmPanel);

		undoButton.addActionListener(e -> {
            mainFrame.setContentPane(new MainWindow(mainFrame));
            mainFrame.revalidate();
        });

		confirmButton.addActionListener((ActionEvent e) -> {
			try {
                String title = titleTextField.getText();
                String beginDate = beginDateTextField.getText();
                String endDate = endDateTextField.getText();
                Category category = (Category) categoryList.getSelectedItem();

                if (task != null) { // We modify the task
                    task.setTitle(title);
                    task.setBeginDate(beginDate);
                    task.setEndDate(endDate);
                    task.setCategory(category);
                    if (task instanceof LongTask) {
                        ((LongTask) task).setAdvancement(progressBar.getValue());
                    }
                    if (task instanceof PunctualTask) {
                        ((PunctualTask) task).setAccomplished(isDone.isSelected());
                    }
                } else { // We create a new task
                    if (isPunctual.isSelected()) {
                        PunctualTask task = new PunctualTask(title, beginDate, endDate, category);
                        task.setAccomplished(isDone.isSelected());
                        TaskList.getTaskList().addNewTask(task);
                    } else {
                        LongTask task = new LongTask(title, beginDate, endDate, category);
                        task.setAdvancement(progressBar.getValue());
                        TaskList.getTaskList().addNewTask(task);
                    }
                }

                mainFrame.setContentPane(new MainWindow(mainFrame));
                mainFrame.revalidate();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(mainFrame,
                        exception.getMessage(),
                        "One of the fields is invalid",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        editCategory.addActionListener(actionEvent -> {
            mainFrame.setContentPane(new CreateCategoryView(mainFrame));
            mainFrame.revalidate();
        });

		isPunctual.addActionListener(e -> {
            if (isPunctual.isSelected()) {
                cl.show(switcher, PUNCTUAL);
            } else {
                cl.show(switcher, LONG);
            }
            mainFrame.repaint();
        });
	}
}


