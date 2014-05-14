<!-- Page for Editing Course Webpages -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" name="viewport" content="800">
		<title>${param.webId} - Course Website</title>
		
		<!-- Bootstrap -->
		<link href="../bootstrap-3.1.1-dist/css/bootstrap.css" rel="stylesheet">
		<link href="../bootstrap-3.1.1-dist/css/custom.css" rel="stylesheet">
			
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		
	</head>
	<body">
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="../bootstrap-3.1.1-dist/js/bootstrap.min.js"></script>
		
		<!-- Custom Javascript -->
		<script src="../bootstrap-3.1.1-dist/js/Sculptor.js"></script>
		<script src="../bootstrap-3.1.1-dist/js/StudentPage.js"></script>
		
		<span id="webId" value="${param.webId}"></span>
		<div id="studentheader">
	      <div class="container-fluid" id="studentbg">
		  	
				<!-- Left Side Tab-bar navigation -->
				<div class="col-md-1 col-md-offset-2 btn-group btn-group-vertical sidebar">
					<ul class="nav nav-pills nav-stacked" id="tab-bar">
						  <li class="active Home"><a data-toggle="tab" class="scrollSelector" onClick="scrollTo('Home')"><div class="scrollText">Home</div></a></li>
					</ul>
				</div>
				
				<!-- Content of tabs on right side -->
				<div class="container-fluid mainHome"> 
					<div class="row col-md-9 col-lg-offset-3" id="main">
					
						<!-- Basic home div -->
						<div class="studentHome rightside" id="Home">
								<h1 id="courseCode"></h1>
								<h2 id="courseName"></h2>
								<h2 id="teacherName"></h2>
								<h2><span id="startTime"></span> - <span id="endTime"></span></h2>
								<h2><span id="daysofWeek"></span></h2>
						</div>
					</div>
				</div>
					
					
					<!--  Basic Syllabus div 
					<div class="studentSyllabus rightside" id="Syllabus">
							<h2> Course Description </h2> 
							<p id="description"> </p> <hr>
							<h2> Course Material </h2>
							<p id="materials"> </p> <hr>
							<h2> Info and Office Hours </h2>
							<p id="infoAndHours"> </p> <hr>
							<h2> Grade Breakdown </h2>
							<p id="breakdown"> </p> <hr>
					</div>
					
					<!-- Basic schedule div
					<div class="studentSchedule rightside" id="Schedule">
							<h2> Important Dates</h2>
							<p id="schedule"> </p> <hr>
					</div>
					
					<div class="studentSchedule rightside" id="Files">
							<h2> Important Dates</h2>
							<p id="schedule"> </p> <hr>
					</div>
					-->
	      </div>
	    </div>
	</body>
</html>