package co.edu.uptc.view.administrator.principalPanel;

import co.edu.uptc.view.administrator.DashBoardAdmin;

import javax.swing.*;
import java.awt.*;

public class PrincipalPanel extends JPanel {

    private OptionsPanel optionsPanel;
    public UsersPanel usersPanel;
    public MoviesPanelAdmin moviesPanelAdmin;
    public AddMoviePanel addMoviePanel;
    public DashBoardAdmin dashBoardAdmin;

    public PrincipalPanel(DashBoardAdmin dashBoardAdmin) {
        this.dashBoardAdmin = dashBoardAdmin;
        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());

        optionsPanel = new OptionsPanel(this);
        usersPanel = new UsersPanel(this);
        moviesPanelAdmin = new MoviesPanelAdmin(this);
        addMoviePanel = new AddMoviePanel(this);
        add(optionsPanel, BorderLayout.WEST);
    }

    public void panelUsers(){
        remove(moviesPanelAdmin);
        remove(addMoviePanel);
        usersPanel = new UsersPanel(this);
        add(usersPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void panelMovies(){
        remove(usersPanel);
        remove(addMoviePanel);
        moviesPanelAdmin = new MoviesPanelAdmin(this);
        add(moviesPanelAdmin, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void panelAddMovie(){
        remove(usersPanel);
        remove(moviesPanelAdmin);
        addMoviePanel = new AddMoviePanel(this);
        add(addMoviePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
