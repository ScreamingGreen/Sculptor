<!-- Page for registering a user -->
<html>
	<head>
	<meta charset="utf-8">
		<title>Register</title>
		
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
		
		<!-- Navigation bar with Sculptor brand, search bar, login, create page -->
		<nav id="nav-bar" class="navbar-inverse navbar-static-top" role="navigation">
			<div class="container">
				<div class="navbar-header" id="nav-bar-header">
					<a class="navbar-brand" href="index.html">Sculptor</a>
				</div>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/loginpage.jsp">Login</a></li> 
						<li><a href="/registeruser.jsp">Register</a></li>
					</ul>
			</div>
		</nav>

		<c:if test="${param.error == 'true'}">
			<c:choose>
				<c:when test="${param.missingfields == 'true'}">
					You are missing some fields.
				</c:when>
				
				<c:when test="${param.accountexists == 'true'}">
					An account with that Web Id exist already.
				</c:when>
			</c:choose>		
		</c:if>		

		<!-- Register Header -->
		<div id="header">
	      <div class="container">
	      		<div id="register-form">				
					<div class="form-group">
						<form class="form-horizontal" method="POST" action="/register" role="form">
						    <h3>Register</h3>
							<!-- Website ID-->
							 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-3 control-label">Website ID</label>
							    <div class="input-group col-sm-8">
							      	<span class="input-group-addon glyphicon glyphicon-user"></span>
									<input type="text" class="form-control" name="registerWebId" id="registerWebId" placeholder="Software-Engineering">
							    </div>
							 </div>
							<!-- E-mail-->
							 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-3 control-label">Email</label>
							    <div class="input-group col-sm-8">
									<span class="input-group-addon glyphicon glyphicon-envelope"></span>
									<input type="text" class="form-control" name="registerEmail" id="registerEmail" placeholder="John.Doe@stonybrook.edu">
							    </div>
							 </div>
							 <!-- Password-->
							 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-3 control-label">Password</label>
							    <div class="input-group col-sm-8">
									<span class="input-group-addon glyphicon glyphicon-lock"></span>		
									<input type="password" class="form-control" name="registerPass" id="registerPass" placeholder="Password">
							    </div>
							 </div>
							 <!-- Register button -->
							 <div class="form-group">
							    <label for="inputEmail3" class="col-sm-3 control-label"></label>
							    <input id="register-button" type="submit" class="btn btn-primary" value="Register"> 
							    <div class="input-group col-sm-8"></div>
							 </div>		
						</form>		
					</div>
				</div>
	      </div>
	    </div>
	</body>
</html>