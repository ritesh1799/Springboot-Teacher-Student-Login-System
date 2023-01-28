package com.defecttracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class DefecttrackingApiApplication {
	private static final Logger log = LoggerFactory.getLogger((DefecttrackingApiApplication.class));

	public static void main(String[] args) {
		SpringApplication.run(DefecttrackingApiApplication.class, args);
		log.debug("Application has been Started");
	}

}
