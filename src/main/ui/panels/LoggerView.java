package ui.panels;

import model.Movie;
import model.MovieList;
import model.MovieLog;
import model.exceptions.InvalidRatingException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// View for logging new movie
public class LoggerView implements ActionListener {

    private JSlider ratingSlider;
    private JTextArea reviewField;
    private JCheckBox rewatchBox;
    private JButton submitButton;
    private JPanel panel;
    private String name;
    private MovieLog log;
    private MovieList watchlist;

    // MODIFIES: this
    // EFFECTS: creates panel with fields
    public LoggerView(String name, MovieLog log, MovieList watchlist) {
        this.name = name;
        this.log = log;
        this.watchlist = watchlist;

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        ratingSlider = new JSlider(JSlider.HORIZONTAL, 10);
        ratingSlider.setMajorTickSpacing(10);
        ratingSlider.setMinorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);

        reviewField = new JTextArea();
        reviewField.setLineWrap(true);

        rewatchBox = new JCheckBox("Rewatch");

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        panel.add(new JLabel("Rating:"));
        panel.add(ratingSlider);
        panel.add(new JLabel("Review:"));
        panel.add(reviewField);
        panel.add(rewatchBox);
        panel.add(submitButton);
    }

    // EFFECTS: handles submit button
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            logMovie();
        }
    }

    // MODIFIES: this
    // EFFECTS: logs movie, removes view
    private void logMovie() {
        int rating = ratingSlider.getValue();
        String review = reviewField.getText();
        Boolean rewatch = rewatchBox.isSelected();
        Movie movie = new Movie(name);

        try {
            movie.logMovie(rating, review, rewatch);
            log.addMovie(movie);
            watchlist.removeMovie(watchlist.getMovie(name));
        } catch (InvalidRatingException e) {
            JOptionPane.showMessageDialog(null, "Invalid input!", "Alert", JOptionPane.ERROR_MESSAGE);
        }

        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        JOptionPane.showMessageDialog(null, "Movie successfully logged", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // EFFECTS: returns panel
    public JPanel getPanel() {
        return panel;
    }

}
