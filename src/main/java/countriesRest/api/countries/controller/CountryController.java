package countriesRest.api.countries.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import countriesRest.api.beans.Country;
import countriesRest.api.countries.services.CountryService;
import countriesRest.api.domains.ResponseEntity;
import countriesRest.api.exception.InternalErrorException;
import countriesRest.api.exception.NoSuchCountryFoundException;

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

	@RequestMapping(value = "alpha{codes}")
	public @ResponseBody Object getByAlpha(@RequestParam(value = "codes") String alpha) {

		LOG.info("Getting by alpha " + alpha);
		try {
			List<Country> countries = countryService.getByAlpha(alpha);
			if (countries != null) {
				return countries;
			}
			return new NoSuchCountryFoundException();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return new InternalErrorException();
		}
	}

	@ExceptionHandler(NoSuchCountryFoundException.class)
	public ResponseEntity NotFound(HttpServletRequest request, Exception exception) {
		ResponseEntity error = new ResponseEntity(HttpStatus.NOT_FOUND.value(), exception.getMessage());
		return error;
	}

	@ExceptionHandler(InternalErrorException.class)
	public ResponseEntity InternalError(HttpServletRequest request, Exception exception) {
		ResponseEntity error = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
		return error;
	}

}
