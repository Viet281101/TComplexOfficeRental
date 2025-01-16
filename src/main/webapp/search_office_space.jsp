<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Search Office Spaces</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h1>Search Office Spaces</h1>
	<form action="SearchOfficeSpaceServlet" method="get">
		<label for="type">Office Type:</label>
		<select id="type" name="type">
			<option value="">All</option>
			<option value="Shared Office">Shared Office</option>
			<option value="Private Office">Private Office</option>
		</select>
		
		<label for="rentPrice">Rent Price (less than):</label>
		<input type="number" id="rentPrice" name="rentPrice" min="0" step="0.01">
		
		<label for="floor">Floor:</label>
		<input type="number" id="floor" name="floor" min="1" max="15">
		
		<button type="submit">Search</button>
	</form>
	
	<h2>Current Office Spaces</h2>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Code</th>
			<th>Area</th>
			<th>Status</th>
			<th>Floor</th>
			<th>Type</th>
			<th>Rent Price</th>
			<th>Start Date</th>
			<th>End Date</th>
			<th>Description</th>
		</tr>
		<%
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TComplexOfficeRental", "root", "Qazqaz123.");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM office_space ORDER BY area ASC");

				while (rs.next()) {
		%>
		<tr>
			<td><%= rs.getInt("id") %></td>
			<td><%= rs.getString("code") %></td>
			<td><%= rs.getDouble("area") %></td>
			<td><%= rs.getString("status") %></td>
			<td><%= rs.getInt("floor") %></td>
			<td><%= rs.getString("type") %></td>
			<td><%= rs.getDouble("rent_price") %></td>
			<td><%= rs.getDate("start_date") %></td>
			<td><%= rs.getDate("end_date") %></td>
			<td><%= rs.getString("description") %></td>
		</tr>
		<%
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		%>
	</table>
</body>
</html>
