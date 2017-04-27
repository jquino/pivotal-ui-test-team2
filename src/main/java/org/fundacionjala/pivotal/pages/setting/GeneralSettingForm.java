package org.fundacionjala.pivotal.pages.setting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fundacionjala.pivotal.framework.util.IAutomationStep;
import org.fundacionjala.pivotal.pages.accounts.Accounts;
import org.fundacionjala.pivotal.pages.BasePage;
import org.fundacionjala.pivotal.pages.project.DeleteProjectAlert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.convertASelect;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.selectAElementComboBox;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.setCheckBox;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.setWebElement;
import static org.fundacionjala.pivotal.framework.util.Constants.ATTRIBUTE_VALUE;
import static org.fundacionjala.pivotal.framework.util.Constants.REGEX_BRACKETS;
import static org.fundacionjala.pivotal.framework.util.Constants.REGEX_HALF_BRACKET;
import static org.fundacionjala.pivotal.framework.util.Constants.REGEX_INSIDE_BRACKETS;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.ALLOW_API_ACCESS;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.BUGS_GIVEN_POINTS;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.DATE_NAME;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.DESCRIPTION;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.ENABLE_INCOMING_EMAIL;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.ENABLE_RSS;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.ENABLE_TASKS;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.HIDE_EMAIL_ADDRESSES;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.INITIAL_VELOCITY;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.ITERATION_LENGTH;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.NUMBER_OF_DONE_ITERATION_SHOW;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.PLAN_CURRENT_ITERATION;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.POINT_SCALE;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.PROJECT_START_DATE;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.PROJECT_TIME_ZONE;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.PUBLIC_ACCESS;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.REQUIRE_HTTPS_FOR_API_ACCESS;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.START_ITERATIONS_ON;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.TITLE_PROJECTS;
import static org.fundacionjala.pivotal.pages.setting.SettingSteps.VELOCITY_STRATEGY;


/**
 * Class that represents the project settings form.
 */
public class GeneralSettingForm extends BasePage {

    private static final Logger LOGGER = Logger.getLogger(GeneralSettingForm.class.getName());

    @FindBy(id = "project_name")
    private WebElement projectTitleTestField;

    @FindBy(id = "project_description")
    private WebElement projectDescriptionTestField;

    @FindBy(id = "project_enable_tasks")
    private WebElement projectEnableTasksCheckbox;

    @FindBy(id = "project_api_access")
    private WebElement projectAPIAccessCheckbox;

    @FindBy(id = "project_use_https")
    private WebElement projectUseHttpsCheckBox;

    @FindBy(id = "project_atom_enabled")
    private WebElement projectAtomRssCheckBox;

    @FindBy(id = "project_public")
    private WebElement projectPublicAccessCheckBox;

    @FindBy(id = "project_enable_incoming_emails")
    private WebElement projectEnableIncomingEmailCheckBox;

    @FindBy(id = "project_hide_emails_from_collaborators")
    private WebElement projectHideEmailsFromCollaboratorsCheckBox;

    @FindBy(id = "project_bugs_and_chores_are_estimatable")
    private WebElement projectBugsCheckBox;

    @FindBy(id = "project_week_start_day")
    private WebElement projectWeekStartDaySelect;

    @FindBy(id = "date_project[start_date]")
    private WebElement dateProjectStartTestField;

    @FindBy(id = "project_time_zone")
    private WebElement projectTimeZoneComboBox;

    @FindBy(id = "project_iteration_length")
    private WebElement projectIterationLengthComboBox;

    @FindBy(css = ".project_settings_field.point_scale")
    private WebElement projectSettingsPointScaleComboBox;

    @FindBy(id = "project_initial_velocity")
    private WebElement projectInitialVelocityTestField;

    @FindBy(id = "project_velocity_scheme")
    private WebElement projectVelocityComboBox;

    @FindBy(id = "project_number_of_done_iterations_to_show")
    private WebElement projectNumberOfDoneIterationsToShowTestField;

    @FindBy(id = "project_automatic_planning")
    private WebElement projectAutomaticPlanningCheckBox;

    @FindBy(className = "save_bar__submit")
    private WebElement saveButton;

    @FindBy(id = "delete_link")
    private WebElement deleteLink;

    @FindBy(id = "project_account_link")
    private WebElement accountLink;

    @FindBy(css = ".message")
    private WebElement testMessage;

    @FindBy(className = "text_column")
    private WebElement projectId;

    @FindBy(className = "error_above_or_below")
    private WebElement massageErrorNameDayText;

    private boolean flat;

    private static final String REGEX_BLACK_SPACE = " ";

    /**
     * Method that maps all the constants into the proper values
     * listed.
     *
     * @param values values on the map
     * @return the strategy mapped
     */
    public Map<SettingSteps, IAutomationStep> getStrategyStepMap(Map<SettingSteps, Object> values) {
        Map<SettingSteps, IAutomationStep> strategyMap = new EnumMap<>(SettingSteps.class);
        strategyMap.put(SettingSteps.TITLE_PROJECTS, () -> setProjectTitleTestField(values.get(TITLE_PROJECTS)
                .toString()));
        strategyMap.put(DESCRIPTION, () -> setProjectDescriptionTestField(values.get(DESCRIPTION)
                .toString()));
        strategyMap.put(START_ITERATIONS_ON, () -> setProjectWeekStartDayComboBox(String.valueOf(values
                .get(START_ITERATIONS_ON).toString())));
        strategyMap.put(PROJECT_TIME_ZONE, () -> setProjectTimeZoneComboBox(values.get(PROJECT_TIME_ZONE)
                .toString()));
        strategyMap.put(ITERATION_LENGTH, () -> setProjectIterationLengthComboBox(values.get(ITERATION_LENGTH)
                .toString()));
        strategyMap.put(POINT_SCALE, () -> setProjectSettingsPointScaleComboBox(values.get(POINT_SCALE)
                .toString()));
        strategyMap.put(INITIAL_VELOCITY, () -> setProjectInitialVelocityTestField(values.get(INITIAL_VELOCITY)
                .toString()));
        strategyMap.put(VELOCITY_STRATEGY, () -> setProjectVelocityComboBox(values.get(VELOCITY_STRATEGY)
                .toString()));
        strategyMap.put(NUMBER_OF_DONE_ITERATION_SHOW, () -> setProjectNumOfDoneIterationsToShowTestField(values
                .get(NUMBER_OF_DONE_ITERATION_SHOW).toString()));
        strategyMap.put(PLAN_CURRENT_ITERATION, () -> setProjectAutomaticPlanningCheckBox(Boolean.parseBoolean(values
                .get(PLAN_CURRENT_ITERATION).toString())));
        strategyMap.put(ENABLE_TASKS, () -> setProjectEnableTasksCheckbox(Boolean.parseBoolean(values.get(ENABLE_TASKS)
                .toString())));
        strategyMap.put(ALLOW_API_ACCESS, () -> setProjectAPIAccessCheckbox(Boolean.parseBoolean(values
                .get(ALLOW_API_ACCESS).toString())));
        strategyMap.put(REQUIRE_HTTPS_FOR_API_ACCESS, () -> setProjectUseHttpsCheckBox(Boolean.parseBoolean(values
                .get(REQUIRE_HTTPS_FOR_API_ACCESS).toString())));
        strategyMap.put(ENABLE_RSS, () -> setProjectAtomRssCheckBox(Boolean.parseBoolean(values.get(ENABLE_RSS)
                .toString())));
        strategyMap.put(PUBLIC_ACCESS, () -> setProjectPublicAccessCheckBox(Boolean.parseBoolean(values
                .get(PUBLIC_ACCESS).toString())));
        strategyMap.put(ENABLE_INCOMING_EMAIL, () -> setProjectEnableIncomingEmailCheckBox(Boolean.parseBoolean(values
                .get(ENABLE_INCOMING_EMAIL).toString())));
        strategyMap.put(HIDE_EMAIL_ADDRESSES, () -> setProjectHideEmailsFromCollaboratorsCheckBox(Boolean
                .parseBoolean(values.get(HIDE_EMAIL_ADDRESSES).toString())));
        strategyMap.put(BUGS_GIVEN_POINTS, () -> setProjectBugsCheckBox(Boolean.parseBoolean(values
                .get(BUGS_GIVEN_POINTS).toString())));
        strategyMap.put(PROJECT_START_DATE, () -> setDateProjectStartTestField(String.valueOf(values
                .get(PROJECT_START_DATE).toString())));
        return strategyMap;
    }

    /**
     * Method to fill the title text field of the instance.
     *
     * @param projectTitle the value to fill on project title
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectTitleTestField(String projectTitle) {
        setWebElement(projectTitleTestField, projectTitle);
        return this;
    }

    /**
     * Method to fill the description text field of the instance.
     *
     * @param projectDescription the value to fill on project description
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectDescriptionTestField(String projectDescription) {
        setWebElement(projectDescriptionTestField, projectDescription);
        return this;
    }

    /**
     * Method to Configure the enable task checkbox.
     *
     * @param enable the value to check or uncheck the option
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectEnableTasksCheckbox(boolean enable) {
        setCheckBox(projectEnableTasksCheckbox, enable);
        return this;
    }

    /**
     * Method to configure the errk start day combo box.
     *
     * @param nameDay the value set of the day
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectWeekStartDayComboBox(String nameDay) {
        selectAElementComboBox(projectWeekStartDaySelect, nameDay);
        return this;
    }

    /**
     * Method to fill the project start date field.
     *
     * @param dateProjectStart value selected as project start date
     * @return this same instance of the class
     */
    public GeneralSettingForm setDateProjectStartTestField(String dateProjectStart) {
        setWebElement(dateProjectStartTestField, dateProjectStart);
        return this;
    }

    /**
     * Method that sets the option from the combo box.
     *
     * @param projectTimeZone the value to select on the combo box
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectTimeZoneComboBox(String projectTimeZone) {
        selectAElementComboBox(projectTimeZoneComboBox, projectTimeZone);
        return this;
    }

    /**
     * Method that sets the iteration length, maybe we should convert the number to string.
     *
     * @param projectIterationLength the value to select from the combo box
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectIterationLengthComboBox(String projectIterationLength) {
        selectAElementComboBox(projectIterationLengthComboBox, projectIterationLength);
        return this;
    }

    /**
     * Method that sets the point scale.
     *
     * @param projectSettingsPointScale the value to select from the combo box
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectSettingsPointScaleComboBox(String projectSettingsPointScale) {
        selectAElementComboBox(projectSettingsPointScaleComboBox, projectSettingsPointScale);
        flat = true;
        return this;
    }

    /**
     * Method that sets the project initial velocity, we need to enter it as string.
     *
     * @param projectInitialVelocity the value to enter to the field
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectInitialVelocityTestField(String projectInitialVelocity) {
        setWebElement(projectInitialVelocityTestField, projectInitialVelocity);
        return this;
    }

    /**
     * Method that sets the project velocity strategy.
     *
     * @param projectVelocity value that will be selected on the combo box
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectVelocityComboBox(String projectVelocity) {
        selectAElementComboBox(projectVelocityComboBox, projectVelocity);
        return this;
    }

    /**
     * Method that sets the number of done iterations to show,
     * maybe it should be converted to string from int.
     *
     * @param projectNumberOfDoneIterationsToShow the value to set the done
     *                                            iterations to show
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectNumOfDoneIterationsToShowTestField(String projectNumberOfDoneIterationsToShow) {
        setWebElement(projectNumberOfDoneIterationsToShowTestField, projectNumberOfDoneIterationsToShow);
        return this;
    }

    /**
     * Methods that checks or unchecks the checkbox depending
     * the parameter given.
     *
     * @param projectAutomaticPlanning the value to decide if the checkbox
     *                                 is checked or not
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectAutomaticPlanningCheckBox(boolean projectAutomaticPlanning) {
        setCheckBox(projectAutomaticPlanningCheckBox, projectAutomaticPlanning);
        return this;
    }

    /**
     * Method that clicks "Save" button after all configurations.
     *
     * @return this same instance of the class
     */
    public GeneralSettingForm clickSaveButton() {
        clickWebElement(saveButton);
        if (flat) {
            wait.until(ExpectedConditions.alertIsPresent()).accept();
            flat = false;
        }

        return this;
    }

    /**
     * Method that checks or unchecks the checkbox depending
     * the parameter given.
     *
     * @param enable the value to decide if the checkbox
     *               is checked or not
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectAPIAccessCheckbox(boolean enable) {
        setCheckBox(projectAPIAccessCheckbox, enable);
        return this;
    }

    /**
     * Method that checks or unchecks the checkbox depending
     * the parameter given.
     *
     * @param enable the value to decide if the checkbox
     *               is checked or not
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectUseHttpsCheckBox(boolean enable) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,500)");
        action.keyDown(Keys.DOWN).perform();
        setCheckBox(projectUseHttpsCheckBox, enable);
        return this;
    }

    /**
     * Method that checks or unchecks the checkbox depending
     * the parameter given.
     *
     * @param enable  the value to decide if the checkbox
     *               is checked or not
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectAtomRssCheckBox(boolean enable) {
        setCheckBox(projectAtomRssCheckBox, enable);
        return this;
    }

    /**
     * Method that checks or unchecks the checkbox depending
     * the parameter given.
     *
     * @param enable  the value to decide if the checkbox
     *               is checked or not
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectPublicAccessCheckBox(boolean enable) {
        setCheckBox(projectPublicAccessCheckBox, enable);
        return this;
    }

    /**
     * Method that checks or unchecks the checkbox depending
     * the parameter given.
     *
     * @param enable  the value to decide if the checkbox
     *               is checked or not
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectEnableIncomingEmailCheckBox(boolean enable) {
        setCheckBox(projectEnableIncomingEmailCheckBox, enable);
        return this;
    }

    /**
     * Method that checks or unchecks the checkbox depending
     * the parameter given.
     *
     * @param enable  the value to decide if the checkbox
     *               is checked or not
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectHideEmailsFromCollaboratorsCheckBox(boolean enable) {
        setCheckBox(projectHideEmailsFromCollaboratorsCheckBox, enable);
        return this;
    }

    /**
     * Method that checks or unchecks the checkbox depending
     * the parameter given.
     *
     * @param enable  the value to decide if the checkbox
     *               is checked or not
     * @return this same instance of the class
     */
    public GeneralSettingForm setProjectBugsCheckBox(boolean enable) {
        setCheckBox(projectBugsCheckBox, enable);
        return this;
    }

    /**
     * Method that clicks the case of delete project confirmation.
     *
     * @return the instance of the browser popup
     */
    public DeleteProjectAlert clickLinkDeleteProject() {
        clickWebElement(deleteLink);
        return new DeleteProjectAlert();
    }

    /**
     * Method that gets the message text.
     *
     * @return the message text
     */
    public String getMessageTest() {
        return testMessage.getText();
    }

    /**
     * Method that gets the text error message for day name.
     *
     * @return the error message text
     */
    public String getMessageErrorNameDayText() {
        return massageErrorNameDayText.getText();
    }

    /**
     * Method that gets the text of the description.
     *
     * @return the attribute value
     */
    public String getDescriptionText() {
        return projectDescriptionTestField.getAttribute(ATTRIBUTE_VALUE);
    }

    /**
     * Method that returns the project id as text.
     *
     * @return the value converted to text
     */
    public String getProjectId() {
        return projectId.getText();
    }

    /**
     * Method that returns the project title text.
     *
     * @return the value for the project title
     */
    public String getProjectTitleTestField() {
        return projectTitleTestField.getAttribute(ATTRIBUTE_VALUE);
    }

    /**
     * Method that returns the value of iterations to show.
     *
     * @return the value required
     */
    public String getNumberIterationShow() {
        return projectNumberOfDoneIterationsToShowTestField.getAttribute(ATTRIBUTE_VALUE);
    }

    /**
     * Method that returns the value for the initial velocity.
     *
     * @return the attribute as text
     */
    public String getInitialVelocity() {
        return projectInitialVelocityTestField.getAttribute(ATTRIBUTE_VALUE);
    }

    /**
     * Method that returns the value of the project week start day.
     *
     * @return the attribute as text
     */
    public String getTextProjectWeekStartDaySelect() {
        return convertASelect(projectWeekStartDaySelect).getFirstSelectedOption().getAttribute(ATTRIBUTE_VALUE);
    }

    /**
     * Method that returns the value of the project date start.
     *
     * @return the attribute as text
     */
    public String getTextDateProjectStart() {
        return dateProjectStartTestField.getAttribute(ATTRIBUTE_VALUE);
    }

    /**
     * Method that returns the value of the projec time zone as text.
     *
     * @return the attribute as text
     */
    public String getTextProjectTimeZone() {
        return convertASelect(projectTimeZoneComboBox).getFirstSelectedOption().getAttribute(ATTRIBUTE_VALUE);
    }

    /**
     * Method that clicks the Account link.
     *
     * @return the Accounts instance page
     */
    public Accounts clickAccountLink() {
        accountLink.click();
        return new Accounts();
    }

    /**
     * Method that returns the project iteration length.
     *
     * @return the attribute as text
     */
    public String getTextProjectIterationLength() {
        return convertASelect(projectIterationLengthComboBox).getFirstSelectedOption().getAttribute(ATTRIBUTE_VALUE);
    }

    /**
     * Method that returns the project point scale.
     *
     * @return the attribute as text
     */
    public String getTextProjectSettingPointScale() {
        return convertASelect(projectSettingsPointScaleComboBox).getFirstSelectedOption().getAttribute(ATTRIBUTE_VALUE);
    }

    /**
     * Method that returns the project velocity.
     *
     * @return the attribute as text
     */
    public String getTextProjectVelocity() {
        return convertASelect(projectVelocityComboBox).getFirstSelectedOption().getAttribute(ATTRIBUTE_VALUE);
    }

    /**
     * Method that verifies the project automatic planning checkbox state.
     *
     * @return the value as boolean
     */
    public boolean getTextProjectAutomaticPlanning() {
        return projectAutomaticPlanningCheckBox.isSelected();
    }

    /**
     * Method that verifies the get allow API access checkbox state.
     * @return the value as boolean
     */
    public boolean getAllowAPIACCES() {
        return projectAPIAccessCheckbox.isSelected();
    }

    /**
     * Method that verifies the use of HTTPS checkbox state.
     *
     * @return the value as boolean
     */
    public boolean getUseHttps() {
        return projectUseHttpsCheckBox.isSelected();
    }

    /**
     * Method that verifies the use of RSS or ATom feeds checkbox state.
     *
     * @return the value as boolean
     */
    public boolean getAtomRss() {
        return projectAtomRssCheckBox.isSelected();
    }

    /**
     * Method that verifies the use of the public access checkbox state.
     *
     * @return the value as boolean
     */
    public boolean getPublicAccess() {
        return projectPublicAccessCheckBox.isSelected();
    }

    /**
     * Method that verifies the incoming email checkbox state.
     *
     * @return the value as boolean
     */
    public boolean getEnableIncomingEmailCheckBox() {
        return projectEnableIncomingEmailCheckBox.isSelected();
    }

    /**
     * Method that verifies the hide emails from collaborators
     * checkbox state.
     *
     * @return the value as boolean
     */
    public boolean getHideEmailsFromCollaboratorsCheckBox() {
        return projectHideEmailsFromCollaboratorsCheckBox.isSelected();
    }

    /**
     * Method that verifies the given points checkbox state.
     *
     * @return the value as boolean
     */
    public boolean getBugGivenPointsCheckBox() {
        return projectBugsCheckBox.isSelected();
    }

    /**
     * Method that verifies the enable project tasks checkbox state.
     *
     * @return the value as boolean
     */
    public boolean getEnableProjectsTasks() {
        return projectEnableTasksCheckbox.isSelected();
    }

    /**
     * Method that maps the parameters to the Map.
     *
     * @return the assertion mapped
     */
    public Map<SettingSteps, Object> getAssertionMap() {
        Map<SettingSteps, Object> assertionMap = new EnumMap<>(SettingSteps.class);
        assertionMap.put(TITLE_PROJECTS, getProjectTitleTestField());
        assertionMap.put(DESCRIPTION, getDescriptionText());
        assertionMap.put(ENABLE_TASKS, getEnableProjectsTasks());
        assertionMap.put(START_ITERATIONS_ON, getTextProjectWeekStartDaySelect());
        assertionMap.put(PROJECT_START_DATE, getTextDateProjectStart());
        assertionMap.put(PROJECT_TIME_ZONE, getTextProjectTimeZone());
        assertionMap.put(ITERATION_LENGTH, getTextProjectIterationLength());
        assertionMap.put(POINT_SCALE, getTextProjectSettingPointScale());
        assertionMap.put(INITIAL_VELOCITY, getInitialVelocity());
        assertionMap.put(VELOCITY_STRATEGY, getTextProjectVelocity());
        assertionMap.put(NUMBER_OF_DONE_ITERATION_SHOW, getNumberIterationShow());
        assertionMap.put(PLAN_CURRENT_ITERATION, getTextProjectAutomaticPlanning());
        assertionMap.put(ALLOW_API_ACCESS, getAllowAPIACCES());
        assertionMap.put(REQUIRE_HTTPS_FOR_API_ACCESS, getUseHttps());
        assertionMap.put(ENABLE_RSS, getAtomRss());
        assertionMap.put(PUBLIC_ACCESS, getPublicAccess());
        assertionMap.put(ENABLE_INCOMING_EMAIL, getEnableIncomingEmailCheckBox());
        assertionMap.put(HIDE_EMAIL_ADDRESSES, getHideEmailsFromCollaboratorsCheckBox());
        assertionMap.put(BUGS_GIVEN_POINTS, getBugGivenPointsCheckBox());
        return assertionMap;
    }

    /**
     * Method that returns the message given the endpoint
     * and the mapping values.
     *
     * @param endPoint the endpoint that has the key and values
     * @param values the values from the map
     * @return the string for the message
     */
    public String getMassage(String endPoint, Map<SettingSteps, Object> values) {
        String nameDay = dayName(String.valueOf(values.get(PROJECT_START_DATE)));
        Map<Enum, Object> copy = new HashMap<>(values);
        copy.put(DATE_NAME, nameDay);
        final String point = "s.";
        final int index = 1;
        String result = endPoint;
        if (result.contains(REGEX_HALF_BRACKET)) {
            for (String endPointSplit : result.split(REGEX_BLACK_SPACE)) {
                if (endPointSplit.matches(REGEX_INSIDE_BRACKETS)) {
                    String[] mapString = endPointSplit.split(REGEX_BRACKETS);
                    StringBuilder value = new StringBuilder();
                    value.append(copy.get(SettingSteps.valueOf(mapString[index])));
                    result = result.replace(endPointSplit, value);
                }
            }
        }
        return result.concat(point);
    }

    /**
     * Method that returns the day name given the input date
     * registered.
     *
     * @param inputDate the parameter to insert
     * @return the date formatted as string
     */
    private String dayName(String inputDate) {
        Date date = null;
        final String format = "YYYY/MM/DD";
        final String standard = "EEEE";
        try {
            date = new SimpleDateFormat(format).parse(inputDate);
        } catch (ParseException e) {
            LOGGER.warn("Convert String format the date", e);
        }
        return new SimpleDateFormat(standard, Locale.ENGLISH).format(date);
    }
}
