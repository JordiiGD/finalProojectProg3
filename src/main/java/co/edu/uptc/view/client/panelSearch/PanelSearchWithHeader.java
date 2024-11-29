package co.edu.uptc.view.client.panelSearch;

import co.edu.uptc.view.client.DashBoard;
import co.edu.uptc.view.client.PanelPrincipal.Header;

import javax.swing.*;
import java.awt.*;

public class PanelSearchWithHeader extends JPanel {

    private DashBoard dashBoard;
    private PanelSearch panelSearch;
    private Header header;

    public PanelSearchWithHeader(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        initComponents();
    }

    public void initComponents(){
        setLayout(new BorderLayout());

        header = new Header(dashBoard);
        panelSearch = new PanelSearch(dashBoard);

        add(header, BorderLayout.NORTH);
        add(panelSearch, BorderLayout.CENTER);
    }

    public PanelSearch getMoviePanel(){
        return panelSearch;
    }
}
