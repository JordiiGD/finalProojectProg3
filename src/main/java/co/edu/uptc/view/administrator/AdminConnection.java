package co.edu.uptc.view.administrator;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.User;
import co.edu.uptc.persistence.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AdminConnection {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Gson gson;

    public AdminConnection(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            gson = getGsonBuilder().setPrettyPrinting().create();
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
            out.flush();

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
            out.flush();
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
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Movie> getMovies(){
        try {
            String key = "getMovies";
            String message = gson.toJson(key);
            out.writeUTF(message);
            out.flush();

            Type moviesListType = new TypeToken<ArrayList<Movie>>(){}.getType();
            ArrayList<Movie> moviesList = gson.fromJson(in.readUTF(), moviesListType);
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
            out.flush();

            Type usersListType = new TypeToken<ArrayList<User>>(){}.getType();
            ArrayList<User> usersList = gson.fromJson(in.readUTF(), usersListType);
            return usersList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            out.writeUTF(gson.toJson("disconnect"));
            out.flush();

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public GsonBuilder getGsonBuilder(){
        GsonBuilder temp = new GsonBuilder();
        temp.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        temp.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        temp.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        temp.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        temp.registerTypeAdapter(Movie.class, new MovieAdapter());
        temp.registerTypeAdapter(Category.class, new CategoryAdapter());
        return temp;
    }
}
