package com.fundacionjala.pivotal.pages.login;

import com.fundacionjala.pivotal.framework.util.PropertiesInfo;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;

/**
 * Created by mijhailvillarroel on 7/6/2016.
 */
public class PivotalHome extends BasePage {

    @FindBy(css = ".btn-login")
    private WebElement signInLink;

    public PivotalHome() {
        driver.get(PropertiesInfo.getInstance().getUrl());
    }

    public Login clickSingInLink() {
        clickWebElement(signInLink);
        return new Login();
    }
}