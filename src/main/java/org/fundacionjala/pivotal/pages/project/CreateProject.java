package org.fundacionjala.pivotal.pages.project;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fundacionjala.pivotal.framework.util.IAutomationStep;
import org.fundacionjala.pivotal.pages.BasePage;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.getWebElementTextFieldColor;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.setCheckBox;

/**
 * Class that represents the options inside a create project page.
 */
public class CreateProject extends BasePage {

    private static final Logger LOGGER = Logger.getLogger(CreateProject.class.getName());

    private String accountMessage;

    private String projectTitleMessage;

    @FindBy(className = ".tc-project-name > label > input")
    private WebElement newProjectName;

    @FindBy(className = "[class=tc-account-creator__name]")
    private WebElement createNewAccountTxt;

    @FindBy(className = "[class=tc-account-selector__header]")
    private WebElement accountDropDown;

    @FindBy(css = ".tc-form-modal-footer__button.tc-form-modal-footer__button--submit")
    private WebElement createNewProjectBtn;

    @FindBy(css = "input[type='radio'][value='public']")
    private WebElement projectPublicRadioBtn;

    @FindBy(css = ".tc-form__input--error-message > span")
    private WebElement accountErrorMessage;

    @FindBy(css = ".tc-project-name > label > div > span")
    private WebElement blankProjectNameMessage;

    @FindBy(css = ".tc-form-modal-footer__button.tc-form-modal-footer__button--cancel")
    private WebElement cancelCreateProjectBtn;

    @FindBy(css = "input[type='radio'][value='private']")
    private WebElement projectPrivateRadioBtn;

    @FindBy(className = "[class=tc-account-selector__create-account-icon]")
    private WebElement createAccountOption;

    /**
     * Method that puts the project name to the new project
     * to be created.
     *
     * @param projectName the new project name
     * @return This same instance with the parameter updated
     */
    public CreateProject setProjectName(String projectName) {
        newProjectName.sendKeys(projectName);
        return this;
    }

    /**
     * Method that enables the creation of an account name.
     *
     * @param accountName the account name to be verified
     * @return this same instance with the parameter updated
     */
    public CreateProject setAccountDropDown(String accountName) {
        accountDropDown.click();
        if (!isAccountNamePresent(accountName)) {
            createAccount(accountName);
        } else {
            driver.findElement(By.xpath("//div[text()='" + accountName + "']")).click();
        }
        return this;
    }

    /**
     * Method that enables to click the data sample checkbox.
     *
     * @param isCheckBoxEnabled boolean value to verify the checkbox
     * @return this same instance with the parameter updated
     */
    public CreateProject clickDataSampleCheckBox(String isCheckBoxEnabled) {
        setCheckBox(projectPrivateRadioBtn, Boolean.parseBoolean(isCheckBoxEnabled));
        return this;
    }

    /**
     * Method that creates a new account.
     * @param accountName the new account name
     * @return this same instance with the parameter updated
     */
    private CreateProject createAccount(String accountName) {
        LOGGER.info("creating account");
        createAccountOption.click();
        createNewAccountTxt.sendKeys(accountName);
        return this;
    }

    /**
     * Method to verify that the account name exists.
     *
     * @param accountName the parameter to check if exists
     * @return the boleean value of this verification
     */
    private boolean isAccountNamePresent(String accountName) {
        boolean answer;
        try {
            answer = driver.findElement(By.xpath("//div[text()='" + accountName + "']")).isDisplayed();
        } catch (NoSuchElementException e) {
            LOGGER.warn("Element could not be found", e);
            answer = false;
        }
        LOGGER.info("AccountName is" + answer);
        return answer;
    }

    /**
     * Method that clicks the radio button "Public"
     * inside Project privacy options.
     */
    public void clickPublicProjectPrivacy() {
        projectPublicRadioBtn.click();
    }

    /**
     * Method that completes the create project process sending the
     * parameters and then displaying the Project instance.
     *
     * @return the project instance created
     */
    public Project clickCreateProject() {
        try {
            clickWebElement(createNewProjectBtn);
            verifyErrorMessagesOfCreateProject();

        } catch (NullPointerException e) {
            LOGGER.error("null pointer", e);

        } catch (InvalidSelectorException e) {
            LOGGER.info("invalid web element", e);

        } catch (NoSuchElementException e) {
            LOGGER.warn("invalid no such element", e);
        }
        return new Project();

    }

    /**
     * Method that verifies the project title message is
     * free of error messages.
     */
    private void verifyErrorMessagesOfCreateProject() {
        if (blankProjectNameMessage.isDisplayed()) {
            LOGGER.info("title message: " + blankProjectNameMessage.getText());
            projectTitleMessage = blankProjectNameMessage.getText();

        } else if (accountErrorMessage.isDisplayed()) {
            LOGGER.info("account message: " + accountErrorMessage.getText());
            accountMessage = accountErrorMessage.getText();
        }
    }

    /**
     * Method that verifies if the project form is displayed.
     *
     * @return the boolean value for the verification
     */
    public boolean createProjectFormIsDisplayed() {
        return newProjectName.isDisplayed() && accountDropDown.isDisplayed() && createNewProjectBtn.isDisplayed();
    }

    /**
     * Method that returns as to the dashboard instance page in the
     * case we cancel the new project creation.
     *
     * @return the Dashboard page instance
     */
    public Dashboard clickCancelCreateProjectBtn() {
        clickWebElement(cancelCreateProjectBtn);
        return new Dashboard();
    }

    /**
     * Method that allows to the project to be visible clicking
     * the checkbox and verifying if it is enabled.
     *
     * @param isCheckBoxEnabled the boolean value that let the
     *                         checkbox knows if it is enabled
     */
    public void clickMakeProjectVisibleCheckBox(String isCheckBoxEnabled) {
        setCheckBox(projectPublicRadioBtn, Boolean.parseBoolean(isCheckBoxEnabled));
    }

    /**
     * Method that defines the strategy given the step values.
     *
     * @param values enum values mapped by steps and objects
     * @return the strategy Map instance
     */
    public Map<Enum, IAutomationStep> getStrategyStepMap(Map<ProjectSteps, Object> values) {
        final Map<Enum, IAutomationStep> strategyMap = new HashMap<>();

        strategyMap.put(ProjectSteps
                   .PROJECT_TITLE, () -> setProjectName(String
                   .valueOf(values.get(ProjectSteps.PROJECT_TITLE))));
        strategyMap.put(ProjectSteps
                   .PROJECT_ACCOUNT, () -> setAccountDropDown(String
                   .valueOf(values.get(ProjectSteps.PROJECT_ACCOUNT))));
        strategyMap.put(ProjectSteps
                   .PROJECT_VISIBLE, () -> clickMakeProjectVisibleCheckBox(String
                   .valueOf(values.get(ProjectSteps.PROJECT_VISIBLE))));
        strategyMap.put(ProjectSteps
                   .PROJECT_SAMPLE_DATA, () -> clickDataSampleCheckBox(String
                   .valueOf(values.get(ProjectSteps.PROJECT_SAMPLE_DATA))));
        return strategyMap;
    }

    /**
     * Method that returns the account message.
     *
     * @return the account message
     */
    public String getAccountMessage() {
        return accountMessage;
    }

    /**
     * Method that retrieves the project title message.
     *
     * @return the project title message
     */
    public String getProjectTitleMessage() {
        return projectTitleMessage;
    }

    /**
     * Method to get the text color of the Project name text field.
     *
     * @return The color of the text field or null.
     */
    public String getProjectNameTextFieldColor() {
        return getWebElementTextFieldColor(newProjectName);
    }

    /**
     * Method to get the text color of the Account text field.
     *
     * @return The color of the text field or null.
     */
    public String getAccountTextFieldColor() {
        return getWebElementTextFieldColor(accountDropDown);
    }
}
