package countriesRest.api.countries.services;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import countriesRest.api.beans.Countries;
import countriesRest.api.beans.Country;
import countriesRest.api.beans.CountryNameCode;
import countriesRest.api.domains.ICountryRestSymbols;

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
	
	public List<CountryNameCode> getAllNameCode() {
		
		List<CountryNameCode> countryNameCodes=new ArrayList<CountryNameCode>();
		CountryNameCode countryNameCode;
		for (Country country : countries) {
		 countryNameCode=new CountryNameCode();
		 
	     countryNameCode.setName(country.getName());
	     countryNameCode.setCca2(country.getCca2());
		 countryNameCodes.add(countryNameCode);
		
		}
		return countryNameCodes;
	}

	

	public Country getByAlpha(String alpha) {
		int alphaLength = alpha.length();
		for (Country country : countries) {
			if (alphaLength == 2) {
				if (country.getCca2().toLowerCase().equals(alpha.toLowerCase())) {
					return country;
				}
			} else if (alphaLength == 3) {
				if (country.getCca3().toLowerCase().equals(alpha.toLowerCase())) {
					return country;
				}
			}
		}
		return null;
	}

	public List<Country> getByCodeList(String codelist) {
		List<Country> result = new ArrayList<Country>();
		if (codelist == null)
			return result;

		List<String> codes = Arrays.asList(codelist.split(ICountryRestSymbols.SEMICOLON));
		for (String code : codes) {
			Country country = this.getByAlpha(code);
			if (!result.contains(country) && country != null)
				result.add(country);
		}
		return result;
	}

	public List<Country> getByCurrency(String currency) {
		List<Country> result = new ArrayList<Country>();
		for (Country country : countries) {
			for (String curr : country.getCurrency()) {
				if (curr.toLowerCase().equals(currency.toLowerCase())) {
					result.add(country);
				}
			}
		}
		return result;
	}

	public List<Country> getByName(String name, boolean fullText) {
		if (fullText) {
			return fulltextSearch(name);
		} else {
			return substringSearch(name);
		}

	}

	private List<Country> substringSearch(String name) {
		// Using 2 different 'for' loops to give priority to 'name' matches over
		// alternative spellings
		List<Country> result = new ArrayList<Country>();
		for (Country country : countries) {
			if (normalize(country.getName().toLowerCase()).contains(normalize(name.toLowerCase()))) {
				result.add(country);
			}
		}
		for (Country country : countries) {
			for (String alternative : country.getAltSpellings()) {
				if (normalize(alternative.toLowerCase()).contains(normalize(name.toLowerCase()))
						&& !result.contains(country)) {
					result.add(country);
				}
			}
		}
		return result;
	}

	private List<Country> fulltextSearch(String name) {
		// Using 2 different 'for' loops to give priority to 'name' matches over
		// alternative spellings
		List<Country> result = new ArrayList<Country>();
		for (Country country : countries) {
			if (normalize(country.getName().toLowerCase()).equals(normalize(name.toLowerCase()))) {
				result.add(country);
			}
		}
		for (Country country : countries) {
			for (String alternative : country.getAltSpellings()) {
				if (normalize(alternative.toLowerCase()).equals(normalize(name.toLowerCase()))
						&& !result.contains(country)) {
					result.add(country);
				}
			}
		}
		return result;
	}

	public List<Country> getByCallingcode(String callingcode) {
		List<Country> result = new ArrayList<Country>();
		for (Country country : countries) {
			for (String callingCode : country.getCallingCodes()) {
				if (callingCode.equals(callingcode))
					result.add(country);
			}
		}
		return result;
	}

	public List<Country> getByCapital(String capital) {
		List<Country> result = new ArrayList<Country>();
		for (Country country : countries) {
			if (normalize(country.getCapital().toLowerCase()).contains(normalize(capital.toLowerCase()))) {
				result.add(country);
			}
		}
		return result;
	}

	public List<Country> getByRegion(String region) {
		List<Country> result = new ArrayList<Country>();
		for (Country country : countries) {
			if (country.getRegion().toLowerCase().equals(region.toLowerCase())) {
				result.add(country);
			}
		}
		return result;
	}

	public List<Country> getBySubregion(String subregion) {
		List<Country> result = new ArrayList<Country>();
		for (Country country : countries) {
			if (country.getSubregion().toLowerCase().equals(subregion.toLowerCase())) {
				result.add(country);
			}
		}
		return result;
	}

	public List<Country> getByLanguage(String language) {
		List<Country> result = new ArrayList<Country>();
		for (Country country : countries) {
			for (String lang : country.getLanguageCodes()) {
				if (lang.toLowerCase().equals(language.toLowerCase())) {
					result.add(country);
				}
			}
		}
		return result;
	}

	private String normalize(String string) {
		return Normalizer.normalize(string, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
}
