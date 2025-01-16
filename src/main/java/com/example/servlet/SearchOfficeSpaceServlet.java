package com.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchOfficeSpaceServlet")
public class SearchOfficeSpaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/TComplexOfficeRental?useUnicode=true&characterEncoding=UTF-8";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "Qazqaz123.";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String rentPrice = request.getParameter("rentPrice");
		String floor = request.getParameter("floor");

		response.setContentType("text/html");
		StringBuilder html = new StringBuilder();
		html.append("<h1>Search Results</h1>");
		html.append("<table border='1'>");
		html.append("<tr><th>ID</th><th>Code</th><th>Area</th><th>Status</th><th>Floor</th><th>Type</th><th>Rent Price</th><th>Start Date</th><th>End Date</th><th>Description</th></tr>");

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

			StringBuilder query = new StringBuilder("SELECT * FROM office_space WHERE 1=1");
			if (type != null && !type.isEmpty()) {
				query.append(" AND type = ?");
			}
			if (rentPrice != null && !rentPrice.isEmpty()) {
				query.append(" AND rent_price <= ?");
			}
			if (floor != null && !floor.isEmpty()) {
				query.append(" AND floor = ?");
			}
			query.append(" ORDER BY area ASC");

			stmt = conn.prepareStatement(query.toString());

			int index = 1;
			if (type != null && !type.isEmpty()) {
				stmt.setString(index++, type);
			}
			if (rentPrice != null && !rentPrice.isEmpty()) {
				stmt.setDouble(index++, Double.parseDouble(rentPrice));
			}
			if (floor != null && !floor.isEmpty()) {
				stmt.setInt(index++, Integer.parseInt(floor));
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				html.append("<tr>");
				html.append("<td>").append(rs.getInt("id")).append("</td>");
				html.append("<td>").append(rs.getString("code")).append("</td>");
				html.append("<td>").append(rs.getDouble("area")).append("</td>");
				html.append("<td>").append(rs.getString("status")).append("</td>");
				html.append("<td>").append(rs.getInt("floor")).append("</td>");
				html.append("<td>").append(rs.getString("type")).append("</td>");
				html.append("<td>").append(rs.getDouble("rent_price")).append("</td>");
				html.append("<td>").append(rs.getDate("start_date")).append("</td>");
				html.append("<td>").append(rs.getDate("end_date")).append("</td>");
				html.append("<td>").append(rs.getString("description")).append("</td>");
				html.append("</tr>");
			}
			html.append("</table>");
			response.getWriter().println(html.toString());

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Error: " + e.getMessage());
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
