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
import model.Painting;
import model.Exhibition;
import DAL.PaintingDAO;
import DAL.ExhibitionDAO;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Admin
 */
public class EditServlet extends HttpServlet {
   
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
            out.println("<title>Servlet EditServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditServlet at " + request.getContextPath () + "</h1>");
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
        List<Exhibition> Exhibition = new ArrayList<>();
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id", id);
        Painting painting = PaintingDAO.INSTANCE.getPaintingById(id);
        
       
        Exhibition = ExhibitionDAO.INSTANCE.getAllExhibitions();
        
      
       
      
         request.setAttribute("painting", painting);
        request.setAttribute("exhibition", Exhibition);
        request.getRequestDispatcher("views/edit.jsp").forward(request, response);
        
        
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
        int id = Integer.parseInt(request.getParameter("id"));  
        String imagePath = request.getParameter("imagePath");
        String title = request.getParameter("title");
        String artist = request.getParameter("artist");
        String description = request.getParameter("description");
        int yearCreated = Integer.parseInt(request.getParameter("yearCreated")); 
        
//        int exhibitionId = Integer.parseInt(request.getParameter("exhibitionId"));
        // Lấy tất cả exhibitionId được chọn từ request

        String[] exhibitionIds = request.getParameterValues("exhibitionId[]");
        
        List<Exhibition> exhibitions = new ArrayList<>();
        
//        Exhibition ex = ExhibitionDAO.INSTANCE.getExhibitionById(exhibitionId);
//        exhibitions.add(ex);

        if (exhibitionIds != null) {
    // Lặp qua tất cả các exhibitionId và lấy từng triển lãm
    for (String exhibitionIdStr : exhibitionIds) {
        int exhibitionId = Integer.parseInt(exhibitionIdStr);
        Exhibition ex = ExhibitionDAO.INSTANCE.getExhibitionById(exhibitionId);
        exhibitions.add(ex);
    }
}           
        
        
            
        
        Painting pt = new Painting(id, title, artist, 
                description, yearCreated, imagePath, exhibitions);
        PaintingDAO.INSTANCE.updatePainting(pt);
        response.sendRedirect("quynh");
        
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
