import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeliveryTest {
   LocalDate date = now();
   LocalDate order = date.plusDays(3);

    @Test
    void ShouldSendForm() {
        Configuration.holdBrowserOpen = true;

        open("http://0.0.0.0:9999");
        $("[data-test-id='city'] input").val("Санкт-Петербург");
        $("[data-test-id='date'] input").setValue(String.valueOf(order));
        $(byName("name")).val("Пушкина Анна-Мария");
        $("[name='phone']").val("+79119786655");
        $(byClassName("checkbox__box")).click();
        $(byText("Забронировать")).click();

        $(withText("Успешно!")).should(appear, Duration.ofSeconds(15));

    }

}
