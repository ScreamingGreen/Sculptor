<!-- Page for Editing Course Webpages -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>CreatePage</title>
		
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
			
		<div id="studentheader">
	      <div class="container studentbg">
		  	
				<!-- Left Side Tab-bar navigation -->
				<div class="col-sm-1 btn-group btn-group-vertical sidebar">
					<ul class="nav nav-pills nav-stacked" id="tab-bar">
						  <li class="active"><a data-toggle="tab" class="scrollSelector" onClick="scrollTo('Home')"><div class="scrollText">HOME</div></a></li>
					</ul>
				</div>
				
				<!-- Content of tabs on right side -->
				<div class="row col-sm-9 col-lg-offset-3">
				
					<!-- Basic home div -->
					<div class="studentHome rightside" id="Home">
							<h1 id="courseCode"></h1>
							<h2 id="courseName"></h2>
							<h2 id="teacherName"></h2>
							<h2><span id="startTime"></span><span id="endTime"></span></h2>
							<h2><span id="daysofWeek"></span></h2>
					</div>
					
					<!--  Basic Syllabus div -->
					<div class="studentSyllabus rightside" id="Syllabus">
							<h2> Course Description </h2>
							<p> Introduces the basic concepts and the modern tools and techniques of Software Engineering. Emphasizes the development of reliable and maintainable software via system requirements and specifications; software design methodologies including object-oriented design, implementation, integration, and testing; software project management; life-cycle documentation; software maintenance; and consideration of human factor issues. </p>
							<h2> Course Topics </h2>
							<ul>
								  <li>Waterfall model, together with its variations and improvements </li>
								  <li>The UML (Unified Modeling Language) </li>
								  <li>Requirements Analysis </li>
								  <li>Use Case Modeling </li>
								  <li>Object Modeling </li>
								  <li>Functional Modeling </li>
								  <li>Dynamic Modeling using State Diagrams, Sequence Diagrams, and Activity Diagrams </li>
								  <li>Specification Documents </li>
								  <li>Design Documents </li>
								  <li>Testing -- Black Box, Glass Box </li>
								  <li>Test Plan Documents </li>
							</ul>
							<h2> Grade Breakdown </h2>
							<ul>
								  <li>Individual HWs	15 %</li>
								  <li>Proposal Doc/SRS Presentation	10%</li>
								  <li>Midterm Exam	20 %</li>
								  <li>Project Progress Reviews	15 %</li>
								  <li>Final Project Presentation	30 %</li>
							</ul>
							<h2> Attendance </h2>
							<p>CSE 308 is a different sort of a class in two regards. 1) Because your primary grade is your team project, students are heavily dependent on the performance of their teammates in the project, and so they depend on their teammates being familiar with the technologies, involved in the project development, and available for team discussions. 2) As the semester progresses there will be very few straight lectures. Lecture time will in fact become an opportunity for teams to meet and make decisions as well as get instructor feedback. In fact, teams will have at least a brief meeting every single lecture. So, attendance is mandatory. Just as in the real world, attendance will be taken for every single meeting. Students who miss more than 3 lectures over the course of the semester will incurr a grade penalty, with every absence thereafter increasing the penalty. In a professional development environment, unprofessionalism is not tolerated, and this starts with being in attendance for all meetings.</p>
					</div>
					
					<!-- Basic schedule div -->
					<div class="rightside" id="Schedule">
					</div>
				</div>
	      </div>
	    </div>
	</body>
</html>