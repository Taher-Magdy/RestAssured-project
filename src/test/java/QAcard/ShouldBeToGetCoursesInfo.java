package QAcard;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ShouldBeToGetCoursesInfo {

    //using header to filter the response and assertion on the response
    @Test
    public void test() {

        HashMap<String, String> infoheader = new HashMap<>();
        infoheader.put("Authorization", "Bearer");
        infoheader.put("Content-Type", "application/json");
//       Header header = new Header("Authorization", "Bearer");
//        Header header1 = new Header("Content-Type", "application/json");
//        Headers headers = new Headers(header, header1);

        given()
                .baseUri("https://todo.qacart.com/")
                .header("Authorization", "Bearer")

//header() used to add the header to the request to make filteration and  assertion on the response and it takes two parameters
// header()  used to filteration and assertion on the response

                // headers used to add multiple headers to the request
                //(1) headers syntax  .headers("key1", "value1", "key2", "value2")

                // (2) another way to add multiple headers to the request (with Header and Headers classes)
//                .header(header)
//                .header(header1)
//                .headers(headers)


                // (3) another way to add multiple headers to the request (with HashMap class)
                .headers(infoheader)

                .log().all()
                .when().get("todo")
                .then().log().headers()
                .assertThat().statusCode(200)
                .assertThat().body("count", equalTo(1));
    }


    //using query param to filter the response and assertion on the response .queryParam("key", "value")
    @Test
    public void test2() {
        // add query param to the request  directly like .queryParam("type", "PAID")
        //HashMap used to add multiple query param to the request

        HashMap<String, String> queryparam = new HashMap<>();
        queryparam.put("type", "PAID");
        queryparam.put("status", "ACTIVE");
        given()
                .baseUri("https://todo.qacart.com/api/v1/info/")
                //.queryParam("type", "PAID")
                //.queryParams("type", "PAID", "status", "ACTIVE")
                // HashMap used to add multiple query param to the request
                .queryParams(queryparam)
                .log().all()
                .when().get("lectures")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    //using body to add the request body and assertion on the response
    // body() used to put input data to the request like email, password, username, etc
    // using post and put methods

    // sending the body with different ways
    @Test
    public void test3() {
        // (1)send the body as a String
        /*String body = "{\n" +
                "    \"email\": \"hatem@example.com\",\n" +
                "    \"password\": \"Test1234\"\n" +
                "}";*/


        // (2) send the body as a File
        //File body = new File("src/test/java/QAcard/login.json");


        // (3) send the body as a HashMap
        //serilization used to convert the body from java object to a json data
        //add dependency to the pom.xml (jackson DataBind)
        //deserilization used to convert the response from json data to java object
        /*HashMap<String, String> body = new HashMap<>();
        body.put("email", "hatem@example.com");
        body.put("password", "Test1234");*/

        // (4) sending the body as a pojo class by setter and getter
        /*PojoLogin body = new PojoLogin();
        body.setEmail("hatem@example.com");
        body.setPassword("Test1234");*/
        // Pojo by constructor
        PojoLogin body = new PojoLogin("hatem@example.com", "Test1234");


        given()
                .baseUri("https://todo.qacart.com/")

                //.header("Content-Type", "application/json")
                //OR
                // .contentType("application/json")     // as String
                .contentType(ContentType.JSON)
                // contentType used to read the body as a json data and it takes one parameter
                .body(body)
                .log().all()
                .when()
                //.post("students/login")
                .post("login")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    // Authentication
    @Test
    public void test4() {
        given()
                .baseUri("https://todo.qacart.com/")
                // (1) Authentication as header
                //.header("Authorization", "Bearer")

                //(2) Authentication with basic auth
                .auth().oauth2("Bearer")

                .when()
                .get( "students")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    // Request Specification
    // RequestSpecification used to add the common request to the request like baseUri, header, query param, body, etc
    RequestSpecification req ;
    // define RequestSpecification as a global variable to use it in all the tests
    @BeforeTest
    public void BeforeTest () {
       req = given()
                .baseUri("https://todo.qacart.com/")
                .log().all();
    }
    // use the RequestSpecification in the test
    @Test
    public void test5() {
        given()
                //.spec() to add the common request to the request
                .spec(req)
                .when()
                .get("students")
                .then().log().all()
                .assertThat().statusCode(200);
    }

}



/*
base_uri=>"https://todo.qacart.com"
        "email"=>"hatem@example.com"
        "password"=>"Test1234"*/
