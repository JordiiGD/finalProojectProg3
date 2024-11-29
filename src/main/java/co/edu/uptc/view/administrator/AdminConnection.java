package co.edu.uptc.view.administrator;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class AdminConnection {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Gson gson;

    public AdminConnection(String host, int port) {
        try {
            socket = new Socket(host, port);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            gson = new Gson();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(String userName, String password){
        try {
            String key = "loginAdmin";
            String message = gson.toJson(key);
            out.writeUTF(message);

            userName = gson.toJson(userName);
            password = gson.toJson(password);
            out.writeUTF(userName);
            out.writeUTF(password);

            return gson.fromJson(in.readUTF(), boolean.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerUser(User user){
        try {
            String key = "registerUser";
            String message = gson.toJson(key);
            out.writeUTF(message);

            out.writeUTF(gson.toJson(user));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addMovie(Movie movie){
        try {
            String key = "addMovie";
            String message = gson.toJson(key);
            out.writeUTF(message);

            out.writeUTF(gson.toJson(movie));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Movie> getMovies(){
        try {
            String key = "getMovies";
            String message = gson.toJson(key);
            out.writeUTF(message);

            Movie[] movies = gson.fromJson(in.readUTF(), Movie[].class);
            ArrayList<Movie> moviesList = new ArrayList<>();
            Collections.addAll(moviesList, movies);
            return moviesList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> getUsers(){
        try {
            String key = "getUsers";
            String message = gson.toJson(key);
            out.writeUTF(message);

            User[] users = gson.fromJson(in.readUTF(), User[].class);
            ArrayList<User> usersList = new ArrayList<>();
            Collections.addAll(usersList, users);
            return usersList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
