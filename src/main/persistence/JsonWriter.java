package persistence;

import model.MovieList;
import model.MovieLog;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer which writes lists to JSON file
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of lists to file
    public void write(MovieList list, MovieLog log) {

        JSONArray jsList = list.toJson();
        JSONArray jsLog = log.toJson();
        JSONObject lists = new JSONObject();

        lists.put("watchlist", jsList);
        lists.put("log", jsLog);

        saveToFile(lists.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String js) {
        writer.print(js);
    }

}
