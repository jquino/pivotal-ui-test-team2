package org.fundacionjala.pivotal.pages.stories;

import java.util.EnumMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fundacionjala.pivotal.framework.util.CommonMethods;
import org.fundacionjala.pivotal.framework.util.IAutomationStep;
import org.fundacionjala.pivotal.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.fundacionjala.pivotal.framework.util.Constants.IMPLICIT_FAIL_WAIT_TIME;
import static org.fundacionjala.pivotal.framework.util.Constants.IMPLICIT_WAIT_TIME;
import static org.fundacionjala.pivotal.framework.util.Constants.WAIT_TIME;
import static org.fundacionjala.pivotal.pages.stories.StoriesSteps.COMMENT;
import static org.fundacionjala.pivotal.pages.stories.StoriesSteps.DESCRIPTION;
import static org.fundacionjala.pivotal.pages.stories.StoriesSteps.LABELS;
import static org.fundacionjala.pivotal.pages.stories.StoriesSteps.STORY_TITLE;
import static org.fundacionjala.pivotal.pages.stories.StoriesSteps.STORY_TYPE;


/**
 * This class is for test the creation, set and delete
 * of a story in a project of pivotal tracker.
 */
public class Story extends BasePage {

    private static final Logger LOGGER = Logger.getLogger(Story.class.getName());
    private static final String TASK_LOCATOR = "//div[.= '%s']";
    private static final String TASK_EDIT_LOCATOR = "//textarea[.= '%s']";
    private static final String SAVE_TASK = "button[data-aid='saveTaskButton']";
    private static final String DELETE_TASK_BUTTON = "span[data-click-aid='delete']";

    /**
     * Web elements to add story.
     */
    @FindBy(name = "story[name]")
    private WebElement storyTitleTextArea;

    @FindBy(css = ".dropdown.story_type")
    private WebElement storyTypeArrow;

    @FindBy(xpath = "//button[contains(.,'Save')]")
    private WebElement saveStoryButton;

    @FindBy(xpath = "//button[contains(.,'Cancel')]")
    private WebElement cancelCreateStoryButton;

    @FindBy(xpath = "//button[contains(.,'(edit)')]")
    private WebElement editDescriptionButton;

    @FindBy(css = ".rendered_description.tracker_markup")
    private WebElement descriptionText;

    @FindBy(css = "textarea[data-aid='textarea']") //old: name = story[pending_description]")
    private WebElement storyDescriptionTextField;

    @FindBy(css = "button[data-aid='save']") //old: xpath = //button[contains(.,'Done')]
    private WebElement doneDescriptionButton;

    @FindBy(name = "label[name]")
    private WebElement label;

    @FindBy(css = ".autosaves.label.name")
    private WebElement labelName;

    @FindBy(name = "comment[text]")
    private WebElement comment;

    @FindBy(css = "button[data-aid='comment-submit']")
    private WebElement addCommentButton;

    @FindBy(css = "button[data-aid='ConfirmationDialog__confirm']")
    private WebElement confirmCancelButton;
    /**
     * Web elements to add an empty story.
     */
    @FindBy(className = "AlertDialog__message___873RxAXD")
    private WebElement addStoryTitleAlert;

    @FindBy(css = "button[data-aid='AlertDialog__confirm']")
    private WebElement okAlertButton;
    /**
     * Web elements to set story.
     */
    @FindBy(xpath = "//button[contains(.,'Close')]")
    private WebElement closeButton;
    /**
     * Web elements to delete story.
     */
    @FindBy(css = ".expander.undraggable")
    private WebElement storyExpander;

    @FindBy(xpath = "//button[@title='Delete this story']")
    private WebElement deleteStoryButton;

    @FindBy(xpath = "//button[@data-aid='DeleteButton']")
    private WebElement confirmDeleteButton;

    @FindBy(xpath = "//li[contains(.,'1 story deleted')]")
    private WebElement storyDeletedMessage;

    @FindBy(xpath = "//button[@data-aid='CancelButton']")
    private WebElement cancelDeleteButton;

    @FindBy(css = "textarea[placeholder='Add a task']")
    private WebElement newTaskDescriptionTextArea;

    @FindBy(css = "button[data-aid='addTaskButton']")
    private WebElement addTaskButton;

    private String commentMesage;

    private String storyTypeName;

    private static final int TIMEOUT = 45;

    /**
     * LA added, "position" on the desired story.
     * @param storyTitle title of the story
     */
    public void clickOnExpanderStoryLA(String storyTitle) {
        String string;
        string = "//a[@title='" + storyTitle + "']/following-sibling::a[@class='expander undraggable']";
        driver.findElement(By.xpath(string)).click();
    }

    /**
     * LA does the story exist?
     * @param storyTitle title of the story
     * @return boolean value on the existence of a story name
     */
    public boolean existsStoryTitle(String storyTitle) {
        return !driver.findElements(By.cssSelector("a[title='" + storyTitle + "']")).isEmpty();
    }

    /**
     *
     * @return same Story object
     */
    public Story clickOnCancelDeleteButton() {
        cancelDeleteButton.click();
        return this;
    }

    /**
     * Method that clicks the save story button.
     *
     * @return the same instance of this class
     */
    public Story clickOnSaveStoryButton() {
        saveStoryButton.click();
        return this;
    }

    /**
     * Method that clicks the close story button.
     *
     * @return the same instance of this class
     */
    public Story clickOnCloseStoryButton() {
        closeButton.click();
        return this;
    }

    /**
     * Method that clicks the expander of a story.
     *
     */
    public void clickOnExpanderStory() {
        try {
            driver.manage().timeouts().implicitlyWait(IMPLICIT_FAIL_WAIT_TIME, SECONDS);
            storyExpander.click();
        } catch (NoSuchElementException e) {
            LOGGER.warn("The Web element click expander Story not was find ", e);
            throw new NoSuchElementException("The Web element not was find ", e);
        } finally {
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, SECONDS);
        }
    }

    /**
     * Method that clicks the delete story button.
     */
    public void clickOnDeleteStoryButton() {
        deleteStoryButton.click();
    }

    /**
     * Method that clicks the confirm delete button.
     */
    public void clickOnConfirmDeleteStoryButton() {
        confirmDeleteButton.click();
    }

    /**
     * Method that cancels the add story process and return
     * the icebox instance.
     *
     * @return the Icebox instance
     */
    public IceBox cancelAddStory() {
        cancelCreateStoryButton.click();
        confirmCancelButton.click();
        return new IceBox();
    }

    /**
     * Method that retrieves the title of the add story
     * browser popup.
     *
     * @return the browser popup alert
     */
    public String getAddStoryTitleAlert() {
        String alert = addStoryTitleAlert.getText();
        okAlertButton.click();
        LOGGER.info("Name the alert " + alert);
        return alert;
    }

    /**
     * Method that returns the delete message text.
     *
     * @return the message as String
     */
    public String getStoryDeletedMessage() {
        return storyDeletedMessage.getText();
    }

    /**
     * Method that retrieves the story title.
     *
     * @return the text of the story title
     */
    public String getStoryTitle() {
        return storyTitleTextArea.getText();
    }

    /**
     * Method that returns the story description text.
     *
     * @return the description text
     */
    public String getDescriptionText() {
        return descriptionText.getText();
    }

    /**
     * Method that retrieves the story label name.
     *
     * @return the value of the label name
     */
    public String getLabel() {
        return labelName.getText();
    }

    /**
     * Method that configures the story label.
     *
     * @param storyLabel the parameter to configure
     *                   on the label option
     */
    public void setLabel(String storyLabel) {
        label.clear();
        label.sendKeys(storyLabel, Keys.ENTER);
    }

    /**
     * Method that returns the comment of a story.
     *
     * @return The text of the comment retrieved
     */
    public String getComment() {
        return driver.findElement(By.xpath("//p[contains(.,'" + commentMesage + "')]")).getText();
    }

    /**
     * Method that sets the story comment.
     *
     * @param storyComment the value to set on the comment
     */
    public void setComment(String storyComment) {
        commentMesage = storyComment;
        comment.clear();
        comment.sendKeys(storyComment);
        addCommentButton.click();
    }

    /**
     * Method that retrieves the story type text.
     *
     * @return the value for the story type
     */
    public String getStoryType() {
        return driver.findElement(By.xpath("//span[contains(.,'" + storyTypeName.toLowerCase() + "')]"))
                .getText().toLowerCase();
    }

    /**
     * Method that configures the story type value.
     *
     * @param storyType the value to be set
     */
    public void setStoryType(String storyType) {
        storyTypeName = storyType.toLowerCase();
        try {
            wait.withTimeout(TIMEOUT, SECONDS);
            storyTypeArrow.click();
            driver.findElement(By.xpath("//span[contains(.,'" + storyTypeName + "')]")).click();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Story type not found", e);
        } finally {
            wait.withTimeout(WAIT_TIME, SECONDS);
        }
    }

    /**
     * This method is for set the story title text field
     * wich contains the name of a story.
     *
     * @param storyTitle it is the name for a story
     */
    public void setStoryTitleTextArea(String storyTitle) {
        CommonMethods.setWebElement(storyTitleTextArea, storyTitle);
    }

    /**
     * This method is for set the description text field
     * wich contains the description of a story.
     *
     * @param storyDescription it is the description for a story
     */
    public void setDescriptionTextarea(String storyDescription) {
        editDescriptionButton.click();
        storyDescriptionTextField.clear();
        storyDescriptionTextField.sendKeys(storyDescription);
        doneDescriptionButton.click();
    }

    /**
     * General method to set the values of properties of a story.
     *
     * @param values Map of properties to set of a story
     */
    public void strategyStepMap(final Map<StoriesSteps, Object> values) {
        EnumMap<StoriesSteps, IAutomationStep> strategyMap = new EnumMap<>(StoriesSteps.class);
        strategyMap.put(STORY_TITLE, () -> setStoryTitleTextArea(String.valueOf(values.get(STORY_TITLE))));
        strategyMap.put(STORY_TYPE, () -> setStoryType(String.valueOf(values.get(STORY_TYPE))));
        strategyMap.put(DESCRIPTION, () -> setDescriptionTextarea(String.valueOf(values.get(DESCRIPTION))));
        strategyMap.put(LABELS, () -> setLabel(String.valueOf(values.get(LABELS))));
        strategyMap.put(COMMENT, () -> setComment(String.valueOf(values.get(COMMENT))));
        for (StoriesSteps step : values.keySet()) {
            strategyMap.get(step).executeStep();
        }
    }

    /**
     * General method to compare the values of properties of a story.
     *
     * @return map with the current values
     */
    public Map<StoriesSteps, Object> getAssertionMap() {
        EnumMap<StoriesSteps, Object> assertionMap = new EnumMap<>(StoriesSteps.class);
        assertionMap.put(STORY_TITLE, getStoryTitle());
        assertionMap.put(STORY_TYPE, getStoryType());
        assertionMap.put(DESCRIPTION, getDescriptionText());
        assertionMap.put(LABELS, getLabel());
        assertionMap.put(COMMENT, getComment());
        return assertionMap;
    }

    /**
     * Adds the specified task to the story.
     *
     * @param taskDescription the task to be added.
     */
    public void addTask(final String taskDescription) {
        newTaskDescriptionTextArea.click();
        newTaskDescriptionTextArea.clear();
        newTaskDescriptionTextArea.sendKeys(taskDescription);
        addTaskButton.click();
    }

    /**
     * Verify if the task exists in the story.
     *
     * @param description the task to be searched.
     * @return true if the task is found, false otherwise.
     */
    public boolean isThereATaskWithDescription(final String description) {
        return !driver.findElements(By.xpath(String.format(TASK_LOCATOR, description))).isEmpty();
    }

    /**
     * Updates the specified task.
     *
     * @param taskDescription    the task to be updated.
     * @param newTaskDescription the new description of the task.
     */
    public void updateTask(final String taskDescription, final String newTaskDescription) {
        CommonMethods.clickWebElement(By.xpath(String.format(TASK_LOCATOR, taskDescription)));
        WebElement taskTextArea = driver.findElement(By.xpath(String.format(TASK_EDIT_LOCATOR, taskDescription)));
        CommonMethods.setWebElement(taskTextArea, newTaskDescription);
        CommonMethods.clickWebElement(By.cssSelector(SAVE_TASK));
    }

    /**
     * Deletes the specified task.
     *
     * @param taskDescription the task to be deleted.
     */
    public void deleteTask(final String taskDescription) {
        WebElement task = driver.findElement(By.xpath(String.format(TASK_LOCATOR, taskDescription)));
        Actions builder = new Actions(driver);
        builder.moveToElement(task).build().perform();
        CommonMethods.clickWebElement(By.cssSelector(DELETE_TASK_BUTTON));
    }
}
