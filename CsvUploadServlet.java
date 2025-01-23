package com;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@WebServlet("/upload")
@MultipartConfig
public class CsvUploadServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Part filePart = request.getPart("file");
		if (filePart == null) {
			response.getWriter().println("No file uploaded.");
			return;
		}
		try (InputStream fileContent = filePart.getInputStream();
				InputStreamReader reader = new InputStreamReader(fileContent, StandardCharsets.UTF_8);
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
			Map<String, Integer> headerMap = csvParser.getHeaderMap();
			List<String> requiredHeaders = Arrays.asList("Column1", "Column2", "Column3");
			for (String header : requiredHeaders) {
				if (!headerMap.containsKey(header)) {
					response.getWriter().println("Missing required column: " + header);
					return;
				}
			}

			int recordCount = 0;
			for (CSVRecord record : csvParser) {
				try {
					double column1 = parseDouble(record.get("Column1"));
					double column2 = parseDouble(record.get("Column2"));
					double column3 = parseDouble(record.get("Column3"));

					saveCsvData(column1, column2, column3);
					recordCount++;
				} catch (NumberFormatException e) {
					response.getWriter().println(
							"Invalid number format in record " + record.getRecordNumber() + ": " + e.getMessage());
					return;
				} catch (SQLException e) {
					response.getWriter().println(
							"Database error while saving record " + record.getRecordNumber() + ": " + e.getMessage());
					return;
				}
			}

			response.getWriter().println("File uploaded and " + recordCount + " records saved successfully.");
		} catch (IOException e) {
			response.getWriter().println("Error reading the file: " + e.getMessage());
		}
	}

	private double parseDouble(String value) throws NumberFormatException {
		if (value == null || value.trim().isEmpty()) {
			throw new NumberFormatException("Empty or null value.");
		}
		return Double.parseDouble(value);
	}

	private void saveCsvData(double column1, double column2, double column3) throws SQLException {
		try (Connection conn = DBHelper.getConnection()) {
			String sql = "INSERT INTO csv_data (column1, column2, column3) VALUES (?, ?, ?)";
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
				statement.setDouble(1, column1);
				statement.setDouble(2, column2);
				statement.setDouble(3, column3);
				statement.executeUpdate();
			}
		}
	}
}
