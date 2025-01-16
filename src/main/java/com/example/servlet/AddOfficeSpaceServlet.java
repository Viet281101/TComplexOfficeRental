package com.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddOfficeSpaceServlet")
public class AddOfficeSpaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/TComplexOfficeRental?useUnicode=true&characterEncoding=UTF-8";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "Qazqaz123.";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String area = request.getParameter("area");
		String status = request.getParameter("status");
		String floor = request.getParameter("floor");
		String type = request.getParameter("type");
		String rentPrice = request.getParameter("rent_price");
		String startDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		String description = request.getParameter("description");

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

			String insertSql = "INSERT INTO office_space (code, area, status, floor, type, rent_price, start_date, end_date, description) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(insertSql);
			stmt.setString(1, code);
			stmt.setDouble(2, Double.parseDouble(area));
			stmt.setString(3, status);
			stmt.setInt(4, Integer.parseInt(floor));
			stmt.setString(5, type);
			stmt.setDouble(6, Double.parseDouble(rentPrice));
			stmt.setString(7, startDate);
			stmt.setString(8, endDate);
			stmt.setString(9, description);
			stmt.executeUpdate();

			String selectSql = "SELECT * FROM office_space";
			stmt = conn.prepareStatement(selectSql);
			ResultSet rs = stmt.executeQuery();

			response.setContentType("text/html");
			response.getWriter().println("<h1>Office Spaces</h1>");
			response.getWriter().println("<table border='1'>");
			response.getWriter().println("<tr><th>ID</th><th>Code</th><th>Area</th><th>Status</th><th>Floor</th><th>Type</th><th>Rent Price</th><th>Start Date</th><th>End Date</th><th>Description</th></tr>");
			while (rs.next()) {
				response.getWriter().println("<tr>");
				response.getWriter().println("<td>" + rs.getInt("id") + "</td>");
				response.getWriter().println("<td>" + rs.getString("code") + "</td>");
				response.getWriter().println("<td>" + rs.getDouble("area") + "</td>");
				response.getWriter().println("<td>" + rs.getString("status") + "</td>");
				response.getWriter().println("<td>" + rs.getInt("floor") + "</td>");
				response.getWriter().println("<td>" + rs.getString("type") + "</td>");
				response.getWriter().println("<td>" + rs.getDouble("rent_price") + "</td>");
				response.getWriter().println("<td>" + rs.getDate("start_date") + "</td>");
				response.getWriter().println("<td>" + rs.getDate("end_date") + "</td>");
				response.getWriter().println("<td>" + rs.getString("description") + "</td>");
				response.getWriter().println("</tr>");
			}
			response.getWriter().println("</table>");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			response.getWriter().println("Error: " + e.getMessage());
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Please use the POST method to submit data.");
	}
}
