package co.edu.uptc.view.administrator.principalPanel;

import co.edu.uptc.model.Movie;
import co.edu.uptc.view.customize.ButtonTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MoviesPanelAdmin extends JScrollPane {

    private JTable table;
    private PrincipalPanel principalPanel;

    public MoviesPanelAdmin(PrincipalPanel principalPanel) {
        this.principalPanel = principalPanel;
        initComponents();
    }

    public void initComponents(){
        setSize(900, 500);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Calificacion");
        model.addColumn("Estreno");
        model.addColumn("Visitas");
        model.addColumn("Eliminar");
        model.addColumn("Editar");

        for (Movie movie : principalPanel.dashBoardAdmin.getConnection().getMovies()){
            JButton btn_delete = new JButton("Eliminar");
            JButton btn_edit = new JButton("Editar");
            model.addRow(new Object[]{movie.getName(), movie.getRating(), movie.getReleaseDate(),
                    movie.getNumberOfVisited(), btn_delete, btn_edit});
        }

        table = new JTable(model);
        table.getColumnModel().getColumn(4).setCellRenderer(new ButtonTable());
        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonTable());
        table.setRowHeight(50);
        table.setRowMargin(5);

        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(300);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(120);
        column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(200);
        column = table.getColumnModel().getColumn(3);
        column.setPreferredWidth(120);
        column = table.getColumnModel().getColumn(4);
        column.setPreferredWidth(100);
        column = table.getColumnModel().getColumn(5);
        column.setPreferredWidth(100);
        setViewportView(table);
    }
}
