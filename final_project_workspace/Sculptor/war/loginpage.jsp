<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Login</title>
    
    <!-- Bootstrap -->
    <link href="bootstrap-3.1.1-dist/css/bootstrap.css" rel="stylesheet">
	<link href="bootstrap-3.1.1-dist/css/custom.css" rel="stylesheet">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
  </head>
  <body>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap-3.1.1-dist/js/bootstrap.min.js"></script>
	
	<!-- Custom Javascript -->
	<script src="bootstrap-3.1.1-dist/js/Sculptor.js"></script>
	
	<!-- Basic navigation bar, has brand, search, login, register and create site -->
		<nav class="navbar navbar-inverse" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="index.html">SCULPTOR</a>
				</div>
					<ul class="nav navbar-nav navbar-right">
						<li><form class="navbar-form " role="search">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Find a Course Website">
							</div>
						</form></li>
						<li class="right-divider"><a href="loginpage.jsp">LOG-IN</a></li>
						<li class="right-divider"><a href="registeruser.jsp">REGISTER</a></li>  
						<li><a href="#">CREATE WEBSITE</a></li>
					</ul>
			</div>
		</nav>
		
	<!-- Creating login area -->
		<div class="container">
			<div class="col-md-4 col-md-offset-2 outerDiv">				
				<div class="form-group innerDiv">
				<form method="POST" action="login">
				    <c:if test="${param.error == 'true'}">Wrong User/Password Combination</c:if>
					<!-- Login Fields, webID and password -->
					<input type="text" name="username" class="form-control" id="loginWeb" placeholder="Website ID">
					<input type="password" name="password" class="form-control" id="loginPass" placeholder="Password">
					
					<br />
					<!-- Buttons here -->					
					<input type="submit" class="btn btn-default" value="LOG IN"> 
					<a class="btn btn-default" href="registeruser.html">NO ACCOUNT? REGISTER HERE</a> 					
				</form>		
				</div>
			</div>
		</div>
  </body>
</html>