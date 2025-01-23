# CSV_Data_Aggregation
CSV Data Aggregation Application
This project is a Java-based web application designed to handle CSV file uploads, store the data in a MySQL database, and perform aggregation operations like SUM, AVG, MIN, and MAX on specific columns. The application consists of a file upload feature and an aggregation feature, both accessible through HTTP endpoints.

The file upload feature allows users to upload CSV files containing columns named Column1, Column2, and Column3. The application validates the file for the presence of these required columns and stores the data in a MySQL database. It ensures data integrity and provides feedback on the upload process, including errors for missing columns or invalid data.

The aggregation feature enables users to perform calculations like SUM, AVG, MIN, and MAX on specific columns of the stored data. Users can access this functionality by sending HTTP GET requests to the /aggregate endpoint with the column name and the type of aggregation as query parameters. For example, to calculate the average of Column1, the request would look like /aggregate?column=Column1&aggregationType=AVG.

To set up the application, ensure that a MySQL database named csv_aggregator is created with a table called csv_data containing columns column1, column2, and column3. Update the database credentials in the DBHelper.java file if they differ from the default settings. The application is built using Java Servlets and requires a servlet container like Apache Tomcat for deployment. Additionally, it uses the Apache Commons CSV library for parsing CSV files and the MySQL Connector/J library for database interaction.

This application is ideal for scenarios where users need to upload and process CSV data while performing quick aggregations. Contributions are welcome via pull requests or issue submissions.

