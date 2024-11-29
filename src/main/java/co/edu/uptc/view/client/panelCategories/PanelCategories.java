package co.edu.uptc.view.client.panelCategories;

import co.edu.uptc.view.client.DashBoard;
import co.edu.uptc.view.client.PanelPrincipal.Header;

import javax.swing.*;
import java.awt.*;

public class PanelCategories extends JPanel {

    private DashBoard dashBoard;
    private ListCategories listCategories;
    private PanelMoviesForCategory2 panelMoviesForCategory2;
    private Header header;

    public PanelCategories(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        initComponents();
    }

    public void initComponents(){
        setLayout(new BorderLayout());
        header = new Header(dashBoard);
        listCategories = new ListCategories(dashBoard);
        add(header, BorderLayout.NORTH);
        add(listCategories, BorderLayout.WEST);
    }

    public void setPanel(String category){
        if (panelMoviesForCategory2 != null){
            remove(panelMoviesForCategory2);
            panelMoviesForCategory2 = new PanelMoviesForCategory2(dashBoard, category);
            add(panelMoviesForCategory2, BorderLayout.CENTER);
            revalidate();
            repaint();
        }else {
            panelMoviesForCategory2 = new PanelMoviesForCategory2(dashBoard, category);
            add(panelMoviesForCategory2, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }
}
