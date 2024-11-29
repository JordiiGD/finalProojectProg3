package co.edu.uptc.model;

import co.edu.uptc.persistence.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ThreadClient extends Thread{

    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    private MoviePlatform moviePlatform;
    private Gson gson;
    private boolean connected;

    public ThreadClient(MoviePlatform moviePlatform, Socket socket) {
        this.socket = socket;
        this.moviePlatform = moviePlatform;
        this.connected = true;
        gson = getGsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void run() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            while (true){
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
                    case "getUser":
                        getUser();
                        break;
                    case "searchMovie":
                        searchMovie();
                        break;
                    case "getMovie":
                        getMovie();
                        break;
                    case "getMovieForCommentAndRating":
                        getMovieForCommentAndRating();
                        break;
                    case "getMostVisitedMovies":
                        getMostVisitedMovies();
                        break;
                    case "getMoviesForCategory":
                        getMoviesForCategory();
                        break;
                    case "getRecentlyAddedMovies":
                        getMoviesRecentlyAdded();
                        break;
                    case "getCategories":
                        getCategories();
                        break;
                    case "addComment":
                        addComment();
                        break;
                    case "addRating":
                        addRating();
                        break;
                    case "disconnect":
                        disconnect();
                        break;
                    default:
                        break;
                }
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
        out.flush();
    }

    public void loginAdmin() throws IOException {
        String username = gson.fromJson(in.readUTF(), String.class);
        String password = gson.fromJson(in.readUTF(), String.class);
        String message = gson.toJson(moviePlatform.loginAdmin(username, password));
        out.writeUTF(message);
        out.flush();
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
        List<Movie> movies = new ArrayList<>(moviePlatform.getMovies());
        String message = gson.toJson(movies);
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getUsers(){
        List<User> users = new ArrayList<>(moviePlatform.getUsers());
        String message = gson.toJson(users);
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getUser() throws IOException {
        String username = gson.fromJson(in.readUTF(), String.class);
        String password = gson.fromJson(in.readUTF(), String.class);
        String user = gson.toJson(moviePlatform.getUser(username, password));
        out.writeUTF(user);
    }

    public void searchMovie() throws IOException {
        String title = gson.fromJson(in.readUTF(), String.class);
        List<Movie> movies = new ArrayList<>(moviePlatform.searchMovie(title));
        String message = gson.toJson(movies);
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMovie() throws IOException {
        String name = gson.fromJson(in.readUTF(), String.class);
        Movie movie = moviePlatform.getMovie(name);
        String message = gson.toJson(movie);
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMovieForCommentAndRating() throws IOException {
        String name = gson.fromJson(in.readUTF(), String.class);
        Movie movie = moviePlatform.getMovieForCommentAndRating(name);
        String message = gson.toJson(movie);
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMostVisitedMovies(){
        List<Movie> movies = new ArrayList<>(moviePlatform.getMostVisitedMovies());
        String message = gson.toJson(movies);
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {}
    }

    public void getMoviesForCategory() throws IOException {
        String category = gson.fromJson(in.readUTF(), String.class);
        List<Movie> movies = new ArrayList<>(moviePlatform.getMoviesForCategory(category));
        String message = gson.toJson(movies);
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMoviesRecentlyAdded(){
        List<Movie> movies = new ArrayList<>(moviePlatform.getRecentlyAddedMovies());
        String message = gson.toJson(movies);
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getCategories(){
        List<Category> categories = new ArrayList<>(moviePlatform.getCategories());
        String message = gson.toJson(categories);
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addComment() throws IOException {
        String commentary = gson.fromJson(in.readUTF(), String.class);
        Movie movie = gson.fromJson(in.readUTF(), Movie.class);
        User user = gson.fromJson(in.readUTF(), User.class);
        moviePlatform.addComment(commentary, movie, user);
    }

    public void addRating() throws IOException {
        Movie movie = gson.fromJson(in.readUTF(), Movie.class);
        int rating = gson.fromJson(in.readUTF(), int.class);
        moviePlatform.addRating(movie, rating);
    }

    public void disconnect() {
        try {
            System.out.println("Cliente desconectado: " + socket.getInetAddress());
            connected = false;
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("Error cerrando recursos: " + e.getMessage());
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
