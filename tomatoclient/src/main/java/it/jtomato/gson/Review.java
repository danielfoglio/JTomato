package it.jtomato.gson;

import com.google.gson.annotations.SerializedName;

public class Review {

	@SerializedName("critic")
	public String critic;

	@SerializedName("date")
	public String date;

	@SerializedName("freshness")
	public String freshness;

	@SerializedName("publication")
	public String publication;

	@SerializedName("quote")
	public String quote;
}
