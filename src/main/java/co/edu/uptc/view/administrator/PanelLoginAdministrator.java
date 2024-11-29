package co.edu.uptc.view.administrator;

import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.view.customize.Components;
import co.edu.uptc.view.customize.UIButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLoginAdministrator extends JPanel {

    private JLabel title;
    private JLabel admin;
    private JLabel username;
    private JTextField usernameTxt;
    private JLabel password;
    private JPasswordField passwordTxt;
    private JLabel incorrectPassword;
    private JButton login;
    private JButton register;
    private Font titleFont;
    private Font titleFont2;
    private Font textFont;
    private UIButton uiButton;
    private DashBoardAdmin dashBoardAdmin;
    private Color colorButton;
    private Color colorFont;

    public PanelLoginAdministrator(DashBoardAdmin dashBoardAdmin) {
        this.dashBoardAdmin = dashBoardAdmin;
        initComponents();
    }

    public void initComponents(){
        setBackground(new Color(33, 37, 41));
        colorButton = new Color(161, 24, 49);
        colorFont = new Color(248, 249, 250);
        this.uiButton = new UIButton(colorButton);
        CustomFont customFont = new CustomFont();
        titleFont = customFont.customizeFont(FilePathConstants.FONT_TITLE_PATH, 3, 80);
        titleFont2 = customFont.customizeFont(FilePathConstants.FONT_TITLE_PATH, 2, 20);
        textFont = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 1, 12);

        createButtonLogin();
        createLabels();
        createTextFields();
        createButtonRegister();

        addComponents();
    }

    public void addComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 10, 10, 10);
        add(admin, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(username, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(usernameTxt, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(password, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(passwordTxt, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(incorrectPassword, gbc);

        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        add(login, gbc);

        gbc.gridy = 6;
        add(register, gbc);
    }


    public void createLabels(){
        Color backGroundColor = new Color(0, 0, 0);
        title = Components.customizeJLabel(colorFont, backGroundColor, new Dimension(300, 90),
                titleFont, SwingConstants.CENTER);
        title.setText(FilePathConstants.APP_NAME);

        admin = Components.customizeJLabel(colorFont, backGroundColor, new Dimension(150, 30),
                titleFont2, SwingConstants.CENTER);
        admin.setText(FilePathConstants.ADMIN_TITLE);

        username = Components.customizeJLabel(colorFont, backGroundColor, new Dimension(100, 36),
                textFont, SwingConstants.CENTER);
        username.setText(FilePathConstants.USER);

        password = Components.customizeJLabel(colorFont, backGroundColor, new Dimension(150, 36),
                textFont, SwingConstants.CENTER);
        password.setText(FilePathConstants.PASSWORD);

        incorrectPassword = Components.customizeJLabel(Color.RED, backGroundColor, new Dimension(300, 36),
                textFont, SwingConstants.CENTER);
    }

    public void createTextFields(){
        usernameTxt = new JTextField();
        usernameTxt.setFont(textFont);

        passwordTxt = new JPasswordField();
        passwordTxt.setFont(textFont);
    }

    public void createButtonLogin(){
        login = Components.customizeButtonWithTextOnly(textFont, colorFont, colorButton,
                new Dimension(150, 40), FilePathConstants.LOGIN);
        login.setUI(uiButton);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dashBoardAdmin.getConnection().login(usernameTxt.getText(), passwordTxt.getText())){
                    dashBoardAdmin.panelPrincipal();
                }else {
                    incorrectPassword.setText(FilePathConstants.USER_INCORRECT);
                }
            }
        });
    }

    public void createButtonRegister(){
        register = Components.customizeButtonNoBackground(textFont, colorFont,
                new Dimension(400, 40), FilePathConstants.NO_ACCOUNT);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoardAdmin.registerPanel();
            }
        });
    }

}
