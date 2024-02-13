package model;

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
    public void listMovieTest() {
        movieList.addMovie(movie1);
        movieList.addMovie(movie2);

        assertEquals("1) Interstellar\n2) Heat\n", movieList.listMovies());
    }

}
