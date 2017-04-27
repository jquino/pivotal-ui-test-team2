package org.fundacionjala.pivotal.pages.workspace;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.fundacionjala.pivotal.pages.BasePage;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;

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
        cancelDeleteLink.click();
    }

    /**
     * Method that completes the delete process
     * once the delete link is pressed.
     *
     * @return the dashboard instance
     */
    public Dashboard clickConfirmDeleteLink() {
        confirmDeleteLink.click();
        return new Dashboard();
    }
}
