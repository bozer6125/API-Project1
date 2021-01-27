package ApiTests;


import io.restassured.path.json.JsonPath;
import java.util.List;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.junit.Assert.*;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

public class SpartanTestsWithPathParameters {

@BeforeClass
    public static void  setUpClass(){
    RestAssured.baseURI="http://54.173.198.79:8000";
}

    /*
    Given accept type is Json
    And Id parameter value is 18
    When user sends GET request to/api/spartans/{id}
    Then response status code should be 200
    And response content type: application/json;
    And "Allen" should be in response payload
     */


@Test
    public void PathTest1(){
    Response response=RestAssured.given().accept(ContentType.JSON)
            .and().pathParam("id",18).when()
            .get("api/spartans/{id}");

    //verify status code
    assertEquals(response.statusCode(),200);

    //verify content type
    assertEquals(response.contentType(),"application/json");

    //verify Allen exist
    assertTrue(response.body().asString().contains("Allen"));

response.body().prettyPrint();

}

/*
    Given accept type is Json
    And Id parameter value is 500
    When user sends GET request to/api/spartans/{id}
    Then response status code should be 404
    And response content type: application/json;
    And "Spartan not found" should be in response payload
     */

    @Test
    public void negativePathTest(){
        Response response=RestAssured.given().accept(ContentType.JSON)
                        .and().pathParam("id",500).when()
                        .get("api/spartans/{id}");

        //verify status code
        assertEquals(response.statusCode(),404);

        //verify content type
        assertEquals(response.contentType(),"application/json");

        //  verify Spartan Not Found
        assertTrue(response.body().asString().contains("Not Found"));

        response.prettyPrint();


    }

}
