package org.example;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Database {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties props = new Properties();
                try (InputStream in = Database.class.getClassLoader().getResourceAsStream("db.properties")) {
                    props.load(in);
                }
                connection = DriverManager.getConnection(
                        props.getProperty("db.url"),
                        props.getProperty("db.user"),
                        props.getProperty("db.password")
                );
            } catch (Exception e) {
                throw new RuntimeException("Помилка підключення до БД", e);
            }
        }
        return connection;
    }
}