package main;

import commands.Commands;
import manager.GenreManager;
import manager.MovieManager;
import model.Genre;
import model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieGenre implements Commands {
    static Scanner scanner = new Scanner(System.in);
    static GenreManager genreManager = new GenreManager();
    static MovieManager movieManager = new MovieManager();

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            printCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_MOVIE:
                    addMovie();
                    break;
                case ADD_GENRE:
                    addGenre();
                    break;
                case ALL_MOVIES:
                    printAllMovies();
                    break;
                case ALL_GENRE:
                    printAllGenre();
                    break;
                case MOVIES_BY_GENRE:
                    moviesByGenre();
                    break;
                case MOVIES_BY_YEAR:
                    movieByYear();
                    break;
                case SEARCH_MOVIE:
                    searchMovieByTitle();
                    break;
                case DELETE_MOVIE_BY_TITLE:
                    deleteMovieByTitle();

                default:

                    System.out.println("Invalid command! Please try again");
            }
        }


    }

    private static void searchMovieByTitle() {
        System.out.println("Please input movie's title for search movie");
        printAllMovies();
        String movSearch = scanner.nextLine();
        List<Movie> movies = movieManager.getAllMovies();
        for (Movie movie:movies ) {
            movie.setTitle(movSearch);
        }
        System.out.println(movieManager.searchMovieByTitle(movSearch));
    }

    private static void deleteMovieByTitle() {
        System.out.println("Please input movie's title for delete movie");
        printAllMovies();
        String movDelete = scanner.nextLine();
//        String[] split = movDelete.split(",");
       movieManager.deleteMovieByTitle(movDelete);
    }

    private static void addMovie() {
        System.out.println("Please choose movie's genre");
        printAllGenre();
        String moviesGenre = scanner.nextLine();
        String[] genreData = moviesGenre.split(",");
        List<Genre> genres = genreManager.getAllGenres();
        List<Genre> newGenre = new ArrayList<Genre>();
        for (Genre genre : genres) {
            for (int i = 0; i < genreData.length; i++) {
                if (genre.getName().equals(genreData[i])) {
                    newGenre.add(genre);
                }

            }
        }
        System.out.println("Please input movies (title, description, year, genre_id)");
        String movGenre = scanner.nextLine();
        String[] movieGenre = movGenre.split(",");
        Movie movie = new Movie();
        movie.setTitle(movieGenre[0]);
        movie.setDescription(movieGenre[1]);
        movie.setYear(Integer.parseInt(movieGenre[2]));
        movie.setGenre_id(Integer.parseInt(movieGenre[3]));
//        System.out.println("Please choose movie's genre");
//        printAllGenre();
//        String moviesGenre = scanner.nextLine();
//
//        String[] genreData = moviesGenre.split(",");
//        List<Genre> genres = genreManager.getAllGenres();
//        List<Genre> newGenre = new ArrayList<Genre>();
//        for (Genre genre : genres) {
//            for (int i = 0; i < genreData.length; i++) {
//                if (genre.getName().equals(genreData[i])) {
//                    movie.setGenre_id(Integer.parseInt(genreData[3]));
//                    newGenre.add(genre);
//                }
//
//            }
//        }
        movieManager.addMovie(movie);


    }

    private static void addGenre() {
        System.out.println("Please input genre name");
        String genre = scanner.nextLine();
        Genre genre1 = new Genre();
        genre1.setName(genre);
        genreManager.addGenre(genre1);
    }

    private static void movieByYear() {
        System.out.println("Please input movie's  year");
        printAllMovies();
        String moviesYear = scanner.nextLine();
        System.out.println(movieManager.printMoviesByYear(Integer.parseInt(moviesYear)));
    }

    private static void moviesByGenre() {
        System.out.println("Please choose movie's genre");
        printAllGenre();
        String movGenre = scanner.nextLine();
        List<Movie> movies = movieManager.getMoviesByGenre(Integer.parseInt(movGenre));
        for (Movie movie : movies) {
            System.out.println(". "+ movie.getTitle() + ",  " + movie.getDescription() + ",  " + movie.getYear() + ",  " + movie.getGenre_id());
        }
    }

    private static void printAllGenre() {
        List<Genre> genres = genreManager.getAllGenres();
        for (Genre genre : genres) {
            System.out.println(genre.getId() + "." + genre.getName());
        }
    }


    private static void printAllMovies() {
        List<Movie> allMovies = movieManager.getAllMovies();
        for (Movie allMovie : allMovies) {
            System.out.println(" - " + allMovie.getId() + ",  " + allMovie.getTitle() + ",  " + allMovie.getDescription() + ",  " + allMovie.getYear());
        }
    }


    public static void printCommands() {
        System.out.println("Please input " + EXIT + " for exit");
        System.out.println("Please input " + ADD_MOVIE + " for add movie");
        System.out.println("Please input " + ADD_GENRE + " for add genre");
        System.out.println("Please input " + ALL_MOVIES + "  print all movies");
        System.out.println("Please input " + ALL_GENRE + " print all genre");
        System.out.println("Please input " + MOVIES_BY_GENRE + " print movies by genre");
        System.out.println("Please input " + MOVIES_BY_YEAR + " print movies by year ");
        System.out.println("Please input " + SEARCH_MOVIE + "input title for search movie's");
        System.out.println("Please input " + DELETE_MOVIE_BY_TITLE + "input title for delete movie");
    }
}
