package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieListTest {

    private MovieList movieList;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;
    private Movie movie4;

    @BeforeEach
    public void setup() {
        movieList = new MovieList();
        movie1 = new Movie("Interstellar");
        movie2 = new Movie("Dead Poets Society");
        movie3 = new Movie("Heat", 9, "Warm", true);
        movie4 = new Movie("The Road", 6, "The Path", false);
    }

    @Test
    public void listMovieTest() {
        movieList.addMovie(movie1);
        movieList.addMovie(movie3);

        assertEquals("Interstellar\nHeat\n", movieList.listMovies());
    }

}
