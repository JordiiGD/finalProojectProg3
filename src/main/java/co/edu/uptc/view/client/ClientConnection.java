package co.edu.uptc.view.client;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.User;
import co.edu.uptc.persistence.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ClientConnection {private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Gson gson;

    public ClientConnection(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            gson = getGsonBuilder().setPrettyPrinting().create();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(String userName, String password){
        try {
            String key = "loginClient";
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

    public User getUser(String userName, String password){
        String key = "getUser";
        String message = gson.toJson(key);
        try {
            out.writeUTF(message);
            userName = gson.toJson(userName);
            password = gson.toJson(password);
            out.writeUTF(userName);
            out.writeUTF(password);
            out.flush();

            return gson.fromJson(in.readUTF(), User.class);
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

    public ArrayList<Movie> searchMovie(String title){
        String key = "searchMovie";
        String message = gson.toJson(key);
        try {
            out.writeUTF(message);

            out.writeUTF(gson.toJson(title));
            out.flush();

            Type moviesListType = new TypeToken<ArrayList<Movie>>(){}.getType();
            ArrayList<Movie> moviesList = gson.fromJson(in.readUTF(), moviesListType);
            return moviesList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Movie getMovie(String name){
        String key = "getMovie";
        String message = gson.toJson(key);
        try {
            out.writeUTF(message);

            out.writeUTF(gson.toJson(name));
            out.flush();

            return gson.fromJson(in.readUTF(), Movie.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Movie getMovieForCommentAndRating(String name){
        String key = "getMovieForCommentAndRating";
        String message = gson.toJson(key);
        try {
            out.writeUTF(message);

            out.writeUTF(gson.toJson(name));
            out.flush();

            return gson.fromJson(in.readUTF(), Movie.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Movie> getMostVisitedMovies(){
        String key = "getMostVisitedMovies";
        String message = gson.toJson(key);
        try {
            out.writeUTF(message);
            out.flush();

            Type moviesListType = new TypeToken<ArrayList<Movie>>(){}.getType();
            ArrayList<Movie> moviesList = gson.fromJson(in.readUTF(), moviesListType);
            return moviesList;
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

    public ArrayList<Movie> getMoviesForCategory(String category){
        String key = "getMoviesForCategory";
        String message = gson.toJson(key);
        try {
            out.writeUTF(message);

            out.writeUTF(gson.toJson(category));
            out.flush();

            Type moviesListType = new TypeToken<ArrayList<Movie>>(){}.getType();
            ArrayList<Movie> moviesList = gson.fromJson(in.readUTF(), moviesListType);
            return moviesList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Movie> getMoviesRecentlyAdded(){
        String key = "getRecentlyAddedMovies";
        String message = gson.toJson(key);
        try {
            out.writeUTF(message);
            out.flush();

            Type moviesListType = new TypeToken<ArrayList<Movie>>(){}.getType();
            ArrayList<Movie> moviesList = gson.fromJson(in.readUTF(), moviesListType);
            return moviesList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Category> getCategories(){
        String key = "getCategories";
        String message = gson.toJson(key);
        try {
            out.writeUTF(message);
            out.flush();

            Type categoriesListType = new TypeToken<ArrayList<Category>>(){}.getType();
            ArrayList<Category> categoriesList = gson.fromJson(in.readUTF(), categoriesListType);
            return categoriesList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addComment(String commentary, Movie movie, User user){
        String key = "addComment";
        String message = gson.toJson(key);
        try {
            out.writeUTF(message);
            out.flush();

            out.writeUTF(gson.toJson(commentary));
            out.writeUTF(gson.toJson(movie));
            out.writeUTF(gson.toJson(user));
        } catch (IOException e) {}
    }

    public void addRating(Movie movie, int rating){
        String key = "addRating";
        String message = gson.toJson(key);
        try {
            out.writeUTF(message);
            out.flush();

            out.writeUTF(gson.toJson(movie));
            out.writeUTF(gson.toJson(rating));
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
