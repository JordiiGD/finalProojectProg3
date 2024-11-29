package co.edu.uptc.view.client.panelSearch;

import co.edu.uptc.view.client.DashBoard;

import javax.swing.*;
import java.awt.*;

public class PanelSearch extends JPanel {

    private DashBoard dashBoard;
    private JTextField txtSearch;
    private MoviesPanelSearch moviesPanelSearch;
    private boolean ready;

    public PanelSearch(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        startSearch();
        threadSearch();
        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());
        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(200, 25));
        add(txtSearch, BorderLayout.NORTH);
    }

    public void threadSearch() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (ready) {
                    try {
                        Thread.sleep(3000);
                        setPanel();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void setPanel(){
        if (!txtSearch.getText().isEmpty()) {
            if (moviesPanelSearch == null) {
                moviesPanelSearch = new MoviesPanelSearch(dashBoard,
                        txtSearch.getText());
                add(moviesPanelSearch, BorderLayout.CENTER);
                revalidate();
                repaint();
            }else {
                remove(moviesPanelSearch);
                moviesPanelSearch = new MoviesPanelSearch(dashBoard,
                        txtSearch.getText());
                add(moviesPanelSearch, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        }
    }
    public void startSearch() {
        ready = true;
    }

    public void stopSearch() {
        ready = false;
    }
}
