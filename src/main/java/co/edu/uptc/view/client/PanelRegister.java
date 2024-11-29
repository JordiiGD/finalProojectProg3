package co.edu.uptc.view.client;

import co.edu.uptc.model.User;
import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.EUserType;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.view.DatePanel;
import co.edu.uptc.view.customize.Components;
import co.edu.uptc.view.customize.UIButton;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class PanelRegister extends JPanel {

    private JLabel name;
    private JTextField nameTxt;
    private JLabel surname;
    private JTextField surnameTxt;
    private JLabel email;
    private JTextField emailTxt;
    private JLabel password;
    private JTextField passwordTxt;
    private JLabel dob;
    private DatePanel dobTxt;
    private JLabel idCard;
    private JTextField idCardTxt;
    private JButton register;;
    private Font textFieldFont;
    private Font textFont;
    private UIButton uiButton;
    private DashBoard dashBoard;
    private Color colorButton;
    private Color colorFont;
    private boolean painting;

    public PanelRegister(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        initComponents();
        threadPainting();
        stop();
    }

    public void initComponents() {
        setBackground(new Color(248, 249, 250));
        colorButton = new Color(0, 123, 255);
        colorFont = new Color(33, 37, 41);
        this.uiButton = new UIButton(colorButton);
        CustomFont customFont = new CustomFont();
        textFieldFont = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 4, 15);
        textFont = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 1, 10);

        createLabels();
        createTextFields();
        createButtonRegister();

        addComponents();
    }

    public void addComponents(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 0, 100);
        add(name, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 10, 100);
        add(nameTxt, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 0, 100);
        add(surname, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 10, 100);
        add(surnameTxt, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 0, 100);
        add(email, gbc);

        gbc.gridy = 5;
        gbc.insets = new Insets(0, 10, 10, 100);
        add(emailTxt, gbc);

        gbc.gridy = 6;
        gbc.insets = new Insets(10, 10, 0, 100);
        add(password, gbc);

        gbc.gridy = 7;
        gbc.insets = new Insets(0, 10, 10, 100);
        add(passwordTxt, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 100, 0, 10);
        add(dob, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 100, 10, 10);
        add(dobTxt, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(10, 100, 0, 10);
        add(idCard, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 100, 10, 10);
        add(idCardTxt, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(10, 100, 0, 10);
        gbc.gridheight = 2;
        add(register, gbc);

    }

    public void createLabels(){
        name = Components.customizeJLabel(colorFont, new Color(0,0,0), new Dimension(300, 16),
                textFont, SwingConstants.CENTER);
        name.setText(FilePathConstants.NAME);

        surname = Components.customizeJLabel(colorFont, new Color(0,0,0), new Dimension(300, 16),
                textFont, SwingConstants.CENTER);
        surname.setText(FilePathConstants.SURNAME);

        email = Components.customizeJLabel(colorFont, new Color(0,0,0), new Dimension(300, 16),
                textFont, SwingConstants.CENTER);
        email.setText(FilePathConstants.EMAIL);

        password = Components.customizeJLabel(colorFont, new Color(0,0,0), new Dimension(300, 16),
                textFont, SwingConstants.CENTER);
        password.setText(FilePathConstants.PASSWORD);

        dob = Components.customizeJLabel(colorFont, new Color(0,0,0), new Dimension(150, 36),
                textFont, SwingConstants.CENTER);
        dob.setText(FilePathConstants.BIRTHDATE);

        idCard = Components.customizeJLabel(colorFont, new Color(0,0,0), new Dimension(300, 16),
                textFont, SwingConstants.CENTER);
        idCard.setText(FilePathConstants.ID_CARD);
    }

    public void createTextFields(){
        nameTxt = new JTextField();
        nameTxt.setFont(textFieldFont);

        surnameTxt = new JTextField();
        surnameTxt.setFont(textFieldFont);

        emailTxt = new JTextField();
        emailTxt.setFont(textFieldFont);

        passwordTxt = new JTextField();
        passwordTxt.setFont(textFieldFont);

        idCardTxt = new JTextField();
        idCardTxt.setFont(textFieldFont);
        DocumentFilter filter = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string.matches("\\d*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text,
                                AttributeSet attrs) throws BadLocationException {
                if (text.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };
        ((AbstractDocument)idCardTxt.getDocument()).setDocumentFilter(filter);

        dobTxt = new DatePanel();
    }

    public void createButtonRegister(){
        register = Components.customizeButtonWithTextOnly(textFont, colorFont, colorButton,
                new Dimension(150, 40), "Registrarse");
        register.setUI(uiButton);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User(nameTxt.getText(), surnameTxt.getText(),
                        emailTxt.getText(), passwordTxt.getText(), dobTxt.getDate(),
                        Integer.parseInt(idCardTxt.getText()));
                user.setUserType(EUserType.client);
                dashBoard.moviePlatform.addUser(user);
                dashBoard.setUser(user);
                dashBoard.principalPanel();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(33, 37, 41));
        g.drawLine((dashBoard.getWidth()/2), 100, (dashBoard.getWidth()/2), (dashBoard.getHeight()-100));
    }

    public void threadPainting(){
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
        thread.start();
    }

    public void start (){
        painting = true;
    }

    public void stop (){
        painting = false;
    }

    public String getName(){
        return nameTxt.getText();
    }

    public String getSurname(){
        return surnameTxt.getText();
    }

    public String getEmail(){
        return emailTxt.getText();
    }

    public LocalDate getDob(){
        return dobTxt.getDate();
    }

    public int getIdCard(){
        return Integer.parseInt(idCardTxt.getText());
    }

    public String getPassword(){
        return passwordTxt.getText();
    }
}
