package co.edu.uptc.view;

import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDate;

public class DatePanel extends JPanel {

    private JComboBox<Integer> menuDays;
    private JComboBox<Integer> menuMonths;
    private JComboBox<Integer> menuYears;

    public DatePanel() {
        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());

        menuDays = createMenu(31, 1);
        menuMonths = createMenu(12, 1);
        menuYears = createMenu(100, 1924);

        addComponents();
    }

    public void addComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        add(menuDays, c);

        c.gridx = 1;
        add(menuMonths, c);

        c.gridx = 2;
        add(menuYears, c);
    }

    public JComboBox<Integer> createMenu(int quantity, int start) {
        CustomFont customFont = new CustomFont();
        Font textFont = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 1, 12);
        Integer[] temp = new Integer[quantity];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = start++;
        }
        JComboBox<Integer> jComboBox = new JComboBox<>(temp);
        jComboBox.setFont(textFont);
        jComboBox.setForeground(new Color(33, 37, 41));
        jComboBox.setBackground(new Color(248, 249, 250));
        jComboBox.setBorder(new LineBorder(new Color(204, 204, 204), 1, true));
        return jComboBox;
    }

    public LocalDate getDate(){
        return LocalDate.of(menuYears.getSelectedItem().toString().length() == 4 ?
                Integer.parseInt(menuYears.getSelectedItem().toString()) : 1924,
                menuMonths.getSelectedIndex() + 1,
                menuDays.getSelectedIndex() + 1);
    }
}
