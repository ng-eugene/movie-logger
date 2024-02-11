package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class MovieTest {

    private Movie movie1;
    private Movie movie2;
    private Movie movie3;
    private Movie movie4;

    @BeforeEach
    public void setup() {
        movie1 = new Movie("Interstellar");
        movie2 = new Movie("Dead Poets Society");
        movie3 = new Movie("Heat", 9, "Warm", true);
        movie4 = new Movie("The Road", 6, "The Path", false);
    }

    @Test
    public void logMovieTest() {
        assertFalse(movie1.isWatched());
        movie1.logMovie(9, "Intrasolar", true);
        assertEquals(9, movie1.getRating());
        assertEquals("Intrasolar", movie1.getReview());
        assertEquals(LocalDate.now(), movie1.dateWatched());
        assertTrue(movie1.isRewatchable());
    }

}