package co.edu.uptc.view.client.PanelPrincipal;

import co.edu.uptc.model.User;
import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.view.client.DashBoard;
import co.edu.uptc.view.customize.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Header extends JPanel {

    private JButton logo;
    private JButton categories;
    private JButton search;
    private JButton profile;
    private Font titleFont;
    private Font textFont;
    private Color colorButton;
    private Color colorFont;
    private DashBoard dashBoard;

    public Header(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        initComponents();
    }

    public void initComponents(){
        setBackground(new Color(33, 37, 41));
        colorButton = new Color(0, 123, 255);
        colorFont = new Color(248, 249, 250);
        CustomFont customFont = new CustomFont();
        titleFont = customFont.customizeFont(FilePathConstants.FONT_TITLE_PATH, 3, 45);
        textFont = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 1, 12);
        setPreferredSize(new Dimension(dashBoard.getWidth(), 70));

        createButtonLogo();
        createButtonCategories();
        createButtonSearch();
        createButtonProfile();

        addComponents();
    }

    public void addComponents(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 10);
        add(logo, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 0, dashBoard.getWidth()/4);
        add(categories, gbc);

        gbc.gridx = 2;
        gbc.insets = new Insets(0, dashBoard.getWidth()/4, 0, 10);
        add(search, gbc);

        gbc.gridx = 3;
        gbc.insets = new Insets(0, 10, 0, 0);
        add(profile, gbc);
    }

    public void createButtonLogo(){
        logo = Components.customizeButtonNoBackground(titleFont, colorButton,
                new Dimension(220, 70), FilePathConstants.APP_NAME);
        logo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.principalPanel();
            }
        });
    }

    public void createButtonCategories(){
        categories = Components.customizeButtonNoBackground(textFont, colorFont,
                new Dimension(110,70), "Categorias");
        categories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.categoriesPanel();
            }
        });
    }

    public void createButtonSearch(){
        search = Components.customizeButtonNoBackground(textFont, colorFont,
                new Dimension(100,70), "Buscar");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.searchPanel();
            }
        });
    }

    public void createButtonProfile(){
        profile = Components.customizeButtonNoBackground(textFont, colorFont,
                new Dimension(150,70), "HOLA, " + dashBoard.getUser().getName());
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.loginPanel();
                dashBoard.setUser(new User());
            }
        });
    }

}
