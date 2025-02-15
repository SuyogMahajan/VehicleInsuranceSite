<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>View Policy</title>
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
      background-color: var(--main-color);
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
      max-width: 1200px;
      margin: 20px auto;
    }

    h2 {
      text-align: center;
      color: var(--main-color);
    }

    .form-container {
      background-color: white;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .form-group {
      margin-bottom: 20px;
    }

    .form-group label {
      display: block;
      margin-bottom: 5px;
      color: #333;
    }

    .form-group input[type="text"] {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
      font-size: 16px;
    }

    .form-group .buttons {
      text-align: center;
      margin-top: 20px;
    }

    .form-group .buttons button {
      background-color: var(--main-color);
      color: white;
      border: none;
      padding: 10px 20px;
      margin: 5px;
      border-radius: 5px;
      cursor: pointer;
      font-size: 16px;
      transition: background-color 0.3s ease;
    }

    .form-group .buttons button:hover {
      background-color: #093d47;
    }

    .policy-details {
      margin-top: 20px;
      /* display: none; */
    }

    .policy-details table {
      width: 100%;
      border-collapse: collapse;
    }

    .policy-details th, .policy-details td {
      padding: 10px;
      border: 1px solid #ccc;
      text-align: left;
    }

    .error-message {
      color: red;
      font-size: 14px;
      margin: 10px 0;
    }

  </style>
</head>
<body>
<%
	
	UnderWriter LOGGED_IN_UNDERWRITER = null;
	if(session.getAttribute("LOGGED_IN_UNDERWRITER") == null){
%>
<jsp:forward page="/login"></jsp:forward>
<%}
	LOGGED_IN_UNDERWRITER = (UnderWriter)(session.getAttribute("LOGGED_IN_UNDERWRITER"));
	%>

  <!-- Header -->
  <header>
    <div class="logo">Star Protect Insurance - View Policy</div>
    <nav>
      <a href="<%=request.getContextPath()%>/underwriter_home.jsp">Home</a>
      <a href="<%=request.getContextPath()%>/underwriter_app/logout">Logout</a>
    </nav>
  </header>

  <!-- View Policy Content -->
  <div class="container">
    <h2>View Policy</h2>

    <div class="form-container">
    <form action="<%=request.getContextPath()%>/underwriter_app/viewInsurance" method="post">
      <div class="form-group">
        <label for="vehicle-no">Vehicle No</label>
        <input type="text" id="vehicle-no" name="vehicle-no" placeholder="Enter Vehicle No (Max 10 characters)">
      </div>

      <div class="form-group">
        <label for="policy-id">Policy ID</label>
        <input type="text" id="policy-id" name="policy-id" placeholder="Enter Policy ID (Max 10 characters)">
      </div>

      <div class="form-group">
        <div class="buttons">
          <button id="view-policy-button" type="submit">View Policy</button>
          <button id="cancel-button">Cancel</button>
        </div>
        </form>
      </div>

    
<%
boolean flag = false;
        		
if( request.getAttribute("Ins") != null) {
Insurance ins = (Insurance) (request.getAttribute("Ins"));
flag = true;%>
      <div id="policy-details" class="policy-details">
        <h3>Policy Details</h3>
        <table>
          <tr>
            <th>Vehicle No</th>
            <td id="detail-vehicle-no"><%=ins.getVehicleNo() %></td>
          </tr>
          <tr>
            <th>Policy ID</th>
            <td id="detail-policy-id"><%=ins.getPolicyNo() %></td>
          </tr>
          <tr>
            <th>Customer Name</th>
            <td id="detail-customer-name"><%=ins.getCustomerName() %></td>
          </tr>
          <tr>
            <th>Engine No</th>
            <td id="detail-engine-no"><%=ins.getEngineNo() %></td>
          </tr>
          <tr>
            <th>Chasis No</th>
            <td id="detail-chasis-no"><%=ins.getChasisNo() %></td>
          </tr>
          <tr>
            <th>Phone No</th>
            <td id="detail-phone-no"><%=ins.getPhoneNo() %></td>
          </tr>
          <tr>
            <th>Policy Type</th>
            <td id="detail-policy-type"><%=ins.getType() %></td>
          </tr>
          <tr>
            <th>From Date</th>
            <td id="detail-from-date"><%=ins.getFromDate() %></td>
          </tr>
          <tr>
            <th>To Date</th>
            <td id="detail-to-date"><%=ins.getToDate() %></td>
          </tr>
        </table>
      </div>
  <%} %>
  
<%if(request.getAttribute("FAILURE_MESSAGE") != null){ %>
<div id="error-message" class="form-group" style="">
  <p class="error-message"><%=request.getAttribute("FAILURE_MESSAGE")%></p>
</div>
<%}%>
	
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

    <p>� 2024 Star Protect Insurance All Rights Reserved</p>
  </footer>

</body>
</html>