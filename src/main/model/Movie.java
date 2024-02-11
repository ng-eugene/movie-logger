package model;

import java.time.LocalDate;

public class Movie {

    private String name;
    private Boolean watched;
    private LocalDate dateWatched;
    private int rating;
    private String review;
    private Boolean rewatchable;

    public Movie(String name) {
        this.name = name;
        this.watched = false;
    }

    public Movie(String name, int rating, String review, Boolean rewatchable) {
        this.name = name;
        this.watched = true;
        this.dateWatched = LocalDate.now();
        this.rating = rating;
        this.review = review;
        this.rewatchable = rewatchable;
    }

    public void logMovie(int rating, String review, Boolean rewatchable) {

        this.watched = true;
        this.dateWatched = LocalDate.now();
        this.rating = rating;
        this.review = review;
        this.rewatchable = rewatchable;

    }

    public String getName() {
        return name;
    }

    public Boolean isWatched() {
        return watched;
    }

    public LocalDate dateWatched() {
        return dateWatched;
    }

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public Boolean isRewatchable() {
        return rewatchable;
    }



}
