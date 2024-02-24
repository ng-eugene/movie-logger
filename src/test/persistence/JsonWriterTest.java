package persistence;

import model.Movie;
import model.MovieList;
import model.MovieLog;
import model.exceptions.InvalidRatingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    private JsonWriter writer;
    private JsonReader reader;
    private MovieList list;
    private MovieList testList;
    private MovieLog log;
    private MovieLog testLog;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;
    private Movie movie4;

    @BeforeEach
    public void setup() {
        writer = new JsonWriter("./data/testwrite.json");
        reader = new JsonReader("./data/testwrite.json");
        list = new MovieList();
        log = new MovieLog();
        testList = new MovieList();
        testLog = new MovieLog();
        movie1 = new Movie("Interstellar");
        movie2 = new Movie("Heat");
        movie3 = new Movie("Dead Poets Society");
        movie4 = new Movie("The Road");

        try {
            movie3.logMovie(9.123, "Carpe diem", true);
            movie4.logMovie(6, "Walk", false);
        } catch (InvalidRatingException e) {
            fail("Unexpected InvalidRatingException");
        }

        list.addMovie(movie1);
        list.addMovie(movie2);
        log.addMovie(movie3);
        log.addMovie(movie4);
    }

    @Test
    public void writerTest() {
        try {

            writer.open();
            writer.write(list, log);
            writer.close();
            reader.read(testList, testLog);

            assertEquals(2, testList.getNumMovies());
            assertEquals(2, testList.getNumMovies());
            assertEquals("Interstellar", testList.getMovie(0).getName());
            assertEquals("The Road", testLog.getMovie(1).getName());
            assertEquals(LocalDate.now().toString(), testLog.getMovie(0).dateWatched());
            assertEquals(6, testLog.getMovie(1).getRating());
            assertEquals("Walk", testLog.getMovie(1).getReview());
            assertFalse(testLog.getMovie(1).isRewatchable());

        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void invalidFileTest() {
        try {
            writer = new JsonWriter("./data/*.json");
            writer.open();
            fail("IOException was not thrown");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void emptyListsTest() {
        try {
            writer.open();
            writer.write(testList, testLog);
            writer.close();

            reader.read(testList, testLog);
            assertEquals(0, testList.getNumMovies());
            assertEquals(0, testLog.getNumMovies());
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }
}
