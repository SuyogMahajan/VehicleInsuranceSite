<%@page import="com.controllers.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Create Vehicle Insurance</title>
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
      max-width: 900px;
      margin: 20px auto;
      background-color: white;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h2 {
      text-align: center;
      color: var(--main-color);
      margin-bottom: 20px;
    }

    form label {
      display: block;
      margin: 10px 0 5px;
      color: #333;
    }

    form input[type="text"], form input[type="number"], form input[type="date"], form input[type="password"], form select {
      width: calc(100% - 20px);
      padding: 15px;
      margin-bottom: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
      font-size: 16px;
    }

    form input[type="submit"] {
      background-color: var(--main-color);
      color: white;
      border: none;
      padding: 15px 30px;
      border-radius: 5px;
      cursor: pointer;
      font-size: 16px;
    }

    form input[type="submit"]:hover {
      background-color: #093d47;
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
    <div class="logo">Star Protect Insurance - Create Vehicle Insurance</div>
    <nav>
      <a href="<%=request.getContextPath()%>/underwriter_home.jsp">Home</a>
      <a href="#">About Us</a>
      <a href="#">Contact</a>
      <a href="<%=request.getContextPath()%>/underwriter_app/logout">Logout</a>
    </nav>
  </header>

  <!-- Create Vehicle Insurance Page Content -->
  <div class="container">
    <h2>Create New Vehicle Insurance Policy</h2>
    <form id="vehicle-insurance-form" method="post" action="<%=request.getContextPath()%>/underwriter_app/insertInsurance">
      <label for="vehicle-no">Vehicle No</label>
      <input type="text" id="vehicle-no" name="vehicle-no" maxlength="10" required>

      <label for="vehicle-type">Vehicle Type</label>
      <select id="vehicle-type" name="vehicle-type" required>
        <option value="2-wheeler">2-wheeler</option>
        <option value="4-wheeler">4-wheeler</option>
      </select>

      <label for="customer-name">Customer Name</label>
      <input type="text" id="customer-name" name="customer-name" maxlength="50" required>

      <label for="engine-no">Engine No</label>
      <input type="text" id="engine-no" name="engine-no" required>

      <label for="chasis-no">Chasis No</label>
      <input type="text" id="chasis-no" name="chasis-no" required>

      <label for="phone-no">Phone No</label>
      <input type="number" id="phone-no" name="phone-no" minlength="10" maxlength="10" required>

      <label for="type">Insurance Type</label>
      <select id="type" name="type" required>
        <option value="Full Insurance">Full Insurance</option>
        <option value="ThirdParty">ThirdParty</option>
      </select>

      <label for="from-date">From Date</label>
      <input type="date" id="from-date" name="from-date" required>
      
      <label for="premium-amount">Premium Amount</label>
      <input type="number" id="premium-amount" value="35000.00" name="premium-amount" readonly>
     

      <label for="underwriter-id">Underwriter ID</label>
      <input type="text" id="underwriter-id" name="underwriter-id" value= "<%=LOGGED_IN_UNDERWRITER.getId()%>" readonly="readonly">

      <input type="submit" value="Submit Insurance Policy">
    </form>
    
    <%if(request.getAttribute("FAILURE_MESSAGE") != null){ %>
      <div id="error-message" class="form-group" style="">
        <p class="error-message"><%=request.getAttribute("FAILURE_MESSAGE")%></p>
      </div>
      <%}%>
	<%if(request.getAttribute("SUCCESS_MESSAGE") != null){ %>
      <div id="success-message" class="form-group" style="">
        <p class="success-message"><%=request.getAttribute("SUCCESS_MESSAGE") %></p>
      </div>
      <%} %>
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