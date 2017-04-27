package org.fundacionjala.pivotal.pages.accounts;

import org.fundacionjala.pivotal.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;

/**
 * Class that represents the tool bar account where is
 * displayed the SETTINGS tab.
 */
public class ToolBarAccount extends BasePage {

    @FindBy(xpath = "//a[contains(.,'Settings')]")
    private WebElement settingTab;

    /**
     * Method that clicks the SETTINGS tab present on the Account.
     *
     * @return the account setting page instance
     */
    public AccountSetting clickSettingTab() {
        clickWebElement(settingTab);
        return new AccountSetting();
    }
}
