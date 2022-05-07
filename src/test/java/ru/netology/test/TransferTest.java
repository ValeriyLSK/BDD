package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferMoneyPage;

import static com.codeborne.selenide.Selenide.open;

public class TransferTest {

    private int sum1 = 300;
    private int sum2 = 10000;

    @Test
    void shouldSuccessTransferFromSecondCardToFirst() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.login(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.verify(verificationCode);
        var dashboardPage = new DashboardPage();
        int balanceCard1 = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
        int balanceCard2 = dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        dashboardPage.shouldTransferFromSecondCardToFirst();
        var cardInfo = DataHelper.getCardTwo();
        var transferMoney = new TransferMoneyPage();
        transferMoney.setAmount(sum1);
        transferMoney.setFrom(cardInfo);
        transferMoney.shouldTransfer();
        Assertions.assertEquals(balanceCard1 + sum1, dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0"));
        Assertions.assertEquals(balanceCard2 - sum1, dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d"));
    }

    @Test
    void shouldSuccessTransferFromFirstCardToSecond() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.login(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.verify(verificationCode);
        var dashboardPage = new DashboardPage();
        int balanceCard1 = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
        int balanceCard2 = dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        dashboardPage.shouldTransferFromFirstCardToSecond();
        var cardInfo = DataHelper.getCardOne();
        var transferMoney = new TransferMoneyPage();
        transferMoney.setAmount(sum1);
        transferMoney.setFrom(cardInfo);
        transferMoney.shouldTransfer();
        Assertions.assertEquals(balanceCard1 - sum1, dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0"));
        Assertions.assertEquals(balanceCard2 + sum1, dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d"));
    }
}
