package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieLogTest {

    private MovieLog movieLog;
    private Movie movie3;
    private Movie movie4;

    @BeforeEach
    public void setup() {
        movieLog = new MovieLog();
        movie3 = new Movie("Heat", 9, "Warm", true);
        movie4 = new Movie("The Road", 6, "The Path", false);
    }

    @Test
    public void listMovieTest() {
        movieLog.addMovie(movie3);
        movieLog.addMovie(movie4);

        assertEquals("Heat\nRating: 9/10\nReview: Warm\nWould rewatch? yes\n" +
                "The Road\nRating: 6/10\nReview: The Path\nWould rewatch? no\n", movieLog.listMovies());
    }

}
