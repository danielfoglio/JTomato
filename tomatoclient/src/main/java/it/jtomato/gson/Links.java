package it.jtomato.gson;

import com.google.gson.annotations.SerializedName;

public class Links {

	@SerializedName("self")
	public String self;

	@SerializedName("alternate")
	public String alternate;

	@SerializedName("cast")
	public String cast;

	@SerializedName("clips")
	public String clips;

	@SerializedName("review")
	public String review;

	@SerializedName("similar")
	public String similar;

}
