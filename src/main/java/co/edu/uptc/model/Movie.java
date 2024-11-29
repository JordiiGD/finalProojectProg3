package co.edu.uptc.model;

import co.edu.uptc.structures.BinaryTree;
import co.edu.uptc.structures.CommentComparator;
import co.edu.uptc.structures.Queue;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class Movie {

    private String name;
    private String description;
    private double rating;
    private String poster;
    private String wallpaper;
    private LocalDate releaseDate;
    private BinaryTree<Comment> comments;
    private BinaryTree<String> category;
    private int numberOfVisited;
    private BinaryTree<Integer> ratings;

    public Movie(LocalDate releaseDate, String description, String name) {
        this.releaseDate = releaseDate;
        this.description = description;
        this.name = name;
        comments = new BinaryTree<>(new CommentComparator());
        category = new BinaryTree<>(String::compareToIgnoreCase);
        ratings = new BinaryTree<>(Integer::compareTo);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addCategory(String category) {
        this.category.add(category);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        DecimalFormat format = new DecimalFormat("#.#");
        String result = format.format(rating);
        return result;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BinaryTree<Comment> getComments() {
        return comments;
    }

    public void setComments(BinaryTree<Comment> comments) {
        this.comments = comments;
    }

    public BinaryTree<String> getCategory() {
        return category;
    }

    public void setCategory(BinaryTree<String> category) {
        this.category = category;
    }

    public int getNumberOfVisited() {
        return numberOfVisited;
    }

    public void setNumberOfVisited(int numberOfVisited) {
        this.numberOfVisited = numberOfVisited;
    }

    public BinaryTree<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(BinaryTree<Integer> ratings) {
        this.ratings = ratings;
    }

    public String getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(String wallpaper) {
        this.wallpaper = wallpaper;
    }
}
