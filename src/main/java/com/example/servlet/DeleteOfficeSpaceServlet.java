package com.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteOfficeSpaceServlet")
public class DeleteOfficeSpaceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            response.getWriter().println("<script>");
            response.getWriter().println("alert('Invalid code received.');");
            response.getWriter().println("window.location.href = 'search_office_space.jsp';");
            response.getWriter().println("</script>");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/TComplexOfficeRental", "root", "Qazqaz123.");

            String sql = "DELETE FROM office_space WHERE code = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, code.trim());

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                response.getWriter().println("<script>");
                response.getWriter().println("alert('Office space with code " + code + " deleted successfully!');");
                response.getWriter().println("window.location.href = 'search_office_space.jsp';");
                response.getWriter().println("</script>");
            } else {
                response.getWriter().println("<script>");
                response.getWriter().println("alert('Failed to delete office space with code " + code + ".');");
                response.getWriter().println("window.location.href = 'search_office_space.jsp';");
                response.getWriter().println("</script>");
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<script>");
            response.getWriter().println("alert('Error: " + e.getMessage() + "');");
            response.getWriter().println("window.location.href = 'search_office_space.jsp';");
            response.getWriter().println("</script>");
        }
    }
}

