package org.fundacionjala.pivotal.api;

import java.util.Map;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 *  This manager method provides the necessary methods
 *  that manage the Pivotal Tracker API REST requests
 *  like GET, POST, PUT, and DELETE.
 */
public final class RequestManager {

    private static final RequestSpecification REQUEST = Authentication.getInstance().getRequestSpecification();

    /**
     * Class constructor.
     */
    private RequestManager() {
    }

    /**
     * It processes the GET request given an endpoint specified.
     * @param endpoint the endpoing with keys and values
     * @return the response of the GET request
     */
    public static Response getRequest(String endpoint) {
        return given().spec(REQUEST)
                .when().get(endpoint);
    }

    /**
     * It processes the POST request given the specified endpoint
     * and the parameters to be created.
     *
     * @param endpoint the endpoint with key and values
     * @param parameters data to be filled on the endpoint created
     * @return the response of the POST request
     */
    public static Response postRequest(String endpoint, Map<String, Object> parameters) {
        return given().spec(REQUEST).params(parameters)
                .when().post(endpoint);
    }

    /**
     * It processes the PUT request given the specified endpoint
     * and the parameters to be updated.
     *
     * @param endpoint the endpoint with key and values
     * @param parameters data to be updated on the endpoint specified
     * @return the response of the PUT request
     */
    public static Response putRequest(String endpoint, Map<String, Object> parameters) {
        return given().spec(REQUEST).params(parameters)
                .when().put(endpoint);
    }

    /**
     * IT processes the DELETE request given the specified endpoint.
     *
     * @param endpoint the endpoint with keys and values
     * @return the response of the DELETE request
     */
    public static Response deleteRequest(String endpoint) {
        return given().spec(REQUEST).when().delete(endpoint);
    }
}
