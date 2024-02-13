package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of movies
public class MovieList {

    protected List<Movie> movieList;

    public MovieList() {
        movieList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds movie to list
    public void addMovie(Movie movie) {
        movieList.add(movie);
    }

    // MODIFIES: this
    // EFFECTS: removes movie from list
    public void removeMovie(Movie movie) {
        movieList.remove(movie);
    }

    // EFFECTS: returns movie at given index
    public Movie getMovie(int index) {
        return movieList.get(index);
    }

    // EFFECTS: returns number of movies in list
    public int numMovies() {
        return movieList.size();
    }

    // EFFECTS: returns all movies in list
    public String listMovies() {
        StringBuilder output = new StringBuilder();

        for (Movie movie : movieList) {
            output.append(movieList.indexOf(movie) + 1).append(") ")
                    .append(movie.getName()).append("\n");
        }

        return output.toString();
    }

}
