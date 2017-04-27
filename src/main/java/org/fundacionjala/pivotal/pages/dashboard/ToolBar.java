package org.fundacionjala.pivotal.pages.dashboard;

import org.fundacionjala.pivotal.pages.BasePage;
import org.fundacionjala.pivotal.pages.setting.Setting;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;

/**
 * Class that represents the tool bar present
 * on the account page.
 */
public class ToolBar extends BasePage {

    @FindBy(css = ".tc_header_item.tc_header_logo")
    private WebElement returnDashboardLink;

    @FindBy(css = "[data-aid='navTab-settings']")
    private WebElement settingsTabLink;

    /**
     * Method that let us click the link to
     * return to a dashboard.
     * @return the Dashboard instance
     */
    public Dashboard clickReturnDashboardLink() {
        clickWebElement(returnDashboardLink);
        return new Dashboard();
    }

    /**
     * Method that let us click the link to
     * enter to the settings options.
     * @return the Settings instance
     */
    public Setting clickSettingTabLink() {
        clickWebElement(settingsTabLink);
        return new Setting();
    }
}
