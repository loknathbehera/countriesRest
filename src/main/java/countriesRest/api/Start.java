package countriesRest.api;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJSONDoc
public class Start {

	final static Logger log = LoggerFactory.getLogger(Start.class);

	public static void main(String[] args) {
		log.info("Starting application..");
		SpringApplication.run(Start.class, args);
	}
}