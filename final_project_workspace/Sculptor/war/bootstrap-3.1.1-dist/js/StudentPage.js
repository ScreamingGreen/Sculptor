$(document).ready(function() {
	getStudentPage();
});

//Gets tab order from servlet
function getStudentPage() {
	
	//Try to load tabs
	//Sends ajax request to servlet
	$.ajax({
	    type: 'post',
	    url: '/loadStudentSidebar',
	    dataType: 'text',
	    data: {'webId':'afung'},
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
		var jsonArray = jQuery.parseJSON(jsonData);
		
		//Appends new sidebar button for each tab.
		for(i=1; i<jsonArray.tabOrder.length; i++)
		{
					console.log(jsonArray.tabOrder[i]);
					$("#tab-bar")
					.append(
						/* Basic attributes for left side tab-bar */
						$('<li></li>').append(
							$('<a></a>')
							.attr('data-toggle', 'tab')
							.attr('onClick', 'scrollTo("' + jsonArray.tabOrder[i].type + '")'))
							.addClass('scrollSelector')
							.append(
								$('<div class="scrollText">' + jsonArray.tabOrder[i].type + '</div>')
							)
					);
		}
		
		//for each tab in get the information that they hold
		for(j=0; j<4; j++)
		{
			//Sends ajax request to servlet
			//Used to get the information in each page tab
			var tabType = jsonArray.tabOrder[j].type
			$.ajax({
				async: false,
			    type: 'post',
			    url: '/loadStudentMain',
			    dataType: 'text',
			    data: {'webId':'afung', 'type':tabType},
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
		
	}
}