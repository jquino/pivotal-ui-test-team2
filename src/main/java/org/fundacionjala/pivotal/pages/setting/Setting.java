package org.fundacionjala.pivotal.pages.setting;

import org.fundacionjala.pivotal.pages.dashboard.ToolBar;

/**
 * Class to set the settings characteristics.
 */
public class Setting {
    private SideBarSetting sideBar;
    private ToolBar toolBar;

    /**
     * Class constructor.
     */
    public Setting() {
        sideBar = new SideBarSetting();
        toolBar = new ToolBar();
    }

    /**
     * Method that returns the side bar object.
     *
     * @return the side bar object
     */
    public SideBarSetting getSideBar() {
        return sideBar;
    }

    /**
     * Method that returns the toolbar.
     *
     * @return the tool bar object
     */
    public ToolBar getToolBar() {
        return toolBar;
    }
}
