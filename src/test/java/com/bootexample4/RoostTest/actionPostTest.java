// Test generated by RoostGPT for test rest-assured-shakul using AI Type Open AI and AI Model gpt-4-1106-preview

// Test generated for /v2.1/flavors/10/action_post for http method type POST in rest-assured framework

// RoostTestHash=225171e11a

package com.bootexample4.RoostTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ActionPostTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getenv("BASE_URL");
    }

    @Test
    public void actionPostTest() {
        String csvFilePath = "src/test/java/com/bootexample4/RoostTest/action_post.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String headerLine = reader.readLine();
            String[] headers = headerLine.split(",");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Map<String, String> requestData = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    requestData.put(headers[i], data[i]);
                }

                Response response = given()
                    .contentType(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post("/v2.1/flavors/10/action")
                    .then()
                    .extract()
                    .response();

                switch (response.statusCode()) {
                    case 200:
                        validateSuccessResponse(response);
                        break;
                    case 400:
                        validateErrorResponse(response);
                        break;
                    case 404:
                        validateNotFoundResponse(response);
                        break;
                    case 422:
                        validateUnprocessableEntityResponse(response);
                        break;
                    default:
                        fail("Unhandled status code: " + response.statusCode());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Test failed due to IOException");
        }
    }

    private void validateSuccessResponse(Response response) {
        response.then()
            .body("success", equalTo(true))
            .body("data", notNullValue())
            .body("message", equalTo("Action completed successfully"));
    }

    private void validateErrorResponse(Response response) {
        response.then()
            .body("error", notNullValue())
            .body("status", equalTo(400))
            .body("message", containsString("Bad Request"));
    }

    private void validateNotFoundResponse(Response response) {
        response.then()
            .body("error", notNullValue())
            .body("status", equalTo(404))
            .body("message", containsString("Not Found"));
    }

    private void validateUnprocessableEntityResponse(Response response) {
        response.then()
            .body("error", notNullValue())
            .body("status", equalTo(422))
            .body("message", containsString("Unprocessable Entity"));
    }
}
