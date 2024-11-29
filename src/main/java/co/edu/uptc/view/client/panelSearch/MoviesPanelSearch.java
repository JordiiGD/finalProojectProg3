package co.edu.uptc.view.client.panelSearch;

import co.edu.uptc.model.Movie;
import co.edu.uptc.view.client.DashBoard;
import co.edu.uptc.view.client.PanelPrincipal.MovieButtonRecommend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MoviesPanelSearch extends JPanel {
    private DashBoard dashBoard;
    private ArrayList<Movie> movies;
    private String title;
    private JPanel panel;
    private JScrollPane scrollPane;

    public MoviesPanelSearch(DashBoard dashBoard, String title) {
        this.dashBoard = dashBoard;
        this.title = title;
        setMovies();
        initComponents();
    }

    public void initComponents(){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension((dashBoard.getWidth()/3)*2, dashBoard.getHeight()));
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        int x = 0;
        int y = 0;
        for (Movie movie : movies) {
            JButton movieButton = new MovieButtonRecommend(movie.getPoster(),
                    new Dimension(150,250));
            movieButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dashBoard.panelMovie(movie);
                    dashBoard.moviePlatform.getMovie(movie.getName());
                }
            });
            c.gridx = x;
            c.gridy = y;
            panel.add(movieButton, c);
            if (x==3){
                y++;
                x=0;
            }else {
                x++;
            }
        }
        scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(33, 37, 41));
        scrollPane.setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void setMovies(){
        movies = dashBoard.moviePlatform.searchMovie(title);
    }
}
