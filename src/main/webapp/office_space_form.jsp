<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Add Office Space for Rent</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h1>Add New Office Space for Rent</h1>
	<form id="officeSpaceForm" action="AddOfficeSpaceServlet" method="post">
		<label for="code">Office Code (*)</label>
		<input type="text" id="code" name="code" required pattern="[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}" title="XXX-XX-XX where X is a number or uppercase alphabet character">
		
		<label for="area">Area (square metre) (*)</label>
		<input type="number" id="area" name="area" required min="20" step="0.01">

		<label for="status">Status (*)</label>
		<select id="status" name="status" required>
			<option value="Available">Available</option>
			<option value="Under Construction">Under Construction</option>
			<option value="Fully Furnished">Fully Furnished</option>
		</select>

		<label for="floor">Floor (*)</label>
		<input type="number" id="floor" name="floor" required min="1" max="15">

		<label for="type">Office Type (*)</label>
		<select id="type" name="type" required>
			<option value="Shared Office">Shared Office</option>
			<option value="Private Office">Private Office</option>
		</select>

		<label for="rent_price">Rental Price (VND) (*)</label>
		<input type="number" id="rent_price" name="rent_price" required min="1000000" step="0.01">

		<label for="start_date">Start Date (*)</label>
		<input type="date" id="start_date" name="start_date" required>

		<label for="end_date">End Date (*)</label>
		<input type="date" id="end_date" name="end_date" required>

		<label for="description">Detailed Description</label>
		<textarea id="description" name="description"></textarea>

		<button type="submit">Save</button>
		<button type="reset">Cancel</button>
	</form>
	<script src="js/validation.js"></script>
	<noscript>You need to enable JavaScript to run this app.</noscript>
</body>
</html>
