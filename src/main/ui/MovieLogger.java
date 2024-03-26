package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panels.ListView;
import ui.panels.LogView;
import ui.panels.StartMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

// Movie logging application
public class MovieLogger implements ActionListener {

    private static final String JSON_STORE = "./data/lists.json";
    private Scanner input;
    private MovieLog movieLog;
    private MovieList watchlist;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private JFrame frame;

    // EFFECTS: begins movie logging app
    public MovieLogger() {
        runMovieLogger();
    }

    // MODIFIES: this
    // EFFECTS: reads user input
    private void runMovieLogger() {
//        String select;
        init();

//        while (true) {
//            showMenu();
//            select = input.nextLine().toLowerCase();
//
//            if (select.equals("q")) {
//                break;
//            }
//
//            try {
//                doCommand(select);
//            } catch (InputMismatchException | IndexOutOfBoundsException e) {
//                System.out.println("Invalid input.");
//            }
//        }
//
//        System.out.println("end");

    }

//    // MODIFIES: this
//    // EFFECTS: processes user actions
//    private void doCommand(String select) {
//        if (select.equals("a")) {
//            showList();
//        } else if (select.equals("b")) {
//            randMovie();
//        } else if (select.equals("c")) {
//            watchlistAdd();
//        } else if (select.equals("d")) {
//            listRemove(watchlist);
//        } else if (select.equals("e")) {
//            showLog();
//        } else if (select.equals("f")) {
//            logMenu();
//        } else if (select.equals("g")) {
//            listRemove(movieLog);
//        } else if (select.equals("h")) {
//            saveLists();
//        } else if (select.equals("i")) {
//            loadLists();
//        } else {
//            throw new InputMismatchException();
//        }
//    }

//    // EFFECTS: shows user options
//    private void showMenu() {
//        System.out.println("\nSelect action");
//        System.out.println("\ta) View watchlist");
//        System.out.println("\tb) Random movie");
//        System.out.println("\tc) Add to watchlist");
//        System.out.println("\td) Remove from watchlist");
//        System.out.println("\te) View log");
//        System.out.println("\tf) Add to log");
//        System.out.println("\tg) Remove from log");
//        System.out.println("\th) Save lists");
//        System.out.println("\ti) Load lists");
//        System.out.println("\n\tq) quit");
//    }

//    // EFFECTS: lists movies on watchlist
//    private void showList() {
//        System.out.println("\nMy watchlist:");
//        System.out.println(watchlist.listMovies());
//    }
//
//    // EFFECTS: lists movies on log
//    private void showLog() {
//        System.out.println("\nMy movie log:");
//        System.out.println(movieLog.outputLog());
//    }

    // EFFECTS: prints out a random movie name from watchlist
    private String randMovie() {
        if (watchlist.getNumMovies() == 0) {
            System.out.println("No movies in watchlist");
            return null;
        }

        Random rand = new Random();
        Movie movie = watchlist.getMovie(rand.nextInt(watchlist.getNumMovies()));

        return movie.getName();
    }

//    // MODIFIES: this
//    // EFFECTS: removes selected movie from list
//    private void listRemove(MovieList list) {
//        Movie movie = chooseMovie(list);
//        list.removeMovie(movie);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: adds a new movie with input name to watchlist
//    private void watchlistAdd() {
//        System.out.println("\nEnter title of movie: ");
//        String name = input.nextLine();
//        watchlist.addMovie(new Movie(name));
//    }

//    // MODIFIES: this
//    // EFFECTS: allows user to either pick a movie from the watchlist or create a new movie to log
//    private void logMenu() {
//        System.out.println("\na) Log from watchlist");
//        System.out.println("b) Log new movie");
//
//        String select = input.nextLine().toLowerCase();
//
//        if (select.equals("a")) {
//            if (watchlist.getNumMovies() == 0) {
//                System.out.println("Watchlist is empty");
//                return;
//            }
//            Movie movie = chooseMovie(watchlist);
//            logMovie(movie);
//        } else if (select.equals("b")) {
//            System.out.println("Input movie name: ");
//            String name = input.nextLine();
//            logMovie(new Movie(name));
//        } else {
//            throw new InputMismatchException();
//        }
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS: logs movie with rating, review, rewatchability
//    private void logMovie(Movie movie) {
//        System.out.println("Input rating out of 10: ");
//        double rating = Double.parseDouble(input.nextLine());
//        System.out.println("Input review: ");
//        String review = input.nextLine();
//        System.out.println("Would you rewatch? (true/false)");
//        Boolean rewatch = Boolean.parseBoolean(input.nextLine());
//
//        try {
//            movie.logMovie(rating, review, rewatch);
//            movieLog.addMovie(movie);
//            watchlist.removeMovie(movie);
//        } catch (InvalidRatingException e) {
//            System.out.println("Invalid rating, must be between 0 and 10");
//        }
//    }

//    // EFFECTS: lists movies in given list, returns chosen movie
//    private Movie chooseMovie(MovieList list) {
//        System.out.println("\nPick movie: ");
//        System.out.println(list.listMovies());
//        int index = Integer.parseInt(input.nextLine()) - 1;
//        return list.getMovie(index);
//    }

    // EFFECTS: saves lists to file
    private void saveLists() throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(watchlist, movieLog);
        jsonWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: loads lists from file
    private void loadLists() throws IOException {
        MovieList watchlist = new MovieList();
        MovieLog movieLog = new MovieLog();
        jsonReader.read(watchlist, movieLog);
        this.watchlist = watchlist;
        this.movieLog = movieLog;
    }

    // MODIFIES: this
    // EFFECTS: initialises lists, scanners, read/writers, graphics
    private void init() {
        movieLog = new MovieLog();
        watchlist = new MovieList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initGraphics();
    }

    // EFFECTS: creates GUI and displays
    private void initGraphics() {
        frame = new JFrame("Frame");
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        StartMenu menu = new StartMenu(this);
        frame.setJMenuBar(menu.createMenuBar());

        frame.setContentPane(menu.createContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {
            JMenuItem source = (JMenuItem) (e.getSource());
            String s = source.getText();
            handleMenu(s);
        }
    }

    private void handleMenu(String s) {
        if (s.equals("View watchlist")) {
            listUI();
        } else if (s.equals("View movie log")) {
            logUI();
        } else if (s.equals("Suggest random movie")) {
            randomUI();
        } else if (s.equals("Save to file")) {
            saveUI();
        } else if (s.equals("Load from file")) {
            loadUI();
        }
        frame.revalidate();
        frame.repaint();
        System.out.println(s);
    }

    private void saveUI() {
        JDialog popup = new JDialog();
        popup.setUndecorated(true);
        JLabel label = new JLabel(new ImageIcon("data/images/hourglass.gif"));
        popup.add(label);
        popup.setLocationRelativeTo(frame);
        popup.pack();
        popup.setVisible(true);
        try {
            saveLists();
            JOptionPane.showInternalMessageDialog(null, "Saved lists to " + JSON_STORE,
                    "Alert", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Could not write to " + JSON_STORE,
                    "Alert", JOptionPane.ERROR_MESSAGE);
        }
        popup.dispose();
    }

    private void loadUI() {
        JDialog popup = new JDialog();
        popup.setUndecorated(true);
        JLabel label = new JLabel(new ImageIcon("data/images/hourglass.gif"));
        popup.add(label);
        popup.setLocationRelativeTo(frame);
        popup.pack();
        popup.setVisible(true);
        try {
            loadLists();
            JOptionPane.showInternalMessageDialog(null, "Saved lists to " + JSON_STORE,
                    "Alert", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not load from " + JSON_STORE,
                    "Alert", JOptionPane.ERROR_MESSAGE);
        }
        popup.dispose();
    }

    private void randomUI() {
        String movie = randMovie();
        if (movie == null) {
            JOptionPane.showMessageDialog(null, "Watchlist is empty!", "Alert", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showInternalMessageDialog(null, "How about " + movie,
                    "Suggestion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void listUI() {
        if (watchlist.getNumMovies() == 0) {
            JOptionPane.showMessageDialog(null, "Watchlist is empty!", "Alert", JOptionPane.ERROR_MESSAGE);
        } else {
            ListView view = new ListView(watchlist);
            frame.setContentPane(view.getSplitPane());
        }
    }

    private void logUI() {
        if (movieLog.getNumMovies() == 0) {
            JOptionPane.showMessageDialog(null, "Log is empty!", "Alert", JOptionPane.ERROR_MESSAGE);
        } else {
            LogView view = new LogView(movieLog);
            frame.setContentPane(view.getSplitPane());
        }
    }
}
