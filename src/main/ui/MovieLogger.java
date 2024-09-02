package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

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
        frame = new JFrame("Movie Logger");
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProc();
            }
        });

        StartMenu menu = new StartMenu(this);
        frame.setJMenuBar(menu.createMenuBar());

        frame.setContentPane(menu.createContentPane());
        frame.pack();
        frame.setVisible(true);
        currentPane = "";
    }

    // EFFECTS: handles menu actions
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() instanceof JMenuItem) {
                JMenuItem source = (JMenuItem) (e.getSource());
                String s = source.getText();
                handleMenu(s);
            }
        } catch (Exception exception) {
            frame.setVisible(false);
            frame.dispose();
            throw exception;
        }
    }

    // MODIFIES: this
    // EFFECTS: handles different menu selections
    private void handleMenu(String s) {
        if (s.equals("View watchlist")) {
            viewListUI();
            currentPane = "list view";
        } else if (s.equals("Add to watchlist")) {
            addListUI();
        } else if (s.equals("Remove from watchlist")) {
            removeListUI();
            currentPane = "remove list view";
        } else if (s.equals("Suggest random movie")) {
            randomUI();
        } else if (s.equals("View movie log")) {
            viewLogUI();
            currentPane = "log view";
        } else if (s.equals("Log a new movie")) {
            logUI();
            currentPane = "logger view";
        } else if (s.equals("Remove from log")) {
            removeLogUI();
            currentPane = "remove log view";
        } else if (s.equals("Save to file")) {
            saveUI();
        } else if (s.equals("Load from file")) {
            loadUI();
        }

        frame.revalidate();
        frame.repaint();
    }

    // EFFECTS: handles list viewing UI
    // displays error message if watchlist is empty
    private void viewListUI() {
        if (watchlist.getNumMovies() == 0) {
            JOptionPane.showMessageDialog(null, "Watchlist is empty!", "Alert", JOptionPane.ERROR_MESSAGE);
        } else {
            ListView view = new ListView(watchlist);
            frame.setContentPane(view.getSplitPane());
        }
    }

    // EFFECTS: handles list addition UI
    // displays error message if input is blank
    private void addListUI() {
        String s = JOptionPane.showInputDialog(null, "Input movie name", "New movie", JOptionPane.QUESTION_MESSAGE);
        if (s == null || s.equals("")) {
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
                return;
            } else {
                String[] names = watchlist.listMovies();
                name = (String) JOptionPane.showInputDialog(null, "Select movie", "New Log",
                        JOptionPane.PLAIN_MESSAGE, null, names, names[0]);
            }
        } else {
            name = JOptionPane.showInputDialog(null, "Select movie", "New Log",
                    JOptionPane.PLAIN_MESSAGE);
        }

        if (name == null || name.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid input!", "Alert", JOptionPane.ERROR_MESSAGE);
        } else {
            LoggerView view = new LoggerView(name, movieLog, watchlist);
            frame.setContentPane(view.getPanel());
        }
    }

    // EFFECTS: handles log removal UI
    private void removeLogUI() {
        if (movieLog.getNumMovies() == 0) {
            JOptionPane.showMessageDialog(null, "Log is empty!", "Alert", JOptionPane.ERROR_MESSAGE);
        } else {
            RemoveView view = new RemoveView(movieLog);
            frame.setContentPane(view.getPanel());
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
            JOptionPane.showInternalMessageDialog(null, "Loaded lists from " + JSON_STORE,
                    "Alert", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not load from " + JSON_STORE,
                    "Alert", JOptionPane.ERROR_MESSAGE);
        }
        popup.dispose();
        if (currentPane.equals("list view")) {
            viewListUI();
        } else if (currentPane.equals("log view")) {
            viewLogUI();
        }
    }


    // EFFECTS: returns a random movie name from watchlist
    // returns null if watchlist is empty
    private String randMovie() {
        if (watchlist.getNumMovies() == 0) {
            System.out.println("No movies in watchlist");
            return null;
        }

        return watchlist.getRandom().getName();
    }

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
        EventLog.getInstance().clear();
    }

    private void exitProc() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.toString());
        }
        frame.dispose();
        System.exit(0);
    }
}
