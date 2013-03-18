package it.jtomato.net;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class NetHttpClient implements NetHttpClientInterface{

	private final String scheme = "http";
	private final String agent = "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2";

	public String get(URI uri) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpProtocolParams.setUserAgent(httpclient.getParams(), agent);

		HttpGet httpget = new HttpGet(uri);
		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					String responseContent = EntityUtils.toString(entity);
					return responseContent;
				} finally {
					instream.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public URI buildURI(String scheme, String host, String path, HashMap<String, String> params) throws URISyntaxException {
		URIBuilder builder = new URIBuilder();
		if (scheme == null) {
			builder.setScheme(this.scheme);
		} else {
			builder.setScheme(scheme);
		}
		builder.setHost(host);
		if (path != null) {
			builder.setPath(path);
		}
		if (params != null) {
			for (String param : params.keySet()) {
				builder.setParameter(param, params.get(param));
			}
		}
		return builder.build();
	}

}
