package helper;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static helper.ProjProperty.property;
import static io.qameta.allure.Allure.step;

public class BaseHelper {

    @BeforeAll
    static void setup(){
        Configuration.remote = property.remoteURL();
        Configuration.browserSize = "2100x1500";
    }

    @BeforeEach
    void openMainPage(){
        step("Open main page ", () -> {
            open("https://magenta-technology.ru/");
        });
    }
}
