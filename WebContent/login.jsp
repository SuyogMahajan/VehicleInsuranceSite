<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Vehicle Insurance - Login</title>
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
      background-image: url("back.jpg");
	  background-size: cover;
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;

      display: flex;
      flex-direction: column;
      min-height: 100vh;
    }

    main {
	  margin: 80px auto;
    }

    header ,footer{
      background-color: transperant;
      color: white;
      padding: 1rem 0;
      display: flex;
      color: black;
      justify-content: space-between;
      align-items: center;
      padding: 15px 50px;
    }

    header .logo {
	font-size: 24px;
	padding: 5px 0;
	font-weight: semi-bold;
    }

    header nav a {
      color: white;
      text-decoration: none;
      margin-left: 20px;
    }

    .container {
      width: 100%;
      max-width: 400px;
      padding: 20px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
    }

    h2 {
      text-align: center;
      color: #0B2027;
    }

    form {
      display: flex;
      flex-direction: column;
    }

    label {
      margin-bottom: 5px;
      color: #333;
    }

    input[type="text"], input[type="password"] {
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    .radio-group {
      display: flex;
      justify-content: space-between;
      margin-bottom: 15px;
    }

    .radio-group label {
      display: flex;
      align-items: center;
    }

    .radio-group input {
      margin-right: 5px;
    }

    input[type="submit"] {
      padding: 15px;
      background-color: #0B2027;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }

    input[type="submit"]:hover {
      background-color: hsl(var--main-color), 69%, 55%);
    }

    footer {
      background-color: #0B2027;
      color: white;
      text-align: center;
      padding: 2rem 0;
      margin-top: 2rem;
    }

    footer p, footer a {
      color: white;
      font-size: 14px;
      margin: 5px 0;
      padding:10px;
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
  </style>
</head>
<body>

  <!-- Header -->
  <header>
    <div class="logo">Star Protect Insurance</div>
  </header>

  <!-- Main content for login form (Centered Vertically) -->
  <main>
    <div class="container">
      <h2>Login</h2>
      <form action="<%=request.getContextPath()%>/login" method="POST">

        <!-- Radio buttons for login selection -->
        <div class="radio-group">
          <label>
            <input type="radio" name="login-type" value="admin" required> Admin Login
          </label>
          <label>
            <input type="radio" name="login-type" value="underwriter" required> Underwriter Login
          </label>
        </div>

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <input type="submit" value="Login">
      </form>
    </div>
  </main>

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