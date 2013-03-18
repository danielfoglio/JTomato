package it.jtomato.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import it.jtomato.JTomato;
import it.jtomato.gson.AbridgedCast;
import it.jtomato.gson.Movie;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * This is a testing class for the JTomato client class. This class REQUIRE an
 * internet connection since it actually invokes the Rotten Tomaatoes API
 * 
 * @author <a href="mailto:tambug@gmail.com">Giordano Tamburrelli</a>
 * 
 * @version 1.0
 * 
 * @see JTomato
 **/
public class JTomatoTestWithNetwork {

	private JTomato rottenClient;
	private final String propertyFile = "res/tomatoes.properties";

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(propertyFile));
		String rottenKey = prop.getProperty("RottenTomatoesApiKey");
		rottenClient = new JTomato(rottenKey);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void simpleSearchTest() {
		List<Movie> movies = new ArrayList<Movie>();
		int total = rottenClient.searchMovie("Con Air", movies, 1);
		assertTrue(total > 0);
		assertTrue(movies.size() > 0);
	}

	@Test
	public void emptySearchTest() {
		List<Movie> movies = new ArrayList<Movie>();
		int total = rottenClient.searchMovie("aljhflsdhjfjadns", movies, 1);
		assertTrue(total == 0);
		assertTrue(movies.size() == 0);
	}

	@Test
	public void simimlarMovieTest() {
		List<Movie> movies = new ArrayList<Movie>();
		int total = rottenClient.searchMovie("Pulp Fiction", movies, 1);
		movies = rottenClient.getSimilarMovies(movies.get(0), null, 3);
		assertTrue(total > 0);
		assertTrue(movies.size() > 0);
	}

	@Test
	public void boxOfficeTest() {
		List<Movie> movies = rottenClient.getBoxOfficeMovies("us", 0);
		assertTrue(movies.size() > 0);
	}

	@Test
	public void getOpeningMoviesTest() {
		List<Movie> movies = rottenClient.getOpeningMovies(null, 0);
		assertTrue(movies.size() > 0);
	}

	@Test
	public void getUpcomingMoviesTest() {
		List<Movie> movies = new ArrayList<Movie>();
		int total = rottenClient.getUpcomingMovies(movies, "us", 1);
		assertTrue(total > 0);
		assertTrue(movies.size() > 0);
	}

	@Test
	public void getTopRentalsMoviesTest() {
		List<Movie> movies = rottenClient.getTopRentalsMovies(null, 0);
		assertTrue(movies.size() > 0);
	}

	@Test
	public void getSimilarMoviesTest() {
		Movie movie = new Movie();
		movie.id = "770687943";
		List<Movie> movies = rottenClient.getSimilarMovies(movie, null, 0);
		assertTrue(movies.size() > 0);
	}

	@Test
	public void getMovieCastTest() {
		List<Movie> movies = new ArrayList<Movie>();
		List<AbridgedCast> cast;
		rottenClient.searchMovie("Con Air", movies, 1);
		cast = rottenClient.getMovieCast(movies.get(0));
		assertTrue(cast.size() > 0);
	}

	@Test
	public void getMovieInfoTest() {
		List<Movie> movies = new ArrayList<Movie>();
		rottenClient.searchMovie("Con Air", movies, 1);
		Movie movie = rottenClient.getMovieAdditionalInfo(movies.get(0));
		assertEquals(movie.id, movies.get(0).id);
		assertEquals(movie.title, movies.get(0).title);
	}

	@Test
	public void movieParseTest() {
		JsonParser parser = new JsonParser();
		JsonObject jsonResponse = parser.parse(JsonResult.jsonMovieSearch).getAsJsonObject();
		JsonArray movies = jsonResponse.get("movies").getAsJsonArray();
		Movie movie = rottenClient.parseMovieJson(movies.get(0).getAsJsonObject());
		assertEquals(movie.title, "Jack and Jill");
		assertEquals(movie.runtime, "");
		assertEquals(movie.abridgedCast.get(0).name, "Al Pacino");
		assertEquals(movie.abridgedCast.get(1).name, "Adam Sandler");
		assertEquals(movie.abridgedCast.get(2).name, "Katie Holmes");
		assertEquals(movie.rating.criticsScore, -1);
		assertEquals(movie.rating.audienceScore, 90);
		assertEquals(movie.releaseDate.theater, "2011-11-11");
	}
}
