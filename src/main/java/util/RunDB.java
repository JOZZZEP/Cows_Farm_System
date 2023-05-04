package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class RunDB {

    private Connection connection;

    public RunDB(){

    }

    public void getData(String table, String value){
        try {
            openDatabaseConnection();
            try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + table +" "+ value)){
                ResultSet resultSet = statement.executeQuery();
                int column = statement.getMetaData().getColumnCount();
                while (resultSet.next()){
                    String[] data = new String[column];
                    for (int i=1;i <= column;i++){
                        data[i-1] = resultSet.getString(i);
                    }
                    System.out.println(Arrays.toString(data));
                }
            };
            closeDatabaseConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void openDatabaseConnection() throws SQLException {
        System.out.println("Connecting to the database....");
        connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/farmdb",
                "root",
                ""
        );
        System.out.println("Connection valid : " + connection.isValid(5)) ;
    }

    private void closeDatabaseConnection() throws SQLException{
        System.out.println("Closing database connection...");
        connection.close();
        System.out.println("Connection valid : "+connection.isValid(5));
    }
}
