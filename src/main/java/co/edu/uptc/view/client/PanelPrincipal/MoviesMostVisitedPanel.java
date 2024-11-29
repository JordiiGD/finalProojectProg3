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

public class MoviesMostVisitedPanel extends JPanel {

    private DashBoard dashBoard;
    private ArrayList<Movie> movies;
    private JLabel title;
    private Font titleFont;
    private Font textFont;
    private Color colorFont;

    public MoviesMostVisitedPanel(DashBoard dashBoard){
        this.dashBoard = dashBoard;
        setMovies();
        initComponents();
    }

    public void initComponents(){
        setLayout(new GridBagLayout());
        setBackground(new Color(248, 249, 250));
        setPreferredSize(new Dimension(dashBoard.getWidth(), 400));
        colorFont = new Color(33, 37, 41);
        CustomFont customFont = new CustomFont();
        titleFont = customFont.customizeFont(FilePathConstants.FONT_TITLE_PATH, 3, 40);
        textFont = customFont.customizeFont(FilePathConstants.FONT_TITLE_PATH, 1, 70);

        title = Components.customizeJLabel(colorFont, new Color(0,0,0),
                new Dimension(300, 90), titleFont, SwingConstants.CENTER);
        title.setText("MOST VISITED");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 12;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        int count =0;
        int numberMovie = 1;
        for (Movie movie : movies){
            JLabel top = Components.customizeJLabel(colorFont, new Color(0,0,0),
                    new Dimension(70, 100), textFont, SwingConstants.CENTER);
            top.setText("" + numberMovie++);
            gbc.gridx = count++;
            gbc.anchor = GridBagConstraints.NORTH;
            add(top, gbc);
            JButton btn = new MovieButtonRecommend(movie.getPoster(), new Dimension(150, 250));
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dashBoard.panelMovie(movie);
                    dashBoard.moviePlatform.getMovie(movie.getName());
                }
            });
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridx = count++;
            add(btn, gbc);
        }
    }

    public void setMovies(){
        if (!dashBoard.moviePlatform.getMostVisitedMovies().isEmpty()){
            movies = dashBoard.moviePlatform.getMostVisitedMovies();
        }else {
            movies = new ArrayList<>();
        }
    }
}
