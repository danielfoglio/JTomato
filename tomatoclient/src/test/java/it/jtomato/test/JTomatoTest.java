package it.jtomato.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import it.jtomato.JTomato;
import it.jtomato.gson.AbridgedCast;
import it.jtomato.gson.Movie;
import it.jtomato.test.net.NetHttpClientMockUp;

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
 * This is a testing class for the JTomato client class. This class do not
 * require an Internet connection since it uses a mockup of RottenTomatoes API
 * 
 * @author <a href="mailto:tambug@gmail.com">Giordano Tamburrelli</a>
 * 
 * @version 1.0
 * 
 * @see JTomato
 **/
public class JTomatoTest {

	private JTomato rottenClient;
	private NetHttpClientMockUp httpClient = new NetHttpClientMockUp();
	private final String propertyFile = "res/tomatoes.properties";

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(propertyFile));
		String rottenKey = prop.getProperty("RottenTomatoesApiKey");
		rottenClient = new JTomato(rottenKey, httpClient);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void simpleSearchTest() {
		httpClient.setResponse(JsonResult.jsonMovieSearch);
		List<Movie> movies = new ArrayList<Movie>();
		int total = rottenClient.searchMovie("lorem ipsum", movies, 1);
		assertEquals(591, total);
		assertEquals(movies.size(), 1);
		assertEquals(movies.get(0).title, "Jack and Jill");
		assertEquals(movies.get(0).runtime, "");
		assertEquals(movies.get(0).abridgedCast.get(0).name, "Al Pacino");
		assertEquals(movies.get(0).abridgedCast.get(1).name, "Adam Sandler");
		assertEquals(movies.get(0).abridgedCast.get(2).name, "Katie Holmes");
		assertEquals(movies.get(0).rating.criticsScore, -1);
		assertEquals(movies.get(0).rating.audienceScore, 90);
		assertEquals(movies.get(0).releaseDate.theater, "2011-11-11");
	}

	@Test
	public void emptySearchTest() {
		httpClient.setResponse(JsonResult.jsonEmptyMovieSearch);
		List<Movie> movies = new ArrayList<Movie>();
		rottenClient.searchMovie("lorem ipsum", movies, 1);
		assertEquals(movies.size(), 0);
	}

	@Test
	public void boxOfficeTest() {
		httpClient.setResponse(JsonResult.jsonBoxOfficeMovies);
		List<Movie> movies = rottenClient.getBoxOfficeMovies("us", 50);
		assertEquals(movies.size(), 1);
		assertEquals(movies.get(0).title, "Harry Potter and the Deathly Hallows - Part 2");
		assertEquals(movies.get(0).year, "2011");
		assertEquals(movies.get(0).id, "770687943");
		assertEquals(movies.get(0).mpaaRating, "PG-13");
		assertEquals(movies.get(0).runtime, "130");
		assertEquals(movies.get(0).rating.criticsScore, 97);
		assertEquals(movies.get(0).rating.audienceScore, 93);
		assertEquals(movies.get(0).rating.audienceRating, "Upright");
		assertEquals(movies.get(0).rating.criticsRating, "Certified Fresh");
		assertEquals(movies.get(0).releaseDate.theater, "2011-07-15");
		assertEquals(movies.get(0).abridgedCast.get(0).name, "Daniel Radcliffe");
		assertEquals(movies.get(0).abridgedCast.get(0).characters.get(0), "Harry Potter");
		assertEquals(movies.get(0).abridgedCast.get(1).name, "Rupert Grint");
		assertEquals(movies.get(0).abridgedCast.get(1).characters.get(0), "Ron Weasley");
		assertEquals(movies.get(0).abridgedCast.get(1).characters.get(1), "Ron Wesley");
		assertEquals(movies.get(0).abridgedCast.get(2).name, "Emma Watson");
		assertEquals(movies.get(0).abridgedCast.get(2).characters.get(0), "Hermione Granger");
		assertEquals(movies.get(0).abridgedCast.get(3).name, "Helena Bonham Carter");
		assertEquals(movies.get(0).abridgedCast.get(3).characters.get(0), "Bellatrix Lestrange");
		assertEquals(movies.get(0).abridgedCast.get(4).name, "Ralph Fiennes");
		assertEquals(movies.get(0).abridgedCast.get(4).characters.get(0), "Lord Voldemort");
	}

	@Test
	public void getInThreathersMoviesTest() {
		httpClient.setResponse(JsonResult.jsonTheathersMovies);
		List<Movie> movies = new ArrayList<Movie>();
		int total = rottenClient.getInThreatersMovies(movies, "us", 1);
		assertEquals(115, total);
		assertEquals(movies.size(), 1);
		assertEquals(movies.get(0).title, "Harry Potter and the Deathly Hallows - Part 2");
		assertEquals(movies.get(0).year, "2011");
		assertEquals(movies.get(0).id, "770687943");
		assertEquals(movies.get(0).mpaaRating, "PG-13");
		assertEquals(movies.get(0).runtime, "130");
		assertEquals(movies.get(0).rating.criticsScore, 97);
		assertEquals(movies.get(0).rating.audienceScore, 93);
		assertEquals(movies.get(0).rating.audienceRating, "Upright");
		assertEquals(movies.get(0).rating.criticsRating, "Certified Fresh");
		assertEquals(movies.get(0).releaseDate.theater, "2011-07-15");
		assertEquals(movies.get(0).abridgedCast.get(0).name, "Daniel Radcliffe");
		assertEquals(movies.get(0).abridgedCast.get(0).characters.get(0), "Harry Potter");
		assertEquals(movies.get(0).abridgedCast.get(1).name, "Rupert Grint");
		assertEquals(movies.get(0).abridgedCast.get(1).characters.get(0), "Ron Weasley");
		assertEquals(movies.get(0).abridgedCast.get(1).characters.get(1), "Ron Wesley");
		assertEquals(movies.get(0).abridgedCast.get(2).name, "Emma Watson");
		assertEquals(movies.get(0).abridgedCast.get(2).characters.get(0), "Hermione Granger");
		assertEquals(movies.get(0).abridgedCast.get(3).name, "Helena Bonham Carter");
		assertEquals(movies.get(0).abridgedCast.get(3).characters.get(0), "Bellatrix Lestrange");
		assertEquals(movies.get(0).abridgedCast.get(4).name, "Ralph Fiennes");
		assertEquals(movies.get(0).abridgedCast.get(4).characters.get(0), "Lord Voldemort");
	}

	@Test
	public void getOpeningMoviesTest() {
		httpClient.setResponse(JsonResult.jsonOpeningMovies);
		List<Movie> movies = rottenClient.getOpeningMovies(null, 0);
		assertEquals(movies.size(), 1);
		assertEquals(movies.get(0).title, "Captain America: The First Avenger");
		assertEquals(movies.get(0).year, "2011");
		assertEquals(movies.get(0).id, "770739679");
		assertEquals(movies.get(0).mpaaRating, "PG-13");
		assertEquals(movies.get(0).runtime, "121");
		assertEquals(movies.get(0).rating.criticsScore, 71);
		assertEquals(movies.get(0).rating.audienceScore, 96);
		assertEquals(movies.get(0).rating.criticsRating, "Fresh");
		assertEquals(movies.get(0).releaseDate.theater, "2011-07-22");
		assertEquals(movies.get(0).abridgedCast.get(0).name, "Chris Evans");
		assertEquals(movies.get(0).abridgedCast.get(0).characters.get(0), "Captain America/Steve Rogers");
		assertEquals(movies.get(0).abridgedCast.get(0).characters.get(1), "Steve Rogers / Captain America");
		assertEquals(movies.get(0).abridgedCast.get(0).characters.get(2), "Steve Rogers/Captain America");
		assertEquals(movies.get(0).abridgedCast.get(1).name, "Hayley Atwell");
		assertEquals(movies.get(0).abridgedCast.get(1).characters.get(0), "Peggy Carter");
		assertEquals(movies.get(0).abridgedCast.get(2).name, "Sebastian Stan");
		assertEquals(movies.get(0).abridgedCast.get(2).characters.get(0), "Bucky Barnes");
		assertEquals(movies.get(0).abridgedCast.get(2).characters.get(1), "James Buchanan \"Bucky\" Barnes");
		assertEquals(movies.get(0).abridgedCast.get(3).name, "Tommy Lee Jones");
		assertEquals(movies.get(0).abridgedCast.get(3).characters.get(0), "Colonel Chester Phillips");
		assertEquals(movies.get(0).abridgedCast.get(4).name, "Hugo Weaving");
		assertEquals(movies.get(0).abridgedCast.get(4).characters.get(0), "Johann Schmidt/Red Skull");
		assertEquals(movies.get(0).abridgedCast.get(4).characters.get(1), "Johann Schmidt/The Red Skull");
		assertEquals(movies.get(0).abridgedCast.get(4).characters.get(2), "Red Skull");
	}

	@Test
	public void getUpcomingMoviesTest() {
		httpClient.setResponse(JsonResult.jsonUpcomingMovies);
		List<Movie> movies = new ArrayList<Movie>();
		int total = rottenClient.getUpcomingMovies(movies, "us", 1);
		assertEquals(29, total);
		assertEquals(movies.size(), 1);
		assertEquals(movies.get(0).title, "Cowboys and Aliens");
		assertEquals(movies.get(0).year, "2011");
		assertEquals(movies.get(0).id, "770863875");
		assertEquals(movies.get(0).mpaaRating, "PG-13");
		assertEquals(movies.get(0).runtime, "");
		assertEquals(movies.get(0).rating.criticsScore, -1);
		assertEquals(movies.get(0).rating.audienceScore, 94);
		assertEquals(movies.get(0).releaseDate.theater, "2011-07-29");
		assertEquals(movies.get(0).abridgedCast.get(0).name, "Daniel Craig");
		assertEquals(movies.get(0).abridgedCast.get(0).characters.get(0), "Zeke Jackson");
	}

	@Test
	public void getTopRentalsMoviesTest() {
		httpClient.setResponse(JsonResult.jsonTopRentalsMovies);
		List<Movie> movies = rottenClient.getTopRentalsMovies(null, 0);
		assertEquals(movies.size(), 1);
		assertEquals(movies.get(0).title, "True Grit");
		assertEquals(movies.get(0).year, "2010");
		assertEquals(movies.get(0).id, "770860165");
		assertEquals(movies.get(0).mpaaRating, "PG-13");
		assertEquals(movies.get(0).runtime, "110");
		assertEquals(movies.get(0).rating.criticsScore, 96);
		assertEquals(movies.get(0).rating.audienceScore, 86);
		assertEquals(movies.get(0).rating.criticsRating, "Certified Fresh");
		assertEquals(movies.get(0).rating.audienceRating, "Upright");
		assertEquals(movies.get(0).releaseDate.theater, "2010-12-22");
		assertEquals(movies.get(0).releaseDate.dvd, "2011-06-07");
	}

	@Test
	public void getSimilarMoviesTest() {
		httpClient.setResponse(JsonResult.jsonMovieSimilar);
		Movie movie = new Movie();
		movie.id = "770687943";
		List<Movie> movies = rottenClient.getSimilarMovies(movie, null, 0);
		assertEquals(movies.get(0).title, "Up");
		assertEquals(movies.get(0).year, "2009");
		assertEquals(movies.get(0).id, "770671912");
		assertEquals(movies.get(0).mpaaRating, "PG");
		assertEquals(movies.get(0).runtime, "89");
		assertEquals(movies.get(0).rating.criticsScore, 98);
		assertEquals(movies.get(0).rating.audienceScore, 86);
		assertEquals(movies.get(0).rating.criticsRating, "Certified Fresh");
		assertEquals(movies.get(0).rating.audienceRating, "Upright");
		assertEquals(movies.get(0).releaseDate.theater, "2009-05-29");
		assertEquals(movies.get(0).releaseDate.dvd, "2009-11-10");
	}

	@Test
	public void getCurrentReleaseDvdsTest() {
		fail();

	}

	@Test
	public void getMovieCastTest() {
		httpClient.setResponse(JsonResult.jsonMovieCast);
		List<AbridgedCast> cast;
		cast = rottenClient.getMovieCast("1");
		assertEquals(cast.size(), 35);
		assertEquals(cast.get(0).id, "162655641");
		assertEquals(cast.get(0).name, "Tom Hanks");
		assertEquals(cast.get(0).characters.get(0), "Woody");
	}

	@Test
	public void getMovieAdditionalInfoTest() {
		httpClient.setResponse(JsonResult.jsonMovieInfo);
		Movie movieInput = new Movie();
		movieInput.id = "770672122";
		Movie movie = rottenClient.getMovieAdditionalInfo(movieInput);
		assertEquals(movie.id, "770672122");
		assertEquals(movie.title, "Toy Story 3");
		assertEquals(movie.year, "2010");
		assertEquals(movie.genres.get(0), "Animation");
		assertEquals(movie.genres.get(1), "Kids & Family");
		assertEquals(movie.genres.get(2), "Science Fiction & Fantasy");
		assertEquals(movie.genres.get(3), "Comedy");
		assertEquals(movie.mpaaRating, "G");
		assertEquals(movie.runtime, "103");
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
