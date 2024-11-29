package co.edu.uptc.model;

import co.edu.uptc.structures.BinaryTree;
import co.edu.uptc.structures.MovieComparator;

public class Category {

    private String name;
    private BinaryTree<Movie> movies;

    public Category(String name) {
        this.name = name;
        movies = new BinaryTree<>(new MovieComparator());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BinaryTree<Movie> getMovies() {
        return movies;
    }

    public void setMovies(BinaryTree<Movie> movies) {
        this.movies = movies;
    }
}
