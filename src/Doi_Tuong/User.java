package DoiTuong;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import DoiTuong.UserResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import DAO.ConnectionUtils;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.sql.SQLException;


/**
 *
 * @author nhuyc
 */
 
public class User {
   
    private String username;
    private String password;
    private String email;
    private String sdt;
    private String name;

    public User(String username, String password, String email, String sdt, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.sdt = sdt;
        this.name = name;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }   

    public String getUsername() {
        return username;
    }

    public User() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", email=" + email + ", sdt=" + sdt + ", name=" + name + '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public UserResponse checkUser() {
        try (Connection con = ConnectionUtils.getMyConnection()) {
            String query = "SELECT ACCOUNT_ID "
                    + "FROM ACCOUNT "
                    + "WHERE USERNAME = ? AND PASSWORD_HASH = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.username);
            ps.setString(2, this.password);
            ResultSet resultSet = ps.executeQuery();    

            UserResponse response = new UserResponse();

            if(resultSet.next()) {
                response.setStatus(true);
                response.setUser_id(resultSet.getInt("ACCOUNT_ID"));

                // Lấy role cho account
                String query_r = "SELECT ROLE.ROLE_NAME "
                        + "FROM ACCOUNT_ROLE "
                        + "JOIN ROLE ON ACCOUNT_ROLE.ROLE_ID = ROLE.ROLE_ID "
                        + "WHERE ACCOUNT_ROLE.ACCOUNT_ID = ?";
                PreparedStatement ps_r = con.prepareStatement(query_r);
                ps_r.setInt(1, response.getUser_id()); 

                ResultSet resultSet_r = ps_r.executeQuery();
                if (resultSet_r.next()) {
                    response.setRole(resultSet_r.getString("ROLE_NAME"));
                }

                return response;
            } else {
                response.setStatus(false);
                return response;
            }
        } catch(Exception e){
            System.out.print(e);
        }
        return new UserResponse(false, -1 ,null);
}

    public int themUser() 
    {
        int i = 0;
        // TODO add your handling code here:
        try (Connection con = ConnectionUtils.getMyConnection()) {
        
//            String query = "INSERT INTO "
//                    + "DOIBONG(MAD,TENDOI,QUOCGIA)"
//                    +" VALUES('"
//                    +maDoi+"','"+tenDoi+"','"+quocGia+"')";

            String query = "INSERT INTO ACCOUNT(USERNAME,PASSWORD_HASH,FULL_NAME,EMAIL,SDT) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.username);
            ps.setString(2, this.password);
            ps.setString(3,this.name);
            ps.setString(4,this.email);
            ps.setString(5,this.sdt);
            i = ps.executeUpdate();
//            Statement stat = con.createStatement();
//            i = stat.executeUpdate(query);
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        return i;
    }   
}
