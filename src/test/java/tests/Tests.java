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
    void selectVacancyTest(){

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
    void sendRequestContactUsTest(){

        step("Open main page ", () -> {
            open("https://magenta-technology.ru/");
        });
        step("Open contact form", () -> {
            $("div.getContactForm").click();
        });
        step("Verify that form is opened", () -> {
            $("form#myForm").getText(); // continued from here
        });
        step("Verify that correct page is opened", () -> {
            assertThat($("h1").getText()).isEqualTo("QA Automation engineer");
        });

    }
}
