package org.fundacionjala.pivotal.pages.project;

import org.fundacionjala.pivotal.pages.BasePage;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;

/**
 * Class that lets us confirm the deletion
 * of the project on the browser popup.
 */
public class DeleteProjectAlert extends BasePage {

    @FindBy(css = ".delete.inline_dialog")
    private WebElement deleteAlertContainer;

    @FindBy(id = "confirm_delete")
    private WebElement deleteProjectBtn;

    /**
     * Method that clicks the delete button inside the
     * project delete container.
     *
     * @return the Dashboard instance
     */
    public Dashboard confirmDeleteProject() {
        wait.until(ExpectedConditions.visibilityOf(deleteAlertContainer));
        clickWebElement(deleteProjectBtn);
        return new Dashboard();
    }
}
