package ru.netology.test;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.GetVerificationCode;
import ru.netology.page.DashBoardPage;
import ru.netology.page.LoginPage;

import java.sql.Connection;
import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.open;

public class LoginPositiveTest {

    @SneakyThrows
    @AfterAll
    static void cleanUp() {
        var runner = new QueryRunner();
        String cleanCards = "DELETE FROM cards;";
        String cleanCodes = "DELETE FROM auth_codes;";
        String cleanTrans = "DELETE FROM card_transactions;";
        String cleanUsers = "DELETE FROM users;";
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            runner.update(conn, cleanCards);
            runner.update(conn, cleanCodes);
            runner.update(conn, cleanTrans);
            runner.update(conn, cleanUsers);
        }
    }

    @Test
    public void shouldSuccessfulLogin() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.validVerify();
    }
}
