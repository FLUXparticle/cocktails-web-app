package com.example.cocktails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CocktailApp {

    public static Map<Integer, String> getCocktails() throws SQLException {
        Map<Integer, String> result = new TreeMap<>();

        // Verbindung zur SQLite-Datenbank herstellen
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite::resource:Cocktails.db")) {
            // SQL-Abfrage zum Abrufen der Cocktails
            String query = "SELECT id, name FROM cocktail";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Ergebnis abrufen
                ResultSet resultSet = preparedStatement.executeQuery();

                // Cocktails anzeigen
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    result.put(id, name);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws SQLException {
        Map<Integer, String> cocktails = getCocktails();

        for (Map.Entry<Integer, String> entry : cocktails.entrySet()) {
            System.out.println("Cocktail ID: " + entry.getKey() + ", Name: " + entry.getValue());
        }
    }

}
