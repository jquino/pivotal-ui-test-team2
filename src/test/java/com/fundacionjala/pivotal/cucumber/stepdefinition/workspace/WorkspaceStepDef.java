package com.fundacionjala.pivotal.cucumber.stepdefinition.workspace;

import com.fundacionjala.pivotal.cucumber.stepdefinition.login.LoginStepDef;
import com.fundacionjala.pivotal.pages.dashboard.CreateWorkspace;
import com.fundacionjala.pivotal.pages.dashboard.Dashboard;
import com.fundacionjala.pivotal.pages.workspace.SideBarWorkspace;
import com.fundacionjala.pivotal.pages.workspace.Workspace;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import static com.fundacionjala.pivotal.framework.util.Constants.DASHBOARD;

/**
 * Created by Daniel on 07/07/2016.
 */
public class WorkspaceStepDef {

    private CreateWorkspace createWorkspace;

    private Workspace workspace;

    private Dashboard dashboard;

    private SideBarWorkspace sideBarWorkspace;

    private LoginStepDef loginStepDef;

    public WorkspaceStepDef(LoginStepDef loginStepDef) {
        this.loginStepDef = loginStepDef;
    }

    @Given("^I am on Pivotal Dashboard page$")
    public void iAmOnPivotalDashboardPage() {
        dashboard = loginStepDef.getDashboard();
    }

    @When("^click on the Create Workspace button of the (Dashboard|Form)$")
    public void iClickOnTheCreateWorkspaceButton(String page) {

        if (DASHBOARD.equalsIgnoreCase(page)) {
            createWorkspace = dashboard.clickCreateWorkspaceLink();
        } else {
            workspace = createWorkspace.clickCreateWorkspaceLink ();
            sideBarWorkspace = workspace.getSideWorkspace();
        }
    }

    @Given("^I am on Pivotal Create Workspace form$")
    public void iAmOnPivotalCreateWorkspaceForm() {
        createWorkspace = dashboard.clickCreateWorkspaceLink();
    }

    @When("^I fill with (.*) the name Workspace field$")
    public void iSendAPOSTRequestToMyWorkspacesWithAsNameWorkspace(String nameWorkspace) {
        createWorkspace.setUserNameTestField(nameWorkspace);
    }

    public CreateWorkspace getCreateWorkspace() {
        return createWorkspace;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    @When("^I click on Add Projects button$")
    public void iClickOnAddProjectsButton () {
        sideBarWorkspace.clickAddProjectLink ();
    }

    @And("^I  click on list projects icon$")
    public void iClickOnListProjectsIcon () {
        sideBarWorkspace.clicklistProjectLink ();
    }

    @When("^I select the project created previously$")
    public void iSelectTheProjectCreatedPreviously () {
        sideBarWorkspace.clickidProjectLink ();
    }

    @And("^I click on Save Workspace button$")
    public void iClickOnSaveWorkspaceButton () {
        workspace = sideBarWorkspace.clickSaveWorkspaceLink ();
    }
}
