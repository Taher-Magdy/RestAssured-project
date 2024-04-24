package Rest;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import groovy.json.JsonSlurper;

public class Testcase {
    @Test
   /* public void test() {
        // given = put the request (header, query param, body)
        // when = send the request (get, post, put, delete)
        // then = validate the response (status code, header, body) and assertion
                 given().baseUri("https://65c9e1133b05d29307df2a54.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().all()
                .assertThat().statusCode(200)

// assertion with groovy syntax is query param like that .body(groovy path, equalTo("..."))
                //.assertThat().body("[0].name", equalTo("Elaine Schmidt"))
                 //.body("[0].name", is(equalTo("Elaine Schmidt")))
        // is() is a static method of the Matchers class for 'readability'
                //.assertThat().body("name", hasItem("Clinton Barton"));

// can read it as body("[0].name", equalTo("Elaine Schmidt"), "name", hasItem("Clinton Barton"));
                         //hasItems()
              //  .assertThat().body("name", hasItems("Elaine Schmidt", "Clinton Barton"))
                         //Not()
               // .assertThat().body("name", not(hasItem("Taher")))
             //contains() used to check the whole list and should be in the same order and the same size
//.assertThat().body("name", contains("Clinton Barton"));
   //containsInAnyOrder() used to check the whole list and should be in the same size but not in the same order
        //empty , not empty , has size (2 ways), everyItem , startswith
        //empty() , not(empty()) , hasSize(3) , hasSize(equalTo(3)) , everyItem(startsWith("C"))

        //haskey , hasValue , hasEntry
        //hasKey("name") , hasValue("Elaine Schmidt") , hasEntry("name", "Elaine Schmidt")
        //hasentry() used to check the key and value in the same time
        //hasValue() used to check the value only
                         .assertThat().body("[0]",hasValue("Elaine Schmidt"))
                         .assertThat().body("[0]",hasKey("name"))
                         .assertThat().body("[0]",hasEntry("name", "Elaine Schmidt"));
*/


    // extract() used to extract the response and store it in a variable res and then we can use it in the next test
        /*public void test2() {
            // there are two ways to extract the response

            // (1) first way to extract the response and store it in a variable res
        *//*Response res = given().baseUri("https://65c9e1133b05d29307df2a54.mockapi.io/api/v1/")
                .when().get("users")
                .then().extract().response();

        *//**//*String name = res.path("[0].name");
        // use string name to store spacific value from the response
        System.out.println(res.asString());*//**//*

        // another way to extract the response and store it in a variable res
        String name = JsonPath.from(res.asString()).getString("[0].name");
        System.out.println(name);*//*

        // (2) Second way to extract the response in String (directly without store it in a variable res
        String name = given().baseUri("https://65c9e1133b05d29307df2a54.mockapi.io/api/v1/")
                .when().get("users")
                .then().extract().path("[0].name");
        System.out.println(name);

    }*/


    // lodding for request and response in the same time
    public void log(){
        given().baseUri("https://65c9e1133b05d29307df2a54.mockapi.io/api/v1/")
                // .log().params() // to log the query param
                // .log().body() // to log the body
                // .log().headers() // to log the headers
                // .log().method() // to log the method
                // .log().uri() // to log the uri
                .when().get("users")
                .then().log().ifValidationFails()
                .body("name", hasItem("Elaine Schmidt"));




        // .log().status() // to log the status code
        // .log().all() // to log the request and response in the same time
        // .log().headers() // to log the headers
        // .log().body() // to log the body
        // .log().ifError() // to log the error data only if the status code is not equal to 200
        // .log().ifStatusCodeIsEqualTo(200) // to log the status code if it is equal to 200
//.log().ifValidationFails() // to log the validation data only if the validation (assertion) which we put in the then() block is failed
  /*      .then().log().ifValidationFails()
                .body("name", hasItem("Elaine Schmidt"));*/
        // .log().everything() // to log the request and response in the same time

    }
}
