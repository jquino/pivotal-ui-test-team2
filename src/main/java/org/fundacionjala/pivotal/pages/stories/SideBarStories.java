package org.fundacionjala.pivotal.pages.stories;

import org.apache.log4j.Logger;
import org.fundacionjala.pivotal.pages.BasePage;
import org.fundacionjala.pivotal.pages.project.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.fundacionjala.pivotal.framework.util.Constants.WAIT_TIME;

/**
 * This class contains the web elements from side bar.
 */
public class SideBarStories extends BasePage {

    private static final String ADD_STORY_BUTTON_WAS_NOT_FOUND_MSG = "Add Story Button was not found";

    private static final Logger LOGGER = Logger.getLogger(Project.class.getName());

    private static final int SLEEP = 10000;

    @FindBy(css = ".button.add_story")
    private WebElement addStoryButton;

    /**
     * Method that clicks the button "Add Story".
     *
     * @return the Story object instance
     */
    public Story clickOnAddStoryButton() {
        try {
            Thread.sleep(SLEEP);
            driver.findElement(By.cssSelector(".button.add_story"));
            addStoryButton.click();
        } catch (NoSuchElementException e) {
            LOGGER.error("click add story button was not found", e);
            throw new NoSuchElementException(ADD_STORY_BUTTON_WAS_NOT_FOUND_MSG);
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted !", e);
            Thread.currentThread().interrupt();
        } finally {
            wait.withTimeout(WAIT_TIME, SECONDS);
        }

        return new Story();
    }
}
