// Test generated by RoostGPT for test rest-assured-shakul using AI Type Open AI and AI Model gpt-4-1106-preview

// Test generated for /v2.1/flavors_post for http method type POST in rest-assured framework

// RoostTestHash=8f1a31ef3f

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

public class flavorsPostTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getenv("BASE_URL");
    }

    @Test
    public void flavors_post_Test() {
        String csvFilePath = "src/test/java/com/bootexample4/RoostTest/flavors_post.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String headerLine = reader.readLine();
            String[] headers = headerLine.split(",");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Map<String, String> requestBody = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    requestBody.put(headers[i], data[i]);
                }

                Response response = given()
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .when()
                        .post("/v2.1/flavors")
                        .then()
                        .log().ifValidationFails()
                        .extract().response();

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
                        fail("Unexpected status code: " + response.statusCode());
                }
            }
        } catch (IOException e) {
            fail("Failed to read CSV file: " + e.getMessage());
        }
    }

    private void validateSuccessResponse(Response response) {
        response.then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo(requestBody.get("name")));
    }

    private void validateErrorResponse(Response response) {
        response.then()
                .statusCode(400)
                .body("error", notNullValue())
                .body("message", containsString("Invalid request"));
    }

    private void validateNotFoundResponse(Response response) {
        response.then()
                .statusCode(404)
                .body("error", notNullValue())
                .body("message", containsString("Not Found"));
    }

    private void validateUnprocessableEntityResponse(Response response) {
        response.then()
                .statusCode(422)
                .body("error", notNullValue())
                .body("errors", hasItem(anything())); // Assuming the response has an "errors" array
    }
}
