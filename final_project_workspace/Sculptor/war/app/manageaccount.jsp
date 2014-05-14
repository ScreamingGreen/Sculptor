<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Manage Account</title>
    
    <!-- Bootstrap -->
    <link href="../bootstrap-3.1.1-dist/css/bootstrap.css" rel="stylesheet">
	<link href="../bootstrap-3.1.1-dist/css/custom.css" rel="stylesheet">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
  </head>
  <body>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../bootstrap-3.1.1-dist/js/bootstrap.min.js"></script>
	
	<!-- Custom Javascript -->
	<script src="../bootstrap-3.1.1-dist/js/Sculptor.js"></script>

	<script src="../bootstrap-3.1.1-dist/js/ManageAccount.js"></script>
	
	<!-- Navigation bar with Sculptor brand, search bar, login, create page -->
	<nav id="nav-bar" class="navbar-inverse navbar-static-top" role="navigation">
		<div class="container">
			<div class="navbar-header" id="nav-bar-header">
				<a class="navbar-brand" href="createpage.jsp">Sculptor</a>
			</div>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/studentpage.jsp?webId=${sessionScope.sessionBean.profBean.webId}" target="_blank">View website</a></li>
					<li><a href="manageaccount.jsp">Logged in as ${sessionScope.sessionBean.profBean.webId}</a></li>
					<li><a href="/logout">Logout</a></li>
				</ul>
		</div>
	</nav>


	<!-- Login header -->
	<div id="header">
      <div class="container">
      		<div id="login-form">				
      			<div id="manage-account-alert-box" class="alert alert-danger alert-dismissable"></div>	
				<div class="form-group">			
				<form class="form-change-password" method="POST" action="/changePassword" role="form">
					
				    <h3>Change Password</h3>				
					
					<c:if test="${param.wrongpassword == 'true'}">	
						Wrong Old Password
					</c:if>
					
					<c:if test="${param.nonewpassword == 'true'}">
						New password field is empty
					</c:if>
						
					<!-- Login Fields, webID and password ${param.wrongpassword || param.nonewpassword ? 'has-error' : ''} -->
					<div class="input-group bottom-margin-10px">
						<span class="input-group-addon glyphicon glyphicon-lock"></span>
						<input type="password" name="oldPassword" class="form-control" id="oldPassword" placeholder="Old Password">
					</div>
					
					<div class="input-group bottom-margin-10px">
						<span class="input-group-addon glyphicon glyphicon-lock"></span>
						<input type="password" name="newPassword" class="form-control" id="newPassword" placeholder="New Password">
					</div>
					
					<!-- Buttons here -->					
					<input id="changePassword-button" type="submit" class="btn btn-success" value="Change"> 				
				</form>		
				</div>
			</div>
      </div>
    </div>
  </body>
</html>