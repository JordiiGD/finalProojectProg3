package co.edu.uptc.view.client;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MoviePlatform;
import co.edu.uptc.model.User;
import co.edu.uptc.structures.BinaryTree;
import co.edu.uptc.utils.EUserType;
import co.edu.uptc.view.client.Movie.PanelMovieWithHeader;
import co.edu.uptc.view.client.PanelPrincipal.PrincipalPanelClient;
import co.edu.uptc.view.client.panelCategories.PanelCategories;
import co.edu.uptc.view.client.panelSearch.PanelSearchWithHeader;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class DashBoard extends JFrame {

    private PanelLogin panelLogin;
    private PanelRegister panelRegister;
    private PrincipalPanelClient principalPanel;
    private PanelCategories panelCategories;
    private PanelSearchWithHeader panelSearchWithHeader;
    private PanelMovieWithHeader panelMovieWithHeader;
    private User user;
    private ClientConnection connection;

    public DashBoard(String host, int port) {
        setLayout(new BorderLayout());
        initComponents();
        connection = new ClientConnection(host, port);
    }

    public void initComponents() {
        setBounds(1, 1, 1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                getConnection().closeConnection();
                System.exit(0);
            }
        });

        panelLogin = new PanelLogin(this);
        this.getContentPane().add(panelLogin, BorderLayout.CENTER);
    }

    public void panelMovie(Movie movie) {
        if (panelMovieWithHeader!=null){
            remove(panelMovieWithHeader);
        }
        panelMovieWithHeader = new PanelMovieWithHeader(movie, this);
        if ( panelCategories != null){
            remove(panelCategories);
        }
        if (panelRegister != null){
            remove(panelRegister);
        }
        if (panelLogin != null){
            remove(panelLogin);
        }if (panelSearchWithHeader!=null){
            remove(panelSearchWithHeader);
        }
        if (principalPanel != null){
            remove(principalPanel);
        }
        this.getContentPane().add(panelMovieWithHeader, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void principalPanel(){
        principalPanel = new PrincipalPanelClient(this);
        if ( panelCategories != null){
            remove(panelCategories);
        }
        if (panelRegister != null){
            remove(panelRegister);
        }
        if (panelLogin != null){
            remove(panelLogin);
        }if (panelSearchWithHeader!=null){
            remove(panelSearchWithHeader);
        }if (panelMovieWithHeader!=null){
            remove(panelMovieWithHeader);
        }
        panelLogin.setVisible(false);
        panelLogin.stop();
        this.getContentPane().add(principalPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void registerPanel(){
        panelRegister = new PanelRegister(this);
        if (panelLogin != null){
            remove(panelLogin);
        }
        if ( panelCategories != null){
            remove(panelCategories);
        }
        if (principalPanel != null){
            remove(principalPanel);
        }if (panelSearchWithHeader!=null){
            remove(panelSearchWithHeader);
        }if (panelMovieWithHeader!=null){
            remove(panelMovieWithHeader);
        }
        panelRegister.setVisible(true);
        panelLogin.setVisible(false);
        panelLogin.stop();
        panelRegister.start();
        this.getContentPane().add(panelRegister,BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void categoriesPanel(){
        panelCategories = new PanelCategories(this);
        if (panelRegister != null){
            remove(panelRegister);
        }
        if (panelLogin != null){
            remove(panelLogin);
        }
        if (principalPanel != null){
            remove(principalPanel);
        }if (panelSearchWithHeader!=null){
            remove(panelSearchWithHeader);
        }if (panelMovieWithHeader!=null){
            remove(panelMovieWithHeader);
        }
        principalPanel.setVisible(false);
        this.getContentPane().add(panelCategories, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void loginPanel(){
        panelLogin = new PanelLogin(this);
        if (panelRegister != null){
            remove(panelRegister);
        }
        if (panelCategories != null){
            remove(panelCategories);
        }
        if (principalPanel != null){
            remove(principalPanel);
        }if (panelSearchWithHeader!=null){
            remove(panelSearchWithHeader);
        }if (panelMovieWithHeader!=null){
            remove(panelMovieWithHeader);
        }
        panelLogin.start();
        this.getContentPane().add(panelLogin, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void searchPanel(){
        panelSearchWithHeader = new PanelSearchWithHeader(this);
        if (panelRegister != null){
            remove(panelRegister);
        }if (panelLogin != null){
            remove(panelLogin);
        }
        if ( panelCategories != null){
            remove(panelCategories);
        }
        if (principalPanel != null){
            remove(principalPanel);
        }if (panelMovieWithHeader!=null){
            remove(panelMovieWithHeader);
        }
        this.getContentPane().add(panelSearchWithHeader);
        revalidate();
        repaint();
    }

    public ClientConnection getConnection() {
        return connection;
    }

    public void run(){
        setVisible(true);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPanelCategories(String category) {
        panelCategories.setPanel(category);
    }

    public static void main(String[] args) {
        DashBoard dashBoard = new DashBoard("localhost", 5000);
        dashBoard.run();
    }

}
