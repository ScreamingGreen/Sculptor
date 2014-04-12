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

		
		<div id="header">
	      <div class="container">
		  
				<!-- Left Side Tab-bar navigation -->
				<div class="col-lg-3 modify-navigation">
					<h4>Modify Navigation</h4>	
						<ul class="nav nav-pills nav-stacked" id="tab-bar">
						  <li class="active"><a href="#Home" data-toggle="tab">Home</a></li>
						  <li><a href="#Syllabus" data-toggle="tab">Syllabus</a></li>
						  <li><a href="#Schedule" data-toggle="tab">Schedule</a></li>
						</ul>						
						<hr>
					<div> <a href="#" class="glyphicon glyphicon-plus" onClick=addPage()> ADD PAGE </a> </div>
					<br />
					<input id="save-tab-button" type="button" class="btn btn-default" value="Save Tab Order" /> 						
				</div>
				
				
				<!-- Content of tabs on right side -->
				<div class="row col-sm-9">
					<div class="tab-content">
						
						<!-- Basic content for home page -->
						<div class="tab-pane active" id="Home">
							<!-- Every div has a form -->							
							<form action="/saveform" method="POST">
							Type of Form: 
							<!-- If this changes.. the form will change dynamically through jQuery -->
							<select name="type-of-form">
								<option>Schedule</option>
								<option selected='selected'>Home</option>
								<option>Information</option>
								<option>File</option>
							</select>
							
							<!-- Must check that all name of the components are distinct! -->
							<h2> Name of Component </h2>
							<input type="text" class="form-control" name="tabName" placeholder="Home" />
							<hr>
							<h4> Course Code </h4>
							<input type="text" class="form-control" name="courseCode" placeholder="CSE 308"/>
							<h4> Course Title </h4>
							<input type="text" class="form-control" name="courseTitle" placeholder="Software Engineering"/>
							<h4> Teacher Name </h4>
							<input type="text" class="form-control" name="teacherName" placeholder="Richard Mckenna"/>
							<h4> Days of the Week </h4>
								<div class="btn-group" data-toggle="buttons">
								  <label class="btn btn-primary">
									<input name="mon" type="checkbox" /> Mon 
								  </label>
								  <label class="btn btn-primary">
									<input name="tue" type="checkbox" /> Tue
								  </label>
								  <label class="btn btn-primary">
									<input name="wed" type="checkbox" /> Wed
								  </label>
								  <label class="btn btn-primary">
									<input name="thu" type="checkbox" /> Thu
								  </label>
								  <label class="btn btn-primary">
									<input name="fri" type="checkbox" /> Fri
								  </label>
								  <label class="btn btn-primary">
									<input name="sat" type="checkbox" /> Sat
								  </label>
								  <label class="btn btn-primary">
									<input name="sun" type="checkbox" /> Sun
								  </label>
								</div>
							<h4> Start Time </h4>
							<input type="text" name="startTime" class="form-control" placeholder="10:00 AM"/>
							<h4> End Time </h4>
							<input type="text" name="endTime" class="form-control" placeholder="11:20 AM"/>
							
							<hr>
							<!-- Buttons at the bottom of tab-content -->
							<input id="save-button" type="submit" class="btn btn-default" value="Save Changes"/> 
							<input id="cancel-button" type="submit" class="btn btn-default" value="Cancel"/> 
							<input id="preview-button" type="submit" class="btn btn-default" value="Preview"/> 
							</form>
						</div>
						
						<!-- Content for Syllabus tab to add here -->
						<div class="tab-pane" id="Syllabus">
							<form>
								<hr>
								<!-- Buttons at the bottom of tab-content -->
								<input id="save-button" type="submit" class="btn btn-default" value="Save Changes"> 
								<input id="cancel-button" type="submit" class="btn btn-default" value="Cancel"> 
								<input id="preview-button" type="submit" class="btn btn-default" value="Preview"> 							
							</form>
						</div>
						
						<!-- Content for Schedule tab to add here -->
						<div class="tab-pane" id="Schedule">
							<form>
								<hr>
								<!-- Buttons at the bottom of tab-content -->
								<input id="save-button" type="submit" class="btn btn-default" value="Save Changes"> 
								<input id="cancel-button" type="submit" class="btn btn-default" value="Cancel"> 
								<input id="preview-button" type="submit" class="btn btn-default" value="Preview"> 
							</form>
						</div>
					</div>			
					<hr>					
				</div>
	      </div>
	    </div>
	</body>
</html>