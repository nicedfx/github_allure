package io.github;

import org.junit.jupiter.api.Test;

public class GitHubIssuesPageObject {

    GitHubBaseSteps baseSteps = new GitHubBaseSteps();

    private String repository = "nicedfx/github_allure";
    private String issueNumber = "6";
    private String issueName = "asdasdas";

    @Test
    public void  testSearchIssue() {
        baseSteps.openMainPage();
        baseSteps.searchForRepository(repository);
        baseSteps.goToRepositoryFromSearch(repository);
        baseSteps.openRepositoryIssues();
        baseSteps.shouldSeeIssueWithTitle(issueName);
    }

}
