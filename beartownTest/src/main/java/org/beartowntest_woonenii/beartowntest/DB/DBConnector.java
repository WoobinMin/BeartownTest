package org.beartowntest_woonenii.beartowntest.DB;

import java.sql.*;

// JDBD 이용했습니다.

public class DBConnector {
    private static final String JDBC_URL = "jdbc:mysql://localhost:[포트]/giveaway";
    private static final String JDBC_USERNAME = "your_username";
    private static final String JDBC_PASSWORD = "your_password";

    public static int GetUserHotTimeCount(String targetUserName)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
                if (!isUserExist(connection, targetUserName)) {
                    addUser(connection, targetUserName);
                }

                int userCount = getUserCount(connection, targetUserName);
                return userCount;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static boolean isUserExist(Connection connection, String userName) throws SQLException {
        String query = "SELECT * FROM user WHERE user_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private static void addUser(Connection connection, String userName) throws SQLException {
        String insertQuery = "INSERT INTO user (user_name, userCount) VALUES (?, 0)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setString(1, userName);
            insertStatement.executeUpdate();
        }
    }

    private static int getUserCount(Connection connection, String userName) throws SQLException {
        String selectQuery = "SELECT userCount FROM user WHERE user_name = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setString(1, userName);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("userCount");
                } else {
                    throw new SQLException("User not found: " + userName);
                }
            }
        }
    }

    public int getTotalCount() {
        int totalCount = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
                String sql = "SELECT totalCount FROM globalDB";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            totalCount = resultSet.getInt("totalCount");
                        }
                    }
                }


            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return totalCount;
    }

    public void setTotalCount(int count)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
                String updateSql = "INSERT INTO globalDB (totalCount) VALUES (?)";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    updateStatement.setInt(1, count);
                    updateStatement.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
