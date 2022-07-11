/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.first_level_divisions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author knfab
 */
public abstract class divisionQuery {
    
    public static ObservableList<first_level_divisions> selectAllCountryDivisions(int Id) throws SQLException{
        ObservableList<first_level_divisions> matchingDivisions = FXCollections.observableArrayList();
        String sql = """
                     SELECT first_level_divisions.Division_ID, first_level_divisions.Division, first_level_divisions.Country_ID
                     From client_schedule.first_level_divisions
                     WHERE first_level_divisions.Country_ID = ?
                     """; 
                     
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, Id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");
            Model.first_level_divisions result = new first_level_divisions(divisionId, division, countryId);
            matchingDivisions.add(result);
                        }
        return matchingDivisions;
        
        
    }
    
    public static ObservableList<first_level_divisions> selectAllCountryDivisions() throws SQLException{
        ObservableList<first_level_divisions> matchingDivisions = FXCollections.observableArrayList();
        String sql = """
                     SELECT first_level_divisions.Division_ID, first_level_divisions.Division, first_level_divisions.Country_ID
                     From client_schedule.first_level_divisions
                     
                     """; 
                     
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");
            Model.first_level_divisions result = new first_level_divisions(divisionId, division, countryId);
            matchingDivisions.add(result);
                        }
        return matchingDivisions;
        
        
    }
    
}
