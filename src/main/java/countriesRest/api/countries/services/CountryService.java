package countriesRest.api.countries.services;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import countriesRest.api.beans.Countries;
import countriesRest.api.beans.Country;

@Component
public class CountryService {
	private static final Logger LOG = Logger.getLogger(CountryService.class);

	private static List<Country> countries;

	private CountryService() {
		initialize();
	}

	private void initialize() {
		LOG.debug("Loading JSON Database v1");
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("countries.json");
		Gson gson = new Gson();
		JsonReader reader;
		try {
			reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
			countries = new ArrayList<Country>();

			Countries cc = gson.fromJson(reader, Countries.class);
			countries = cc.getCountries();
			reader.close();
		} catch (Exception e) {
			LOG.error("Could not load JSON Database v1 ");
		}

	}

	public List<Country> getAll() {
		LOG.debug("Countries size " + countries.size());
		return countries;
	}

}
