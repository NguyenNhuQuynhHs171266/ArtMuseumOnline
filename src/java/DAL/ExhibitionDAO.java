/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Exhibition;
import model.Painting;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class ExhibitionDAO extends DBContext  {
    public static ExhibitionDAO INSTANCE= new ExhibitionDAO();
    
    public List<Exhibition> getAllExhibitions() {
        List<Exhibition> exhibitions = new ArrayList<>();
        String sql = "SELECT * FROM exhibitions";
        try (PreparedStatement statement = connect.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Exhibition exhibition = new Exhibition();
                exhibition.setExhibitionId(rs.getInt("exhibition_id"));
                exhibition.setName(rs.getString("name"));
                exhibition.setStartDate(rs.getDate("start_date"));
                exhibition.setEndDate(rs.getDate("end_date"));
                exhibitions.add(exhibition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exhibitions;
    }
    
    // Phương thức lấy tất cả các triển lãm dựa trên painting_id
     public List<Exhibition> getExhibitionsByPaintingId(int paintingId) {
         List<Exhibition> exhibitions = new ArrayList<>();
          String sql = "SELECT e.exhibition_id, e.name, e.start_date, e.end_date "
                   + "FROM exhibitions e "
                   + "JOIN painting_exhibition pe ON e.exhibition_id = pe.exhibition_id "
                   + "WHERE pe.painting_id = ?";
          try {
             PreparedStatement ps = connect.prepareStatement(sql);
              ps.setInt(1, paintingId);
               ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                Exhibition exhibition = new Exhibition();
                exhibition.setExhibitionId(rs.getInt("exhibition_id"));
                exhibition.setName(rs.getString("name"));
                exhibition.setStartDate(rs.getDate("start_date"));
                exhibition.setEndDate(rs.getDate("end_date"));
                exhibitions.add(exhibition);
            }
                
               
         } catch (SQLException e) {
             e.printStackTrace();
         }
        return exhibitions;
          
     
     }
     
     // phương thức lấy ra exhibition dựa trên id
     public Exhibition getExhibitionById(int exhibitionId){
         Exhibition ex =null;
         String sql = "SELECT * FROM exhibitions WHERE exhibition_id = ?";
         try {
             PreparedStatement ps = connect.prepareStatement(sql);
             ps.setInt(1, exhibitionId);
             ResultSet rs = ps.executeQuery();
             if(rs.next()){
             
             // Tạo đối tượng Exhibition từ kết quả truy vấn
                ex = new Exhibition();
                ex.setExhibitionId(rs.getInt("exhibition_id"));
                ex.setName(rs.getString("name"));
                
                ex.setStartDate(rs.getDate("start_date"));
                ex.setEndDate(rs.getDate("end_date"));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
  
         
     
     
        return ex;
     
     
     }
     
     public void insertExhibition(Exhibition exhibition) {
    String sql = "INSERT INTO exhibitions (name, start_date, end_date) VALUES (?, ?, ?)";
    try (PreparedStatement ps = connect.prepareStatement(sql)) {
        ps.setString(1, exhibition.getName());
        ps.setDate(2, exhibition.getStartDate()); // chuyển đổi String sang java.sql.Date
        ps.setDate(3, exhibition.getEndDate());   // chuyển đổi String sang java.sql.Date
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
     
     public void deleteExhibition(int exhibitionId) {
    // Xóa tất cả các bản ghi trong bảng painting_exhibition liên quan đến exhibitionId
    String deletePaintingExhibitionSql = "DELETE FROM painting_exhibition WHERE exhibition_id = ?";
    try (PreparedStatement ps1 = connect.prepareStatement(deletePaintingExhibitionSql)) {
        ps1.setInt(1, exhibitionId);
        ps1.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Xóa triển lãm
    String sql = "DELETE FROM exhibitions WHERE exhibition_id = ?";
    try (PreparedStatement ps2 = connect.prepareStatement(sql)) {
        ps2.setInt(1, exhibitionId);
        ps2.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
     
    public static void main(String[] args) {
        List<Exhibition> Exhibitions = new ArrayList<>();
        
        Exhibitions = ExhibitionDAO.INSTANCE.getAllExhibitions();
        for (Exhibition exhibition : Exhibitions) {
            System.out.println(exhibition);
            
        }
        
    }
    
}
