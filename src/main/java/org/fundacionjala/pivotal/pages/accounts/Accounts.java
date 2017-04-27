package org.fundacionjala.pivotal.pages.accounts;

import org.apache.log4j.Logger;
import org.fundacionjala.pivotal.pages.dashboard.ToolBar;
import org.fundacionjala.pivotal.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;

/**
 * This class represents Accounts page and its characteristics.
 */
public class Accounts extends BasePage {

    private static final Logger LOGGER = Logger.getLogger(Accounts.class.getName());

    private final ToolBarAccount toolBarAccount;

    @FindBy(id = "notice")
    private WebElement deleteAccountMessage;

    @FindBy(xpath = "//a[contains(.,'Manage Account')]")
    private WebElement manageAccountBtn;

    @FindBy(id = "new_account_link")
    private WebElement createNewAccountLink;

    @FindBy(id = "account_name")
    private WebElement accountTextBox;

    @FindBy(id = "add_account_button")
    private WebElement createNewAccountBtn;

    /**
     * Class constructor,
     * This class instances a ToolBarAccount.
     */
    public Accounts() {
        toolBarAccount = new ToolBarAccount();
    }

    /**
     * This method is used to get into an Account properties and settings.
     *
     * @return {@link ToolBarAccount}
     */
    public ToolBarAccount manageAccount() {
        clickWebElement(manageAccountBtn);
        return new ToolBarAccount();
    }

    /**
     * This method is used to catch the message that is displayed after deleting an
     * account.
     *
     * @return the message of deleting an account.
     */
    public String getDeleteAccountMessage() {
        LOGGER.info("Message delete account " + deleteAccountMessage.getText());
        return deleteAccountMessage.getText();
    }

    /**
     * This method clicks the new account button displayed on the page.
     *
     * @return {@link CreateAccountForm}
     */
    public CreateAccountForm clickNewAccountBtn() {
        clickWebElement(createNewAccountLink);
        return new CreateAccountForm();
    }

    /**
     * it returns the web element that represents
     * the manage accounts button.
     *
     * @return the web element from the instance
     */
    public WebElement getManageAccountBtn() {
        return manageAccountBtn;
    }

    /**
     * It gives us the tool bar account that is present on the instance.
     *
     * @return the tool bar account instance
     */
    public ToolBarAccount getToolBarAccount() {
        return toolBarAccount;
    }

    /**
     * The method returns the tool bar instance.
     *
     * @return the tool bar retrieved from the instance
     */
    public ToolBar getToolBar() {
        return new ToolBar();
    }
}
