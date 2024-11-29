package co.edu.uptc.view.administrator.principalPanel;

import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.view.customize.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsPanel extends JPanel {

    private JButton btn_users;
    private JButton btn_movies;
    private JButton btn_add_movie;
    private Font textFont;
    private Color colorFont;
    private PrincipalPanel principalPanel;

    public OptionsPanel(PrincipalPanel principalPanel) {
        this.principalPanel = principalPanel;
        initComponents();
    }

    public void initComponents() {
        setBackground(new Color(33, 37, 41));
        colorFont = new Color(248, 249, 250);
        CustomFont customFont = new CustomFont();
        textFont = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 1, 18);

        createButtonUsers();
        createButtonMovies();
        createButtonAddMovie();

        addComponents();
    }

    public void addComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        add(btn_users, c);

        c.gridy = 1;
        add(btn_movies, c);

        c.gridy = 2;
        add(btn_add_movie, c);
    }

    public void createButtonUsers(){
        btn_users = Components.customizeButtonNoBackground(textFont, colorFont,
                new Dimension(300, 40), "Usuarios");
        btn_users.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                principalPanel.panelUsers();
            }
        });
    }

    public void createButtonMovies(){
        btn_movies = Components.customizeButtonNoBackground(textFont, colorFont,
                new Dimension(300, 40), "Peliculas");
        btn_movies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                principalPanel.panelMovies();
            }
        });
    }

    public void createButtonAddMovie(){
        btn_add_movie = Components.customizeButtonNoBackground(textFont, colorFont,
                new Dimension(300, 40), "Agregar pelicula");
        btn_add_movie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                principalPanel.panelAddMovie();
            }
        });
    }
}
