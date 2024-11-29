package co.edu.uptc.view.client.Movie;

import co.edu.uptc.model.Movie;
import co.edu.uptc.view.client.DashBoard;
import co.edu.uptc.view.client.PanelPrincipal.Header;

import javax.swing.*;
import java.awt.*;

public class PanelMovieWithHeader extends JPanel {

    private Movie movie;
    private DashBoard dashBoard;
    private Header header;
    private PanelMovie panelMovie;

    public PanelMovieWithHeader(Movie movie, DashBoard dashBoard) {
        this.movie = movie;
        this.dashBoard = dashBoard;
        initComponents();
    }

    public void initComponents() {
        header = new Header(dashBoard);
        panelMovie = new PanelMovie(dashBoard, movie);

        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(panelMovie, BorderLayout.CENTER);
    }
}
