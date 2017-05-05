package org.fundacionjala.pivotal.pages;

import org.fundacionjala.pivotal.framework.selenium.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.fundacionjala.pivotal.framework.util.Constants.WAIT_TIME;

/**
 * Abstract class that acts as a template for the rest of subclasses
 * that will be invoked for Pivotal Tracker project.
 */
public abstract class BasePage {

    @FindBy(css = "[class='tc_header_item tc_header_logo']")
    protected WebElement pivotalLogo;

    protected WebDriver driver;

    protected WebDriverWait wait;

    //protected Actions action;

    /**
     * Class constructor.
     */
    public BasePage() {
        driver = DriverManager.getInstance().getDriver();
        wait = new WebDriverWait(driver, WAIT_TIME);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that removes the possibility of having
     * a modal view opened and have the desired components
     * resent to be clicked or selected.
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }
}
