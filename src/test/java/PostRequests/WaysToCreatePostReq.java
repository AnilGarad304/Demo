package PostRequests;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;


/*How many ways we can create request body
1) using HashMap
2) using Org.json
3) using POJO (Plain Old Java Object)
4) using external json file*/

//1) using HashMap
public class WaysToCreatePostReq {
	
	//@Test
	public void HashMapPost()
	{
		HashMap<String,String> data=new HashMap<String, String>();
		data.put("name", "Anil");
		data.put("job", "Engineer");
				
		 given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			//.jsonPath().getInt("id")
		.then()
			.statusCode(201)
			.body("name", equalTo("Anil"))
			.body("job",equalTo("Engineer"))
			.headers("Content-Type","application/json; charset=utf-8")
			.log().all();	
		
	}
	
//2-using Org.json
	//@Test
	public void JSONLibrary()
	{
		
		
		JSONObject data =new JSONObject();
		data.put("name", "Anil");
		data.put("job", "Engineer");
				
		 given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://reqres.in/api/users")
			//.jsonPath().getInt("id")
		.then()
			.statusCode(201)
			.body("name", equalTo("Anil"))
			.body("job",equalTo("Engineer"))
			.headers("Content-Type","application/json; charset=utf-8")
			.log().all();	
		
	}
	
	//3-using POJO Class
		//@Test
		public void POJOClass()
		{
			
			
			Post_POJO_Class data =new Post_POJO_Class();
			data.setName("Anil");
			data.setJob("Engineer");
			
		
			 given()
				.contentType("application/json")
				.body(data)
			.when()
				.post("https://reqres.in/api/users")
				//.jsonPath().getInt("id")
			.then()
				.statusCode(201)
				.body("name", equalTo("Anil"))
				.body("job",equalTo("Engineer"))
				.headers("Content-Type","application/json; charset=utf-8")
				.log().all();	
			
		}

//4-using External JSON file.
				@Test
				public void ExternalJSONFile() throws FileNotFoundException
				{
					
					File F =new File(".//body.json");
					
					FileReader FR =new FileReader(F);
					
					JSONTokener JT =new JSONTokener(FR);
					
					JSONObject data =new JSONObject(JT);
					
					
					 given()
						.contentType("application/json")
						.body(data.toString())
					.when()
						.post("https://reqres.in/api/users")
						//.jsonPath().getInt("id")
					.then()
						.statusCode(201)
						.body("name", equalTo("Anil"))
						.body("job",equalTo("Engineer"))
						.headers("Content-Type","application/json; charset=utf-8")
						.log().all();	
					
				}

}
