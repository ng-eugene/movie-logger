package model;

import model.exceptions.InvalidRatingException;
import org.json.JSONObject;
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
            movie1.logMovie(9.5, "Humour Setting: 75%", true);
        } catch (InvalidRatingException e) {
            fail("Unexpected InvalidRatingException");
        }

        assertEquals(9.5, movie1.getRating());
        assertEquals("Humour Setting: 75%", movie1.getReview());
        assertEquals(LocalDate.now().toString(), movie1.getDate());
        assertTrue(movie1.getRewatch());
    }

    @Test
    public void logMovieInvalidHighTest() {
        try {
            movie2.logMovie(10.5, "O captain My captain", true);
            fail("InvalidRatingException was not thrown");
        } catch (InvalidRatingException e) {
            // pass
        }

        assertEquals(0, movie2.getRating());
    }

    @Test
    public void logMovieInvalidLowTest() {
        try {
            movie2.logMovie(-1, "O captain My captain", true);
            fail("InvalidRatingException was not thrown");
        } catch (InvalidRatingException e) {
            // pass
        }

        assertEquals(0, movie2.getRating());
    }

    @Test
    public void toJsonTest() {
        JSONObject js = movie1.toJson();

        assertEquals("Interstellar", js.get("name"));
    }

    @Test
    public void toJsonLoggedTest() {
        try {
            movie1.logMovie(9.5, "Humour Setting: 75%", true);
        } catch (InvalidRatingException e) {
            fail("Unexpected InvalidRatingException");
        }

        JSONObject js = movie1.toJson();

        assertEquals("Interstellar", js.get("name"));
        assertEquals(LocalDate.now().toString(), js.get("date"));
        assertEquals(9.5, js.get("rating"));
        assertEquals("Humour Setting: 75%", js.get("review"));
        assertEquals(true, js.get("rewatch"));
    }

}