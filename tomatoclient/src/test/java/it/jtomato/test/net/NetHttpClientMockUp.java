package it.jtomato.test.net;

import it.jtomato.net.NetHttpClientInterface;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.http.client.utils.URIBuilder;

public class NetHttpClientMockUp implements NetHttpClientInterface {

	private String response = "{}";

	public void setResponse(String response) {
		this.response = response;
	}

	public String get(URI uri) {
		return response;
	}

	public URI buildURI(String scheme, String host, String path, HashMap<String, String> params) throws URISyntaxException {
		URIBuilder builder = new URIBuilder();
		return builder.build();
	}
}
