package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class RunDB {

    private Connection connection;

    public RunDB(){}

    public ArrayList<String[]> getCowParent(String cowCode){
        ArrayList<String[]> dataList = new ArrayList<>();
        try {
            openDatabaseConnection();
            try(PreparedStatement statement = connection.prepareStatement(
                    "WITH RECURSIVE cow_family_tree(id, momId, dadId) AS ("+
                            " SELECT id, momId, dadId"+
                            " FROM cow"+
                            " WHERE id = '"+cowCode+"'"+
                            " UNION ALL"+
                            " SELECT cow.id, cow.momId, cow.dadId"+
                            " FROM cow"+
                            " JOIN cow_family_tree ON cow_family_tree.momId = cow.id"+
                            " WHERE cow_family_tree.momId IS NOT NULL)"+
                    " SELECT id, momId, dadId"+
                    " FROM cow_family_tree")){
                ResultSet resultSet = statement.executeQuery();
                int column = statement.getMetaData().getColumnCount();
                while (resultSet.next()){
                    String[] data = new String[column];
                    for (int i=1;i <= column;i++){
                        data[i-1] = resultSet.getString(i);
                    }
                    System.out.println(Arrays.toString(data));
                    dataList.add(data);
                }
            };
            closeDatabaseConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataList;
    }

    public ArrayList<String[]> getAllCows(){
        ArrayList<String[]> dataList = new ArrayList<>();
        try {
            openDatabaseConnection();
            try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM cow")){
                ResultSet resultSet = statement.executeQuery();
                int column = statement.getMetaData().getColumnCount();
                while (resultSet.next()){
                    String[] data = new String[column];
                    for (int i=1;i <= column;i++){
                        data[i-1] = resultSet.getString(i);
                    }
                    dataList.add(data);
                }
            };
            closeDatabaseConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataList;
    }

    public ArrayList<String[]> getAllCorrectBreed(){
        ArrayList<String[]> dataList = new ArrayList<>();
        try {
            openDatabaseConnection();
            try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT cowId, GROUP_CONCAT(CONCAT(breedId, ' : ', percen) SEPARATOR ' , ') AS Breed," +
                            " SUM(percen) AS total_percent"+
                            " FROM cowbreed"+
                            " GROUP BY cowId"+
                            " HAVING total_percent = 100")){
                ResultSet resultSet = statement.executeQuery();
                int column = statement.getMetaData().getColumnCount();
                while (resultSet.next()){
                    String[] data = new String[column];
                    for (int i=1;i <= column;i++){
                        data[i-1] = resultSet.getString(i);
                    }
                    dataList.add(data);
                }
            };
            closeDatabaseConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataList;
    }

    public ArrayList<String[]> getAllErrorBreed(){
        ArrayList<String[]> dataList = new ArrayList<>();
        try {
            openDatabaseConnection();
            try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT cowId, GROUP_CONCAT(CONCAT(breedId, ' : ', percen) SEPARATOR ' , ') AS Breed," +
                            " SUM(percen) AS total_percent"+
                            " FROM cowbreed"+
                            " GROUP BY cowId"+
                            " HAVING total_percent != 100")){
                ResultSet resultSet = statement.executeQuery();
                int column = statement.getMetaData().getColumnCount();
                while (resultSet.next()){
                    String[] data = new String[column];
                    for (int i=1;i <= column;i++){
                        data[i-1] = resultSet.getString(i);
                    }
                    dataList.add(data);
                }
            };
            closeDatabaseConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataList;
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
