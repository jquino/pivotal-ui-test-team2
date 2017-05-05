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
public class BrowserStack implements IDriver {

    private static final Logger LOGGER = Logger.getLogger(BrowserStack.class.getName());

    private static final PropertiesInfo ENVIRONMENT = PropertiesInfo.getInstance();

    private static final String HTTPS_PROXY_HOST = "https.proxyHost";

    private static final String HTTPS_PROXY_PORT = "https.proxyPort";

    private static final String BROWSER = "browser";

    private static final String BROWSER_STACK_DEBUG = "browserstack.debug";

    private static final String BROWSER_VERSION = "browser_version";

    private static final String OS = "os";

    private static final String OS_VERSION = "os_version";

    private static final String RESOLUTION = "resolution";

    private static final String VALUE = "true";

    private static final String FIRST_BUILD = "First build";

    private static final String BUILD = "build";

    /**
     * {@inheritDoc}
     */
    @Override
    public WebDriver initDriver() {
        final String url = String.format("https://%s:%s@hub-cloud.browserstack.com/wd/hub",
                ENVIRONMENT.getRemoteUser(),
                ENVIRONMENT.getRemoteAccessKey());
        DesiredCapabilities caps = new DesiredCapabilities();
        //Setting proxy.
        if (!(ENVIRONMENT.getProxy() == null)) {
            System.getProperties().put(HTTPS_PROXY_HOST, ENVIRONMENT.getProxyHost());
            System.getProperties().put(HTTPS_PROXY_PORT, ENVIRONMENT.getProxyPort());
        }
        caps.setCapability(BROWSER, ENVIRONMENT.getRemoteBrowser());
        caps.setCapability(BROWSER_VERSION, ENVIRONMENT.getBrowserVersion());
        caps.setCapability(OS, ENVIRONMENT.getPlatform());
        caps.setCapability(OS_VERSION, ENVIRONMENT.getRemotePlatformVersion());
        caps.setCapability(RESOLUTION, ENVIRONMENT.getRemoteResolution());
        caps.setCapability(BROWSER_STACK_DEBUG, VALUE);
        caps.setCapability(BUILD, FIRST_BUILD);
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
