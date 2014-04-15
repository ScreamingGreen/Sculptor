<!-- Page for Editing Course Webpages -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>CreatePage</title>
				
		<!-- Bootstrap -->
		<link href="../bootstrap-3.1.1-dist/css/bootstrap.css" rel="stylesheet">
		<link href="../bootstrap-3.1.1-dist/css/custom.css" rel="stylesheet">
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

		
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="../bootstrap-3.1.1-dist/js/bootstrap.min.js"></script>
		
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
					<a class="navbar-brand" href="/index.html">Sculptor</a>
				</div>

					<ul class="nav navbar-nav navbar-right">
						<li><a>Logged in as ${sessionScope.sessionBean.profBean.webId}</a></li>
						<li><a href="/logout">Logout</a></li>
					</ul>
			</div>
		</nav>

		
		<!-- Everything below navigation bar -->
		<div id="teacher-body">

			<!-- Left Side Tab-bar navigation -->
			<div id="teacher-navigation" class="col-sm-2">
				<h4> Modify Navigation </h4>
				
				<p> Add, remove and edit different types of pages for your course site.</p>
					<ul class="nav nav-pills nav-stacked" id="tab-bar">
						  <li class="active"><a id="Home" data-toggle="tab"> 
						  		<span class="glyphicon glyphicon-home"></span> 
						  		&nbsp;
 								Home</a>
 						  </li>
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
					</ul>
					<!-- Add Button -->
					<ul class="nav nav-pills nav-stacked">
						<li class="dropdown"><a href="#"  class="dropdown-toggle" data-toggle="dropdown">
					  		<span class="glyphicon glyphicon-plus"></span> 
					  		&nbsp;
					  		Add Page</a>
					  		<ul id="page-dropdown-menu" class="dropdown-menu">
 							  	<li role="presentation"><a role="menuitem" tabindex="-1" href="#" onClick=addPage()>
 							  		<!--<span class="glyphicon glyphicon-home"></span> 
						  			&nbsp;-->
 							  		Home
 							  		</a>
 							  	</li>
 							  	<li role="presentation"><a role="menuitem" tabindex="-1" href="#" onClick=addPage()>Schedule</a></li>
 							  	<li role="presentation"><a role="menuitem" tabindex="-1" href="#" onClick=addPage()>Files</a></li>
							</ul>
					  	</li>
					</ul>

					<!-- Save tab button (needs work) -->
					<br />
				<input id="save-tab-button" type="button" class="btn btn-default" onClick=saveTabOrder() value="Save Tab Order" /> 	
			</div>

			<!-- Teacher content header -->
			<div id="teacher-content-header" class="col-sm-10"> 
				<div class="col-sm-9">
					<h3> 
						Home
						<input id="preview-button" type="submit" class="btn btn-default" value="Cancel"> 
						<input id="preview-button" type="submit" class="btn btn-default" value="Preview"> 
						<input id="save-button" type="submit" class="btn btn-success" value="Save Changes" form="pageForm"> 
					</h3>
					<p> Edit basic information in here. </p>
				</div>
			</div>

			<!-- Teacher content -->
			<div class="row col-sm-6">
				<div id="teacher-content" class="tab-content">
					<!-- Here we will plug in with Ajax different forms -->
					<!-- Basic content for home page -->
						<div class="tab-pane active" id="Home">
							<!-- Every div has a form -->
							<form id="pageForm" action="/saveform" method="POST">
							
								<!-- Must check that all name of the components are distinct! -->
								<input name="type-of-form" type="hidden" value="Home" />

								<h5> Course Code </h5>
								<input type="text" class="form-control" placeholder="CSE 308">
								<h5> Course Title </h5>
								<input type="text" class="form-control" placeholder="Software Engineering">
								<h5> Teacher Name </h5>
								<input type="text" class="form-control" placeholder="Richard Mckenna">
								<h5> Days of the Week </h5>
									<div class="btn-group margin-bottom-14px" data-toggle="buttons">
									  <label class="btn btn-primary">
										<input type="checkbox"> Mon
									  </label>	
									  <label class="btn btn-primary">
										<input type="checkbox"> Tue
									  </label>
									  <label class="btn btn-primary">
										<input type="checkbox"> Wed
									  </label>
									  <label class="btn btn-primary">
										<input type="checkbox"> Thu
									  </label>
									  <label class="btn btn-primary">
										<input type="checkbox"> Fri
									  </label>
									  <label class="btn btn-primary">
										<input type="checkbox"> Sat
									  </label>
									  <label class="btn btn-primary">
										<input type="checkbox"> Sun
									  </label>
									</div>
								<h5> Start Time </h5>
								<input type="text" name="webId" class="form-control" placeholder="10:00 AM">
								<h5> End Time </h5>
								<input type="text" name="webId" class="form-control" placeholder="11:20 AM">
							</form>	
						</div>
				</div>			
			</div>
	    </div>
	</body>
</html>