package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement codeOrPush = $("[data-test-id=code] input");
    private SelenideElement continueButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeOrPush.shouldBe(Condition.visible);
    }

    public DashboardPage verify(DataHelper.VerificationCode verificationCode) {
        codeOrPush.setValue(verificationCode.getCode());
        continueButton.click();
        return new DashboardPage();
    }
}
