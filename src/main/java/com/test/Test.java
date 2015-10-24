package com.test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import countriesRest.api.beans.Countries;
import countriesRest.api.beans.Country;

public class Test {
	
	public Test() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static void main(String[] args) {
	 new Test().execute1();
	}
	
	private void execute1() {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("countries.json");
		Gson gson = new Gson();
		JsonReader reader;
		try {
			reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
			List<Country>countries = new ArrayList<Country>();

			Countries cc = gson.fromJson(reader, Countries.class);
			countries = cc.getCountries();
			reader.close();
		} catch (Exception e) {
			
		}
			
	}

}
