package persistence;

import model.MovieList;
import model.MovieLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    private JsonReader reader;
    private MovieList list;
    private MovieLog log;

    @BeforeEach
    public void setup() {
        reader = new JsonReader("./data/testread.json");
        list = new MovieList();
        log = new MovieLog();
    }

    @Test
    public void readerTest() {
        try {
            reader.read(list, log);
            assertEquals(2, list.getNumMovies());
            assertEquals(2, log.getNumMovies());
            assertEquals("The Departed", list.getMovie(0).getName());
            assertEquals("Good Will Hunting", log.getMovie(0).getName());
            assertEquals("William", log.getMovie(0).getReview());
            assertEquals("2024-01-15", log.getMovie(0).getDate());
            assertEquals(9.235, log.getMovie(0).getRating());
            assertTrue(log.getMovie(0).getRewatch());
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void invalidFileTest() {
        try {
            reader = new JsonReader("./data/null.json");
            reader.read(list, log);
            fail("IOException was not thrown");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void emptyFileTest() {
        try {
            reader = new JsonReader("./data/testreadempty.json");
            reader.read(list, log);
            assertEquals(0, list.getNumMovies());
            assertEquals(0, log.getNumMovies());
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

}
