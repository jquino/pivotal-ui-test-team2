package org.fundacionjala.pivotal.pages.workspace;

import org.fundacionjala.pivotal.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Class that represernts the side bar in the workspace page.
 */
public class SideBarWorkspace extends BasePage {

    @FindBy(xpath = "//button[contains(.,'add project')]")
    private WebElement addProjectLink;

    @FindBy(id = "_dropdown__arrow")
    private WebElement listProjectLink;

    @FindBy(id = "save_workspace_button")
    private WebElement saveWorkspaceLink;

    /**
     * Method that cliks the add project link.
     */
    public void clickAddProjectLink() {
        addProjectLink.click();
    }

    /**
     * Method that clicks the project list link.
     */
    public void clickListProjectLink() {
        listProjectLink.click();
    }

    /**
     * Method that clicks the selected project link.
     * @param projectName the name of the prokect to be clicked
     */
    public void clickProjectNameLink(String projectName) {
        driver.findElement(By.xpath("//span[contains(.,'" + projectName + "')]")).click();
    }

    /**
     * Method that clicks the save workspace link
     * and retrieve the workspace instance.
     * @return the workspace instance
     */
    public Workspace clickSaveWorkspaceLink() {
        saveWorkspaceLink.click();
        return new Workspace();
    }
}
