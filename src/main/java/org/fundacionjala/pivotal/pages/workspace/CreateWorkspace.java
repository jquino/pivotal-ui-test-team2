package org.fundacionjala.pivotal.pages.workspace;

import java.util.EnumMap;
import java.util.Map;

import org.fundacionjala.pivotal.framework.util.IAutomationStep;
import org.fundacionjala.pivotal.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * CreateWorkspace is the class to create a new Workspace.
 */
public class CreateWorkspace extends BasePage {

    @FindBy(className = "tc_form_input")
    private WebElement workspaceNameTextField;

    @FindBy(css = ".tc_button.tc_button_submit")
    private WebElement createWorkspaceLink;

    @FindBy(css = ".tc_button.tc_button_cancel")
    private WebElement cancelCreateWorkspaceLink;

    @FindBy(className = "tc_form_error_message")
    private WebElement messageWorkspaceNameEmpty;

    @FindBy(className = "tc_modal_content")
    private WebElement getCreateWorkspaceContainer;

    /**
     * Method that clicks the button "Create Workspace" on the Form.
     *
     * @return the workspace instance
     */
    public Workspace clickCreateWorkspaceLink() {
        wait.until(ExpectedConditions.visibilityOf(getCreateWorkspaceContainer));
        createWorkspaceLink.click();
        return new Workspace();
    }

    /**
     * Method that clicks the button "Cancel" on the Form.
     */
    public void clickCancelCreateWorkspaceLink() {
        cancelCreateWorkspaceLink.click();
    }

    /**
     * Method that inserts a name into Workspace Name field.
     *
     * @param workspaceName the given parameter to insert
     * @return this CreateWorkspace instance
     */
    public CreateWorkspace setWorkspaceName(String workspaceName) {
        workspaceNameTextField.sendKeys(workspaceName);
        return this;
    }

    /**
     * Method that permits set values necessary to create a new Workspace.
     *
     * @param values values map to refer to the workspace
     * @return a Map with the values of the workspace created
     */
    public Map<WorkspaceSteps, IAutomationStep> getStrategyStepMap(Map<WorkspaceSteps, Object> values) {
        final Map<WorkspaceSteps, IAutomationStep> strategyMap = new EnumMap<>(WorkspaceSteps.class);
        strategyMap.put(WorkspaceSteps.WORKSPACE_NAME, () -> setWorkspaceName(String.valueOf(values.get(WorkspaceSteps
                        .WORKSPACE_NAME))));
        return strategyMap;
    }

    /**
     * Method that gets a message when a workspace try to be created with empty name.
     *
     * @return the test of the message
     */
    public String getMessageWorkspaceNameEmpty() {
        return messageWorkspaceNameEmpty.getText();
    }
}
