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
    public MoviePlatform moviePlatform;
    private PrincipalPanelClient principalPanel;
    private PanelCategories panelCategories;
    private PanelSearchWithHeader panelSearchWithHeader;
    private PanelMovieWithHeader panelMovieWithHeader;
    private User user;

    public DashBoard() {
        setLayout(new BorderLayout());
        loadData();
        initComponents();
    }

    public void initComponents() {
        setBounds(1, 1, 1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
            panelSearchWithHeader.getMoviePanel().stopSearch();
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
            panelSearchWithHeader.getMoviePanel().stopSearch();
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
            panelSearchWithHeader.getMoviePanel().stopSearch();
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
            panelSearchWithHeader.getMoviePanel().stopSearch();
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
            panelSearchWithHeader.getMoviePanel().stopSearch();
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

    public void loadData(){
        moviePlatform = new MoviePlatform();

        LocalDate dob = LocalDate.of(1998, 9,6);
        User userAd = new User("Jorge", "Gonzalez", "jagd334@gmail.com", "12345678", dob, 1054095677);
        userAd.setUserType(EUserType.administrator);
        moviePlatform.addUser(userAd);

        LocalDate dob2 = LocalDate.of(2000, 9,6);
        User userClie = new User("Pepito", "Perez", "pepito3", "123", dob, 148561258);
        user = userClie;
        userClie.setUserType(EUserType.client);
        moviePlatform.addUser(userClie);

        LocalDate movieDate1 = LocalDate.of(2000, 9,6);
        Movie movie = new Movie(movieDate1, "", "Interestelar");
        movie.setPoster("src/data/posters/Interestelar.jpg");
        movie.setWallpaper("src/data/wallpapers/InterstellarWallpaper.jpg");
        movie.setNumberOfVisited(2);
        BinaryTree temp = new BinaryTree<>(String::compareTo);
        temp.add("Ficcion");
        temp.add("Suspenso");
        temp.add("Espacio");
        temp.add("Prueba");
        movie.setCategory(temp);
        moviePlatform.addMovie(movie);

        Movie movie2 = new Movie(movieDate1, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "La Sirenita");
        movie2.setPoster("src/data/posters/laSirenita.jpg");
        movie2.setNumberOfVisited(4);
        BinaryTree temp2 = new BinaryTree<>(String::compareTo);
        temp2.add("Ficcion");
        temp2.add("Romance");
        temp2.add("Encanto");
        temp2.add("Prueba");
        movie2.setCategory(temp2);
        moviePlatform.addMovie(movie2);

        Movie movie3 = new Movie(movieDate1, "", "Mario Bros");
        movie3.setPoster("src/data/posters/marioBros.jpg");
        movie3.setNumberOfVisited(20);
        BinaryTree temp3 = new BinaryTree<>(String::compareTo);
        temp3.add("Ficcion");
        temp3.add("Hermanos");
        temp3.add("Animacion");
        temp3.add("Prueba");
        movie3.setCategory(temp3);
        moviePlatform.addMovie(movie3);

        Movie movie4 = new Movie(movieDate1, "", "Mulan");
        movie4.setPoster("src/data/posters/mulan.jpg");
        movie4.setNumberOfVisited(2);
        BinaryTree temp4 = new BinaryTree<>(String::compareTo);
        temp4.add("Accion");
        temp4.add("Guerra");
        temp4.add("China");
        temp4.add("Prueba");
        movie4.setCategory(temp4);
        moviePlatform.addMovie(movie4);

        Movie movie5 = new Movie(movieDate1, "", "Rapidos y furiosos");
        movie5.setPoster("src/data/posters/rapidosyfuriosos.jpg");
        movie5.setNumberOfVisited(7);
        BinaryTree temp5 = new BinaryTree<>(String::compareTo);
        temp5.add("Ficcion");
        temp5.add("Accion");
        temp5.add("Carreras");
        temp5.add("Prueba");
        movie5.setCategory(temp5);
        moviePlatform.addMovie(movie5);

        Movie movie6 = new Movie(movieDate1, "", "Spiderman: Multiverso");
        movie6.setPoster("src/data/posters/SpiderManMultiverso.jpg");
        movie6.setWallpaper("src/data/wallpapers/SpiderManMultiverse.jpeg");
        BinaryTree temp6 = new BinaryTree<>(String::compareTo);
        temp6.add("Animacion");
        temp6.add("Accion");
        temp6.add("Super-Heroe");
        temp6.add("Prueba");
        movie6.setCategory(temp6);
        moviePlatform.addMovie(movie6);
    }

    public static void main(String[] args) {
        DashBoard dashBoard = new DashBoard();
        dashBoard.run();
    }

}
