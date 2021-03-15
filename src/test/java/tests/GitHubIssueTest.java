package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import static com.codeborne.selenide.Browsers.CHROME;
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
    static final String BASEURL = "https://gighub.com";

    @Test
    @Link(name = "BaseUrl", value = BASEURL)
    @Tags({@Tag("Web"), @Tag("Basic"), @Tag("ListenerOnly")})
    @DisplayName("Create an issue with an assignee and labels and remove it: NO ALLURE")
    @Feature("Issues")
    @Story("Create new issue")
    @Owner("Sba")
    public void createIssue() {
        //adding selenide logger - to use when many test are already developed w/o steps or
        SelenideLogger.addListener("allure", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.browser = CHROME;
        Configuration.startMaximized = true;

        Configuration.remote = "http://Z^p&F:alcatel_1@selenoid.sbacho.ml:4444/wd/hub";

        open("https://github.com/");

//      Navigate to the issue creation page and create a new issue.

        $("header.header-logged-out").$(byText("Sign in")).click();
        $("input#login_field").setValue(new Credentials().getUserName());
        $("input#password").setValue(new Credentials().getPassword());

        $(byValue("Sign in")).click();
        $("form.js-more-repos-form").click();
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