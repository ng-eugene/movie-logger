package model;

import model.exceptions.InvalidRatingException;
import org.json.JSONObject;

import java.time.LocalDate;

// Represents a movie with a name, possibly a rating, review, rewatchability, and log date
public class Movie {

    private final String name;
    private String dateWatched;
    private double rating;
    private String review;
    private Boolean rewatch;

    // EFFECTS: creates new movie with given name
    public Movie(String name) {
        this.name = name.trim();
    }

    // MODIFIES: this
    // EFFECTS: adds rating, review, and whether you would rewatch
    // throws InvalidRatingException in case of rating out of bounds
    public void logMovie(double rating, String review, Boolean rewatch) throws InvalidRatingException {
        if (rating > 10 || rating < 0) {
            throw new InvalidRatingException();
        }

        this.dateWatched = LocalDate.now().toString();
        this.rating = rating;
        this.review = review;
        this.rewatch = rewatch;

        EventLog.getInstance().logEvent(new Event("Logged movie " + name));
    }

    // MODIFIES: this
    // EFFECTS: adds date watched, rating, review, and whether you would rewatch
    public void logMovie(String date, double rating, String review, Boolean rewatch) {
        this.dateWatched = date;
        this.rating = rating;
        this.review = review;
        this.rewatch = rewatch;
    }

    // EFFECTS: returns movie in JSON format
    public JSONObject toJson() {
        JSONObject js = new JSONObject();

        js.put("name", getName());

        if (dateWatched != null) {
            js.put("date", getDate());
            js.put("rating", getRating());
            js.put("review", getReview());
            js.put("rewatch", getRewatch());
        }

        return js;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return dateWatched;
    }

    public double getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public Boolean getRewatch() {
        return rewatch;
    }

}
