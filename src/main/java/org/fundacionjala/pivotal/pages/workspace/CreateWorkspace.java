package org.fundacionjala.pivotal.pages.workspace;

import java.util.EnumMap;
import java.util.Map;

import org.fundacionjala.pivotal.framework.util.CommonMethods;
import org.fundacionjala.pivotal.framework.util.IAutomationStep;
import org.fundacionjala.pivotal.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.getTextFieldValue;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.setWebElement;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.getTextFieldColor;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.getWebElementAttribute;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.isElementPresent;

/**
 * CreateWorkspace is the class to create a new Workspace.
 */
public class CreateWorkspace extends BasePage {

    @FindBy(css = ".tc_form_label + input")
    private WebElement workspaceNameTextField;

    @FindBy(css = "[class $= 'submit']")
    private WebElement createWorkspaceBtn;

    @FindBy(css = "[class $= 'cancel']")
    private WebElement cancelCreateWorkspaceBtn;

    @FindBy(css = ".tc-form__input--error-message span")
    private WebElement messageWorkspaceNameEmpty;

    @FindBy(css = "div.tc_modal_content")
    private WebElement getCreateWorkspaceContainer;

    @FindBy(css = "form header h1")
    private WebElement createWorkspaceWindowsTitle;

    @FindBy(css = "label.tc_form_label")
    private WebElement workspaceNameLabel;

    /**
     * Method that clicks the button "Create Workspace" on the Form.
     *
     * @return the workspace instance
     */
    public Workspace clickCreateWorkspaceButton() {
        clickWebElement(createWorkspaceBtn);
        return new Workspace();
    }

    /**
     * Method that clicks the button "Cancel" on the Form.
     */
    public void clickCancelCreateWorkspaceButton() {
        clickWebElement(cancelCreateWorkspaceBtn);
    }

    /**
     * Method that inserts a name into Workspace Name field.
     *
     * @param workspaceName the given parameter to insert
     * @return this CreateWorkspace instance
     */
    public CreateWorkspace setWorkspaceName(String workspaceName) {
        setWebElement(workspaceNameTextField, workspaceName);
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
        return getTextFieldValue(messageWorkspaceNameEmpty);
    }

    /**
     * Method to get the text color of the Workspace name text field.
     * @return The color of the text field or null.
     */
    public String getWorkspaceNameTextFieldColor() {
        String color = getTextFieldColor(workspaceNameTextField);
        try {
            return CommonMethods.getColorInHex(color);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    /**
     * Method to get the create workspace windows title.
     * @return Windows title name.
     */
    public String getCreateWorkspaceWindowsTitle() {
        return getTextFieldValue(createWorkspaceWindowsTitle);
    }

    /**
     * Method to get the workspace name label.
     * @return The label name.
     */
    public String getWorkspaceNameLabel() {
        return getTextFieldValue(workspaceNameLabel);
    }

    /**
     * Method to get the workspace name place holder value.
     * @return Place holder value.
     */
    public String getWorkspaceNamePlaceholderAttribute() {
        return getWebElementAttribute(workspaceNameTextField, "Placeholder");
    }

    /**
     * Method to know if the cancel button is present or not.
     * @return True or False.
     */
    public boolean isCancelButtonPresent() {
        return isElementPresent(cancelCreateWorkspaceBtn);
    }

    /**
     * Method to know if the create button is present or not.
     * @return True or False.
     */
    public boolean isCreateButtonPresent() {
        return isElementPresent(createWorkspaceBtn);
    }
}
