package model;

import java.util.ArrayList;
import java.util.List;

public class MovieList {

    protected List<Movie> movieList;

    public MovieList() {
        movieList = new ArrayList<Movie>();
    }

    public void addMovie(Movie movie) {
        movieList.add(movie);
    }

    public void removeMovie(Movie movie) {
        movieList.remove(movie);
    }

    public String listMovies() {
        StringBuilder output = new StringBuilder();

        for (Movie movie : movieList) {
            output.append(movie.getName()).append("\n");
        }

        return output.toString();
    }

}
