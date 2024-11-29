package co.edu.uptc.model;

import co.edu.uptc.structures.BinaryTree;
import co.edu.uptc.utils.EUserType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

public class Server {

    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private MoviePlatform moviePlatform;

    public Server(int port) throws IOException {
        moviePlatform = new MoviePlatform();
        this.port = port;
        serverSocket = new ServerSocket(port);
        loadData();
    }

    public void start() throws IOException {
        while (true) {
            socket = serverSocket.accept();
            ThreadClient threadClient = new ThreadClient(moviePlatform, socket);
            threadClient.start();
        }
    }

    public void loadData() {
        moviePlatform = new MoviePlatform();

        LocalDate dob = LocalDate.of(1998, 9, 6);
        User userAd = new User("Jorge", "Gonzalez", "jagd334@gmail.com", "12345678", dob, 1054095677);
        userAd.setUserType(EUserType.administrator);
        moviePlatform.addUser(userAd);

        LocalDate dob2 = LocalDate.of(2000, 9, 6);
        User userClie = new User("Pepito", "Perez", "pepito3@gmail.com", "12345678", dob, 148561258);
        userClie.setUserType(EUserType.client);
        moviePlatform.addUser(userClie);

        LocalDate movieDate1 = LocalDate.of(2000, 9, 6);
        Movie movie = new Movie(movieDate1, "", "Interestelar");
        movie.setPoster("src/data/posters/Interestelar.jpg");
        BinaryTree temp = new BinaryTree<>(String::compareTo);
        temp.add("Ficcion");
        temp.add("Suspenso");
        temp.add("Espacio");
        movie.setCategory(temp);
        moviePlatform.addMovie(movie);

        Movie movie2 = new Movie(movieDate1, "", "La Sirenita");
        movie2.setPoster("src/data/posters/laSirenita.jpg");
        BinaryTree temp2 = new BinaryTree<>(String::compareTo);
        temp2.add("Ficcion");
        temp2.add("Romance");
        temp2.add("Encanto");
        movie2.setCategory(temp2);
        moviePlatform.addMovie(movie2);

        Movie movie3 = new Movie(movieDate1, "", "Mario Bros");
        movie3.setPoster("src/data/posters/marioBros.jpg");
        BinaryTree temp3 = new BinaryTree<>(String::compareTo);
        temp3.add("Ficcion");
        temp3.add("Hermanos");
        temp3.add("Animacion");
        movie3.setCategory(temp3);
        moviePlatform.addMovie(movie3);

        Movie movie4 = new Movie(movieDate1, "", "Mulan");
        movie4.setPoster("src/data/posters/mulan.jpg");
        BinaryTree temp4 = new BinaryTree<>(String::compareTo);
        temp4.add("Accion");
        temp4.add("Guerra");
        temp4.add("China");
        movie4.setCategory(temp4);
        moviePlatform.addMovie(movie4);

        Movie movie5 = new Movie(movieDate1, "", "Rapidos y furiosos");
        movie5.setPoster("src/data/posters/rapidosyfuriosos.jpg");
        BinaryTree temp5 = new BinaryTree<>(String::compareTo);
        temp5.add("Ficcion");
        temp5.add("Accion");
        temp5.add("Carreras");
        movie5.setCategory(temp5);
        moviePlatform.addMovie(movie5);

        Movie movie6 = new Movie(movieDate1, "", "Spiderman: Multiverso");
        movie6.setPoster("src/data/posters/SpiderManMultiverso.jpg");
        BinaryTree temp6 = new BinaryTree<>(String::compareTo);
        temp6.add("Animacion");
        temp6.add("Accion");
        temp6.add("Super-Heroe");
        movie6.setCategory(temp6);
        moviePlatform.addMovie(movie6);
    }

    public static void main(String[] args) {
        try {
            Server server = new Server(5000);
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
