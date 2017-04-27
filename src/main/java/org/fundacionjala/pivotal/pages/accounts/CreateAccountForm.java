package org.fundacionjala.pivotal.pages.accounts;

import org.fundacionjala.pivotal.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.isElementPresent;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.setWebElement;

/**
 * Class that represents the create
 * account form with all its characteristics.
 */
public class CreateAccountForm extends BasePage {

    @FindBy(id = "account_name")
    private WebElement accountNameTextField;

    @FindBy(id = "add_account_button")
    private WebElement createNewAccountBtn;

    @FindBy(id = "error")
    private WebElement errorMessageAtCreateAccount;

    /**
     * Method that puts the account name
     * on the text field web element.
     *
     * @param accountName the account name to be inserted
     */
    public void setAccountNameTextField(String accountName) {
        setWebElement(accountNameTextField, accountName);
    }

    /**
     * Method that clicks the create account button
     * and then goes to the Accounts page.
     *
     * @return the Accounts page instance
     */
    public Accounts clickCreateAccountBtn() {
        createNewAccountBtn.click();
        return new Accounts();
    }

    /**
     * Method that saves the error message in the
     * case that the account thorws an exception.
     *
     * @return the error message returned as string.
     */
    public String getErrorMessageAtCreateAccount() {
        String errorMessage = "";
        if (isElementPresent(errorMessageAtCreateAccount)) {
            errorMessage = errorMessageAtCreateAccount.getText();
        }
        return errorMessage;
    }

}
