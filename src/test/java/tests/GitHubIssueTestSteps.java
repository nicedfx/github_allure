package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

public class GitHubIssueTestSteps {
    private String issueName = "AllureIssue";
    private String assigneeName = "nicedfx";
    private String label1 = "duplicate";
    private String label2 = "good first issue";
    private String repoName = "github_allure";
    private static final String BASEURL = "https://github.com/";

    @Test
    @Link(name = "BaseUrl", value = BASEURL)
    @Tags({@Tag("Web"), @Tag("Basic"), @Tag("AllureSteps")})
    @DisplayName("Create an issue with an assignee and labels and remove it")
    @Feature("Issues")
    @Story("Create new issue")
    @Owner("Sba")

    public void createIssue() {
        parameter("Repository", repoName);
        parameter("Issue name", issueName);

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Github main page", () -> {
            open(BASEURL);
        });

//      Navigate to the issue creation page and create a new issue.
        step("Log in to Github", () -> {
            $("header.header-logged-out").$(byText("Sign in")).click();
            $("input#login_field").setValue(new Credentials().getUserName());
            $("input#password").setValue(new Credentials().getPassword());
            $(byValue("Sign in")).click();
        });

        step("Create a new issue", () -> {
            $("form.js-more-repos-form").click();
            $("#repos-container").$(byText(repoName)).click();
            $("[data-content=Issues]").click();
            $$(".d-none.d-md-block").find(text("New issue")).click();
            $("#issue_title").setValue(issueName);
        });

        step("Add assignee to the issue", () -> {
            $("#assignees-select-menu").click();
            $(".select-menu-list").$(byText(assigneeName)).click();
            $("#assignees-select-menu").click();
        });

        step("Add labels", () -> {
            $("#labels-select-menu").click();
            $(".js-filterable-issue-labels").$(byText(label1)).click();
            $(".js-filterable-issue-labels").$(byText(label2)).click();
            $("#labels-select-menu").click();
        });

        step("Submit the issue", () -> {
            $$(".btn.btn-primary").find(text("Submit new issue")).click();

        });

        step("Check that the issue has been created", () -> {
            $("#partial-discussion-header").shouldHave(text(issueName));
            $(".js-issue-assignees").shouldHave(text(assigneeName));
            $(".js-issue-labels").shouldHave(text(label1));
            $(".js-issue-labels").shouldHave(text(label2));
        });

        step("Delete the issue", () -> {
            open("https://github.com/nicedfx/github_allure/issues");
            $("body").$(byText(issueName)).click();
            $$("strong").find(text("Delete issue")).click();
            $(byText("Delete this issue")).click();
        });
    }

    @Test
    @Disabled
    @Link(name = "BaseUrl", value = BASEURL)
    @Tags({@Tag("Web"), @Tag("Basic")})
    @DisplayName("Create an issue with an assignee and labels and remove it")
    @Feature("Issues")
    @Story("Создание Issue")
    @Owner("Sba")

    public void createIssue2() {
        parameter("Repository", repoName);
        parameter("Issue name", issueName);

        step("Open Github main page", () -> {
            open(BASEURL);
        });

//      Navigate to the issue creation page and create a new issue.
        step("Log in to Github", () -> {
            $("header.header-logged-out").$(byText("Sign in")).click();
            $("input#login_field").setValue(new Credentials().getUserName());
            $("input#password").setValue(new Credentials().getPassword());
            $(byValue("Sign in")).click();
        });

        step("Create a new issue", () -> {
            $("form.js-more-repos-form").click();
            $("#repos-container").$(byText(repoName)).click();
            $("[data-content=Issues]").click();
            $$(".d-none.d-md-block").find(text("New issue")).click();
            $("#issue_title").setValue(issueName);
        });

        step("Add assignee to the issue", () -> {
            $("#assignees-select-menu").click();
            $(".select-menu-list").$(byText(assigneeName)).click();
            $("#assignees-select-menu").click();
        });

        step("Add labels", () -> {
            $("#labels-select-menu").click();
            $(".js-filterable-issue-labels").$(byText(label1)).click();
            $(".js-filterable-issue-labels").$(byText(label2)).click();
            $("#labels-select-menu").click();
        });

        step("Submit the issue", () -> {
            $$(".btn.btn-primary1").find(text("Submit new issue")).click();

        });

        step("Check that the issue has been created", () -> {
            $("#partial-discussion-header").shouldHave(text(issueName));
            $(".js-issue-assignees").shouldHave(text(assigneeName));
            $(".js-issue-labels").shouldHave(text(label1));
            $(".js-issue-labels").shouldHave(text(label2));
        });

        step("Delete the issue", () -> {
            open("https://github.com/nicedfx/github_allure/issues");
            $("body").$(byText(issueName)).click();
            $$("strong").find(text("Delete issue")).click();
            $(byText("Delete this issue")).click();
        });
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}