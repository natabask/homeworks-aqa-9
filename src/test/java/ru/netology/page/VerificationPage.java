package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DBHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(Condition.visible);
    }

    public DashBoardPage validVerify() {
        DBHelper getCode = new DBHelper();
        codeField.setValue(getCode.returnCode());
        verifyButton.click();
        return new DashBoardPage();
    }
}
