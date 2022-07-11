/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.User;
import Model.CurrentUser;
 





/**
 *
 * @author knfab
 */
public abstract class userQuery {
    
    
    public static void setCurrentUserQuery(String userName) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String password = rs.getString("Password");
            int userId = rs.getInt("User_ID");
            Model.CurrentUser cu = new CurrentUser(userId, userName, password);
        }
    
    }
    
    public static String selectUsernamePassword(String userName) throws SQLException{
        String password = null;
        String sql = "SELECT Password FROM users WHERE User_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            password = rs.getString("Password");
            }
        //System.out.println(password);
        return password;
    }
    
    public static ObservableList<User> selectAllUsers() throws SQLException{
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            User result = new User(userId, userName);  
            allUsers.add(result);
        }
        return allUsers;
    }
    
}
