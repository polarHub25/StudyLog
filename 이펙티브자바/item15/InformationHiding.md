``` java 
public class DatabaseManager {
    private Connection connection;

    public DatabaseManager(String url, String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}



public class EmployeeService {
    private DatabaseManager dbManager;

    public EmployeeService(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void displayEmployeeDetails(String employeeId) {
        try {
            String query = "SELECT * FROM Employees WHERE ID = '" + employeeId + "'";
            ResultSet resultSet = dbManager.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
                // 추가적인 정보 출력
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


`````
