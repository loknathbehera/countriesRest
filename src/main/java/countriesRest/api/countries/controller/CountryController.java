package countriesRest.api.countries.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import countriesRest.api.countries.services.CountryService;

@Controller
@RequestMapping(value = "rest/v1", produces = { "application/json"})
public class CountryController {
	private static final Logger LOG = Logger.getLogger(CountryController.class);

	@RequestMapping(value="/all",method=RequestMethod.GET)
	  public @ResponseBody Object getCountries() {
		try {
			return CountryService.getInstance().getAll();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return getResponse(HttpStatus.INTERNAL_SERVER_ERROR); 
		}
    }



	private Object getResponse(HttpStatus internalServerError) {
	
		return null;
	}

	
    
	
	
}
