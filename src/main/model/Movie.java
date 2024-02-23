package model;

import model.exceptions.InvalidRatingException;
import org.json.JSONObject;

import java.time.LocalDate;

// Represents a movie with a name, possibly a rating, review, and log date
public class Movie {

    private final String name;
    private String dateWatched;
    private double rating;
    private String review;
    private Boolean rewatchable;

    // EFFECTS: creates new movie with given name
    public Movie(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: adds rating, review, and whether you would rewatch
    public void logMovie(double rating, String review, Boolean rewatchable) throws InvalidRatingException {
        if (rating > 10 || rating < 0) {
            throw new InvalidRatingException();
        }

        this.dateWatched = LocalDate.now().toString();
        this.rating = rating;
        this.review = review;
        this.rewatchable = rewatchable;
    }

    public void logMovie(String date, double rating, String review, Boolean rewatchable) {
        this.dateWatched = date;
        this.rating = rating;
        this.review = review;
        this.rewatchable = rewatchable;
    }

    public JSONObject toJson() {
        JSONObject js = new JSONObject();

        js.put("name", this.name);

        if (dateWatched != null) {
            js.put("date", this.dateWatched);
            js.put("rating", this.rating);
            js.put("review", this.review);
            js.put("rewatch", this.rewatchable);
        }

        return js;
    }

    public String getName() {
        return name;
    }

    public String dateWatched() {
        return dateWatched;
    }

    public double getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public Boolean isRewatchable() {
        return rewatchable;
    }

}
