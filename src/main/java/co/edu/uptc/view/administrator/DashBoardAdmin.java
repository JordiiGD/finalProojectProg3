package co.edu.uptc.view.administrator;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MoviePlatform;
import co.edu.uptc.model.User;
import co.edu.uptc.structures.BinaryTree;
import co.edu.uptc.utils.EUserType;
import co.edu.uptc.view.administrator.principalPanel.PrincipalPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class DashBoardAdmin extends JFrame {

    private PanelLoginAdministrator panelLogin;
    private PanelRegisterAdmin panelRegister;
    private PrincipalPanel principalPanel;
    private AdminConnection connection;

    public DashBoardAdmin(String host, int port) {
        setLayout(new BorderLayout());
        initComponents();
        try {
            connection = new AdminConnection(host, port);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void initComponents(){
        setBounds(1, 1, 500, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelLogin = new PanelLoginAdministrator(this);
        this.getContentPane().add(panelLogin, BorderLayout.CENTER);
    }

    public void registerPanel(){
        panelRegister.setVisible(true);
        panelLogin.setVisible(false);
        panelRegister = new PanelRegisterAdmin(this);
        this.getContentPane().add(panelRegister,BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void panelPrincipal(){
        setBounds(1, 1, 1280, 720);
        setLocationRelativeTo(null);
        principalPanel.setVisible(true);
        panelLogin.setVisible(false);
        principalPanel = new PrincipalPanel(this);
        this.getContentPane().add(principalPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void run(){
        setVisible(true);
    }

    public AdminConnection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        DashBoardAdmin dashBoardAdmin = new DashBoardAdmin("localhost", 5000);
        dashBoardAdmin.run();
    }
}
