/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

//Need to create a calendar utility class

/**
 *
 * @author knfab
 */
public class CurrentUser {
    private static int userId;
    private static String userName;
    private static String password;
    private String createdBy;
    private String lastUpdateBy;
    

     


public CurrentUser(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        CurrentUser.userId = userId;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        CurrentUser.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        CurrentUser.password = password;
    }

 }