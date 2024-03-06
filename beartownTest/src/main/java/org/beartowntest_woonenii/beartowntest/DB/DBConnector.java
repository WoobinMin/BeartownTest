package org.beartowntest_woonenii.beartowntest.DB;

import java.sql.*;

// JDBD 이용했습니다.

public class DBConnector {
    private static final String JDBC_URL = "jdbc:mysql://localhost:[포트]/giveaway";
    private static final String JDBC_USERNAME = "beartown";
    private static final String JDBC_PASSWORD = "1111";

    public int getIntValueForUserName(String userName) {
        int value = 0;

        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스에 연결
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
                // SQL 쿼리 작성
                String selectSql = "SELECT intColumn FROM user WHERE user_name = ?";

                try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
                    selectStatement.setString(1, userName);

                    // 쿼리 실행 및 결과 가져오기
                    try (ResultSet resultSet = selectStatement.executeQuery()) {
                        if (resultSet.next()) {
                            // user_name에 해당하는 값이 있을 경우 가져오기
                            value = resultSet.getInt("intColumn");
                        }
                    }
                }

                // user_name에 해당하는 값이 없을 경우 추가
                if (value == 0) {
                    String insertSql = "INSERT INTO user (user_name, intColumn) VALUES (?, ?)";
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                        insertStatement.setString(1, userName);
                        insertStatement.setInt(2, 0);
                        insertStatement.executeUpdate();
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // 오류 처리를 적절히 수행하세요.
        }

        return value;
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
