package co.edu.uptc.view.administrator.principalPanel;

import co.edu.uptc.model.Movie;
import co.edu.uptc.structures.BinaryTree;
import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.view.DatePanel;
import co.edu.uptc.view.administrator.loadImage.DialogUploadImage;
import co.edu.uptc.view.administrator.loadImage.DialogUploadImage2;
import co.edu.uptc.view.customize.Components;
import co.edu.uptc.view.customize.UIButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMoviePanel extends JPanel {

    private JLabel nameMovie;
    private JTextField nameMovieField;
    private JLabel description;
    private JTextArea descriptionTxt;
    private JLabel dateRelease;
    private DatePanel dateReleasePanel;
    private JLabel genre;
    private JTextField genreField;
    private JLabel poster;
    private JButton posterButton;
    private JLabel wallpaper;
    private JButton wallpaperButton;
    private DialogUploadImage dialogUploadImage;
    private DialogUploadImage2 dialogUploadImage2;
    private JLabel confirmationLoad;
    private JButton addMovie;
    private Font textFieldFont;
    private Font textFont;
    private UIButton uiButton;
    private PrincipalPanel panel;
    private Color colorButton;
    private Color colorFont;

    public AddMoviePanel(PrincipalPanel panel){
        this.panel = panel;
        initComponents();
    }

    public void initComponents() {
        setBackground(new Color(33, 37, 41));
        colorButton = new Color(161, 24, 49);
        colorFont = new Color(248, 249, 250);
        this.uiButton = new UIButton(colorButton);
        CustomFont customFont = new CustomFont();
        textFieldFont = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 4, 15);
        textFont = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 1, 10);

        createLabels();
        createFields();
        createPosterButton();
        createAddButton();
        createWallpaperButton();

        addComponents();
    }

    public void addComponents(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 0, 10);
        add(nameMovie, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        add(nameMovieField, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(description, gbc);

        gbc.fill= GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        add(descriptionTxt, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(dateRelease, gbc);

        gbc.fill= GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        add(dateReleasePanel, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(genre, gbc);

        gbc.fill= GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        add(genreField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(poster, gbc);

        gbc.fill= GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        add(posterButton, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(wallpaper, gbc);

        gbc.fill= GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        add(wallpaperButton, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(confirmationLoad, gbc);

        gbc.gridy = 7;
        add(addMovie, gbc);
    }

    public void createLabels(){
        Color color = new Color(0,0,0);
        nameMovie = Components.customizeJLabel(colorFont, color,
                new Dimension(300, 16), textFont, SwingConstants.CENTER);
        nameMovie.setText("Nombre de la pelicula");

        description = Components.customizeJLabel(colorFont, color,
                new Dimension(300, 16), textFont, SwingConstants.CENTER);
        description.setText("Descripcion");

        dateRelease = Components.customizeJLabel(colorFont, color,
                new Dimension(300, 16), textFont, SwingConstants.CENTER);
        dateRelease.setText("Fecha de estreno");

        genre = Components.customizeJLabel(colorFont, color,
                new Dimension(300, 16), textFont, SwingConstants.CENTER);
        genre.setText("Categorias (Debe ir cada una separa por una ,)");

        poster = Components.customizeJLabel(colorFont, color,
                new Dimension(300, 16), textFont, SwingConstants.CENTER);
        poster.setText("Poster");

        wallpaper = Components.customizeJLabel(colorFont, color,
                new Dimension(300, 16), textFont, SwingConstants.CENTER);
        wallpaper.setText("Wallpaper");

        confirmationLoad = Components.customizeJLabel(Color.GREEN, color,
                new Dimension(300, 16), textFieldFont, SwingConstants.CENTER);
        confirmationLoad.setText("");
    }

    public void createFields(){
        nameMovieField = new JTextField();
        nameMovieField.setFont(textFieldFont);

        descriptionTxt = new JTextArea();
        descriptionTxt.setFont(textFieldFont);
        descriptionTxt.setPreferredSize(new Dimension(300, 100));

        dateReleasePanel = new DatePanel();

        genreField = new JTextField();
        genreField.setFont(textFieldFont);
    }

    public void createPosterButton(){
        posterButton = Components.customizeButtonNoBackground(textFieldFont, colorFont,
                new Dimension(200,20), "Seleccionar imagen");
        dialogUploadImage = new DialogUploadImage(this);
        posterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogUploadImage.init();
            }
        });
    }

    public void createWallpaperButton(){
        wallpaperButton = Components.customizeButtonNoBackground(textFieldFont, colorFont,
                new Dimension(200,20), "Seleccionar imagen");
        dialogUploadImage2 = new DialogUploadImage2(this);
        wallpaperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogUploadImage2.init();
            }
        });
    }

    public void createAddButton(){
        addMovie = Components.customizeButtonWithTextOnly(textFieldFont, colorFont,
                colorButton, new Dimension(150, 40), "Agregar");
        addMovie.setUI(uiButton);
        addMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.dashBoardAdmin.getConnection().addMovie(getMovie());
                setConfirmationLoadMovie();
            }
        });
    }

    public void setConfirmationLoad(){
        this.confirmationLoad.setText("Imagen subida correctamente");
        Timer timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmationLoad.setText("");
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void setConfirmationLoadMovie(){
        this.confirmationLoad.setText("Pelicula agregada correctamente");
        Timer timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmationLoad.setText("");
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public Movie getMovie(){
        Movie movie = new Movie(dateReleasePanel.getDate(), descriptionTxt.getText(),
                nameMovieField.getText());
        movie.setCategory(getCategories());
        movie.setPoster(dialogUploadImage.getNameImgMovie());
        movie.setWallpaper(dialogUploadImage2.getNameImgMovie());
        return movie;
    }

    public BinaryTree<String> getCategories(){
        BinaryTree<String> tree = new BinaryTree<>(String::compareTo);
        String[] categories = genreField.getText().split(",");
        for (String category : categories) {
            category = category.trim();
            tree.add(category);
        }
        return tree;
    }
}
