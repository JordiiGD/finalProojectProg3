package co.edu.uptc.structures;

import co.edu.uptc.model.Movie;

import java.util.Comparator;

public class MovieVisitedComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        if (o1.getNumberOfVisited() == o2.getNumberOfVisited()){
            return o1.getName().compareTo(o2.getName());
        }else {
            return o2.getNumberOfVisited() - o1.getNumberOfVisited();
        }
    }
}
