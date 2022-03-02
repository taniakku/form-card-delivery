import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void ShouldSendForm() {
        Configuration.holdBrowserOpen = true;

        String orderDate = generateDate(3);

        open("http://localhost:9999");
        $("[data-test-id='city'] input").val("Санкт-Петербург");
        $("[data-test-id='date'] input").setValue(orderDate);
        $(byName("name")).val("Пушкина Анна-Мария");
        $("[name='phone']").val("+79119786655");
        $(byClassName("checkbox__box")).click();
        $(byText("Забронировать")).click();

        $(withText("Успешно!")).should(appear, Duration.ofSeconds(15));
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно забронирована на " + orderDate), Duration.ofSeconds(15));


    }

}
