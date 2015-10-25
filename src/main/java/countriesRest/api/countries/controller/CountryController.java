package countriesRest.api.countries.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import countriesRest.api.beans.Country;
import countriesRest.api.countries.services.CountryService;
import countriesRest.api.domains.ResponseEntity;

@RestController
@RequestMapping(value = "rest", produces = { "application/json" })
public class CountryController {
	private static final Logger LOG = Logger.getLogger(CountryController.class);

	@Autowired
	CountryService countryService;

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public @ResponseBody Object getCountries() {
		LOG.info("Getting all Countries");
		return countryService.getAll();
	}

	@RequestMapping(value = "alpha{codes}", method = RequestMethod.GET)
	public @ResponseBody Object getByAlpha(@RequestParam(value = "codes") String codelist) {

		LOG.info("Getting by alpha " + codelist);
		try {
			List<Country> countries = countryService.getByCodeList(codelist);
			if (!countries.isEmpty()) {
				return countries;
			}
			return getResponse(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return getResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("currency/{currency}")
	public Object getByCurrency(@PathVariable("currency") String currency) {
		LOG.info("Getting by currency " + currency);
		try {
			List<Country> countries = countryService.getByCurrency(currency);
			if (!countries.isEmpty()) {
				return countries;
			}
			return getResponse(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return getResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("name/{name}")
	public Object getByName(@PathParam("name") String name, @RequestParam("fullText") boolean fullText) {
		LOG.info("Getting by name " + name);
		try {
			List<Country> countries = countryService.getByName(name, fullText);
			if (!countries.isEmpty()) {
				return countries;
			}
			return getResponse(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return getResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("callingcode/{callingcode}")
	public Object getByCallingCode(@PathParam("callingcode") String callingcode) {
		LOG.info("Getting by calling code " + callingcode);
		try {
			List<Country> countries = countryService.getByCallingcode(callingcode);
			if (!countries.isEmpty()) {
				return countries;
			}
			return getResponse(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return getResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("capital/{capital}")
	public Object getByCapital(@PathParam("capital") String capital) {
		LOG.info("Getting by capital " + capital);
		try {
			List<Country> countries = countryService.getByCapital(capital);
			if (!countries.isEmpty()) {
				return countries;
			}
			return getResponse(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return getResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("region/{region}")
	public Object getByRegion(@PathParam("region") String region) {
		LOG.info("Getting by region " + region);
		try {
			List<Country> countries = countryService.getByRegion(region);
			if (!countries.isEmpty()) {
				return countries;
			}
			return getResponse(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return getResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("subregion/{subregion}")
	public Object getBySubregion(@PathParam("subregion") String subregion) {
		LOG.info("Getting by region " + subregion);
		try {
			List<Country> countries = countryService.getBySubregion(subregion);
			if (!countries.isEmpty()) {
				return countries;
			}
			return getResponse(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return getResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("lang/{lang}")
	public Object getByLanguage(@PathParam("lang") String language) {
		LOG.info("Getting by language " + language);
		try {
			List<Country> countries = countryService.getByLanguage(language);
			if (!countries.isEmpty()) {
				return countries;
			}
			return getResponse(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return getResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String getResponse(HttpStatus status) {
		Gson gson = new Gson();
		return gson.toJson(new ResponseEntity(status, status.toString()));
	}

}
