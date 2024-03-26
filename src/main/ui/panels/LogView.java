package ui.panels;

import model.Movie;
import model.MovieLog;

import javax.swing.*;

public class LogView extends ListView {

    protected MovieLog movieLog;

    // MODIFIES: this
    // EFFECTS: creates new view for log
    public LogView(MovieLog movieLog) {
        super(movieLog);
        this.movieLog = movieLog;
    }

    // MODIFIES: this
    // EFFECTS: creates display pane
    @Override
    protected void setDisplay() {
        displayPane = new JEditorPane();
    }

    // EFFECTS: Renders the selected image
    @Override
    protected void updateLabel(String name) {
        Movie movie = movieLog.getMovie(name);
        JEditorPane pane = (JEditorPane) displayPane;
        pane.setText(movieLog.outputMovie(movie));
    }

}
