package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

    @SneakyThrows
    public static String returnCode() {
        var usersSQL = "SELECT code FROM auth_codes;";
        var runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            String code = runner.query(conn, usersSQL, new ScalarHandler<>());
            return code;
        }
    }

    @SneakyThrows
    public static void prepareStatement() {
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
}