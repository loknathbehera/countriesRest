package countriesRest.api.beans;

import java.util.ArrayList;
import java.util.List;

public class Countries {
	List<Country> countries = new ArrayList<Country>();

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	
}
