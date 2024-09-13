package com.example.javafxmysqltemplate;

import com.example.database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        try (Connection connection = Database.newConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("SELECT VERSION() AS version")) {
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) { // only expect 1 row, so using if instead of while
                        welcomeText.setText(rs.getString("version"));
                    }
                    else {
                        welcomeText.setText("No version returned???");
                    }
                }
            }
        } catch (SQLException e) {
            welcomeText.setText("ERROR: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}