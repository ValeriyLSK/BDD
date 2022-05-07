package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private static SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection topUpButton = $$("[data-test-id=action-deposit]");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public void dashboardVisible() {
        heading.shouldBe(Condition.visible);
    }

    public int getCardBalance(String id) {
        for (SelenideElement card : cards) {
            if (Objects.equals(card.getDomAttribute("data-test-id"), id)) {
                val text = card.text();
                return extractBalance(text);
            }
        }

        return 0;
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferMoneyPage shouldTransferFromFirstCardToSecond() {
        topUpButton.last().click();
        return new TransferMoneyPage();
    }

    public TransferMoneyPage shouldTransferFromSecondCardToFirst() {
        topUpButton.first().click();
        return new TransferMoneyPage();
    }
}