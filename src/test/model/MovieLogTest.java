package model;

import model.exceptions.InvalidRatingException;
import org.json.JSONArray;
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
            fail("Unexpected InvalidRatingException");
        }

        now = LocalDate.now().toString();
    }

    @Test
    public void outputMovieTest() {
        movieLog.addMovie(movie3);

        assertEquals("Heat\nRating: 9.5/10\nReview: Warm\nDate watched: " +
                now + "\nWould rewatch? yes\n\n", movieLog.outputMovie(movie3));
    }


    @Test
    public void toJsonTest() {
        movieLog.addMovie(movie3);
        movieLog.addMovie(movie4);

        JSONArray js = movieLog.toJson();

        assertEquals("The Road", js.getJSONObject(1).get("name"));
        assertEquals(now, js.getJSONObject(1).get("date"));
        assertEquals(6.0, js.getJSONObject(1).get("rating"));
        assertEquals("The Path", js.getJSONObject(1).get("review"));
        assertEquals(false, js.getJSONObject(1).get("rewatch"));
    }

}
