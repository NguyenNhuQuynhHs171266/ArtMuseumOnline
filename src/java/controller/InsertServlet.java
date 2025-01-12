/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAL.ExhibitionDAO;
import DAL.PaintingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Exhibition;
import model.Painting;

/**
 *
 * @author Admin
 */
public class InsertServlet extends HttpServlet {
   
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
            out.println("<title>Servlet InsertServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertServlet at " + request.getContextPath () + "</h1>");
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
        
       
        Exhibition = ExhibitionDAO.INSTANCE.getAllExhibitions();
        
      
       
      
        request.setAttribute("exhibition", Exhibition);
        
        request.getRequestDispatcher("views/insert.jsp").forward(request, response);
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
        int id = PaintingDAO.INSTANCE.getAllPaintings().size()+1;
        String imagePath = request.getParameter("imagePath");
        String title = request.getParameter("title");
        String artist = request.getParameter("artist");
        String description = request.getParameter("description");
        int yearCreated = Integer.parseInt(request.getParameter("yearCreated")); 
        


        String[] exhibitionIds = request.getParameterValues("exhibitionId[]");
        
        List<Exhibition> exhibitions = new ArrayList<>();
        


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
        PaintingDAO.INSTANCE.insertPainting(pt);
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
