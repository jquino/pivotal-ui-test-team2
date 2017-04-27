package org.fundacionjala.pivotal.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import org.fundacionjala.pivotal.framework.util.PropertiesInfo;

/**
 *  It establishes the connection with Pivotal Tracker.
 *  It is a singleton class.
 */
public final class Authentication {

    private static Authentication instance;

    private RequestSpecification requestSpecification;

    private static final String TOKEN_HEADER = "X-TrackerToken";

    /**
     * Class constructor.
     */
    private Authentication() {
        initApi();
    }

    /**
     * It returns the instance of the Authentication class.
     *
     * @return  the needeed instance.
     */
    public static Authentication getInstance() {
        if (instance == null) {
            instance = new Authentication();
        }
        return instance;
    }

    /**
     *  Method that initializes the request specification.
     *  It validates if there is a HTTPS validation and
     *  check if there is any proxy to get the instance.
     */
    private void initApi() {
        requestSpecification = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .addHeader(TOKEN_HEADER, PropertiesInfo.getInstance().getApiToken())
                .build();
        requestSpecification.baseUri(PropertiesInfo.getInstance().getUrlApi());
        if (PropertiesInfo.getInstance().getProxy() != null) {
            requestSpecification.proxy(PropertiesInfo.getInstance().getProxy());
        }
    }

    /**
     *  It gets the request specification parameter
     *  from the instance created.
     *  @return the specification proxy that had obtained
     *          inside initApi method.
     */
    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
}
