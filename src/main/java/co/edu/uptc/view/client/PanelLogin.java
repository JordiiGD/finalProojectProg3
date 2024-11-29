package co.edu.uptc.view.client;

import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.view.customize.Components;
import co.edu.uptc.view.customize.UIButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLogin extends JPanel {

    private JLabel title;
    private JLabel username;
    private JTextField usernameTxt;
    private JLabel password;
    private JPasswordField passwordTxt;
    private JLabel incorrectPassword;
    private JButton login;
    private JButton register;
    private Font titleFont;
    private Font textFont;
    private UIButton uiButton;
    private boolean painting;
    private DashBoard dashBoard;
    private Color colorButton;
    private Color colorFont;
    private JPanel panel;

    public PanelLogin(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        this.painting = true;
        initComponents();
        threadPaint();
    }

    public void initComponents() {
        setBackground(new Color(248, 249, 250));
        colorButton = new Color(0, 123, 255);
        colorFont = new Color(33, 37, 41);
        this.uiButton = new UIButton(colorButton);
        CustomFont customFont = new CustomFont();
        titleFont = customFont.customizeFont(FilePathConstants.FONT_TITLE_PATH, 3, 80);
        textFont = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 1, 12);
        setLayout(new BorderLayout());

        createLabels();
        createTextFields();
        createButtonLogin();
        createButtonRegister();

        panel = new JPanel();
        panel.setBackground(new Color(248, 249, 250));

        addComponents();
    }

    public void addComponents() {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(username, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(usernameTxt, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(password, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passwordTxt, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(incorrectPassword, gbc);

        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(login, gbc);

        gbc.gridy = 5;
        panel.add(register, gbc);

        this.add(panel, BorderLayout.LINE_START);
    }

    public void createLabels(){
        Color backGroundColor = new Color(0, 0, 0);
        title = Components.customizeJLabel(colorFont, backGroundColor, new Dimension(500, 90),
                titleFont, SwingConstants.CENTER);
        title.setText(FilePathConstants.APP_NAME);

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
                if (dashBoard.moviePlatform.loginClient(usernameTxt.getText(), passwordTxt.getText())){
                    setUser();
                    dashBoard.principalPanel();
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
                dashBoard.registerPanel();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = new ImageIcon(FilePathConstants.IMG_LOGIN).getImage();
        g.drawImage(image, (dashBoard.getWidth()/2), 0, (dashBoard.getWidth()/2), dashBoard.getHeight(), null);
    }

    public void threadPaint(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (painting){
                    try {
                        Thread.sleep(80);
                        repaint();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void start(){
        painting = true;
    }

    public void stop(){
        painting = false;
    }

    public void setUser(){
        dashBoard.setUser(dashBoard.moviePlatform.getUser(usernameTxt.getText(),
                passwordTxt.getText()));
    }
}
