$(document).ready(function() {
	console.log(screen.width);
	if(screen.width < 1000){
		$('#tab-bar').hide();
	}
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
							.addClass(jsonArray.tabOrder[i].type)
					);
					
					//Appends syllabus section to rightside
					if(jsonArray.tabOrder[i].type == "Syllabus")
					{
						$("#main")
							.append(
							$('<div class="studentOther rightside" id="Syllabus"></div>')
								.append($('<h2 class="description"> Course Description </h2> <p id="description"></p>'))
								.append($('<h2 class="materials"><hr> Course Material </h2> <p id="materials"></p>'))
								.append($('<h2 class="infoAndHours"><hr> Info and Office Hours </h2> <p id="infoAndHours"></p>'))
								.append($('<h2 class="breakdown"><hr> Grade Breakdown </h2> <p id="breakdown"></p>'))
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
		
		$('.rightside').css("height", $(window).height() );
		
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
	console.log(tabType);
	console.log(jsonData);
	
	jsonData = jsonData.replace(/\\n/g, "<br />");

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
		
		if(jsonArray.data[0].mon == 'true')
		{
			$('#daysofWeek').append('Mon');
		}
		
		if(jsonArray.data[0].tue == 'true')
		{
			$('#daysofWeek').append('Tue');
		}
		
		if(jsonArray.data[0].wed == 'true')
		{
			$('#daysofWeek').append('Wed');
		}
		
		if(jsonArray.data[0].thu == 'true')
		{
			$('#daysofWeek').append('Thu');
		}
		
		if(jsonArray.data[0].fri == 'true')
		{
			$('#daysofWeek').append('Fri');
		}
		
		if(jsonArray.data[0].sat == 'true')
		{
			$('#daysofWeek').append('Sat');
		}
		
		if(jsonArray.data[0].sun == 'true')
		{
			$('#daysofWeek').append('Sun');
		}
	}
	
	else if (tabType == 'Syllabus')
	{
		console.log(jsonArray.data[0].description);
		if(jsonArray.data[0].description == null || jsonArray.data[0].description == "")
			$('.description').hide();
		else
		// Course Description
			$('#description').html(jsonArray.data[0].description);
		
		if(jsonArray.data[0].materials == null || jsonArray.data[0].materials == "")
			$('.materials').hide();
		// Course Material
		else
			$('#materials').html(jsonArray.data[0].materials);
		
		if(jsonArray.data[0].infoAndHours == null || jsonArray.data[0].infoAndHours == "")
			$('.infoAndHours').hide();
		// Course Info and Office Hours
		else
			$('#infoAndHours').html(jsonArray.data[0].infoAndHours);
		
		if(jsonArray.data[0].breakdown == null || jsonArray.data[0].breakdown == "")
			$('.breakdown').hide();
		// Grade Breakdown
		else
			$('#breakdown').html(jsonArray.data[0].breakdown);
	}
	
	else if (tabType == 'Schedule')
	{
		// Important Dates
		$('#schedule').html(jsonArray.data[0].schedule);
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

// Active navigation when scrolling
$(window).scroll(function(){
	//Current Position from the top
	var currentPos = $(window).scrollTop();
	
	//Gets the position of each section
	//Checks if the currentPos is in a section and sets active button
	$('.rightside').each(function(i){
		if((currentPos >= $(this).position().top) && (currentPos <= $(this).position().top + $(this).height()))
		{
			$('.active').removeClass('active');
			$('.' + $(this).attr('id')).addClass('active');
		}
	});

})