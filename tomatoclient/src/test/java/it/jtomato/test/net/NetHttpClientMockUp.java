package it.jtomato.test.net;

import it.jtomato.net.NetHttpClientInterface;

import java.util.HashMap;

/**
 * HTTP Client mockup used for testing purposes
 * 
 * @author <a href="mailto:tambug@gmail.com">Giordano Tamburrelli</a>
 * 
 * @version 1.0
 **/
public class NetHttpClientMockUp implements NetHttpClientInterface {

	private String response = "{}";

	public void setResponse(String response) {
		this.response = response;
	}

	public String get(String url) {
		return response;
	}

	public String buildUrl(String path, HashMap<String, String> params){
		return "";
	}
}
