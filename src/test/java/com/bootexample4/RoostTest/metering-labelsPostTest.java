// Test generated by RoostGPT for test rest-assured-metric using AI Type Open AI and AI Model gpt-4


// Test generated for /v2.0/metering/metering-labels_post for http method type POST in rest-assured framework


// RoostTestHash=b9a8e69447


package com.bootexample4.RoostTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class metering-labelsPostTest {

    @Test
    public void metering-labels_post_Test() {
        RestAssured.baseURI = System.getenv("BASE_URL");

        // Read CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/java/com/bootexample4/RoostTest/metering-labels_post.csv"))) {
            String headerLine = reader.readLine();
            String[] headers = headerLine.split(",");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // Create a map of header to data
                Map<String, String> map = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    map.put(headers[i], data[i]);
                }

                Response response = given()
                        .contentType(ContentType.JSON)
                        .body(map)
                        .when()
                        .post("/v2.0/metering/metering-labels")
                        .then()
                        .assertThat()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .extract().response();

                assertEquals("Expected status code 201", 201, response.getStatusCode());
                assertNotNull("Response body should not be null", response.getBody());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
