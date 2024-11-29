package co.edu.uptc.view.client.PanelPrincipal;

import co.edu.uptc.model.Movie;
import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.view.client.DashBoard;
import co.edu.uptc.view.customize.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PaneRecentlyAddedMovie extends JPanel {

    private DashBoard dashBoard;
    private ArrayList<Movie> movies;
    private JLabel title;
    private Font titleFont;
    private Color colorFont;

    public PaneRecentlyAddedMovie(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        setMovies();
        initComponents();
    }

    public void initComponents() {
        setLayout(new GridBagLayout());
        setBackground(new Color(248, 249, 250));
        setPreferredSize(new Dimension(dashBoard.getWidth(), 400));
        colorFont = new Color(33, 37, 41);
        CustomFont customFont = new CustomFont();
        titleFont = customFont.customizeFont(FilePathConstants.FONT_TITLE_PATH, 3, 40);

        title = Components.customizeJLabel(colorFont, new Color(0,0,0),
                new Dimension(300, 90), titleFont, SwingConstants.CENTER);
        title.setText("AGREGADAS RECIENTEMENTE");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        int count = 0;

        if (!movies.isEmpty()){
            for (Movie movie : movies) {
                JButton jButton = new MovieButtonRecommend(movie.getPoster(),
                        new Dimension(220, 400));
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dashBoard.panelMovie(movie);
                        dashBoard.moviePlatform.getMovie(movie.getName());
                    }
                });
                gbc.gridx = count++;
                add(jButton, gbc);
            }
        }
    }

    public void setMovies(){
        movies = dashBoard.moviePlatform.getRecentlyAddedMovies();
    }
}
