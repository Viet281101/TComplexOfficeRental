package com.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddOfficeSpaceServlet")
public class AddOfficeSpaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/TComplexOfficeRental-1.0-SNAPSHOT?useUnicode=true&characterEncoding=UTF-8";
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

		String[] validStatuses = {"Trống", "Hạ tầng", "Đầy đủ"};
		if (!Arrays.asList(validStatuses).contains(status)) {
			response.getWriter().println("Invalid status value.");
			response.getWriter().println("Received status: " + status);
			return;
		}

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

			String sql = "INSERT INTO office_space (code, area, status, floor, type, rent_price, start_date, end_date, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
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

			response.getWriter().println("Office space added successfully!");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			response.getWriter().println("Error adding office space: " + e.getMessage());
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

	private boolean isValidStatus(String status) {
		return "Trống".equals(status) || "Hạ tầng".equals(status) || "Đầy đủ".equals(status);
	}
}
