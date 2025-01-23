package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.annotation.MultipartConfig;
import java.sql.*;
@WebServlet("/aggregate")
@MultipartConfig
public class AggregationServlet extends HttpServlet
{
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String column = request.getParameter("column");
        String aggregationType = request.getParameter("aggregationType");

        if (column != null && aggregationType != null) {
            try {
                double result = performAggregation(column, aggregationType);
                response.getWriter().println("Aggregation result: " + result);
            } catch (SQLException e) {
                response.getWriter().println("Error performing aggregation: " + e.getMessage());
            }
        } else {
            response.getWriter().println("Invalid parameters for aggregation.");
        }
    }

    private double performAggregation(String column, String aggregationType) throws SQLException {
        String sql = buildAggregationQuery(column, aggregationType);
        try (Connection conn = DBHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        }
        return 0.0;
    }
    private String buildAggregationQuery(String column, String aggregationType) {
        switch (aggregationType) {
            case "SUM":
                return "SELECT SUM(" + column + ") FROM csv_data";
            case "AVG":
                return "SELECT AVG(" + column + ") FROM csv_data";
            case "MIN":
                return "SELECT MIN(" + column + ") FROM csv_data";
            case "MAX":
                return "SELECT MAX(" + column + ") FROM csv_data";
            default:
                throw new IllegalArgumentException("Invalid aggregation type");
        }
    }
}
