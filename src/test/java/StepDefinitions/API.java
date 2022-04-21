package StepDefinitions;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class API {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSteps.class);


    @And("^I execute (.*) template rest service")
    public void request(String template) throws IOException, ParseException {
        Object document = null;
        JSONParser parser = new JSONParser();
        String projectPath = System.getProperty("user.dir");
        Object obj = parser.parse(new FileReader(projectPath + "\\src\\test\\java\\RestClients\\" + template + ".json"));
        JSONObject jsonObject = (JSONObject) obj;
        document = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toJSONString());
        String url = JsonPath.read(document, "$.Url");
        String method = JsonPath.read(document, "$.method");
        String checkCode = JsonPath.read(document, "$.responseCode");
        String content = JsonPath.read(document, "$.contentType");
        String check = JsonPath.read(document, "$.check");
        if(method.equals("get")){
            given()
                    .when()
                    .get(url)

                    .then()
                    .statusCode(Integer.parseInt(checkCode));
        }else if (method.equals("post")){
            given()
                    .when()
                    .post(url)

                    .then()
                    .statusCode(Integer.parseInt(checkCode));
        }else if (method.equals("put")){
            given()
                    .when()
                    .put(url)

                    .then()
                    .statusCode(Integer.parseInt(checkCode));
        }else if (method.equals("delete")){
            given()
                    .when()
                    .delete(url)

                    .then()
                    .statusCode(Integer.parseInt(checkCode));
        }else {
            Assert.fail("Your method is failed");
        }
        LOGGER.info("Requested to "+url);
        System.out.println("Requested to "+url);
    }


    @And("^I execute (.*) template rest service with parameters:")
    public void requestWithBody(String template,DataTable map) throws IOException, ParseException {

        Object document = null;
        JSONParser parser = new JSONParser();
        String projectPath = System.getProperty("user.dir");
        Object obj = parser.parse(new FileReader(projectPath + "\\src\\test\\java\\RestClients\\" + template + ".json"));
        JSONObject jsonObject = (JSONObject) obj;
        document = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toJSONString());
        String url = JsonPath.read(document, "$.Url");
        String method = JsonPath.read(document, "$.method");
        String checkCode = JsonPath.read(document, "$.responseCode");
        String content = JsonPath.read(document, "$.contentType");
        String check = JsonPath.read(document, "$.check");

        if(method.equals("get")){
            Response res =
            given()
                    .contentType(content)
                    .body(map)
                    .when()
                    .get(url)
                    .then()
                    .statusCode(Integer.parseInt(checkCode))
                    .log().body()
                    .extract().response();
            String jsonString = res.asString();
            Assert.assertEquals(jsonString.contains(check),true);

        }else if (method.equals("post")){
            Response res =
            given()
                    .contentType(content)
                    .body(map)
                    .when()
                    .post(url)
                    .then()
                    .statusCode(Integer.parseInt(checkCode))
                    .log().body()
                    .extract().response();
            String jsonString = res.asString();
            Assert.assertEquals(jsonString.contains(check),true);

        }else if (method.equals("put")){
            Response res =
            given()
                    .contentType(content)
                    .body(map)
                    .when()
                    .put(url)
                    .then()
                    .statusCode(Integer.parseInt(checkCode))
                    .log().body()
                    .extract().response();
            String jsonString = res.asString();
            Assert.assertEquals(jsonString.contains(check),true);

        }else if (method.equals("delete")){
            Response res =
            given()
                    .contentType(content)
                    .body(map)
                    .when()
                    .delete(url)
                    .then()
                    .statusCode(Integer.parseInt(checkCode))
                    .log().body()
                    .extract().response();

                    String jsonString = res.asString();
                    Assert.assertEquals(jsonString.contains(check),true);
        }else {
            Assert.fail("Your method is failed");
        }
    }


}
