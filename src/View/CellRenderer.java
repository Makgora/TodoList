package View;

import Model.Task;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class CellRenderer extends JLabel implements ListCellRenderer {

    public CellRenderer() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());

        Task t = (Task) value;
        if (isSelected) {
            setBackground(Color.lightGray);
        } else {
            setBackground(Color.WHITE);
        }
        if (t.getPriority().equals(Task.Priority.HIGH))  {
            setBorder(new LineBorder(Color.RED));
        } else if (t.getPriority().equals(Task.Priority.MEDIUM)) {
            setBorder(new LineBorder(Color.YELLOW));
        } else if (t.getPriority().equals(Task.Priority.LOW)){
            setBorder(new LineBorder(Color.GREEN));
        }

        return this;
    }
}
