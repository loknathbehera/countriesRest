package countriesRest.api.countries.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
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

@Api(name = "Country services", description = "Methods for managing cities", group = "Geography", visibility = ApiVisibility.PUBLIC, stage = ApiStage.RC)
@ApiVersion(since = "1.0", until = "2.12")
@ApiAuthNone
@RestController
@RequestMapping(value = "/rest", produces = { "application/json" })
public class CountryController {
	private static final Logger LOG = Logger.getLogger(CountryController.class);

	@Autowired
	CountryService countryService;

	@ApiMethod
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody Object getCountries() {
		LOG.info("Getting all Countries");
		return countryService.getAll();
	}

	@ApiMethod
	@RequestMapping(value = "/alpha{codes}", method = RequestMethod.GET)
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

	@ApiMethod
	@RequestMapping("/currency/{currency}")
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
	public Object getByName(@PathVariable("name") String name, @RequestParam("fulltext") boolean fulltext) {
		LOG.info("Getting by name " + name);
		try {
			List<Country> countries = countryService.getByName(name, fulltext);
			if (!countries.isEmpty()) {
				return countries;
			}
			return getResponse(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return getResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("/callingcode/{callingcode}")
	public Object getByCallingCode(@PathVariable("callingcode") String callingcode) {
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

	@RequestMapping("/capital/{capital}")
	public Object getByCapital(@PathVariable("capital") String capital) {
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

	@RequestMapping("/region/{region}")
	public Object getByRegion(@PathVariable("region") String region) {
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

	@RequestMapping("/subregion/{subregion}")
	public Object getBySubregion(@PathVariable("subregion") String subregion) {
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

	@RequestMapping("/lang/{lang}")
	public Object getByLanguage(@PathVariable("lang") String language) {
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
