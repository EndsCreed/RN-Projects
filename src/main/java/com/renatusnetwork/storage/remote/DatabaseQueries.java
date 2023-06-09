package com.renatusnetwork.storage.remote;

import com.renatusnetwork.Projects;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseQueries {

    // Data parsing
    private static Map<String, String> resultSetToMap(ResultSet resultSet) {
        try {
            ResultSetMetaData md = resultSet.getMetaData();
            Map<String, String> results = new HashMap<>();

            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; ++i)
                results.put(md.getColumnName(i), resultSet.getString(i));

            return results;
        } catch (SQLException exception) {
            Projects.getPluginLogger().severe("ERROR: Occurred within DatabaseQueries.resultSetTomap()");
            Projects.getPluginLogger().severe("ERROR:   printing StackTrace=");
            exception.printStackTrace();
        }

        return null;
    }

    public static List<Map<String, String>> getResults(String tableName, String selection, String trailingSQL) {
        List<Map<String, String>> finalResults = new ArrayList<>();

        String query = "SELECT " + selection + " FROM " + tableName;
        if (!trailingSQL.equals(""))
            query = query + " " + trailingSQL;

        try {
            Statement statement = Projects.getDatabaseManager().get().get().createStatement();
            ResultSet results = statement.executeQuery(query);

            while (results.next())
                finalResults.add(resultSetToMap(results));
        } catch (SQLException exception) {
            Projects.getPluginLogger().severe(
                    "ERROR: Occurred within DatabaseQueries.getResults(" + tableName + ", " + trailingSQL + ")"
            );
            Projects.getPluginLogger().severe("ERROR:  query='" + query + "'");
            Projects.getPluginLogger().severe("ERROR:   exception=" + exception.getLocalizedMessage());
        }

        return finalResults;
    }

}
