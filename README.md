# CSV_Data_Aggregation
CSV Data Aggregation Application

The CSV Data Aggregation Application is a Java-based web application designed to handle CSV file uploads, store the data in a MySQL database, and perform aggregation operations such as SUM, AVG, MIN, and MAX on specific columns. The application includes two primary features: file upload and aggregation functionality, both accessible through HTTP endpoints.

The file upload feature allows users to upload CSV files containing the required columns: Column1, Column2, and Column3. The application validates the uploaded file to ensure that these mandatory columns are present, stores the data in a MySQL database table named csv_data, and provides feedback on the upload status. If the file is missing any required columns or contains invalid data, the application will return appropriate error messages.

The aggregation feature enables users to perform calculations such as SUM, AVG, MIN, and MAX on specific columns of the stored data. Users can access this feature by sending HTTP GET requests to the /aggregate endpoint with query parameters specifying the column name and the type of aggregation. For example, to calculate the average of Column1, users can send a request to /aggregate?column=Column1&aggregationType=AVG.


---

Tech Stack

Backend: Java Servlets
Database: MySQL

Libraries:
Apache Commons CSV for parsing CSV files
MySQL Connector/J for database connectivity
Server: Apache Tomcat

---

Project Setup

1. Database Setup

1. Create a MySQL database named csv_aggregator.
2. Run the following SQL query to create the csv_data table:
CREATE TABLE csv_data (
    id INT AUTO_INCREMENT PRIMARY KEY,
    column1 DOUBLE,
    column2 DOUBLE,
    column3 DOUBLE
);
3. Update the database credentials in the DBHelper.java file:

private static final String DB_URL = "jdbc:mysql://localhost:3306/csv_aggregator";
private static final String DB_USER = "your_username";
private static final String DB_PASSWORD = "your_password";

2. Application Setup

1. Clone the repository or download the source code.

2. Add the required libraries to your project:

Apache Commons CSV (commons-csv.jar)

MySQL Connector/J (mysql-connector-java.jar)

3. Deploy the application to a servlet container such as Apache Tomcat.

3. Running the Application

1. Start the MySQL database and the servlet container.

2. Access the following endpoints:

/upload: To upload a CSV file.

/aggregate: To perform aggregation operations (e.g., /aggregate?column=Column1&aggregationType=SUM).

---

How to Use

File Upload: Use the /upload endpoint to upload a CSV file. Ensure the file includes the columns Column1, Column2, and Column3. The application will validate and store the data in the database.

Aggregation: Use the /aggregate endpoint to perform operations. For example:

To calculate the sum of Column1: /aggregate?column=Column1&aggregationType=SUM

To calculate the average of Column2: /aggregate?column=Column2&aggregationType=AVG

---

Sample Test Data

Here is a sample CSV file (test_data.csv) to test the application:

Column1,Column2,Column3
10,20,30
15,25,35
20,30,40

Example aggregation results:

/aggregate?column=Column1&aggregationType=SUM → 45

/aggregate?column=Column2&aggregationType=AVG → 25

/aggregate?column=Column3&aggregationType=MAX → 40

---
Contributions
Contributions are welcome! If you want to improve the application, feel free to submit a pull request or raise an issue. Any suggestions or feedback are highly appreciated.

This application is perfect for scenarios requiring quick CSV file processing and data aggregation. Happy coding!
