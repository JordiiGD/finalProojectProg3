package co.edu.uptc.model;

import co.edu.uptc.structures.*;
import co.edu.uptc.utils.EUserType;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MoviePlatform {

    private BinaryTree<User> users;
    private BinaryTree<Movie> movies;
    private BinaryTree<Category> categories;
    private Queue<Movie> recentlyAddedMovies;
    private BinaryTree<Movie> mostVisitedMovies;

    public MoviePlatform() {
        users = new BinaryTree<>(new UserComparator());
        movies = new BinaryTree<>(new MovieComparator());
        categories = new BinaryTree<>(new CategoryComparator());
        recentlyAddedMovies = new Queue<>();
        mostVisitedMovies = new BinaryTree<>(new MovieVisitedComparator());
        threadUpdateMostVisitedMovie();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        addRecentlyAddedMovie(movie);
        assignCategory(movie);
    }

    public void addRecentlyAddedMovie(Movie movie) {
        if (recentlyAddedMovies.size() < 5) {
            recentlyAddedMovies.push(movie);
        }else {
            recentlyAddedMovies.poll();
            recentlyAddedMovies.push(movie);
        }
    }

    public void assignCategory(Movie movie){
        for (String category : movie.getCategory().inOrder()){
            boolean exist = false;
            ArrayList<Category> categoryArrayList = (ArrayList<Category>) categories.inOrder();
            for (Category c : categoryArrayList) {
                if (c.getName().equalsIgnoreCase(category)) {
                    c.getMovies().add(movie);
                    exist = true;
                    categories = setCategories(categoryArrayList);
                }
            }
            if (!exist){
                Category categoryTemp = new Category(category);
                categoryTemp.getMovies().add(movie);
                categories.add(categoryTemp);
            }
        }
    }

    public BinaryTree<Category> setCategories(ArrayList<Category> categories){
        BinaryTree<Category> categoriesTemp = new BinaryTree<>(new CategoryComparator());
        for (Category c : categories) {
            categoriesTemp.add(c);
        }
        return categoriesTemp;
    }

    public void addComment(String comment, Movie movie, User author) {
        BinaryTree<Movie> moviesTemp = new BinaryTree<>(new MovieComparator());
        ArrayList<Movie> moviesToAdd = (ArrayList<Movie>) movies.inOrder();
        for (Movie m : moviesToAdd) {
            if (m.equals(movie)) {
                m.addComment(new Comment(author, comment, LocalDateTime.now()));
            }
            moviesTemp.add(m);
        }
        movies = moviesTemp;
    }

    public void addRating(Movie movie, int rating) {
        BinaryTree<Movie> moviesTemp = new BinaryTree<>(new MovieComparator());
        ArrayList<Movie> moviesToAdd = (ArrayList<Movie>) movies.inOrder();
        for (Movie m : moviesToAdd) {
            if (m.getName().equals(movie.getName())) {
                calculateRatings(m, rating);
            }
            moviesTemp.add(m);
        }
        movies = moviesTemp;
    }

    public void calculateRatings(Movie movie, int rating) {
        ArrayList<Integer> ratings = (ArrayList<Integer>) movie.getRatings().inOrder();
        BinaryTree<Integer> moviesTemp = new BinaryTree<>(Integer::compareTo);
        ratings.add(rating);
        int ratingNumber = 0;
        for (Integer i : ratings) {
            ratingNumber += i;
            moviesTemp.add(i);
        }
        movie.setRating((double) ratingNumber /ratings.size());
        movie.setRatings(moviesTemp);
    }

    public void updateMostVisitedMovie() {
        mostVisitedMovies = new BinaryTree<>(new MovieVisitedComparator());
        ArrayList<Movie> moviesToAdd = (ArrayList<Movie>) movies.inOrder();
        if (!moviesToAdd.isEmpty()) {
            for (Movie m : moviesToAdd) {
                mostVisitedMovies.add(m);
            }
        }
    }

    public void threadUpdateMostVisitedMovie() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        updateMostVisitedMovie();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();
    }

    public Movie getMovie(String title) {
        ArrayList<Movie> movieArrayList = (ArrayList<Movie>) movies.inOrder();
        for (Movie m : movieArrayList) {
            if (m.getName().equals(title)) {
                m.setNumberOfVisited(m.getNumberOfVisited() + 1);
                movies = setMovies(movieArrayList);
                return m;
            }
        }
        return null;
    }

    public BinaryTree<Movie> setMovies(ArrayList<Movie> movies) {
        BinaryTree<Movie> moviesTemp = new BinaryTree<>(new MovieComparator());
        for (Movie m : movies) {
            moviesTemp.add(m);
        }
        return moviesTemp;
    }

    public ArrayList<User> getUsers(){
        return (ArrayList<User>) users.inOrder();
    }

    public ArrayList<Movie> getMovies(){
        return (ArrayList<Movie>) movies.inOrder();
    }

    public ArrayList<Movie> getRecentlyAddedMovies(){
        Queue<Movie> recentlyAddedMoviesTemp = this.recentlyAddedMovies;
        ArrayList<Movie> recentlyAddedMoviesArrayList = new ArrayList<>();
        int size = recentlyAddedMoviesTemp.size();
        for (int i = 0; i < size; i++) {
            recentlyAddedMoviesArrayList.add(recentlyAddedMoviesTemp.poll());
        }
        return recentlyAddedMoviesArrayList;
    }

    public ArrayList<Movie> getMostVisitedMovies() {
        return (ArrayList<Movie>) mostVisitedMovies.inOrder();
    }

    public ArrayList<Category> getCategories() {
        return (ArrayList<Category>) categories.inOrder();
    }

    public ArrayList<Movie> getMoviesForCategory(String category) {
        ArrayList<Category> categoryArrayList = (ArrayList<Category>) categories.inOrder();
        for (Category c : categoryArrayList) {
            if (c.getName().equals(category)) {
                return (ArrayList<Movie>) c.getMovies().inOrder();
            }
        }
        return null;
    }

    public boolean loginClient(String username, String password) {
        ArrayList<User> userArrayList = (ArrayList<User>) users.inOrder();
        for (User user : userArrayList) {
            if (user.getEmail().equals(username) && user.getPassword().equals(password)
                    && user.getUserType().equals(EUserType.client)) {
                return true;
            }
        }
        return false;
    }

    public boolean loginAdmin(String username, String password) {
        ArrayList<User> userArrayList = (ArrayList<User>) users.inOrder();
        for (User user : userArrayList) {
            if (user.getEmail().equals(username) && user.getPassword().equals(password)
                    && user.getUserType().equals(EUserType.administrator)) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String username, String password) {
        ArrayList<User> userArrayList = (ArrayList<User>) users.inOrder();
        for (User user : userArrayList) {
            if (user.getEmail().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<Movie> searchMovie(String title) {
        ArrayList<Movie> getMovies = new ArrayList<>();
        ArrayList<Movie> moviesArrayList = (ArrayList<Movie>) movies.inOrder();
        for (Movie m : moviesArrayList) {
            if (m.getName().toLowerCase().contains(title.toLowerCase())) {
                getMovies.add(m);
            }
        }
        return getMovies;
    }
}
