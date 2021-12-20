package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helper.BaseHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class Tests extends BaseHelper {

    @Test
    @DisplayName("Find vacancy test")
    void selectVacancyTest() {

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
    @DisplayName("Contact us from bottom form")
    void sendRequestContactUsTest() {

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
            $("input[name=phone]").setValue("+7(495)557-29-29").pressEnter();
        });
        step("Checkbox selected", () -> {
            $("input#modalPrivacy").click();
        });
        step("Click on send request", () -> {
            $("button[type=button]").click();
            //button disabled, false test
        });
        step("Verify that request is sent", () -> {
            $("div.mg-demo__thanks-content").shouldHave(Condition.text("Спасибо за заявку!"));
        });

    }

    @Test
    @DisplayName("Contact us from section page")
    void fillFormContactUsAtSectionTest() {

        step("Select section", () -> {
            $$("div.dropdown").find(Condition.text("Решения")).hover()
                    .$$("a").get(5).click();
        });
        step("Scroll to Send Request form", () -> {
            $("section.mg-demo").scrollTo().click();
        });
        step("Fill out name", () -> {
            $("input[name=name]").setValue("Валентин").pressEnter();
        });
        step("Fill out company name", () -> {
            $("input[name=company]").setValue("AlfaBank").pressEnter();
        });
        step("Fill out email", () -> {
            $("input[name=email]").setValue("vprohorov@gmail.com").pressEnter();
        });
        step("Fill out phone number", () -> {
            $("input[name=phone]").setValue("+7(495)557-81-29").pressEnter();
        });
        step("Click on send request", () -> {
            $("button[type=submit]").click();
        });
        step("Verify that request is sent", () -> {
            $("div.mg-demo__thanks-content").shouldHave(Condition.text("Спасибо за заявку!"));
        });
    }

    @Test
    @DisplayName("Navigate through news")
    void newsOverviewTest() {

        step("Select menu", () -> {
            $$(".mg-menu__list-item").find(Condition.text("Новости")).click();
        });
        step("Navigation to previous page", () -> {
            SelenideElement nav = $("nav.pagination");
            nav.scrollTo().click();
            $$("a").find(Condition.text("Предыдущие")).click();
        });
        step("Navigation to first page", () -> {
            SelenideElement nav = $("nav.pagination");
            nav.scrollTo().click();
            $$("a").find(Condition.text("Новые")).click();
        });
        step("Verify that first page is opened", () -> {
            $("#post-287").shouldHave(Condition.text("30 сентября 2021"));
        });

    }

    @MethodSource("helper.DataLoadHelper#loadDataFromFile")
    @ParameterizedTest(name = "Contact us from section page")
    void fillFormsAtSomeSectionsTest(Map data) {

        Map<String, String> sections = data;

        step("Select section", () -> {
            $$("div.dropdown").find(Condition.text("Решения")).hover()
                    .$$("a").find(Condition.text(sections.get("link"))).click();
                    //find(Condition.attribute("href", sections.get("link"))).click();
        });
        step("Scroll to Send Request form", () -> {
            $("section.mg-demo").scrollTo().click();
        });
        step("Fill out name", () -> {
            $("input[name=name]").setValue(sections.get("name")).pressEnter();
        });
        step("Fill out company name", () -> {
            $("input[name=company]").setValue(sections.get("bank")).pressEnter();
        });
        step("Fill out email", () -> {
            $("input[name=email]").setValue(sections.get("email")).pressEnter();
        });
        step("Fill out phone number", () -> {
            $("input[name=phone]").setValue(sections.get("phone")).pressEnter();
        });
        step("Click on send request", () -> {
            $("button[type=submit]").click();
            sleep(1000);
        });
        step("Verify that request is sent", () -> {
            $("div.mg-demo__thanks-content").shouldHave(Condition.text(sections.get("thankyou")));
        });
    }
}
