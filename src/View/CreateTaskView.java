package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTaskView extends JPanel {

	JList list;
	DefaultListModel model;
	final Dimension dimension = new Dimension(500,30);
	JFrame mainFrame;
	
	public CreateTaskView(JFrame mainFrame) {
		this.mainFrame = mainFrame;
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
		
		JPanel punctualPanel = new JPanel();
		punctualPanel.setLayout(new FlowLayout());
		punctualPanel.setPreferredSize(dimension);
		JLabel punctualLabel = new JLabel("Tache ponctuelle ?");
		punctualPanel.add(punctualLabel);
		JCheckBox isPunctual = new JCheckBox();
		punctualPanel.add(isPunctual);
		add(punctualPanel);

		
		Object[] elements = new Object[]{"Categorie 1", "Element 2", "Element 3", "Element 4", "Element 5"};
		JPanel categoryPanel = new JPanel();
		categoryPanel.setLayout(new FlowLayout());
		categoryPanel.setPreferredSize(dimension);
		JLabel categoryLabel = new JLabel("Categorie :");
		categoryPanel.add(categoryLabel);
		JComboBox categoryList = new JComboBox(elements);
		categoryPanel.add(categoryList);
		add(categoryPanel);
		
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new FlowLayout());
		datePanel.setPreferredSize(dimension);
		JLabel dateLabel = new JLabel("Date :");
		datePanel.add(dateLabel);
		JTextField dateTextField = new JTextField(20);
		datePanel.add(dateTextField);
		add(datePanel);
		
		
		JPanel progressPanel = new JPanel();
		progressPanel.setLayout(new FlowLayout());
		progressPanel.setPreferredSize(dimension);
		JLabel progresLabel = new JLabel("Progression :");
		progressPanel.add(progresLabel);
		JProgressBar progressBar = new JProgressBar(0, 100);
		progressBar.setValue(20);
		progressBar.setStringPainted(true);
		progressPanel.add(progressBar);
		add(progressPanel);
		if (false) { // TODO
			remove(progressPanel);
		}
		
		
		
		JPanel confirmPanel = new JPanel();
		confirmPanel.setLayout(new FlowLayout());
		confirmPanel.setPreferredSize(dimension);
		JButton undoButton = new JButton("Annuler");
		confirmPanel.add(undoButton);
		JButton confirmButton = new JButton("Confirmer");
		confirmPanel.add(confirmButton);
		add(confirmPanel);
		undoButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				mainFrame.setContentPane(new MainWindow(mainFrame));
				mainFrame.revalidate();
			}
		});
		confirmButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				mainFrame.setContentPane(new MainWindow(mainFrame));
				mainFrame.revalidate();
			}
		});
		isPunctual.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(isPunctual.isSelected()) {
        			remove(progressPanel);
        		} else {
        			add(progressPanel);
        		}
        		mainFrame.repaint();
			}
		});
	}
}


