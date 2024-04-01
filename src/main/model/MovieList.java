package model;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Represents a list of movies
public class MovieList {

    protected List<Movie> movieList;

    // EFFECTS: creates new empty list of movies
    public MovieList() {
        movieList = new ArrayList<>();
    }

    // EFFECTS: returns array of all movie names in list
    public String[] listMovies() {
        String[] arr = new String[movieList.size()];
        for (int i = 0; i < movieList.size(); i++) {
            arr[i] = movieList.get(i).getName();
        }
        return arr;
    }

    // EFFECTS: returns list of movies as JSON array of movies
    public JSONArray toJson() {
        JSONArray list = new JSONArray();

        for (Movie m : movieList) {
            list.put(m.toJson());
        }

        return list;
    }

    // MODIFIES: this
    // EFFECTS: adds movie to list
    public void addMovie(Movie movie) {
        movieList.add(movie);
        EventLog.getInstance().logEvent(new Event("Added a movie "
                + movie.getName() + " to " + getClass().getSimpleName()));
    }

    // MODIFIES: this
    // EFFECTS: removes movie from list
    public void removeMovie(Movie movie) {
        if (movieList.remove(movie)) {
            EventLog.getInstance().logEvent(new Event("Removed movie "
                    + movie.getName() + " from " + getClass().getSimpleName()));
        }
    }

    // EFFECTS: returns movie at given index
    public Movie getMovie(int index) {
        return movieList.get(index);
    }

    // EFFECTS: returns movie with given name
    public Movie getMovie(String name) {
        for (Movie m : movieList) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }

    // EFFECTS: returns number of movies in list
    public int getNumMovies() {
        return movieList.size();
    }

    // REQUIRES: movieList is not empty
    // EFFECTS: returns a random movie from list
    public Movie getRandom() {
        Random rand = new Random();
        Movie movie = movieList.get(rand.nextInt(getNumMovies()));
        EventLog.getInstance().logEvent(new Event("Randomly generated movie " + movie.getName()));
        return movie;
    }

}
