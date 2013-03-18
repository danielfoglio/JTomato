package it.jtomato.test.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import it.jtomato.net.NetHttpClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is a testing class for the default HTTP client
 * 
 * @author <a href="mailto:tambug@gmail.com">Giordano Tamburrelli</a>
 * 
 * @version 1.0
 **/
public class NetHttpClientTest {

	private NetHttpClient client;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		client = new NetHttpClient();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuildSimpleURI() {
		try {
			URI uri = client.buildURI(null, "www.google.it", null, null);
			assertEquals(uri.toString(), "http://www.google.it");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void testBuildURIWithParameters() {
		try {
			HashMap<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("apikey", "abc");
			paramsMap.put("q", "scuola di polizia");
			URI uri = client.buildURI(null, "www.google.it/", "path", paramsMap);
			assertEquals(uri.toString(), "http://www.google.it/path?q=scuola+di+polizia&apikey=abc");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testBuildURIwithPath() {
		try {
			URI uri = client.buildURI(null, "www.google.it/", "path", null);
			assertEquals(uri.toString(), "http://www.google.it/path");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			fail();
		}
	}
}
