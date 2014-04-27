$(document).ready(function() {
	getStudentPage();
});

//Gets tab order from servlet
function getStudentPage() {
	
	//Gets webId request
	var webIdParam = $('#webId').attr('value');
	
	//Try to load tabs
	//Sends ajax request to servlet
	$.ajax({
	    type: 'post',
	    url: '/loadStudentSidebar',
	    dataType: 'text',
	    data: {'webId': webIdParam},
	    success: function(jsonData) {
	    		loadStudentPageSidebar(jsonData);
	    },
	    error: function(jsonData) {
	        alert('Error loading teacher navigation');
	    }
	});
}

//Load sidebar navigation from JSON.
function loadStudentPageSidebar(jsonData){
		console.log(jsonData);
		
		//Parses JSON string from ajax call
		var jsonArray = jQuery.parseJSON(jsonData);
		
		//Gets webId requested
		var webIdParam = $('#webId').attr('value');
		
		//Appends new sidebar button for each tab.
		for(i=1; i<jsonArray.tabOrder.length; i++)
		{
					console.log(jsonArray.tabOrder[i]);
					$("#tab-bar")
					.append(
						/* Basic attributes for left side tab-bar */
						$('<li></li>').append(
							$('<a class="scrollSelector"></a>')
							.attr('data-toggle', 'tab')
							.attr('onClick', 'scrollTo("' + jsonArray.tabOrder[i].type + '")')
							.append(
								$('<div class="scrollText">' + jsonArray.tabOrder[i].type + '</div>')
							))
					);
					
					//Appends syllabus section to rightside
					if(jsonArray.tabOrder[i].type == "Syllabus")
					{
						$("#main")
							.append(
							$('<div class="studentOther rightside" id="Syllabus"></div>')
								.append($('<h2> Course Description </h2> <p id="description"></p> <hr>'))
								.append($('<h2> Course Material </h2> <p id="materials"></p> <hr>'))
								.append($('<h2> Info and Office Hours </h2> <p id="infoAndHours"></p> <hr>'))
								.append($('<h2> Grade Breakdown </h2> <p id="breakdown"></p> <hr>'))
							);
					}
					
					//Appends schedule section to rightside
					if(jsonArray.tabOrder[i].type == "Schedule")
					{
						$("#main")
							.append(
							$('<div class="studentOther rightside" id="Schedule"></div>')
								.append($('<h2> Important Dates </h2> <p id="schedule"></p>'))
							);
					}
					
					//Appends file section to rightside
					if(jsonArray.tabOrder[i].type == "Files")
					{
						$("#main")
							.append(
							$('<div class="studentOther rightside" id="Files"></div>')
								.append($('<h2> Files </h2>'))
							);
					}
		}
		
		//for each tab in get the information that they hold
		for(j=0; j<jsonArray.tabOrder.length; j++)
		{
			//Sends ajax request to servlet
			//Used to get the information in each page tab
			var tabType = jsonArray.tabOrder[j].type
			$.ajax({
				async: false,
			    type: 'post',
			    url: '/loadStudentMain',
			    dataType: 'text',
			    data: {'webId':webIdParam, 'type':tabType},
			    success: function(jsonData) {
			    		loadStudentPageMain(jsonData, tabType);				    		
			    },
			    error: function(jsonData) {
			        alert('Error loading teacher navigation');
			    }
			});
		}
}


//loads rightside of student page with information from tabs
function loadStudentPageMain(jsonData, tabType){
	console.log(jsonData);
	console.log(tabType);
	var jsonArray = jQuery.parseJSON(jsonData);
	console.log(jsonArray);
	
	//Loads data from JSON into rightside of student page
	if(tabType == 'Home') 
	{
		// Course Code
		$('#courseCode').text(jsonArray.data[0].courseCode);
		
		//Course Name
		$('#courseName').text(jsonArray.data[0].courseName);
		
		//Teacher Name
		$('#teacherName').text(jsonArray.data[0].teacherName);
		
		//Start Time
		$('#startTime').text(jsonArray.data[0].startTime);
		
		//End Time
		$('#endTime').text(jsonArray.data[0].endTime);
	}
	
	else if (tabType == 'Syllabus')
	{
		// Course Description
		$('#description').text(jsonArray.data[0].description);
		
		// Course Material
		$('#materials').text(jsonArray.data[0].materials);
		
		// Course Info and Office Hours
		$('#infoAndHours').text(jsonArray.data[0].infoAndHours);
		
		// Grade Breakdown
		$('#breakdown').text(jsonArray.data[0].breakdown);
	}
	
	else if (tabType == 'Schedule')
	{
		// Important Dates
		$('#schedule').text(jsonArray.data[0].schedule);
	}
	
	else if (tabType == 'Files')
	{
		//Gets webId request
		var webIdParam = $('#webId').attr('value');
		
		//Retrieve the current files
		$.ajax({
			url: '/loadfiles',
			type: 'GET',
			data: {'webId': webIdParam},
			dataType: 'text',
			success: function(data) {
				if(data == "") {
					$("#Files").append('<div>No files uploaded.</div>');
					return;
				}
				
				var keys = jQuery.parseJSON(data);
				//Print on screen
				for(var i = 0; i < keys.length; i++) {
					$('#Files')
						.append($('<div> </div>')
							.append($("<a href=/servefile?blob-key=" + keys[i].key + ">" + keys[i].name +" </a>"))
					);
				}
			},
			error: function(data) {
				alert("Failed");
			}
		});
	}
}