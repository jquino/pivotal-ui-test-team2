package org.fundacionjala.pivotal.pages.workspace;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.fundacionjala.pivotal.pages.BasePage;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;

/**
 * This class creates a form to create a new Workspace.
 */
public class DeleteWorkspace extends BasePage {

    @FindBy(id = "confirm_delete")
    private WebElement confirmDeleteLink;

    @FindBy(className = "cancel")
    private WebElement cancelDeleteLink;

    /**
     * Method that cancels the delete process once
     * the delete link is pressed.
     */
    public void clickCancelDeleteLink() {
        clickWebElement(cancelDeleteLink);
    }

    /**
     * Method that completes the delete process
     * once the delete link is pressed.
     *
     * @return the dashboard instance
     */
    public Dashboard clickConfirmDeleteLink() {
        clickWebElement(confirmDeleteLink);
        return new Dashboard();
    }
}
