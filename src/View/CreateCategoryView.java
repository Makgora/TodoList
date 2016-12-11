package View;

import Controller.CategoryList;
import Model.Category;
import Model.Exception.CategoryException;
import Model.Exception.TaskException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class CreateCategoryView extends JPanel {

    private CategoryList categories;
    private final Dimension dimension = new Dimension(500,50);
    private JFrame mainFrame;
    private JList<Object> categoryJList;

    public CreateCategoryView(JFrame mainFrame) {
        this.categories = CategoryList.getCategoryList();
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout());
        setupScrollList();
        setupView();
    }

    private void setupScrollList() {
        categoryJList = new JList<>(categories.getCategories().toArray());
        categoryJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        categoryJList.setFixedCellHeight(30);
        JScrollPane taskListPanel = new JScrollPane(categoryJList);
        taskListPanel.setPreferredSize(new Dimension(500, 300));

        add(taskListPanel);
    }

    public void setupView() {
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new FlowLayout());
        categoryPanel.setPreferredSize(dimension);
        JLabel titleLabel = new JLabel("Category name :");
        categoryPanel.add(titleLabel);
        JTextField titleTextField = new JTextField(20);
        categoryPanel.add(titleTextField);
        JButton addButton = new JButton("Add");
        categoryPanel.add(addButton);
        add(categoryPanel);

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new FlowLayout());
        optionPanel.setPreferredSize(dimension);
        JButton clearSelectionButton = new JButton("Clean selection");
        optionPanel.add(clearSelectionButton);
        JButton deleteButton = new JButton("Delete category");
        optionPanel.add(deleteButton);
        add(optionPanel);

        JPanel confirmPanel = new JPanel();
        confirmPanel.setLayout(new FlowLayout());
        confirmPanel.setPreferredSize(dimension);
        JButton confirmButton = new JButton("Back");
        confirmPanel.add(confirmButton);
        add(confirmPanel);

        confirmButton.addActionListener(e -> {
            mainFrame.setContentPane(new CreateTaskView(mainFrame));
            mainFrame.revalidate();
        });

        clearSelectionButton.addActionListener((ActionEvent e) -> {
            categoryJList.clearSelection();
        });

        deleteButton.addActionListener((ActionEvent e) -> {
            try {
                int index = categoryJList.getSelectedIndex();
                categories.removeCategory(index);
                categoryJList.setListData(categories.getCategories().toArray());
            } catch (TaskException e1) {
                JOptionPane.showMessageDialog(mainFrame,
                        e1.getMessage(),
                        "One of the fields is invalid",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        categoryJList.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                boolean selected = !categoryJList.isSelectionEmpty();
                if (selected) {
                    int index = categoryJList.getSelectedIndex();
                    Category category = categories.getCategories().get(index);
                    addButton.setText("Modify");
                    titleTextField.setText(category.getName());
                } else {
                    addButton.setText("Add");
                    titleTextField.setText("");
                }

                clearSelectionButton.setEnabled(selected);
                deleteButton.setEnabled(selected);
            }
        });

        clearSelectionButton.setEnabled(false);
        deleteButton.setEnabled(false);

        addButton.addActionListener(actionEvent -> {
            try {
                boolean selected = !categoryJList.isSelectionEmpty();
                if(selected)
                {
                    int index = categoryJList.getSelectedIndex();
                    Category category = categories.getCategories().get(index);
                    category.setName(titleTextField.getText());
                }
                else
                {
                    Category newCat = new Category(titleTextField.getText());
                    categories.addNewCategory(newCat);
                }
                categoryJList.setListData(categories.getCategories().toArray());
            } catch (CategoryException e) {
                JOptionPane.showMessageDialog(mainFrame,
                        e.getMessage(),
                        "One of the fields is invalid",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}


