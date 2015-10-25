package countriesRest.api.countries.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import countriesRest.api.countries.services.CountryService;

@Controller
@RequestMapping(value = "rest/v1", produces = { "application/json" })
public class CountryController {
	@Autowired
	CountryService countryService;

	private static final Logger LOG = Logger.getLogger(CountryController.class);

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody Object getCountries() {
		LOG.info("Getting all Countries");
		return countryService.getAll();
	}

}
