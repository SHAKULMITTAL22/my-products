// Test generated by RoostGPT for test rest-assured-shakul using AI Type Open AI and AI Model gpt-4-1106-preview

// Test generated for /v2.1/flavors_post for http method type POST in rest-assured framework

// RoostTestHash=8f1a31ef3f

package com.bootexample4.RoostTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

public class FlavorsPostTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getenv("BASE_URL");
    }

    @Test
    public void flavorsPostTest() {
        String csvFile = "src/test/java/com/bootexample4/RoostTest/flavors_post.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String headerLine = reader.readLine();
            String[] headers = headerLine.split(",");

            String line;
            while ((line = reader.readLine()) != null) {
                Map<String, Object> payload = createPayload(headers, line.split(","));

                Response response = given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("/v2.1/flavors")
                    .then()
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
                        throw new IllegalStateException("Unexpected response status code: " + response.statusCode());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateSuccessResponse(Response response) {
        response.then()
            .statusCode(200)
            .body("flavor.id", notNullValue())
            .body("flavor.name", equalToIgnoringCase("test_flavor"));
    }

    private void validateErrorResponse(Response response) {
        response.then()
            .statusCode(400)
            .body("error.message", notNullValue())
            .body("error.type", equalTo("BadRequest"));
    }

    private void validateNotFoundResponse(Response response) {
        response.then()
            .statusCode(404)
            .body("error.message", notNullValue())
            .body("error.type", equalTo("NotFound"));
    }

    private void validateUnprocessableEntityResponse(Response response) {
        response.then()
            .statusCode(422)
            .body("error.message", notNullValue())
            .body("error.type", equalTo("UnprocessableEntity"));
    }

    private Map<String, Object> createPayload(String[] headers, String[] values) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            map.put(headers[i], values[i]);
        }
        return map;
    }
}

