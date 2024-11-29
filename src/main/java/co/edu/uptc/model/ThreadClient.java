package co.edu.uptc.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadClient extends Thread{

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket socket;
    private MoviePlatform moviePlatform;
    private Gson gson;

    public ThreadClient(MoviePlatform moviePlatform, Socket socket) {
        this.socket = socket;
        this.moviePlatform = moviePlatform;
        gson = new Gson();
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            String message = gson.fromJson(in.readUTF(), String.class);
            switch (message){
                case "loginClient":
                    loginClient();
                    break;
                case "loginAdmin":
                    loginAdmin();
                    break;
                case "registerUser":
                    registerUser();
                    break;
                case "addMovie":
                    addMovie();
                    break;
                case "getMovies":
                    getMovies();
                    break;
                case "getUsers":
                    getUsers();
                    break;
                default:
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loginClient() throws IOException {
        String username = gson.fromJson(in.readUTF(), String.class);
        String password = gson.fromJson(in.readUTF(), String.class);
        String message = gson.toJson(moviePlatform.loginClient(username, password));
        out.writeUTF(message);
    }

    public void loginAdmin() throws IOException {
        String username = gson.fromJson(in.readUTF(), String.class);
        String password = gson.fromJson(in.readUTF(), String.class);
        String message = gson.toJson(moviePlatform.loginClient(username, password));
        out.writeUTF(message);
    }

    public void registerUser() throws IOException {
        User user = gson.fromJson(in.readUTF(), User.class);
        moviePlatform.addUser(user);
    }

    public void addMovie() throws IOException {
        Movie movie = gson.fromJson(in.readUTF(), Movie.class);
        moviePlatform.addMovie(movie);
    }

    public void getMovies(){
        String message = gson.toJson(moviePlatform.getMovies());
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getUsers(){
        String message = gson.toJson(moviePlatform.getUsers());
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
