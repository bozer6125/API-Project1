package ApiTests;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;
public class SpartanTestWithHamcrest {
    @BeforeClass
    public static void setUpClass() {
        baseURI = "http://54.173.198.79:8000";
    }




    /*
    Given accept type is Json
    And path param spartan id is 15
    When user sends GET request to /spartans/{id}
    Then response status code should be 200
    And content-type is "application/json"
    And response payload values match the fallowing:

                    "id": 15,
                    "name": "Meta"
                    "gender": "Female"
                    "phone": 1938695106
     */

    @Test
    public void test1() {
            //request
            given().accept(ContentType.JSON)
            .pathParam("id",15)
            .when().get("/api/spartans/{id}")
            //response
            .then().statusCode(200).and()
            .assertThat().contentType("application/json");

    }

    @Test
    public void test2(){
        given().accept(ContentType.JSON)
        .pathParam("id",15)
        .when().get("/api/spartans/{id}")
        .then().assertThat().statusCode(200)
        .and().assertThat().contentType("application/json")
        .and().assertThat().body("id",equalTo(15),
                "name",equalTo("Meta"),
                "gender", equalTo("Female"),
                "phone",equalTo(1938695106));

    }
}
