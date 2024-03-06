package org.beartowntest_woonenii.beartowntest.DB;

import org.beartowntest_woonenii.beartowntest.BeartownTest;

import java.sql.*;

// JDBD 이용했습니다.

public class DBConnector {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/giveaway";
    private static final String JDBC_USERNAME = "beartown";
    private static final String JDBC_PASSWORD = "1111";

    public static int getHotTimeValue(String userName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;

        int hotTimeValue = 0;

        try {
            // JDBC 드라이버 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            statement = connection.createStatement();

            // 테이블 존재 여부 확인 쿼리
            String tableExistsQuery = "SHOW TABLES LIKE ?";
            preparedStatement = connection.prepareStatement(tableExistsQuery);
            preparedStatement.setString(1, userName);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // 테이블이 존재하는 경우, 해당 테이블에서 hotTime 값을 가져옴
                String selectQuery = "SELECT hotTime FROM " + userName;
                preparedStatement = connection.prepareStatement(selectQuery);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    hotTimeValue = resultSet.getInt("hotTime");
                }
            } else {
                // 테이블이 존재하지 않는 경우, 테이블을 생성하고 0을 반환
                String createTableQuery = "CREATE TABLE " + userName + " (hotTime INT DEFAULT 0)";
                statement.executeUpdate(createTableQuery);
                createTableQuery = "INSERT INTO " + userName + " (hotTime) VALUES (0)";
                statement.executeUpdate(createTableQuery);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return hotTimeValue;
    }

    public static void incrementHotTimeValue(String userName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            // JDBC 드라이버 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            // 테이블 존재 여부 확인 쿼리
            String tableExistsQuery = "SHOW TABLES LIKE ?";
            preparedStatement = connection.prepareStatement(tableExistsQuery);
            preparedStatement.setString(1, userName);

            resultSet = preparedStatement.executeQuery();

            statement = connection.createStatement();

            if (resultSet.next()) {
                // 테이블이 존재하는 경우, 해당 테이블의 hotTime 값을 1 올려줌
                String updateQuery = "UPDATE " + userName + " SET hotTime = hotTime + 1";
                statement.executeUpdate(updateQuery);
            } else {
                // 테이블이 존재하지 않는 경우, 테이블을 생성하고 0을 반환
                String createTableQuery = "CREATE TABLE " + userName + " (hotTime INT DEFAULT 1)";
                statement.executeUpdate(createTableQuery);
                createTableQuery = "INSERT INTO " + userName + " (hotTime) VALUES (1)";
                statement.executeUpdate(createTableQuery);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static int getTotalCount() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;

        int totalCount = 0;

        try {
            // JDBC 드라이버 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            statement = connection.createStatement();

            // 테이블 존재 여부 확인 쿼리
            String tableExistsQuery = "SHOW TABLES LIKE ?";
            preparedStatement = connection.prepareStatement(tableExistsQuery);
            preparedStatement.setString(1, "totalCount");

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // 테이블이 존재하는 경우, 해당 테이블에서 hotTime 값을 가져옴
                String selectQuery = "SELECT count FROM totalCount";
                preparedStatement = connection.prepareStatement(selectQuery);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    totalCount = resultSet.getInt("count");
                }
            } else {
                // 테이블이 존재하지 않는 경우, 테이블을 생성하고 0을 반환
                String createTableQuery = "CREATE TABLE totalCount (count INT DEFAULT 0)";
                statement.executeUpdate(createTableQuery);
                createTableQuery = "INSERT INTO totalCount (count) VALUES (0)";
                statement.executeUpdate(createTableQuery);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return totalCount;
    }

    public static void increaseTotalCount() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            // JDBC 드라이버 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            // 테이블 존재 여부 확인 쿼리
            String tableExistsQuery = "SHOW TABLES LIKE ?";
            preparedStatement = connection.prepareStatement(tableExistsQuery);
            preparedStatement.setString(1, "totalCount");

            resultSet = preparedStatement.executeQuery();

            statement = connection.createStatement();

            if (resultSet.next()) {
                // 테이블이 존재하는 경우, 해당 테이블의 hotTime 값을 1 올려줌
                String updateQuery = "UPDATE totalCount SET count = count + 1";
                statement.executeUpdate(updateQuery);
            } else {
                // 테이블이 존재하지 않는 경우, 테이블을 생성하고 0을 반환
                String createTableQuery = "CREATE TABLE totalCount (count INT DEFAULT 1)";
                statement.executeUpdate(createTableQuery);
                createTableQuery = "INSERT INTO totalCount (count) VALUES (1)";
                statement.executeUpdate(createTableQuery);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
