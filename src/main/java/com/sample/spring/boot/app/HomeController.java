package com.sample.spring.boot.app;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.spring.boot.app.PersonDetails;
import com.sample.spring.boot.app.Name;

@RestController
public class HomeController {

	@Autowired
	private PersonDetails personDetails;
	
	@Autowired
	private JSONParserService parserService;
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/wish")
	public @ResponseBody String sayHello() {
		// To know authentication and authorization details SecurityContextHolder will be used
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		if (!authentication.getAuthorities().isEmpty()) {
			System.out.println("authorities collection is not empty");
			for (GrantedAuthority auth: authentication.getAuthorities()) {
				// auth.getAuthority() method specifies role
				System.out.println(auth.getAuthority());
			}
		}
		return "Hello" + " " + name;
	}
	
	@GetMapping(value="/wish/{name}") 
	@ResponseBody
	public String sayHello(@PathVariable String name) {
		return "hello" + " " + name;
	}
	
	@PostMapping("/wish") 
	@ResponseBody
	public String wish(@RequestBody() Name name) {
		return "hello" + " " + name.getName();
	}
	
	@GetMapping(value="/get-person-details")
	public PersonDetails retrieve() {
		
		// print PersonDetails object which is set through properties file
		System.out.println(personDetails.toString());
		
		// can't serialize spring managed bean through Gson/Jackson. It gives UnsupportedOperationException
		// below line gives above mentioned exception since personDetails is managed by spring which is injected
		// into this class. So we can't serialize/ convert it to JSON through Gson. So commenting below line
		//String info = new Gson().toJson(personDetails);
		
		PersonDetails person = new PersonDetails();
		person.setFirstName("sri");
		person.setLastName("jayanthi");
		person.setNativePlace("bcm");
		
		/*
		 * **************************************************************
		 * Using Google Gson to parse Java objects
		 * 
		 * **************************************************************
		 */
		parseUsingGson(person);
		
		/*
		 * **************************************************************
		 * 
		 * Using Jackson API to parse Java objects. Jackson is leading JSON parser
		 * 
		 * **************************************************************
		 */
		parseUsingJackson(person);
		
		return person;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/get-employee-details-xml", produces = MediaType.APPLICATION_XML_VALUE)
	public Employee getEmployeeDetailsInXML() {
		Employee emp = new Employee();
		emp.setId(111);
		emp.setName("Tej");
		emp.setAddress("back gate");
		return emp;
	}

	/**
	 * This method explains Java to Json and Json to Java conversion using Jackson
	 * 
	 * @param person
	 */
	private void parseUsingJackson(PersonDetails person) {
		parserService.convertJavaToJsonUsingJackson(person);

		parserService.convertJavaToJsonStringUsingJackson(person);

		Map<Integer, String> empMap = new HashMap<>();
		empMap.put(1, "Santosh");
		empMap.put(2, "Srilalitha");

		parserService.convertMapToJsonStringUsingJackson(empMap);

		parserService.convertJsonToJavaUsingJackson();
	}

	/**
	 * This method explains Java to JSON and JSON to Java conversion using Google Gson.
	 * 
	 * @param person
	 */
	private void parseUsingGson(PersonDetails person) {
		// convert java pojo to JSON using Gson
		parserService.convertJavaToJson(person);

		// convert java object to JSON file
		parserService.convertJavaToJsonFile(person);

		// convert json to java pojo using Gson
		parserService.convertJsonToJava();

		// convert json to java pojo. Json file should be in classpath
		parserService.convertJsonFileToJava();
	}
	
}
