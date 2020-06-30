/**
 * 
 */
package com.sample.spring.boot.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * @author Srilalitha
 *
 */
public class JSONParserService {

	/**
	 * Converts JSON file to Java pojo using Jackson
	 */
	public void convertJsonToJavaUsingJackson() {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			PersonDetails person = mapper.readValue(new File("C:\\Srilalitha\\jackson-sample.json"), PersonDetails.class);
			System.out.println("===========converted json to java pojo using jackson===============");
			System.out.println(person.toString());
			// read file from classpath
			File file = ResourceUtils.getFile("classpath:json-files/sample.json");
			System.out.println("========converting json file in classpath to java pojo========");
			person = mapper.readValue(file, PersonDetails.class);
			System.out.println(person.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Converts Map to JSON string using Jackson
	 * 
	 * @param empMap
	 */
	public void convertMapToJsonStringUsingJackson(Map<Integer, String> empMap) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonResponse = mapper.writeValueAsString(empMap);
			System.out.println("========map converted to json======="+jsonResponse);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Converts Java pojo to Json string using Jackson
	 * 
	 * @param person
	 */
	public void convertJavaToJsonStringUsingJackson(PersonDetails person) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonResponse = mapper.writeValueAsString(person);
			System.out.println("=============json output in compact mode================");
			System.out.println("========using jackson to convert java pojo=========="+jsonResponse);
			System.out.println("==============json output in pretty print mode================");
			jsonResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
			System.out.println(jsonResponse);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Converts Java object to JSON using Jackson
	 * 
	 * @param person
	 */
	public void convertJavaToJsonUsingJackson(PersonDetails person) {
		ObjectMapper mapper = new ObjectMapper();	
		try {
			mapper.writeValue(new File("C:\\Srilalitha\\jackson-sample.json"), person);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Converts JSON file to Java object. JSON file is available in classpath.
	 * src/main/resources/json-files/sample.json
	 */
	public void convertJsonFileToJava() {
		Gson gson = new Gson();
		// read json from file which is in classpath and convert it to Java object
		File file = null;
		try {
			file = ResourceUtils.getFile("classpath:json-files/sample.json");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try (FileReader reader = new FileReader(file)) {
			PersonDetails personInformation = gson.fromJson(reader, PersonDetails.class);
			System.out.println("==========read from classpath=============" + personInformation.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Converts JSON file to Java object. JSON file is in System.
	 */
	public void convertJsonToJava() {
		Gson gson = new Gson();
		// read json from file
		try (FileReader reader = new FileReader("C:\\Srilalitha\\trial.json")) {
			PersonDetails personInformation = gson.fromJson(reader, PersonDetails.class);
			System.out.println(personInformation.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Converts Java object into Json file.
	 * 
	 * @param person
	 */
	public void convertJavaToJsonFile(PersonDetails person) {
		Gson gson = new Gson();
		try (FileWriter writer = new FileWriter("C:\\Srilalitha\\sample.json")) {
			System.out.println("======check person object =========="+person.toString());
            gson.toJson(person, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	/**
	 * Converts Java object to JSON using Gson
	 * 
	 * @param person
	 */
	public void convertJavaToJson(PersonDetails person) {
		Gson gson = new Gson();
		String personInfo = gson.toJson(person);
		System.out.println(personInfo);	
	}
	
}
