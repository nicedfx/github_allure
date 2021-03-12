package io.github;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GitHubBaseSteps {

    private static String BASE_URL = "https://github.com";

    @Step("Open main page")
    public void openMainPage() {
        open(BASE_URL);
    }

    @Step("Search for a repo: {repository}")
    public void searchForRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
    }
    @Step("Go to the {repository} repo")
    public void goToRepositoryFromSearch(String repository) {
        $(By.linkText(repository)).click();
    }

    @Step("Go to Issues")
    public void openRepositoryIssues() {
        $(withText("Issues")).click();
    }

    @Step("Check that issue with title {title} exists")
    public void shouldSeeIssueWithTitle(final String title) {
        $(withText(title)).should(Condition.exist);
    }

    @Step("Check that issue with number {number} exists")
    public void shouldSeeIssueWithNumber(final String number) {
        $(withText(number)).should(Condition.exist);
    }


}
