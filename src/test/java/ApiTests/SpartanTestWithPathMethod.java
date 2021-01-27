package ApiTests;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.testng.Assert.*;
public class SpartanTestWithPathMethod {

    @BeforeClass
    public static void  setUpClass(){
        baseURI="http://54.173.198.79:8000";
    }

    /*
    Given accept type is Json
    And Id param id is 6
    When user sends GET request to /"spartans/{id}
    Then response status code should be 200
    And content-type is "application/json"
    And response payload values match the fallowing:
                    id is 6,
                    name is "Tedmund"
                    gender is "Male"
                    phone number is 2227140732
     */

    @Test
    public void test1(){

Response response=given().accept(ContentType.JSON)
        .pathParam("id",6)
        .when().get("/api/spartans/{id}");

        //verify status code
        assertEquals(response.statusCode(),200);

        //verify content type
        assertEquals(response.contentType(),"application/json");
        System.out.println("Id:"+response.body().path("id").toString());
        System.out.println("Name:"+response.body().path("name").toString());
        System.out.println("Gender:"+response.body().path("gender").toString());
        System.out.println("Phone number:"+response.body().path("phone").toString());

        int id=response.path("id");
        String name=response.body().path("name");
        String gender=response.body().path("gender");
        long phone=response.body().path("phone");

        System.out.println("id = "+id);
        System.out.println("name = "+name);
        System.out.println("gender = "+gender);
        System.out.println("phone = "+phone);
//verify json keys value
        assertEquals(id,6);
        assertEquals(name,"Tedmund");
        assertEquals(gender,"Male");
        assertEquals(phone, 2227140732l);

    }
@Test
    public void test2(){
        Response response= get("/api/spartans");

        //extract first id
    int firstId = response.path("id[0]");
    System.out.println("firstId= "+firstId);

    //extract name
    String first1stName=response.path("name[0]");
    System.out.println("first1stName= "+first1stName);

    //extract the last firstName
    String last1stName=response.path("name[-1]");
    System.out.println("last1stName= "+last1stName);

    //extract all first names and print them
    List<String> names=response.path("name");
    System.out.println(names);
    System.out.println("names ="+names.size());
    }
}
