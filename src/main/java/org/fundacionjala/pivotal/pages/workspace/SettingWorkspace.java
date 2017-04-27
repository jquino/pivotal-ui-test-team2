package org.fundacionjala.pivotal.pages.workspace;

import org.fundacionjala.pivotal.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Class that represents to the workspace settings with
 * its characteristics.
 */
public class SettingWorkspace extends BasePage {

    @FindBy(id = "workspace_name")
    private WebElement workspaceNameText;

    @FindBy(id = "delete_link")
    private WebElement deleteWorkspaceLink;

    @FindBy(className = "save_bar__submit")
    private WebElement saveChangesWorkspaceLink;

    @FindBy(className = "save_bar__cancel")
    private WebElement cancelChangesWorkspaceLink;

    @FindBy(className = "message")
    private WebElement messageChangesWorkspace;

    /**
     * Method that sets the workspace name text field.
     *
     * @param workspaceName the value to enter to the field
     */
    public void setNameWorkspaceTestField(String workspaceName) {
        workspaceNameText.clear();
        workspaceNameText.sendKeys(workspaceName);
    }

    /**
     * Method that returns the delete workspace instance
     * once we click the link.
     *
     * @return the delete workspace instance
     */
    public DeleteWorkspace clickDeleteWorkspaceLink() {
        deleteWorkspaceLink.click();
        return new DeleteWorkspace();
    }

    /**
     * Method that clicks the workspace link.
     */
    public void clickSaveChangesWorkspaceLink() {
        saveChangesWorkspaceLink.click();
    }

    /**
     * Method that clicks the cancel changes on
     * workspace link.
     */
    public void cancelChangesWorkspaceLink() {
        cancelChangesWorkspaceLink.click();
    }

    /**
     * Method that returns the message about changes
     * on the workspace.
     *
     * @return the value of the message
     */
    public String getMessageChangesWorkspace() {
        return messageChangesWorkspace.getText();
    }

}
