package org.fundacionjala.pivotal.pages.setting;

import org.fundacionjala.pivotal.pages.BasePage;
import org.fundacionjala.pivotal.pages.project.ProjectSettings;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;

/**
 * Class that represents the side bar settings placed
 * at the left of the Settings screen.
 */
public class SideBarSetting extends BasePage {

    @FindBy(xpath = "//a[text()='General Settings']")
    private WebElement linkGeneralSettings;

    @FindBy(xpath = "//a[text()='Project Profile']")
    private WebElement linkProjectProfile;

    @FindBy(xpath = "//a[text()='Integrations']")
    private WebElement linkIntegrations;

    @FindBy(xpath = "//a[text()='Webhooks']")
    private WebElement linkWebHooks;

    @FindBy(xpath = "//a[text()='Import CSV']")
    private WebElement linkImportCSV;

    @FindBy(xpath = "//a[text()='Export CSV']")
    private WebElement linkExportCSV;

    /**
     * Method that clicks the button that directs
     * to General settings page.
     *
     * @return the instance of the general settings form
     */
    public ProjectSettings clickGeneralSetting() {
        clickWebElement(linkGeneralSettings);
        return new ProjectSettings();
    }
}
