package org.fundacionjala.pivotal.pages.accounts;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.fundacionjala.pivotal.pages.BasePage;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;

/**
 * This class represents the AccountSettings page and
 * its characteristics.
 */
public class AccountSetting extends BasePage {

    @FindBy(css = "a[data-method='delete']")
    private WebElement deleteAccountLink;

    @FindBy(css = ".account_name>span")
    private WebElement accountName;

    @FindBy(xpath = "//h4[text()='ID']/following-sibling::div")
    private WebElement accountID;

    /**
     * Method that deletes the account where the settings is present.
     *
     * @return The Accounts page once we remove the account on Settings tab
     */
    public Accounts deleteAccount() {
        clickWebElement(deleteAccountLink);
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        return new Accounts();
    }

    /**
     * This method gets the account ID from the Account instance.
     *
     * @return the account ID
     */
    public String getAccountID() {
        return accountID.getText();
    }

    /**
     * This method gets the account name from the Account instance.
     *
     * @return the account name
     */
    public String getAccountName() {
        return accountName.getText();
    }

}
