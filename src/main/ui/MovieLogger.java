package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

// Movie logging application
public class MovieLogger implements ActionListener {

    private static final String JSON_STORE = "./data/lists.json";
    private MovieLog movieLog;
    private MovieList watchlist;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private JFrame frame;
    private String currentPane;

    // EFFECTS: begins movie logging app
    public MovieLogger() {
        runMovieLogger();
    }

    // MODIFIES: this
    // EFFECTS: initialises movie logger
    private void runMovieLogger() {
        init();
    }

    // EFFECTS: returns a random movie name from watchlist
    // returns null if watchlist is empty
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


    // MODIFIES: this
    // EFFECTS: saves lists to file
    // throws FileNotFoundException if the file does not exist
    private void saveLists() throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(watchlist, movieLog);
        jsonWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: loads lists from file
    // throws IOException if error occurs reading file
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
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initGraphics();
            }
        });
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
        currentPane = "menu";
    }

    // EFFECTS: handles menu actions
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {
            JMenuItem source = (JMenuItem) (e.getSource());
            String s = source.getText();
            handleMenu(s);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles different menu selections
    private void handleMenu(String s) {
        if (s.equals("View watchlist")) {
            viewListUI();
        } else if (s.equals("Add to watchlist")) {
            addListUI();
        } else if (s.equals("Remove from watchlist")) {
            removeListUI();
        } else if (s.equals("Suggest random movie")) {
            randomUI();
        } else if (s.equals("View movie log")) {
            viewLogUI();
        } else if (s.equals("Log a new movie")) {
            logUI();
        } else if (s.equals("Remove from log")) {
            removeLogUI();
        } else if (s.equals("Save to file")) {
            saveUI();
        } else if (s.equals("Load from file")) {
            loadUI();
        }

        frame.revalidate();
        frame.repaint();
        System.out.println(s);
    }

    // EFFECTS: handles list viewing UI
    // displays error message if watchlist is empty
    private void viewListUI() {
        if (watchlist.getNumMovies() == 0) {
            JOptionPane.showMessageDialog(null, "Watchlist is empty!", "Alert", JOptionPane.ERROR_MESSAGE);
        } else {
            ListView view = new ListView(watchlist);
            frame.setContentPane(view.getSplitPane());
            currentPane = "list view";
        }
    }

    // EFFECTS: handles list addition UI
    // displays error message if input is blank
    private void addListUI() {
        String s = JOptionPane.showInputDialog(null, "Input movie name", "New movie", JOptionPane.QUESTION_MESSAGE);
        if (s.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid input!", "Alert", JOptionPane.ERROR_MESSAGE);
        } else {
            watchlist.addMovie(new Movie(s));
            if (currentPane.equals("list view")) {
                viewListUI();
            }
        }
    }

    // EFFECTS: handles list removal UI
    private void removeListUI() {
        if (watchlist.getNumMovies() == 0) {
            JOptionPane.showMessageDialog(null, "Watchlist is empty!", "Alert", JOptionPane.ERROR_MESSAGE);
        } else {
            RemoveView view = new RemoveView(watchlist);
            frame.setContentPane(view.getPanel());
            currentPane = "list remove view";
        }
    }

    // EFFECTS: handles random movie UI, displays dialog box with randomly selected movie
    // displays error message if watchlist is empty
    private void randomUI() {
        String movie = randMovie();
        if (movie == null) {
            JOptionPane.showMessageDialog(null, "Watchlist is empty!", "Alert", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showInternalMessageDialog(null, "How about " + movie,
                    "Suggestion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // EFFECTS: handles log viewing UI
    // displays error message if log is empty
    private void viewLogUI() {
        if (movieLog.getNumMovies() == 0) {
            JOptionPane.showMessageDialog(null, "Log is empty!", "Alert", JOptionPane.ERROR_MESSAGE);
        } else {
            LogView view = new LogView(movieLog);
            frame.setContentPane(view.getSplitPane());
            currentPane = "log view";
        }
    }

    // EFFECTS: handles logging UI
    private void logUI() {
        String[] options = {"Watchlist", "New"};
        int n = JOptionPane.showOptionDialog(null, "Log from watchlist or new movie?",
                "New Log", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

        String name = "";

        if (n == 0) {
            if (watchlist.getNumMovies() == 0) {
                JOptionPane.showMessageDialog(null, "Watchlist is empty!", "Alert", JOptionPane.ERROR_MESSAGE);
            } else {
                String[] names = watchlist.listMovies();
                name = (String) JOptionPane.showInputDialog(null, "Select movie", "New Log",
                        JOptionPane.PLAIN_MESSAGE, null, names, names[0]);
            }
        } else {
            name = JOptionPane.showInputDialog(null, "Select movie", "New Log",
                    JOptionPane.PLAIN_MESSAGE);
        }

        if (name.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid input!", "Alert", JOptionPane.ERROR_MESSAGE);
        } else {
            LoggerView view = new LoggerView(name);
            frame.setContentPane(view.getPanel());
            currentPane = "logger view";
        }
    }

    // EFFECTS: handles log removal UI
    private void removeLogUI() {
        if (movieLog.getNumMovies() == 0) {
            JOptionPane.showMessageDialog(null, "Log is empty!", "Alert", JOptionPane.ERROR_MESSAGE);
        } else {
            RemoveView view = new RemoveView(movieLog);
            frame.setContentPane(view.getPanel());
            currentPane = "log remove view";
        }
    }

    // MODIFIES: this
    // EFFECTS: handles saving UI, saves data to files
    // displays error message if file does not exist
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

    // MODIFIES: this
    // EFFECTS: handles file loading UI, loads data from files
    // displays error message if file cannot be read
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
}
