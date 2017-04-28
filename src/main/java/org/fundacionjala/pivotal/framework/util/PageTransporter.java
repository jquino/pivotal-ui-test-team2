package org.fundacionjala.pivotal.framework.util;

import org.fundacionjala.pivotal.framework.selenium.DriverManager;

/**
 * Created by Jose Rioja on 4/27/2017.
 */
public final class PageTransporter {
    private static String baseUrl = PropertiesInfo.getInstance().getUrl();

    /**
     * PageTransporter constructor.
     */
    private PageTransporter() {
    }

    /**
     * Method to go to an specific page through the url.
     * @param endPoint End point.
     */
    public static void goToURL(String endPoint) {
        String url = String.format("%s%s", baseUrl, endPoint);
        DriverManager.getInstance().getDriver().navigate().to(url);
    }
}
