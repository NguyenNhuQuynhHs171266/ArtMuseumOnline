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
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class PaintingDAO extends DBContext {

    public static PaintingDAO INSTANCE = new PaintingDAO();

    public List<Painting> getAllPaintings() {
        List<Painting> paintings = new ArrayList<>();
        String sql = "SELECT * FROM paintings";
        try (
                PreparedStatement statement = connect.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Painting painting = new Painting();
                painting.setPaintingId(rs.getInt("painting_id"));
                painting.setTitle(rs.getString("title"));
                painting.setArtist(rs.getString("artist"));
                painting.setDescription(rs.getString("description"));
                painting.setYearCreated(rs.getInt("year_created"));
                painting.setImagePath(rs.getString("image_path"));
                paintings.add(painting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paintings;
    }

    //// Phương thức lấy tất cả các tranh cùng với tên triển lãm
    public List<Painting> getAllPaintingsWithExhibition() {
        List<Painting> paintings = new ArrayList<>();
        String sql = "SELECT p.painting_id, p.title, p.artist, p.description, p.year_created, p.image_path, e.name AS exhibition_name "
                + "FROM paintings p "
                + "LEFT JOIN painting_exhibition pe ON p.painting_id = pe.painting_id "
                + "LEFT JOIN exhibitions e ON pe.exhibition_id = e.exhibition_id";
        try {
            PreparedStatement statement = connect.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // Tạo đối tượng Painting
                Painting painting = new Painting();
                painting.setPaintingId(rs.getInt("painting_id"));
                painting.setTitle(rs.getString("title"));
                painting.setArtist(rs.getString("artist"));
                painting.setDescription(rs.getString("description"));
                painting.setYearCreated(rs.getInt("year_created"));
                painting.setImagePath(rs.getString("image_path"));

                // Tạo đối tượng Exhibition
                Exhibition exhibition = new Exhibition();
                exhibition.setName(rs.getString("exhibition_name"));

                // Thêm Exhibition vào danh sách của Painting
                List<Exhibition> exhibitions = new ArrayList<>();
                exhibitions.add(exhibition);
                painting.setExhibitions(exhibitions);

                // Thêm Painting vào danh sách
                paintings.add(painting);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paintings;

    }

    // Lấy danh sách tất cả các tranh trong một exhibition
    public List<Painting> getPaintingsByExhibition(int exhibitionId) {
        List<Painting> paintings = new ArrayList<>();
        String sql = "SELECT p.*, e.name, e.exhibition_id FROM Paintings p JOIN painting_exhibition \n"
                + "ep ON p.painting_id = ep.painting_id \n"
                + "JOIN exhibitions e ON ep.exhibition_id = e.exhibition_id \n"
                + "WHERE e.exhibition_id = ?";
        try (
                PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, exhibitionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Painting painting = new Painting();
                painting.setPaintingId(rs.getInt("painting_id"));
                painting.setDescription(rs.getString("description"));
                painting.setImagePath(rs.getString("image_path"));
                painting.setTitle(rs.getString("title"));
                painting.setArtist(rs.getString("artist"));
                painting.setYearCreated(rs.getInt("year_created"));

                // Set exhibition thông tin
                Exhibition exhibition = new Exhibition();
                exhibition.setExhibitionId(rs.getInt("exhibition_id"));
                exhibition.setName(rs.getString("name"));

                painting.getExhibitions().add(exhibition);

                // câu lệnh trên là  painting.getExhibitions() nó sẽ trả về 1 danh sách
                // ban đầu chưa có gì thì sẽ là null sau đó nó sẽ add thằng exhibition 
                // có exhibitionId từ trong câu lệnh truy vấn
                paintings.add(painting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paintings;
    }

    // phương thức lấy ra painting theo painting id
    public Painting getPaintingById(int paintingId) {
        Painting painting = null;

        String sql = "SELECT p.*, e.name AS exhibition_name, ep.exhibition_id FROM Paintings p JOIN painting_exhibition \n"
                + "ep ON p.painting_id = ep.painting_id \n"
                + "JOIN exhibitions e ON ep.exhibition_id = e.exhibition_id \n"
                + "WHERE p.painting_id = ?";

        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, paintingId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                // Tạo đối tượng Exhibition
                Exhibition exhibition = new Exhibition();
                exhibition.setExhibitionId(rs.getInt("exhibition_id"));
                exhibition.setName(rs.getString("exhibition_name"));
                // Thêm Exhibition vào danh sách của Painting
                List<Exhibition> exhibitions = new ArrayList<>();
                exhibitions.add(exhibition);

                // Tạo đối tượng Painting
                painting = new Painting(rs.getInt("painting_id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("description"),
                        rs.getInt("year_created"),
                        rs.getString("image_path"), exhibitions);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return painting;

    }

    // phương thức cập nhật dữ liệu Painting tương đương với 
    // bảng exhibition, bảng exhibitionid
    public void updatePainting(Painting painting) {
        try {

            String sqlUpdatePainting = "UPDATE paintings SET title = ?, artist = ?, \n"
                    + "description = ?, year_created = ?, \n"
                    + "image_path = ? WHERE painting_id = ?";
            PreparedStatement ps = connect.prepareStatement(sqlUpdatePainting);
            ps.setString(1, painting.getTitle());
            ps.setString(2, painting.getArtist());
            ps.setString(3, painting.getDescription());
            ps.setInt(4, painting.getYearCreated());
            ps.setString(5, painting.getImagePath());
            ps.setInt(6, painting.getPaintingId());

            ps.executeUpdate();

            // Xóa các liên kết hiện tại trong bảng painting_exhibition
            String sqlDeleteExhibitionLinks = "DELETE FROM painting_exhibition WHERE painting_id = ?";
            ps = connect.prepareStatement(sqlDeleteExhibitionLinks);
            ps.setInt(1, painting.getPaintingId());
            ps.executeUpdate();

            // Thêm các liên kết mới vào bảng painting_exhibition
            String sqlInsertExhibitionLinks = "INSERT INTO painting_exhibition (painting_id, exhibition_id) VALUES (?, ?)";
            ps = connect.prepareStatement(sqlInsertExhibitionLinks);

            for (Exhibition exhibition : painting.getExhibitions()) {
                ps.setInt(1, painting.getPaintingId());
                ps.setInt(2, exhibition.getExhibitionId());
                ps.executeUpdate(); // Thực thi từng lệnh riêng lẻ
            }

        } catch (Exception e) {
        }

    }
    //phương thức xóa painting dựa trên id đã cho

    public void deletePainting(int paintingId) {
        String deleteFromPaintingExhibition = "DELETE FROM painting_exhibition WHERE painting_id = ?";
        String deleteFromPaintings = "DELETE FROM paintings WHERE painting_id = ?";

        try {

            PreparedStatement ps1 = connect.prepareStatement(deleteFromPaintingExhibition);
            PreparedStatement ps2 = connect.prepareStatement(deleteFromPaintings);

            // Xóa các bản ghi trong bảng painting_exhibition liên quan đến paintingId
            ps1.setInt(1, paintingId);
            ps1.executeUpdate();
            // Xóa bức tranh từ bảng paintings

            ps2.setInt(1, paintingId);
            ps2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    // phương thức tạo thêm Painting

    public void insertPainting(Painting painting) {

        String insertPaintingSQL = "INSERT INTO paintings (title, artist, description, year_created, image_path) VALUES (?, ?, ?, ?, ?)";
        String insertPaintingExhibitionSQL = "INSERT INTO painting_exhibition (painting_id, exhibition_id) VALUES (?, ?)";
        try {
PreparedStatement ps1 = connect.prepareStatement(insertPaintingSQL, Statement.RETURN_GENERATED_KEYS);
           
PreparedStatement ps2 = connect.prepareStatement(insertPaintingExhibitionSQL);

            // Thêm tranh vào bảng paintings
            ps1.setString(1, painting.getTitle());
            ps1.setString(2, painting.getArtist());
            ps1.setString(3, painting.getDescription());
            ps1.setInt(4, painting.getYearCreated());
            ps1.setString(5, painting.getImagePath());

            ps1.executeUpdate();

            // Lấy painting_id vừa được sinh tự động
            ResultSet rs = ps1.getGeneratedKeys();

            if (rs.next()) {
                int paintingId = rs.getInt(1);  // Lấy giá trị khóa chính (ví dụ: painting_id)
                
                 // Chèn vào bảng painting_exhibition với mỗi triển lãm liên kết
                 for (Exhibition exhibition : painting.getExhibitions()) {
                            ps2.setInt(1, paintingId);  // painting_id vừa tạo
                            ps2.setInt(2, exhibition.getExhibitionId());  // exhibition_id từ đối tượng Exhibition
                            ps2.executeUpdate();
                        }
                
            }
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        List<Painting> paintings = new ArrayList<>();

//        paintings = PaintingDAO.INSTANCE.getPaintingsByExhibition(1);
//        for (Painting painting : paintings) {
//            System.out.println(painting);
//
//        }
        paintings = PaintingDAO.INSTANCE.getAllPaintings();
        System.out.println(paintings.size());
    }
}
