package co.edu.uptc.view.administrator;

import co.edu.uptc.model.User;
import co.edu.uptc.utils.CustomFont;
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

public class PanelRegisterAdmin extends JScrollPane {

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
    private JButton register;
    private Font textFieldFont;
    private Font textFont;
    private UIButton uiButton;
    private DashBoardAdmin dashBoard;
    private Color colorButton;
    private Color colorFont;
    private JPanel panel;

    public PanelRegisterAdmin(DashBoardAdmin dashBoard) {
        this.dashBoard = dashBoard;
        initComponents();
    }

    public void initComponents() {
        panel = new JPanel();
        panel.setBackground(new Color(33, 37, 41));
        colorButton = new Color(161, 24, 49);
        colorFont = new Color(248, 249, 250);
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
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 0, 10);
        panel.add(name, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 10, 10);
        panel.add(nameTxt, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 0, 10);
        panel.add(surname, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 10, 10);
        panel.add(surnameTxt, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 0, 10);
        panel.add(email, gbc);

        gbc.gridy = 5;
        gbc.insets = new Insets(0, 10, 10, 10);
        panel.add(emailTxt, gbc);

        gbc.gridy = 6;
        gbc.insets = new Insets(10, 10, 0, 10);
        panel.add(password, gbc);

        gbc.gridy = 7;
        gbc.insets = new Insets(0, 10, 10, 10);
        panel.add(passwordTxt, gbc);

        gbc.gridy = 8;
        gbc.insets = new Insets(10, 10, 0, 10);
        panel.add(dob, gbc);

        gbc.gridy = 9;
        gbc.insets = new Insets(0, 10, 10, 10);
        panel.add(dobTxt, gbc);

        gbc.gridy = 10;
        gbc.insets = new Insets(10, 10, 0, 10);
        panel.add(idCard, gbc);

        gbc.gridy = 11;
        gbc.insets = new Insets(0, 10, 10, 10);
        panel.add(idCardTxt, gbc);

        gbc.gridy = 12;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.gridheight = 2;
        panel.add(register, gbc);

        this.add(panel);
        this.setViewportView(panel);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(new Color(248, 249, 250));
        this.setOpaque(false);
        this.getVerticalScrollBar().setUnitIncrement(16);
        this.getHorizontalScrollBar().setUnitIncrement(16);
        this.getViewport().setOpaque(false);
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
                new Dimension(150, 40), FilePathConstants.REGISTER);
        register.setUI(uiButton);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.getConnection().registerUser(getUser());
            }
        });
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

    public User getUser(){
        return new User(getName(), getSurname(), getEmail(), getPassword(), getDob(), getIdCard());
    }

}
