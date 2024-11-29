package co.edu.uptc.view.client.Movie;

import co.edu.uptc.model.Movie;
import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.view.client.DashBoard;
import co.edu.uptc.view.client.PanelPrincipal.MovieButtonRecommend;
import co.edu.uptc.view.customize.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PosterAndRating extends JPanel {

    private Movie movie;
    private JButton movieButton;
    private JLabel ratingLabel;
    private Font font;
    private Color fontColor;
    private DashBoard dashBoard;
    private JPanel panel;

    public PosterAndRating(DashBoard dashBoard, Movie movie) {
        this.dashBoard = dashBoard;
        this.movie = movie;
        initComponents();
    }

    public void initComponents() {
        CustomFont customFont = new CustomFont();
        font = customFont.customizeFont(FilePathConstants.FONT_TITLE_PATH, 2, 17);
        fontColor = new Color(33, 37, 41);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 650));
        movieButton = new MovieButtonRecommend(movie.getPoster(),
                new Dimension(290, 500));
        ratingLabel = Components.customizeJLabel(fontColor, new Color(0,0,0),
                new Dimension(250, 30), font, SwingConstants.CENTER);
        ratingLabel.setText(movie.getRating() + " / 5.0");
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(200, 30));
        GridBagConstraints c = new GridBagConstraints();
        for (int i =0; i<5; i++){
            JButton rating = new MovieButtonRecommend("src/data/png-star.png", new Dimension(30,30));
            int count = i + 1;
            rating.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dashBoard.getConnection().addRating(movie, count);
                    dashBoard.panelMovie(dashBoard.getConnection().getMovieForCommentAndRating(movie.getName()));
                }
            });
            c.gridx = i;
            panel.add(rating, c);
        }

        addComponents();
    }

    public void addComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.CENTER;
        add(movieButton, c);

        c.gridy = 1;
        add(ratingLabel, c);

        c.gridy = 2;
        add(panel, c);
    }
}
