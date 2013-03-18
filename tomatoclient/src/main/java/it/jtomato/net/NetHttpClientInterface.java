package it.jtomato.net;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * Interface for the JTomato HTTPClient
 * 
 * @author <a href="mailto:tambug@gmail.com">Giordano Tamburrelli</a>
 * 
 * @version 1.0
 **/
public interface NetHttpClientInterface {

	public URI buildURI(String scheme, String host, String path, HashMap<String, String> params) throws URISyntaxException;

	public String get(URI uri);
}
