/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Country;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author knfab
 */
public abstract class countryQuery {
    
    public static ObservableList<Country> selectAllCountries() throws SQLException{
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        String sql = """
                     SELECT countries.Country_ID, countries.Country
                     From client_schedule.countries
                     """; 
                     
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int countryId = rs.getInt("Country_ID");
            String country = rs.getString("Country");
            Model.Country result = new Country(countryId, country);
            allCountries.add(result);
           
            
            }
        return allCountries;
    }
}
