package com.renatusnetwork.storage.remote;

import com.renatusnetwork.managers.DatabaseManager;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TablesDB {

    public static void configure(DatabaseManager database) {
        List<String> tableNames = get(database.get());
    }

    private static List<String> get(DatabaseConnection connection) {
        List<String> tableNames = new ArrayList<>();
        DatabaseMetaData meta = connection.getMeta();

        try {
            ResultSet rs = meta.getTables(null, null, "%", null);

            while (rs.next())
                tableNames.add(rs.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableNames;
    }

    // Create each table under here
}
