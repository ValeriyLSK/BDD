package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class TransferMoneyPage {

    private SelenideElement transferMoneyPage = $(withText("Пополнение карты"));
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement topUpButton = $("[data-test-id=action-transfer]");

    public void transfer() {
        transferMoneyPage.shouldBe(Condition.visible);
    }

    public void setAmount(int sum) {
        amount.setValue(String.valueOf(sum));
    }

    public void setFrom(String numberCard) {
        from.setValue(numberCard);
    }

    public void shouldTransfer() {
        topUpButton.click();
    }
}
