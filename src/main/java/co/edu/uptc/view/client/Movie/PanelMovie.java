package co.edu.uptc.view.client.Movie;

import co.edu.uptc.model.Movie;
import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.view.client.DashBoard;
import co.edu.uptc.view.customize.Components;

import javax.swing.*;
import java.awt.*;

public class PanelMovie extends JPanel {

    private DashBoard dashBoard;
    private Movie movie;
    private PosterAndRating posterAndRating;
    private JLabel titleLabel;
    private JTextArea descriptionTextArea;
    private Comments comments;
    private Font titleFont;
    private Font descriptionFont;
    private Color titleColor;
    private Color descriptionColor;

    public PanelMovie(DashBoard dashBoard, Movie movie) {
        this.dashBoard = dashBoard;
        this.movie = movie;
        initComponents();
    }

    public void initComponents() {
        CustomFont customFont = new CustomFont();
        titleFont = customFont.customizeFont(FilePathConstants.FONT_TITLE_PATH, 1 , 70);
        descriptionFont = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 1 , 12);
        titleColor = new Color(0, 123, 255);
        descriptionColor = new Color(33, 37, 41);

        posterAndRating = new PosterAndRating(dashBoard, movie);
        createTitle();
        createDescription();
        comments = new Comments(dashBoard, movie);

        addComponents();
    }

    public void addComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.CENTER;
        c.gridheight = 3;
        add(posterAndRating, c);

        c.gridx = 1;
        c.gridheight = 1;
        c.gridwidth = 2;
        add(titleLabel, c);

        c.gridy = 1;
        c.gridwidth = 2;
        c.insets = new Insets(5, 5, 10, 5);
        add(descriptionTextArea, c);

        c.gridy = 2;
        c.gridx = 1;
        c.insets = new Insets(10, 5, 5, 5);
        c.gridwidth = 2;
        add(comments, c);
    }

    public void createTitle(){
        titleLabel = Components.customizeJLabel(titleColor, new Color(0,0,0),
                new Dimension(900, 190), titleFont, SwingConstants.CENTER);
        titleLabel.setText(movie.getName());
    }

    public void createDescription(){
        descriptionTextArea = new JTextArea();
        descriptionTextArea.setPreferredSize(new Dimension(900, 230));
        descriptionTextArea.setText(movie.getDescription());
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setFont(descriptionFont);
        descriptionTextArea.setForeground(descriptionColor);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setBorder(null);
        descriptionTextArea.setBackground(new Color(248, 249, 250));
    }
}
