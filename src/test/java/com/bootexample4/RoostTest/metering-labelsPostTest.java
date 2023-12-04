// Test generated by RoostGPT for test rest-assured-metric using AI Type Open AI and AI Model gpt-4-1106-preview


// Test generated for /v2.0/metering/metering-labels_post for http method type POST in rest-assured framework


// RoostTestHash=b9a8e69447


package com.bootexample4.RoostTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class metering-labelsPostTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL");
    }

    @Test
    public void metering_labels_post_Test() {
        // Read CSV file
        String filePath = "src/test/java/com/bootexample4/RoostTest/metering-labels_post.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String headerLine = reader.readLine();
            String[] headers = headerLine.split(",");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // Create a map of headers to data
                Map<String, String> requestBody = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    requestBody.put(headers[i], data[i]);
                }

                // Send POST request with the requestBody map as JSON body
                Response response = given()
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .when()
                        .post("/v2.0/metering/metering-labels")
                        .then()
                        .extract().response();

                // Validate response based on status code
                switch (response.statusCode()) {
                    case 201:
                        validateCreatedResponse(response);
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
                        throw new IllegalStateException("Unexpected response status: " + response.statusCode());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateCreatedResponse(Response response) {
        response.then().assertThat()
                .statusCode(201)
                .body("message", equalTo("Resource created successfully"));
    }

    private void validateErrorResponse(Response response) {
        response.then().assertThat()
                .statusCode(400)
                .body("error", equalTo("Bad Request"))
                .body("details", equalTo("Invalid request parameters"));
    }

    private void validateNotFoundResponse(Response response) {
        response.then().assertThat()
                .statusCode(404)
                .body("error", equalTo("Not Found"))
                .body("details", equalTo("The requested resource was not found"));
    }

    private void validateUnprocessableEntityResponse(Response response) {
        response.then().assertThat()
                .statusCode(422)
                .body("error", equalTo("Unprocessable Entity"))
                .body("details", equalTo("Unable to process the contained instructions"));
    }
}
