package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class API {

    @And("^I execute GET parameters URL:(.*) PATH:(.*) RESPONSECODE:(\\d+)$")
    public void get(String URL, String PATH, int RESPONSECODE){
        given()
                .when()
                .get(URL+PATH)

                .then()
                .statusCode(RESPONSECODE);
    }


    @And("^I execute POST parameters URL:(.*) PATH:(.*) RESPONSECODE:(\\d+) CHECK:(.*) BODY:$")
    public void post(String URL, String PATH, int RESPONSECODE , String CHECK, DataTable map){

        Response res =

        given()
                .contentType("application/json")
                .body(map)
        .when()
                .post(URL+PATH)
         .then()
                .statusCode(RESPONSECODE)
                .log().body()
                .extract().response();

        String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains(CHECK),true);
    }


    @And("^I execute PUT parameters URL:(.*) PATH:(.*) RESPONSECODE:(\\d+) CHECK:(.*) BODY:$")
    public void put(String URL, String PATH, int RESPONSECODE , String CHECK, DataTable map){

        Response res =

                given()
                        .contentType("application/json")
                        .body(map)
                        .when()
                        .put(URL+PATH)
                        .then()
                        .statusCode(RESPONSECODE)
                        .log().body()
                        .extract().response();

        String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains(CHECK),true);
    }


    @And("^I execute DELETE parameters URL:(.*) PATH:(.*) RESPONSECODE:(\\d+) CHECK:(.*)$")
    public void delete(String URL, String PATH, int RESPONSECODE , String CHECK){

        Response res =

                given()
                        .when()
                        .delete(URL+PATH)
                        .then()
                        .statusCode(RESPONSECODE)
                        .log().body()
                        .extract().response();

        String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains(CHECK),true);
    }
}
