package ui;

import model.*;
import model.exceptions.InvalidRatingException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Movie logging application
public class MovieLogger {

    private static final String JSON_STORE = "./data/lists.json";
    private Scanner input;
    private MovieLog movieLog;
    private MovieList watchlist;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: begins movie logging app
    public MovieLogger() {
        runMovieLogger();
    }

    // MODIFIES: this
    // EFFECTS: reads user input
    private void runMovieLogger() {
        String select;
        init();

        while (true) {
            showMenu();
            select = input.nextLine().toLowerCase();

            if (select.equals("q")) {
                break;
            }

            try {
                doCommand(select);
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println("Invalid input.");
            }
        }

        System.out.println("end");

    }

    // MODIFIES: this
    // EFFECTS: processes user actions
    private void doCommand(String select) {
        if (select.equals("a")) {
            System.out.println("\nMy watchlist:");
            System.out.println(watchlist.listMovies());
        } else if (select.equals("b")) {
            watchlistAdd();
        } else if (select.equals("c")) {
            listRemove(watchlist);
        } else if (select.equals("d")) {
            System.out.println("\nMy movie log:");
            System.out.println(movieLog.outputLog());
        } else if (select.equals("e")) {
            logMenu();
        } else if (select.equals("f")) {
            listRemove(movieLog);
        } else if (select.equals("g")) {
            saveLists();
        } else if (select.equals("h")) {
            loadLists();
        } else {
            throw new InputMismatchException();
        }
    }

    // EFFECTS: shows user options
    private void showMenu() {
        System.out.println("\nSelect action");
        System.out.println("\ta) View watchlist");
        System.out.println("\tb) Add to watchlist");
        System.out.println("\tc) Remove from watchlist");
        System.out.println("\td) View log");
        System.out.println("\te) Add to log");
        System.out.println("\tf) Remove from log");
        System.out.println("\tg) Save lists");
        System.out.println("\th) Load lists");
        System.out.println("\n\tq) quit");
    }

    // MODIFIES: this
    // EFFECTS: removes selected movie from list
    private void listRemove(MovieList list) {
        Movie movie = chooseMovie(list);
        list.removeMovie(movie);
    }

    // MODIFIES: this
    // EFFECTS: adds a new movie with input name to watchlist
    private void watchlistAdd() {
        System.out.println("\nEnter title of movie: ");
        String name = input.nextLine();
        watchlist.addMovie(new Movie(name));
    }

    // MODIFIES: this
    // EFFECTS: allows user to either pick a movie from the watchlist or create a new movie to log
    private void logMenu() {
        System.out.println("\na) Log from watchlist");
        System.out.println("b) Log new movie");

        String select = input.nextLine().toLowerCase();

        if (select.equals("a")) {
            if (watchlist.getNumMovies() == 0) {
                System.out.println("Watchlist is empty");
                return;
            }
            Movie movie = chooseMovie(watchlist);
            logMovie(movie);
        } else if (select.equals("b")) {
            System.out.println("Input movie name: ");
            String name = input.nextLine();
            logMovie(new Movie(name));
        } else {
            throw new InputMismatchException();
        }

    }

    // MODIFIES: this
    // EFFECTS: logs movie with rating, review, rewatchability
    private void logMovie(Movie movie) {
        System.out.println("Input rating out of 10: ");
        double rating = Double.parseDouble(input.nextLine());
        System.out.println("Input review: ");
        String review = input.nextLine();
        System.out.println("Would you rewatch? (true/false)");
        Boolean rewatch = Boolean.parseBoolean(input.nextLine());

        try {
            movie.logMovie(rating, review, rewatch);
            movieLog.addMovie(movie);
            watchlist.removeMovie(movie);
        } catch (InvalidRatingException e) {
            System.out.println("Invalid rating, must be between 0 and 10");
        }
    }

    // EFFECTS: lists movies in given list, returns chosen movie
    private Movie chooseMovie(MovieList list) {
        System.out.println("\nPick movie: ");
        System.out.println(list.listMovies());
        int index = Integer.parseInt(input.nextLine()) - 1;
        return list.getMovie(index);
    }

    // EFFECTS: saves lists to file
    private void saveLists() {
        try {
            jsonWriter.open();
            jsonWriter.write(watchlist, movieLog);
            jsonWriter.close();
            System.out.println("Saved lists to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads lists from file
    private void loadLists() {
        try {
            MovieList watchlist = new MovieList();
            MovieLog movieLog = new MovieLog();
            jsonReader.read(watchlist, movieLog);
            this.watchlist = watchlist;
            this.movieLog = movieLog;
            System.out.println("Loaded lists from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: initialises lists
    private void init() {
        movieLog = new MovieLog();
        watchlist = new MovieList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

}
