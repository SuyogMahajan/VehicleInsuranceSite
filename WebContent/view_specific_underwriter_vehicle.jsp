<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.*"%>
<%@ page import="java.util.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>View Underwriter Vehicles</title>
  <style>
@import
	url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap')
	;

* {
	font-family: poppins;
}

.poppins-regular {
	font-family: "Poppins", sans-serif;
	font-weight: 400;
	font-style: normal;
}

.poppins-semibold {
	font-family: "Poppins", sans-serif;
	font-weight: 600;
	font-style: normal;
}

.poppins-bold {
	font-family: "Poppins", sans-serif;
	font-weight: 700;
	font-style: normal;
}

:root { 
	--main-color: #0B2027;
}

body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
	display: flex;
	flex-direction: column;
	min-height: 100vh;
}

header, footer {
	background-color: transperant;
	color: #0B2027;
	padding: 0.5rem 0;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 15px 50px;
}

header .logo {
	font-size: 24px;
	padding: 5px 0;
	font-weight: semi-bold;
}

header nav a, footer a {
	color: #0B2027;
	text-decoration: none;
	margin-left: 20px;
}

    footer {
      background-color: #0B2027;
      color: white;
      text-align: center;
      padding: 2rem 0;
      margin-top: auto;
    }

    footer p, footer a {
      color: white;
      font-size: 14px;
      margin: 5px 0;
      padding: 10px;
      text-decoration: none;
    }

    footer .footer-content {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 0 50px;
      flex-wrap: wrap;
    }

    footer .social-icons img {
      width: 24px;
      margin-right: 10px;
    }

    footer .policies, footer .contact-info {
      text-align: left;
    }

    footer .contact-info {
      text-align: right;
    }

    @media screen and (max-width: 600px) {
      footer .footer-content {
        flex-direction: column;
        text-align: center;
      }

      footer .policies, footer .contact-info {
        text-align: center;
      }
    }

    .container {
      width: 90%;
      max-width: 100%;
      margin: 40px auto;
      background-color: white;
      padding: 30px 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h2 {
      text-align: center;
      color: var(--main-color);
      margin-bottom: 20px;
    }

    form {
      margin-bottom: 30px;
    }

    form label {
      display: block;
      margin: 10px 0 5px;
      color: #333;
    }

    form input[type="text"], form input[type="number"], form input[type="date"], form select {
      width: calc(100% - 20px);
      padding: 15px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 5px;
      font-size: 16px;
    }

    form input[type="submit"], form button {
      background-color: var(--main-color);
      color: white;
      border: none;
      padding: 15px 30px;
      border-radius: 5px;
      cursor: pointer;
      font-size: 16px;
    }

    form input[type="submit"]:hover, form button:hover {
      background-color: #093d47;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin: 20px 0;
    }

    table, th, td {
      border: 1px solid #ccc;
    }
    th, td {
      padding: 15px;
      text-align: left;
    }

    th {
      background-color: var(--main-color);
      color: white;
    }

    td {
      background-color: #f9f9f9;
    }

    .message {
      font-size: 14px;
      color: red;
      margin: 10px 0;
    }
  </style>
</head>
<body>
<%
	
	Admin LOGGED_IN_ADMIN = null;
	if(session.getAttribute("LOGGED_IN_ADMIN") == null){
%>
<jsp:forward page="/login"></jsp:forward>
<%}
	LOGGED_IN_ADMIN = (Admin)(session.getAttribute("LOGGED_IN_ADMIN"));
	%>
  <!-- Header -->
  <header>
    <div class="logo">Star Protect Insurance - Admin Dashboard</div>
    <nav>
      <a href="<%=request.getContextPath()%>/admin_home.jsp">Dashboard</a>
      <a href="<%=request.getContextPath()%>/admin_app/logout">Logout</a>
    </nav>
  </header>

  <!-- View Specific Underwriter Vehicles Content -->
  <div class="container">
    <h2>View Underwriter Vehicles</h2>

    <!-- Form to Enter Underwriter ID -->
    <form action="<%=request.getContextPath()%>/admin_app/viewUnderwriterVehicles" method="POST">
      <label for="underwriter-id">Enter Underwriter ID</label>
      <input type="text" id="underwriter-id" name="underwriter-id" required>

      <input type="submit" value="Search">
    </form>

    <!-- Example Table for Displaying Vehicles; replace with dynamic data -->
    <div id="vehicles-table" style="">
      <h3>Vehicle Insurance Details</h3>
      <table>
      
        		<%
        		 if(request.getAttribute("listIns") != null) { %>
        		<thead>
          <tr>
          	<th>Policy No.</th>
            <th>Vehicle No.</th>
            <th>Vehicle Type</th>
            <th>Customer Name</th>
            <th>Engine No</th>
            <th>Chassis No</th>
            <th>Phone No</th>
            <th>Premium Amount</th>
            <th>Type</th>
            <th>From Date</th>
            <th>To Date</th>
          </tr>
        </thead>
        		<% 
				List<Insurance> list = new ArrayList<Insurance>();
				list = (List<Insurance>) (request.getAttribute("listIns"));
				for(Insurance ins:list){
				%>
      
        
        <tbody>
          <!-- Example rows; replace with dynamic data -->
          <tr>
          	<td><%=ins.getPolicyNo() %></td>
            <td><%=ins.getVehicleNo() %></td>
            <td><%=ins.getVehicleType() %></td>
            <td><%=ins.getCustomerName() %></td>
            <td><%=ins.getEngineNo() %></td>
            <td><%=ins.getChasisNo() %></td>
            <td><%=ins.getPhoneNo() %></td>
            <td><%=ins.getPremiumAmnt() %></td>
            <td><%=ins.getType() %></td>
            <td><%=ins.getFromDate().toString() %></td>
            <td><%=ins.getToDate().toString() %></td>
          </tr>
          <!-- More rows will be dynamically generated here -->
        </tbody>
        
        <%} } %>
      </table>
    </div>
  </div>

  <!-- Footer -->
  <footer>
    <div class="footer-content">
      <!-- Social Media Icons -->
      <div class="social-icons">
        <a href="#"><img src="https://img.icons8.com/fluent/48/000000/facebook-new.png" alt="Facebook"></a>
        <a href="#"><img src="https://img.icons8.com/fluent/48/000000/twitter.png" alt="Twitter"></a>
        <a href="#"><img src="https://img.icons8.com/fluent/48/000000/linkedin.png" alt="LinkedIn"></a>
      </div>

      <!-- Policies Links -->
      <div class="policies">
        <a href="#">Privacy Policy</a> |
        <a href="#">Terms of Service</a>
      </div>

      <!-- Contact Info -->
      <div class="contact-info">
        <p>Contact Us: 123-456-7890 | info@vehicleinsurance.com</p>
      </div>
    </div>

    <p>© 2024 Star Protect Insurance All Rights Reserved</p>
  </footer>

</body>
</html>