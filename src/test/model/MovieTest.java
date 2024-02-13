package model;

import model.exceptions.InvalidRatingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class MovieTest {

    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    public void setup() {
        movie1 = new Movie("Interstellar");
        movie2 = new Movie("Dead Poets Society");
    }

    @Test
    public void logMovieValidTest() {
        try {
            movie1.logMovie(9.5, "Intrasolar", true);
        } catch (InvalidRatingException e) {
            fail();
        }
        assertEquals(9.5, movie1.getRating());
        assertEquals("Intrasolar", movie1.getReview());
        assertEquals(LocalDate.now(), movie1.dateWatched());
        assertTrue(movie1.isRewatchable());
    }

    @Test
    public void logMovieInvalidHighTest() {
        try {
            movie2.logMovie(10.5, "O captain My captain", true);
            fail();
        } catch (InvalidRatingException e) {

        }
    }

    @Test
    public void logMovieInvalidLowTest() {
        try {
            movie2.logMovie(-1, "O captain My captain", true);
            fail();
        } catch (InvalidRatingException e) {

        }
    }

}