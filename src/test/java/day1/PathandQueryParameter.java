package day1;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import groovy.util.logging.Log;
import io.restassured.response.Response;


public class PathandQueryParameter {
//	/*
//	 * Path and Query Parameter
//	 * https://reqres.in/api/users?page=2
//	 * Domain : https://reqres.in
//	 //*/users and /api:Path
//	 //after ? page=2 : Query parameter
//	 https://reqres.in/api/users?page=2&id=5
//	*/
	//@Test
	void testQueryAndPathParams()
	{
		given()
		.pathParams("mypath", "users")//path parameters
		.queryParam("page", 1) //query parameter
		//.queryParam("id", 6) //query parameter
		
		.when()
		.get("https://reqres.in/api/{mypath}")
		
		.then()
		.statusCode(200)
		//.body("first_name",equalTo("Charles"))
		.log().all();
		
	}
	//@Test(priority = 1)
	void testCookies()
	{
		given()
		
		.when()
		.get("https://www.google.com")
		
		
		.then()
		.cookie("AEC","AVcja2caG49ivCSpCR2MgHXJdskhJyRgdcbl9iyqn0O3wruV0QHejvgF5w")
		.log().all();
	}
	//@Test(priority = 2)
	void getCookiesInfo()
	{
		Response res=given()
		
		.when()
		.get("https://www.google.com");
		
		//get single cookie info AEC
		String cookie_value=res.getCookie("AEC");
		System.out.println(cookie_value);
		
		//get all cookies info
		Map <String,String>values= res.getCookies();
		//System.out.println(values.keySet());
		for(String k:values.keySet())
		{
			String cookie_value1=res.getCookie(k);
			System.out.println(k+"      "+values);
		}
		
		
		
	}
	//@Test
	void getHeaders()
	{
		given()
		
		.when()
		.get("https://www.google.com")
		
		.then()
		.header("Content-Type", "text/html; charset=ISO-8859-1")
		.header("Content-Encoding","gzip")
		.header("Server", "gws")
		.log().all();
	}
	
	//@Test
	void getHeadersInfo()
	{
		Response res=
		given()
		
		.when()
		.get("https://www.google.com");
		
		//get single header info
		System.out.println(res.getHeader("Content-Type"));
		
		//get multiple headers
		System.out.println(res.getHeaders());
	}
	
	@Test
		void testLogging()
		{
			
			given()
			
			.when()
			.get("http://localhost:3000/students")
			
			.then()
			.log().headers();
			
		}
}
