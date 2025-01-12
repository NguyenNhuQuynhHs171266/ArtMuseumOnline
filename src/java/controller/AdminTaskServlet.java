/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Painting;
import model.Exhibition;
import DAL.DAO;
import DAL.ExhibitionDAO;
import DAL.PaintingDAO;
import jakarta.servlet.http.HttpSession;


/**
 *
 * @author Admin
 */
public class AdminTaskServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SellerTaskServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SellerTaskServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        List<Painting> Painting = new ArrayList<>();
        List<Exhibition> Exhibition = new ArrayList<>();
        
        Painting = PaintingDAO.INSTANCE.getAllPaintingsWithExhibition();
        Exhibition = ExhibitionDAO.INSTANCE.getAllExhibitions();
        
      
       
      
         request.setAttribute("painting", Painting);
        request.setAttribute("exhibition", Exhibition);
        request.getRequestDispatcher("views/list.jsp").forward(request, response);
       
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String exhibitionid_raw = request.getParameter("exhibitionid");
    int exhibitionid = -1;

    // Kiểm tra giá trị "exhibitionid" từ request
    if (exhibitionid_raw != null && !exhibitionid_raw.equals("all")) {
        try {
            exhibitionid = Integer.parseInt(exhibitionid_raw);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    // Lấy danh sách Painting dựa trên exhibitionid
    List<Painting> paintings;
    if (exhibitionid == -1) { // Nếu "all" được chọn
        paintings = PaintingDAO.INSTANCE.getAllPaintingsWithExhibition();
    } else {
        paintings = PaintingDAO.INSTANCE.getPaintingsByExhibition(exhibitionid);
    }

    // Đặt danh sách paintings và human types vào request attribute
    request.setAttribute("painting", paintings);

    List<Exhibition> exhibition = ExhibitionDAO.INSTANCE.getAllExhibitions();
    request.setAttribute("exhibition", exhibition);

    // Đặt typeid vào request để JSP có thể giữ giá trị đã chọn
    request.setAttribute("exhibitionid", exhibitionid);

    // Chuyển tiếp yêu cầu đến trang JSP
    request.getRequestDispatcher("views/list.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
