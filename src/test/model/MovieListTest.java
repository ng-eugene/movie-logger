package model;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieListTest {

    private MovieList movieList;
    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    public void setup() {
        movieList = new MovieList();
        movie1 = new Movie("Interstellar");
        movie2 = new Movie("Heat");
    }

    @Test
    public void getMovieTest() {
        movieList.addMovie(movie1);
        assertEquals(movie1, movieList.getMovie(0));
    }

    @Test
    public void addMovieTest() {
        movieList.addMovie(movie1);
        assertEquals(1, movieList.getNumMovies());
        movieList.addMovie(movie2);
        assertEquals(2, movieList.getNumMovies());
}

    @Test
    public void removeMovieTest() {
        movieList.addMovie(movie1);
        assertEquals(1, movieList.getNumMovies());
        movieList.removeMovie(movie1);
        assertEquals(0, movieList.getNumMovies());
    }

    @Test
    public void getNumMoviesTest() {
        assertEquals(0, movieList.getNumMovies());
        movieList.addMovie(movie1);
        assertEquals(1, movieList.getNumMovies());
    }

    @Test
    public void listMovieTest() {
        movieList.addMovie(movie1);
        movieList.addMovie(movie2);

        String[] arr = movieList.listMovies();
        assertEquals("Interstellar", arr[0]);
        assertEquals("Heat", arr[1]);
    }

    @Test
    public void toJsonTest() {
        movieList.addMovie(movie1);
        movieList.addMovie(movie2);

        JSONArray js = movieList.toJson();

        assertEquals("Interstellar", js.getJSONObject(0).get("name"));
        assertEquals("Heat", js.getJSONObject(1).get("name"));
    }

}
