package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class Tests {

    @Test
    @DisplayName("Magenta tests")
    void selectVacancyTest() {

        step("Open main page ", () -> {
            open("https://magenta-technology.ru/");
        });
        step("Open vacancy list ", () -> {
            $("span.vacancy-text").click();
        });
        step("Select vacancy", () -> {
            $$("li").find(Condition.text("QA Automation engineer")).click();
        });
        step("Verify that correct page is opened", () -> {
            assertThat($("h1").getText()).isEqualTo("QA Automation engineer");
        });

    }

    @Test
    @DisplayName("Magenta tests")
    void sendRequestContactUsTest() {

        step("Open main page ", () -> {
            open("https://magenta-technology.ru/");
        });
        step("Open contact form", () -> {
            $("a.showContactFormButton").click();
        });
        step("Verify that form is opened", () -> {
            $("h5.modal-title").shouldHave(Condition.text("Узнайте больше о возможностях"));
        });
        step("Fill out name", () -> {
            $("input[name=name]").setValue("Валентин").pressEnter();
        });
        step("Fill out company name", () -> {
            $("input[name=company]").setValue("Sberbank").pressEnter();
        });
        step("Fill out email", () -> {
            $("input[name=email]").setValue("osipov@gmail.com").pressEnter();
        });
        step("Fill out phone number", () -> {
            $("input[name=phone]").setValue("9023875883").pressEnter();
        });
        step("Checkbox selected", () -> {
            $("input#modalPrivacy").click();
        });
        step("Click on send request", () -> {
            $("button[type=button]").click();
           //button disabled, false test
        });
        step("Verify that request is sent", () -> {
            $("h5.modal-title").shouldBe(Condition.disappear);
        });

    }
}
