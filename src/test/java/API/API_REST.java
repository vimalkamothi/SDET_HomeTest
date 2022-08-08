package API;

import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class API_REST {


@Test(priority=1)
void findPetByID_PositiveTest()
{

RestAssured.baseURI="https://petstore.swagger.io/#/pet/1";

RequestSpecification httpRequest = RestAssured.given();

Response response = httpRequest.request(Method.GET);

String responseBody = response.getBody().asString();

System.out.println("Response Body is: "+responseBody);

int statusCode = response.getStatusCode();

System.out.println("Response Code is: "+statusCode);
Assert.assertEquals(statusCode, 200);

String statusLine = response.getStatusLine();
System.out.println("Status Line is: "+statusLine);


}

@Test(priority=2)
void findPetByID_NegativeTest()
{

RestAssured.baseURI="https://petstore.swagger.io/v2/pet/23424232324";

RequestSpecification httpRequest = RestAssured.given();

Response response = httpRequest.request(Method.GET);

String responseBody = response.getBody().asString();

System.out.println("Response Body is: "+responseBody);

int statusCode = response.getStatusCode();

System.out.println("Response Code is: "+statusCode);
Assert.assertEquals(statusCode, 404);

String statusLine = response.getStatusLine();
System.out.println("Status Line is: "+statusLine);

}

}
	

