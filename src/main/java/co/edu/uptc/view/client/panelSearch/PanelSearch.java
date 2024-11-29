package co.edu.uptc.view.client.panelSearch;

import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.view.client.DashBoard;
import co.edu.uptc.view.customize.Components;
import co.edu.uptc.view.customize.UIButton;

import javax.swing.*;
import java.awt.*;

public class PanelSearch extends JPanel {

    private DashBoard dashBoard;
    private JTextField txtSearch;
    private JButton btnSearch;
    private MoviesPanelSearch moviesPanelSearch;
    private Font font;
    private Color fontColor;
    private Color color;
    private UIButton uiButton;
    private JPanel panel;

    public PanelSearch(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());
        CustomFont customFont = new CustomFont();
        font = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 1, 15);
        fontColor = new Color(33, 37, 41);
        color = new Color(0, 123, 255);
        uiButton = new UIButton(color);
        panel = new JPanel();


        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(700, 40));

        createButtonSearch();
        addComponents();
        add(panel, BorderLayout.NORTH);
    }

    public void addComponents(){
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(txtSearch, c);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(btnSearch, c);
    }

    public void createButtonSearch(){
        btnSearch = Components.customizeButtonWithTextOnly(font, fontColor, new Color(0,0,0),
                new Dimension(90,40), "Buscar");
        btnSearch.setUI(uiButton);
        btnSearch.addActionListener(e -> setPanel());
        add(btnSearch, BorderLayout.SOUTH);
    }

    public void setPanel(){
        if (!txtSearch.getText().isEmpty()) {
            if (moviesPanelSearch == null) {
                moviesPanelSearch = new MoviesPanelSearch(dashBoard,
                        txtSearch.getText());
                add(moviesPanelSearch, BorderLayout.CENTER);
                revalidate();
                repaint();
            }else {
                remove(moviesPanelSearch);
                moviesPanelSearch = new MoviesPanelSearch(dashBoard,
                        txtSearch.getText());
                add(moviesPanelSearch, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        }
    }
}
