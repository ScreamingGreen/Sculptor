<!-- Page for Editing Course Webpages -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>CreatePage</title>
		
		<!-- Plupload -->		
 		<link href="../plupload-2.1.1/js/jquery.plupload.queue/css/jquery.plupload.queue.css" rel="stylesheet">
 		
		<!-- Bootstrap -->
		<link href="../bootstrap-3.1.1-dist/css/bootstrap.css" rel="stylesheet">
		<link href="../bootstrap-3.1.1-dist/css/custom.css" rel="stylesheet">

		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		<script type="text/javascript" src="../plupload-2.1.1/js/plupload.full.min.js"></script>
  		<script type="text/javascript" src="../plupload-2.1.1/js/jquery.plupload.queue/jquery.plupload.queue.min.js"></script>
	
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="../bootstrap-3.1.1-dist/js/bootstrap.min.js"></script>

		<!-- Notifications -->
		<script src="../bootstrap-3.1.1-dist/js/jquery.bootstrap-growl.js"></script>

		<!-- Remove popover -->
		<script src="../bootstrap-3.1.1-dist/js/bootstrap-confirmation.js"></script>

		<!-- Custom Javascript -->
		<script src="../bootstrap-3.1.1-dist/js/Sculptor.js"></script>			
		<script src="../bootstrap-3.1.1-dist/js/CreatePage.js"></script>
		
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	</head>
	<body>
		
		<!-- Navigation bar with Sculptor brand, search bar, login, create page -->
		<nav id="nav-bar" class="navbar-inverse navbar-static-top" role="navigation">
			<div class="container">

				<div class="navbar-header" id="nav-bar-header">
					<a class="navbar-brand" href="#">Sculptor</a>
				</div>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="/studentpage.jsp?webId=${sessionScope.sessionBean.profBean.webId}" target="_blank">View website</a></li>
					<li><a>Logged in as ${sessionScope.sessionBean.profBean.webId}</a></li>
					<li><a href="manageaccount.jsp"> Manage Account </a></li>
					<li><a href="/logout">Logout</a></li>
				</ul>
			</div>
		</nav>

		<!-- Notificaiton div -->
		<div class="notifications top-right"></div>
		
		<!-- Everything below navigation bar -->
		<div id="teacher-body">

			<!-- Left Side Tab-bar navigation -->
			<div id="teacher-navigation" class="col-sm-2">
				<h4> Modify Navigation </h4>
				
				<p> Add, remove and edit different types of pages for your course site.</p>
					<ul class="nav nav-pills nav-stacked" id="tab-bar">
						  <li class="active home-tab"><a id="Home" data-toggle="tab"> 
						  		<span class="glyphicon glyphicon-home"></span> 
						  		&nbsp;
 								Home</a>
 						  </li>

 						  <!--
						  <li><a id="Syllabus" data-toggle="tab">
						  		<span class="glyphicon glyphicon-pencil"></span> 
						  		&nbsp;
						  		Syllabus</a>
						  </li>
						  <li><a id="Schedule" data-toggle="tab">
						  		<span class="glyphicon glyphicon-list-alt"></span> 
						  		&nbsp;
						  		Schedule</a>
						  </li>
						  -->
					</ul>
					<!-- Add Button -->
					<ul class="nav nav-pills nav-stacked">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					  		<span class="glyphicon glyphicon-plus"></span> 
					  		&nbsp;
					  		Add Page</a>

					  		<!-- Navigation for Add Button -->
					  		<ul id="page-dropdown-menu" class="dropdown-menu">
					  			<!--
 							  	<li role="presentation">
 							  		<a class="addpage-option" role="menuitem" tabindex="-1" href="#">Schedule</a>
 							  	</li>
 							  	<li role="presentation">
 							  		<a class="addpage-option" role="menuitem" tabindex="-1" href="#">Syllabus</a>
 							  	</li>
 							  	<li role="presentation">
 							  		<a class="addpage-option" role="menuitem" tabindex="-1" href="#">File</a>
 							  	</li>
 							  	-->
							</ul>
					  	</li>
					</ul>
			</div>

			<!-- Teacher content header -->
			<div id="teacher-content-header" class="col-sm-10"> 
				<div class="col-sm-9">
					<h3> 
						<span id="page-title"> Home </span>
						<input id="cancel-button" type="submit" class="btn btn-default" value="Cancel"> 
						<!-- <input id="preview-button" type="submit" class="btn btn-default" value="Preview"> -->
						<input id="save-button" type="submit" class="btn btn-success" value="Save Changes" form="pageForm"> 
					</h3>
					<p> Edit information in below. </p>
				</div>
			</div>

			<!-- Teacher content -->
			<div class="row col-sm-6">
				<div id="teacher-content" class="tab-content">
					<!-- Here we will plug in with Ajax different forms -->
				</div>			
			</div>
	    </div>
	</body>
</html>