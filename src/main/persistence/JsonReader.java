package persistence;

import model.Movie;
import model.MovieList;
import model.MovieLog;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader which reads lists from JSON file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads lists from file, writes to given lists
    // throws IOException in case of error reading file
    public void read(MovieList watchlist, MovieLog log) throws IOException {
        assert (watchlist.getNumMovies() == 0 && log.getNumMovies() == 0);
        String data = readFile(source);
        JSONObject js = new JSONObject(data);
        addMovies(watchlist, js.getJSONArray("watchlist"));
        addMovies(log, js.getJSONArray("log"));
    }

    // EFFECTS: reads source as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder str = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(str::append);
        }

        return str.toString();
    }

    // MODIFIES: list
    // EFFECTS: adds movies to list
    private void addMovies(MovieList list, JSONArray js) {
        for (Object movie : js) {
            JSONObject next = (JSONObject) movie;
            addMovie(list, next);
        }
    }

    // MODIFIES: list, movie
    // EFFECTS: creates movie from object, adds data as present and adds to list
    private void addMovie(MovieList list, JSONObject js) {
        String name = js.getString("name");
        Movie movie = new Movie(name);

        if (js.has("date")) {
            String date = js.getString("date");
            double rating = js.getDouble("rating");
            String review = js.getString("review");
            Boolean rewatch = js.getBoolean("rewatch");
            movie.logMovie(date, rating, review, rewatch);
        }

        list.addMovie(movie);
    }

}
