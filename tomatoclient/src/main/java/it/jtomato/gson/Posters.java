package it.jtomato.gson;

import com.google.gson.annotations.SerializedName;

public class Posters {
	
	@SerializedName("thumbnail")
	public String thumbnail;

	@SerializedName("profile")
	public String profile;

	@SerializedName("detailed")
	public String detailed;

	@SerializedName("original")
	public String original;


}
