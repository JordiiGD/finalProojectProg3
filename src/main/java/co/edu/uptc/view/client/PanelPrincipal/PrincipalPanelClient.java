package co.edu.uptc.view.client.PanelPrincipal;

import co.edu.uptc.model.Category;
import co.edu.uptc.view.client.DashBoard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PrincipalPanelClient extends JPanel {

    private DashBoard dashBoard;
    private Header header;
    private PanelMovies panelMovies;
    private MoviesMostVisitedPanel moviesMostVisitedPanel;
    private PaneRecentlyAddedMovie paneRecentlyAddedMovie;
    private ArrayList<Category> categories;
    private JPanel panel;
    private JScrollPane scrollPane;

    public PrincipalPanelClient(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        setCategories();
        initComponents();
    }

    public void initComponents(){
        header = new Header(dashBoard);
        panelMovies = new PanelMovies(dashBoard);
        scrollPane = new JScrollPane();
        panel = new JPanel();
        moviesMostVisitedPanel = new MoviesMostVisitedPanel(dashBoard);
        paneRecentlyAddedMovie = new PaneRecentlyAddedMovie(dashBoard);

        setLayout(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        add(header, BorderLayout.NORTH);
        panel.add(panelMovies);
        panel.add(moviesMostVisitedPanel);
        panel.add(paneRecentlyAddedMovie);

        for (Category category : categories) {
            PanelMoviesForCategory1 panelMoviesForCategory1 = new PanelMoviesForCategory1(dashBoard, category);
            panel.add(panelMoviesForCategory1);
        }

        scrollPane.setPreferredSize(new Dimension(dashBoard.getWidth(), 650));
        scrollPane.setViewportView(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(248, 249, 250));
        scrollPane.setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setCategories(){
        categories = dashBoard.moviePlatform.getCategories();
    }
}
