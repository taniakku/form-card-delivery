import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeliveryTest {

    @Test
    void ShouldSendForm() {
        Configuration.holdBrowserOpen = true;

        open("http://0.0.0.0:9999");
        $("[placeholder='Город']").val("Санкт-Петербург");
        $("[placeholder='Дата встречи']").val(""); //дата +3 дня от текущей проставляется автоматически
        $(byName("name")).val("Пушкина Анна-Мария");
        $("[name='phone']").val("+79119786655");
        $(byClassName("checkbox__box")).click();
        $(byText("Забронировать")).click();

        $(withText("Успешно!")).should(appear, Duration.ofSeconds(15));

    }

}
