package org.fundacionjala.pivotal.pages.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fundacionjala.pivotal.framework.util.IAutomationStep;
import org.fundacionjala.pivotal.pages.BasePage;
import org.fundacionjala.pivotal.pages.project.Project;
import org.fundacionjala.pivotal.pages.project.ProjectSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.setCheckBox;

/**
 * Class that represents the options inside a create project page.
 */
public class CreateProject extends BasePage {

    private static final Logger LOGGER = Logger.getLogger(CreateProject.class.getName());

    private String accountMessage;

    private String projectTitleMessage;

    @FindBy(className = "tc-project-name__input")
    private WebElement newProjectName;

    @FindBy(className = "tc-account-creator__name")
    private WebElement createNewAccountTxt;

    @FindBy(className = "tc-account-selector__header")
    private WebElement accountDropDown;

    @FindBy(css = ".tc-create-project-footer__button.tc-create-project-footer__button--submit")
    private WebElement createNewProjectBtn;

    @FindBy(css = "input[type='radio'][value='public']")
    private WebElement projectPublicRadioBtn;

    @FindBy(css = "span[contains(.,'Please select or create an account for the new project')]")
    private WebElement accountErrorMessage;

    @FindBy(css = ".tc-project-name__info > span")
    private WebElement blankProjectNameMessage;

    @FindBy(css = ".tc-create-project-footer__button.tc-create-project-footer__button--cancel")
    private WebElement cancelCreateProjectBtn;

    @FindBy(css = "input[type='radio'][value='private']")
    private WebElement projectPrivateRadioBtn;

    @FindBy(className = "tc-account-selector__create-account-icon")
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
            driver.findElement(By.xpath("//span[text()='" + accountName + "']")).click();
        }
        return this;
    }

    /**
     * Method that enables to click the data sample checkbox.
     *
     * @param isCheckBoxEnable boolean value to verify the checkbox
     * @return this same instance with the parameter updated
     */
    public CreateProject clickDataSampleCheckBox(String isCheckBoxEnable) {
        setCheckBox(projectPrivateRadioBtn, Boolean.parseBoolean(isCheckBoxEnable));
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
            answer = driver.findElement(By.xpath("//span[text()='" + accountName + "']")).isDisplayed();
        } catch (NoSuchElementException e) {
            LOGGER.warn("Element could not be found", e);
            answer = false;
        }
        LOGGER.info("AccountName is" + answer);
        return answer;
    }

    /**
     * Method that verifies that the radio button is visible.
     */
    public void checkProjectVisible() {
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
     * @return the boleean value for the verification
     */
    public boolean createProjectFormIsdisplayed() {
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
     * @param isCheckBoxEnable the boleean value that let the
     *                         checkbox knows if it is enabled
     */
    public void clickMakeProjectVisibleCheckBox(String isCheckBoxEnable) {
        setCheckBox(projectPublicRadioBtn, Boolean.parseBoolean(isCheckBoxEnable));
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
}
