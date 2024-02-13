package model;

import model.exceptions.InvalidRatingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MovieLogTest {

    private MovieLog movieLog;
    private Movie movie3;
    private Movie movie4;
    private String now;

    @BeforeEach
    public void setup() {
        movieLog = new MovieLog();
        movie3 = new Movie("Heat");
        movie4 = new Movie("The Road");
        try {
            movie3.logMovie(9.5, "Warm", true);
            movie4.logMovie(6, "The Path", false);
        } catch (InvalidRatingException e) {
            fail();
        }
        now = LocalDate.now().toString();
    }

    @Test
    public void outputLogTest() {
        movieLog.addMovie(movie3);
        movieLog.addMovie(movie4);

        assertEquals("Heat\nRating: 9.5/10\nReview: Warm\nDate watched: " +
                now + "\nWould rewatch? yes\n\n" +
                "The Road\nRating: 6.0/10\nReview: The Path\nDate watched: " +
                now + "\nWould rewatch? no\n\n", movieLog.outputLog());
    }

}
