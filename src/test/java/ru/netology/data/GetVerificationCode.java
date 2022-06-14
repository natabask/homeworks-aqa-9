package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
public class GetVerificationCode {

    @SneakyThrows
    public String returnCode() {
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
}
