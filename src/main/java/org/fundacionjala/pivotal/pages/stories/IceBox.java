package org.fundacionjala.pivotal.pages.stories;

import org.fundacionjala.pivotal.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Class that represents the icebox area.
 */
public class IceBox extends BasePage {

    @FindBy(css = ".items.panel_content")
    private WebElement iceboxSection;

    /**
     * Method that retrieves the icebox section length.
     *
     * @return the length converted to String
     */
    public String getIceboxSection() {
        return iceboxSection.getSize().toString();
    }
}
