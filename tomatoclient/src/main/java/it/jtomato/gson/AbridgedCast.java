package it.jtomato.gson;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class AbridgedCast {
	
	@SerializedName("id")
	public String id;
	
	@SerializedName("name")
	public String name;

	@SerializedName("characters")
	public List<String> characters;

}
