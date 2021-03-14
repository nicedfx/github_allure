package io.github;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class GitHubIssuesPageObject {

    GitHubBaseSteps baseSteps = new GitHubBaseSteps();

    private String repository = "nicedfx/github_allure";
    private String issueNumber = "6";
    private String issueName = "asdasdas";

    @Test
    @Tags({@Tag("Web"), @Tag("Basic"), @Tag("AllurePO")})
    @DisplayName("Create an issue with an assignee and labels and remove it: NO ALLURE")
    @Feature("Issues")
    @Story("Create new issue")
    @Owner("Sba")
    public void  testSearchIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        baseSteps.openMainPage();
        baseSteps.searchForRepository(repository);
        baseSteps.goToRepositoryFromSearch(repository);
        baseSteps.openRepositoryIssues();
        baseSteps.shouldSeeIssueWithTitle(issueName);
    }

}
