package org.fundacionjala.pivotal.pages.workspace;

import org.fundacionjala.pivotal.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.getTextFieldValue;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.setWebElement;

/**
 * Class that represents to the workspace settings with
 * its characteristics.
 */
public class SettingWorkspace extends BasePage {

    public static final String VALUE = "value";
    @FindBy(id = "workspace_name")
    private WebElement workspaceNameText;

    @FindBy(css = "a#delete_link")
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
        setWebElement(workspaceNameText, workspaceName);
    }

    /**
     * Method that returns the delete workspace instance
     * once we click the link.
     *
     * @return the delete workspace instance
     */
    public DeleteWorkspace clickDeleteWorkspaceLink() {
        clickWebElement(deleteWorkspaceLink);
        return new DeleteWorkspace();
    }

    /**
     * Method that clicks the workspace link.
     */
    public void clickSaveChangesWorkspaceLink() {
        clickWebElement(saveChangesWorkspaceLink);
    }

    /**
     * Method that clicks the cancel changes on
     * workspace link.
     */
    public void cancelChangesWorkspaceLink() {
        clickWebElement(cancelChangesWorkspaceLink);
    }

    /**
     * Method that returns the message about changes
     * on the workspace.
     *
     * @return the value of the message
     */
    public String getMessageChangesWorkspace() {
        return getTextFieldValue(messageChangesWorkspace);
    }

    /**
     * Returns the name of the workspace.
     * @return the value of the workspace name.
     */
    public String getWorkspaceName() {
        return workspaceNameText.getAttribute(VALUE);
    }

}
