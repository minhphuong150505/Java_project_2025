package DoiTuong;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author nhuyc
 */
public class UserResponse{

        
        private boolean status;
        private int User_id;
        private String Role;

    public UserResponse() {
    }

    public UserResponse(boolean status, int User_id, String Role) {
        this.status = status;
        this.User_id = User_id;
        this.Role = Role;
    }

    public boolean isStatus() {
        return status;
    }

    public int getUser_id() {
        return User_id;
    }

    public String getRole() {
        return Role;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
        
        
}