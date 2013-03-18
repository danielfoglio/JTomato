package it.jtomato.net;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public interface NetHttpClientInterface {

	public URI buildURI(String scheme, String host, String path, HashMap<String, String> params) throws URISyntaxException;

	public String get(URI uri);
}
