package model;

// Represents a log of watched movies
public class MovieLog extends MovieList {

    // EFFECTS: creates new empty list of movies
    public MovieLog() {
        super();
    }

    // EFFECTS: returns text output of given movie with rating, review,
    // date watched, rewatch
    public String outputMovie(Movie movie) {
        StringBuilder output = new StringBuilder();

        output.append(movie.getName()).append("\n");
        output.append("Rating: ").append(movie.getRating()).append("/10").append("\n");
        output.append("Review: ").append(movie.getReview()).append("\n");
        output.append("Date watched: ").append(movie.getDate()).append("\n");
        output.append("Would rewatch? ").append(movie.getRewatch() ? "yes" : "no").append("\n\n");

        return output.toString();
    }

}
