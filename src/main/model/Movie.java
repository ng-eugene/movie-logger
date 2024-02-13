package model;

import model.exceptions.InvalidRatingException;

import java.time.LocalDate;

// Represents a movie with a name, possibly a rating, review, and log date
public class Movie {

    private final String name;
    private LocalDate dateWatched;
    private double rating;
    private String review;
    private Boolean rewatchable;

    public Movie(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: adds rating, review, and whether you would rewatch
    public void logMovie(double rating, String review, Boolean rewatchable) throws InvalidRatingException {
        if (rating > 10 || rating < 0) {
            throw new InvalidRatingException();
        }

        this.dateWatched = LocalDate.now();
        this.rating = rating;
        this.review = review;
        this.rewatchable = rewatchable;
    }

    public String getName() {
        return name;
    }

    public LocalDate dateWatched() {
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
