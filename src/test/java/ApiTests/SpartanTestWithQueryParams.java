package ApiTests;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.testng.Assert.*;

public class SpartanTestWithQueryParams {

    @BeforeClass
    public static void  setUpClass(){
        RestAssured.baseURI="http://54.173.198.79:8000";
    }

    /*
    Given accept type is Json
    And query parameter values are:
    gender/Female
    nameContains |J
    When user sends GET request to/api/spartans/search
    Then response status code should be 200
    And response content type: application/json;
    And "Female" should be in response payload
    And "Janette" should be in response payload
     */

    @Test
    public void QueryParam1(){

      Response response=given().accept(ContentType.JSON)
              .and().queryParam("gender","Female")
              .and().queryParam("nameContains","J")
              .when().get("/api/spartans/search");


        //verify status code
        assertEquals(response.statusCode(),200);

        //verify content type
        assertEquals(response.contentType(),"application/json");

        //verify female
        assertTrue(response.body().asString().contains("Female"));

        //verify male not exist
        assertFalse(response.body().asString().contains("Male"));

        //verify Janette
        assertTrue(response.body().asString().contains("Janette"));

        response.prettyPrint();


    }

}
