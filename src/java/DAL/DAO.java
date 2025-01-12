/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;
import java.sql.*;
import model.Admin;

/**
 *
 * @author Admin
 */
public class DAO extends DBContext{
    public static DAO INSTANCE= new DAO();
    
    public Admin check(String user,String pass){
        String sql = "select * from users where username = ? and password=?";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return new Admin( user, pass, rs.getInt("role_id"));
            
            }
            
        } catch (Exception e) {
        }
        return null;
    
    
    
    }
    
     public Admin getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (
             PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Admin(rs.getString("username"), rs.getString("password"), rs.getInt("role_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Không tìm thấy người dùng
    }
     
     public void insertUser(Admin user) {
        String sql = "INSERT INTO users (username, password, role_id) VALUES (?, ?, ?)";
        try (
             PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        
       Admin a= DAO.INSTANCE.check("admin", "admin");
       
       if(a!=null){
           System.out.println(" okkk");
       }else{
           System.out.println("not ok");
       }
        
    }
    
}
