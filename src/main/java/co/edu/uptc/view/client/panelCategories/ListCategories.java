package co.edu.uptc.view.client.panelCategories;

import co.edu.uptc.model.Category;
import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.view.client.DashBoard;
import co.edu.uptc.view.customize.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListCategories extends JPanel {

    private DashBoard dashBoard;
    private JPanel panel;
    private JScrollPane scrollPane;
    private ArrayList<Category> categories;
    private Font font;
    private Color colorFont;

    public ListCategories(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        setCategories();
        initComponents();
    }

    public void initComponents(){
        CustomFont customFont = new CustomFont();
        font = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 1, 20);
        colorFont = new Color(33, 37, 41);
        setPreferredSize(new Dimension(300, dashBoard.getHeight()));
        setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (Category category : categories) {
            JButton btn = Components.customizeButtonNoBackground(font, colorFont,
                    new Dimension(200, 30), category.getName());
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dashBoard.setPanelCategories(category.getName());
                }
            });
            panel.add(btn);
        }

        scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(33, 37, 41));
        scrollPane.setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void setCategories(){
        categories = dashBoard.getConnection().getCategories();
    }
}
