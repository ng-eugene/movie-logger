package model;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MovieListTest {

    private MovieList movieList;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;

    @BeforeEach
    public void setup() {
        movieList = new MovieList();
        movie1 = new Movie("Interstellar");
        movie2 = new Movie("Heat");
        movie3 = new Movie("Cars");
    }

    @Test
    public void getMovieTest() {
        movieList.addMovie(movie1);
        assertEquals(movie1, movieList.getMovie(0));
    }

    @Test
    public void getMovieNameTest() {
        movieList.addMovie(movie1);
        assertEquals(movie1, movieList.getMovie("Interstellar"));
    }

    @Test
    public void getMovieNameNoneTest() {
        movieList.addMovie(movie1);
        assertNull(movieList.getMovie("Heat"));
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
        movieList.removeMovie(movie1);
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

    // Small chance that the test may fail when it shouldn't (e-18)
    @Test
    public void randomTest() {
        movieList.addMovie(movie1);
        movieList.addMovie(movie2);
        movieList.addMovie(movie3);
        Set<Movie> set = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            Movie movie = movieList.getRandom();
            set.add(movie);
        }

        assertTrue(set.contains(movie1));
        assertTrue(set.contains(movie2));
        assertTrue(set.contains(movie3));
    }

}
