package day1;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;


/*ADD for checking
 * given()- content type,set cookies,add auth,add param,set header info etc
 * when()-request url keep inside get,put,post,delete, patch
 * then()-validate the response status code,extract response,header,cookies,header body
 * 
 * get request: https://reqres.in/api/users/2
 * post request :https://reqres.in/api/users/
 * body :{
    "name": "morpheus",
    "job": "leader"
}
	put request: https://reqres.in/api/users/2
	body:{
    "name": "morpheus",
    "job": "zion resident"
}
delete request: https://reqres.in/api/users/2
status code :204
*/
public class HTTPRequests {
	int id;
	@Test(priority = 1)
	void getUser()
	{
		//given()
		
		when()
		.get("https://reqres.in/api/users?page=2")//list user
		
		.then()
		.statusCode(200)
		.body("page", equalTo(2))

		.log().all();
	}
	
	@Test(priority = 2)
	void createUser()
	{
		//post user pass the body
//		HashMap <String,String>hm=new HashMap<>();
//		hm.put("name","Ashu");
//		hm.put("job", "QA");
//		given()
//		.contentType("applicatio/json")
//		.body(hm)
//		.when()
//		.post("https://reqres.in/api/users/")
//		.then()
//		.statusCode(201)
//		.log().all();
		 Map<String, String> user = new HashMap<>();
	        user.put("name", "Ashu");
	        user.put("job", "QA");

	       id= given()
	            .contentType("application/json")  // Set Content-Type
	            .body(user)  // Send as a Map (RestAssured will convert to JSON)
	        .when()
	            .post("https://reqres.in/api/users")
	        	.jsonPath().getInt("id");
	            
//	        .then()
//	            .statusCode(201)  // Check for successful creation
//	            .
//	        	.log().all();
	    }
	@Test(priority = 3,dependsOnMethods = "createUser")
	void updateUser()
	{
		Map<String, String> user = new HashMap<>();
        user.put("name", "Ira");
        user.put("job", "Selenium");

        given()
            .contentType("application/json")  // Set Content-Type
            .body(user)  // Send as a Map (RestAssured will convert to JSON)
        .when()
            .put("https://reqres.in/api/users/"+id)
        	//.jsonPath().getInt("id")
        .then()
         .statusCode(200)  // Check for successful creation
         .log().all();
		
	}
	
	@Test(priority = 4)
	void deleteUser()
	{
		when()
        .delete("https://reqres.in/api/users/"+id)
    	//.jsonPath().getInt("id")
    .then()
     .statusCode(204)
		.log().all();
	}
	}


