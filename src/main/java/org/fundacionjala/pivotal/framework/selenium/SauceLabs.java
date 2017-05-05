package org.fundacionjala.pivotal.framework.selenium;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.fundacionjala.pivotal.framework.util.PropertiesInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by xain on 5/4/2017.
 */
public class SauceLabs implements IDriver {
    private static final Logger LOGGER = Logger.getLogger(SauceLabs.class.getName());

    private static final PropertiesInfo ENVIRONMENT = PropertiesInfo.getInstance();

    private static final String HTTPS_PROXY_HOST = "https.proxyHost";

    private static final String HTTPS_PROXY_PORT = "https.proxyPort";

    private static final String BROWSER = "browserName";

    private static final String BROWSER_VERSION = "version";

    private static final String PLATFORM = "platform";

    private static final String RESOLUTION = "screenResolution";

    /**
     * {@inheritDoc}
     */
    @Override
    public WebDriver initDriver() {
        final String url = String.format("https://%s:%s@ondemand.saucelabs.com:443/wd/hub",
                ENVIRONMENT.getRemoteUser(),
                ENVIRONMENT.getRemoteAccessKey());
        DesiredCapabilities caps = new DesiredCapabilities();

        if (!(ENVIRONMENT.getProxy() == null)) {
            System.getProperties().put(HTTPS_PROXY_HOST, ENVIRONMENT.getProxyHost());
            System.getProperties().put(HTTPS_PROXY_PORT, ENVIRONMENT.getProxyPort());
        }
        caps.setCapability(BROWSER, ENVIRONMENT.getRemoteBrowser());
        caps.setCapability(BROWSER_VERSION, ENVIRONMENT.getBrowserVersion());
        caps.setCapability(PLATFORM, String.format("%s %s", ENVIRONMENT.getPlatform(),
                ENVIRONMENT.getRemotePlatformVersion()));
        caps.setCapability(RESOLUTION, ENVIRONMENT.getRemoteResolution());
        RemoteWebDriver remoteWebDriver;
        try {
            remoteWebDriver = new RemoteWebDriver(new URL(url), caps);
        } catch (MalformedURLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new RuntimeException();
        }
        return remoteWebDriver;
    }
}
