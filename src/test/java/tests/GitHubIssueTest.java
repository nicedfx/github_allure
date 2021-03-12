package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.*;

public class GitHubIssueTest {
    static final String issueName = "AllureIssue";
    static final String assigneeName = "nicedfx";
    static final String label1 = "duplicate";
    static final String label2 = "good first issue";
    static final String repoName = "github_allure";

    @Test
    @Disabled
    public void createIssue() {
        open("https://github.com/");

//      Navigate to the issue creation page and create a new issue.

        $("header.header-logged-out").$(byText("Sign in")).click();
        $("input#login_field").setValue(new Credentials().getUserName());
        $("input#password").setValue(new Credentials().getPassword());

        $(byValue("Sign in")).click();
        $("#repos-container").$(byText(repoName)).click();
        $("[data-content=Issues]").click();
        $$(".d-none.d-md-block").find(text("New issue")).click();
        $("#issue_title").setValue(issueName);
        $("#assignees-select-menu").click();
        $(".select-menu-list").$(byText(assigneeName)).click();
        $("#assignees-select-menu").click();
        $("#labels-select-menu").click();
        $(".js-filterable-issue-labels").$(byText(label1)).click();
        $(".js-filterable-issue-labels").$(byText(label2)).click();
        $("#labels-select-menu").click();
        $$(".btn.btn-primary").find(text("Submit new issue")).click();

//      Check that the issue has been created.

        $("#partial-discussion-header").shouldHave(text(issueName));
        $(".js-issue-assignees").shouldHave(text(assigneeName));
        $(".js-issue-labels").shouldHave(text(label1));
        $(".js-issue-labels").shouldHave(text(label2));

//      Delete the issue

        open("https://github.com/nicedfx/github_allure/issues");
        $("body").$(byText(issueName)).click();
        $$("strong").find(text("Delete issue")).click();
        $(byText("Delete this issue")).click();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}