package co.edu.uptc.view.administrator.principalPanel;

import co.edu.uptc.model.User;
import co.edu.uptc.view.customize.ButtonTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class UsersPanel extends JScrollPane {

    private JTable table;
    private PrincipalPanel principalPanel;

    public UsersPanel(PrincipalPanel principalPanel) {
        this.principalPanel = principalPanel;
        initComponents();
    }

    public void initComponents(){
        setSize(900, 500);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Correo");
        model.addColumn("Tipo de usuario");
        model.addColumn("Fecha de nacimiento");
        model.addColumn("Eliminar");
        model.addColumn("Editar");

        for (User user: principalPanel.dashBoardAdmin.getConnection().getUsers()){
            JButton btn_delete = new JButton("Eliminar");
            JButton btn_edit = new JButton("Editar");
            model.addRow(new Object[]{user.getName(), user.getSurname(), user.getEmail(),
                    user.getUserType(), user.getDob(), btn_delete, btn_edit});
        }

        table = new JTable(model);
        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonTable());
        table.getColumnModel().getColumn(6).setCellRenderer(new ButtonTable());
        table.setRowHeight(50);
        table.setRowMargin(5);

        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(120);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(120);
        column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(250);
        column = table.getColumnModel().getColumn(3);
        column.setPreferredWidth(170);
        column = table.getColumnModel().getColumn(4);
        column.setPreferredWidth(190);
        column = table.getColumnModel().getColumn(5);
        column.setPreferredWidth(100);
        column = table.getColumnModel().getColumn(6);
        column.setPreferredWidth(100);
        setViewportView(table);
    }
}
