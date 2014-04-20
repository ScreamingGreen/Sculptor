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
		for(i=0; i<1; i++)
		{
			//Sends ajax request to servlet
			//Used to get the information in each page tab
			$.ajax({
			    type: 'post',
			    url: '/loadStudentMain',
			    dataType: 'text',
			    data: {'webId':'afung', 'type':jsonArray.tabOrder[i].type},
			    success: function(jsonData) {
			    		loadStudentPageMain(jsonData);				    		
			    },
			    error: function(jsonData) {
			        alert('Error loading teacher navigation');
			    }
			});
		}
}


//loads rightside of student page with information from tabs
function loadStudentPageMain(jsonData){
	console.log(jsonData);
	var jsonArray = jQuery.parseJSON(jsonData);
	console.log(jsonArray);
	
	//Loads data from JSON into rightside of student page
	$("#Home").append($('<h1>'+jsonArray.data[0].courseCode+'</h1>'));
	$("#Home").append($('<h2>'+jsonArray.data[0].courseName+'</h2>'));
	$("#Home").append($('<h2>'+jsonArray.data[0].teacherName+'</h2>'));
}