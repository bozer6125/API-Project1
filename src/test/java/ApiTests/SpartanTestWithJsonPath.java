package ApiTests;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.testng.Assert.*;
public class SpartanTestWithJsonPath {
    @BeforeClass
    public static void setUpClass() {
        baseURI = "http://54.173.198.79:8000";
    }




    /*
    Given accept type is Json
    And path param spartan id is 11
    When user sends GET request to /spartans/{id}
    Then response status code should be 200
    And content-type is "application/json"
    And response payload values match the fallowing:
                    id is 11,
                    name is "Nona"
                    gender is "Female"
                    phone number is 7959094216
     */

    @Test
    public void test1() {
        Response response=given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}");
assertEquals(response.statusCode(),200);
//how to read value with path
        int id=response.path("id");
        System.out.println("id = "+id);

        //how read value with JsonPath?
       JsonPath jsonData= response.jsonPath();

       int id1=jsonData.getInt("id");
       String name=jsonData.getString("name");
       String gender=jsonData.getString("gender");
       long phone=jsonData.getLong("phone");

        System.out.println("id1 = "+id1);
        System.out.println("name = "+name);
        System.out.println("gender = "+gender);
        System.out.println("phone = "+phone);

        //verify json payload with JsonPath
        assertEquals(id1,11);
        assertEquals(name,"Nona");
        assertEquals(gender, "Female");
        assertEquals(phone,7959094216l);
    }
}