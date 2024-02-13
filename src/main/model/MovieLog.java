package model;

// Represents a log of watched movies
public class MovieLog extends MovieList {

    public MovieLog() {
        super();
    }

    // EFFECTS: returns all movies in list along with rating, review, date watched, and whether you would rewatch
    public String outputLog() {
        StringBuilder output = new StringBuilder();

        for (Movie movie : movieList) {
            output.append(movie.getName()).append("\n");
            output.append("Rating: ").append(movie.getRating()).append("/10").append("\n");
            output.append("Review: ").append(movie.getReview()).append("\n");
            output.append("Date watched: ").append(movie.dateWatched()).append("\n");
            output.append("Would rewatch? ").append(movie.isRewatchable() ? "yes" : "no").append("\n\n");
        }

        return output.toString();
    }

}
