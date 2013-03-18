package it.jtomato.example;

import it.jtomato.JTomato;
import it.jtomato.gson.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * This is simple example of JTomato
 * 
 * @author <a href="mailto:tambug@gmail.com">Giordano Tamburrelli</a>
 * 
 * @version 1.0
 **/
public class Example {

	
	public static void main(String[] args) {
		//Creating a JTomato instance, you need a vali API key.
		JTomato jtomato = new JTomato("5cb467m9cke8c2smzrtqpebb");
		
		//Creating a list to store the result
		List<Movie> movies = new ArrayList<Movie>();
		
		//Searching a movie, page 1 
		int total = jtomato.searchMovie("spider man", movies, 1);
		System.out.println("Found "+total+" results");
		System.out.println("First result: "+ movies.get(0));
		
		//Obtaning additional info
		Movie movie = jtomato.getMovieAdditionalInfo(movies.get(0));
		//Printing the movie genre
		System.out.println(movie.genres.get(0));
		
		//Getting similar movies
		List<Movie> similarMovies = jtomato.getSimilarMovies(movie, "us", 5);
		
	}

}
