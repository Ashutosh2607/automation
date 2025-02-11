package day1;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;

/*
POST Request
How many ways to creat the POST request PAYLOAD
1.HashMap
2.using org.json
3.using POJO class (Plain old Java Object)
4.using external JSON file
 */
public class MultipleWayPayload {
	//@Test
	void testPostUsingHashmap() {
	//Using HASHMAP
	Map  hm=new HashMap();
	hm.put("name", "ABC");
	hm.put("location", "Canada");
	hm.put("phone", "123456");
	String[] courseArr= {"c++","java"};
	hm.put("courses",courseArr);
	
	given()
	.contentType("application/json")
	.body(hm)
	.when()
	.post("http://localhost:3000/students")
	.then()
	.statusCode(201)
	.body("name",equalTo("ABC"))
	.body("location",equalTo( "Canada"))
	.body("phone", equalTo("123456"))
	.body("courses[0]",equalTo("c++"))
	.body("courses[1]",equalTo("java"))
	.header("Content-Type", "application/json")
	.log().all();

	
	}
	//@Test
	void testPostUsingorgJSON() {
		//Using ORG.JSON
		JSONObject data=new JSONObject();
		data.put("name","XYZ");
		data.put("location","India");
		data.put("phone","1234567");
		String[] courseArr= {"c++","java"};
		data.put("courses",courseArr);
		
		given()
		.contentType("application/json")
		.body(data.toString())
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name",equalTo("XYZ"))
		.body("location",equalTo( "India"))
		.body("phone", equalTo("1234567"))
		.body("courses[0]",equalTo("c++"))
		.body("courses[1]",equalTo("java"))
		.header("Content-Type", "application/json")
		.log().all();

		
		}
	//@Test
	void testPostUsingPOJOClass() {
		//Using POJO Class
		PojoClassforpost data=new PojoClassforpost();
		data.setName("Scoot");
		data.setLocation("Iraq");
		data.setPhone("987654");
		String coursesArr[]= {"Abc","Xyz"};
		data.setCourses(coursesArr);
		
		given()
		.contentType("application/json")
		.body(data)
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name",equalTo("Scoot"))
		.body("location",equalTo( "Iraq"))
		.body("phone", equalTo("987654"))
		.body("courses[0]",equalTo("Abc"))
		.body("courses[1]",equalTo("Xyz"))
		.header("Content-Type", "application/json")
		.log().all();

		
		}
		@Test
	void testPostUsingExternalJson() throws Exception {
		//Using External JSON
//		File f=new File("C.\Body.json");
//		FileReader fr=new FileReader(f);
//		JSONTokener jt=new JSONTokener(fr);
//		
//		JSONObject data=new JSONObject();
//		
//		given()
//		.contentType("application/json")
//		.body(data.toString())
//		.when()
//		.post("http://localhost:3000/students")
//		.then()
//		.statusCode(201)
//		.body("name",equalTo("JohnDoe"))
//		.body("location",equalTo( "USA"))
//		.body("phone", equalTo("9876543210"))
//		.body("courses[0]",equalTo("Java"))
//		.body("courses[1]",equalTo("Selenium"))
//		.header("Content-Type", "application/json")
//		.log().all();
			 File jsonData = new File(".\\Body.json");

		        given()
		            .contentType(ContentType.JSON)  // ✅ Set JSON content type
		            .body(jsonData)  // ✅ Read JSON file
		        .when()
		            .post("http://localhost:3000/students")  // ✅ Ensure JSON Server is running
		        .then()
		            .statusCode(201)  // ✅ Expect HTTP 201 Created
		            .body("name", equalTo("JohnDoe"))
		            .body("location", equalTo("USA"))
		            .body("phone", equalTo("9876543210"))
		            .body("courses[0]", equalTo("Java"))
		            .body("courses[1]", equalTo("Selenium"))
		            .header("Content-Type", equalTo("application/json"))
		            .log().all();  // ✅ Log response for debugging

		
		}



}
