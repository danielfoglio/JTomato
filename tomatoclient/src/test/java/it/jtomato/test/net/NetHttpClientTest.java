package it.jtomato.test.net;

import static org.junit.Assert.assertEquals;
import it.jtomato.net.NetHttpClient;

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
		String url = client.buildUrl("www.google.it", null);
		assertEquals(url.toString(), "www.google.it");
	}

	@Test
	public void testBuildURIWithParameters() {
		HashMap<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("apikey", "abc");
		paramsMap.put("q", "scuola di polizia");
		String url = client.buildUrl("www.google.it", paramsMap);
		assertEquals(url, "www.google.it?q=scuola+di+polizia&apikey=abc");
	}
}
