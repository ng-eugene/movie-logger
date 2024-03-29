package ui.panels;

import model.MovieList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Random;

// View for movies in a list
public class ListView implements ListSelectionListener {

    protected JLabel picture;
    protected JList list;
    protected JSplitPane splitPane;
    protected JComponent displayPane;
    protected String[] movieNames;

    // MODIFIES: this
    // EFFECTS: creates new view for given movie list
    public ListView(MovieList movieList) {
        movieNames = movieList.listMovies();

        list = new JList(movieNames);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);

        JScrollPane listScrollPane = new JScrollPane(list);
        picture = new JLabel();
        picture.setHorizontalAlignment(JLabel.CENTER);

        setDisplay();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                listScrollPane, displayPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        Dimension minimumSize = new Dimension(100, 50);
        listScrollPane.setMinimumSize(minimumSize);
        displayPane.setMinimumSize(minimumSize);

        splitPane.setPreferredSize(new Dimension(400, 200));
    }

    // MODIFIES: this
    // EFFECTS: creates display pane
    protected void setDisplay() {
        displayPane = new JScrollPane(picture);
    }

    // EFFECTS: listens to list events
    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList)e.getSource();
        updateLabel(movieNames[list.getSelectedIndex()]);
    }

    // EFFECTS: Renders the selected image for given movie
    protected void updateLabel(String name) {
        Random rand = new Random();
        String url = "data/images/placeholder" + (rand.nextInt(5) + 1) + ".jpg";
        ImageIcon icon = new ImageIcon(url);
        picture.setIcon(icon);
    }

    // EFFECTS: returns pane
    public JSplitPane getSplitPane() {
        return splitPane;
    }

}
