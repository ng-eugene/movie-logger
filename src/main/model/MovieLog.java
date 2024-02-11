package model;

public class MovieLog extends MovieList {

    public MovieLog() {
        super();
    }

    public void watchMovie(Movie movie, int rating, String review, Boolean rewatchable) {
        addMovie(movie);
        movie.logMovie(rating, review, rewatchable);
    }

    @Override
    public String listMovies() {
        StringBuilder output = new StringBuilder();

        for (Movie movie : movieList) {
            output.append(movie.getName()).append("\n");
            output.append("Rating: ").append(movie.getRating()).append("/10").append("\n");
            output.append("Review: ").append(movie.getReview()).append("\n");
            output.append("Would rewatch? ").append(movie.isRewatchable() ? "yes" : "no").append("\n");
        }

        return output.toString();
    }
}
