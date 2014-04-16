$(document).ready(function() {

	// Load the home form first
	loadForm("Home");
	
	//Bind buttons accordingly
	bindButtons();
	
	// We need to catch what happens when a user presses
	// a navigation tab
	bindClickToTeacherNavigation();

	// Catch what happens when a menu item from the "+ Add Page"
	// button gets pressed
	bindClickToAddPageNavigation();
});

function bindButtons() {
	
	//Make save button do ajax to save form
	$('#save-button').click(function (){
		saveForm();
	});
}

function saveForm(){	
	   var frm = $('#pageForm');
	    frm.submit(function (ev) {
	        $.ajax({
	            type: frm.attr('method'),
	            url: frm.attr('action'),
	            data: frm.serialize(),
	            success: function (data) {},
	        	error: function (data) {alert ("Try again please."); }
	        });

	        ev.preventDefault();
	    });
}

function bindClickToTeacherNavigation() {
	$('a[data-toggle="tab"]').click(function() {

		// Get the name of the navigation tab we are trying to access
		var nameOfPressedTab = this.id;

		// Change the header title
		$('#page-title').html(nameOfPressedTab);

		// Clean the current HTML form so we can place a new one
		$('div[class="tab-content"]').html("");

		// We need to now load a new form in place of the old one
		loadForm(nameOfPressedTab);
		
	});
}

function bindClickToAddPageNavigation() {
	$('a[class="addpage-option"]').click(function() {
	
		var nameOfPressedOption = $(this).html().trim();

		// Adds new tab for left side tab-bar 
		addPage(nameOfPressedOption);

		// Make sure that the new navigation button that we 
		// added can fire off an event
		bindClickToTeacherNavigation();
	});
}

function addPage(nameOfPage) {

	// Add the item to the teacher navigation
	$("#tab-bar")
			.append(
				$('<li></li>')
				.append(
					$('<a></a>')
					.attr('data-toggle', 'tab')
					.attr('id', nameOfPage)
					.addClass('newTab')
					.append(
						// Need to modify this dynamically
						'<span class="glyphicon glyphicon-list-alt"></span>'
					)
					.append("&nbsp;&nbsp;&nbsp;")
					.append(nameOfPage)
					.append(
						'<button class="remove-button" type="button" onClick="removePage(this)"><span class="glyphicon glyphicon-remove pull-right"></span></button>'
					)
				)
			);
}

// Called when remove checkmark is pressed in teacher navigation
function removePage(removeButton) {

	// Remove the item from the teacher navigation
	var aElement = $(removeButton).parent();
	var liElement = aElement.parent();
	$(liElement).addClass("remove-the-element");
	$(".remove-the-element").remove();

	// Now remove the item from the datastore 
	var nameOfRemovedOption = $(aElement).attr('id');
}

// We need to get a new form (Ex. HomeForm.html)
function loadForm(typeOfForm) {
	var formPath = "forms/"+typeOfForm+"Form.html";

	$.get(formPath, function(formHTML) {

		// Set the form we got
		$('div[class="tab-content"]').html(formHTML);

		// Now fill the form with data
		populateForm(typeOfForm);
	});
}

// We need to populate the form we got with information
function populateForm(typeOfForm) {
	
	$.ajax({
	    type: 'post',
	    url: '/populateform',
	    dataType: 'text',
	    data: typeOfForm,
	    success: function(jsonData) {
	    	populateSpecificForm(typeOfForm,jsonData);
	    },
	    error: function(jsonData) {
	        alert('Error on populating form: ' + typeOfForm);
	    }
	});
}

function populateSpecificForm(typeOfForm, jsonData) {

	if (typeOfForm == "Home") {
		populateHomeForm(jsonData);
	}
}

function populateHomeForm(jsonData) {
	
	var parsedJSON = jQuery.parseJSON(jsonData);
	var data = parsedJSON.data[0];

	// Course code
	$('input[name="courseCode"]').val(data.courseCode);

	// Course title
	$('input[name="courseName"]').val(data.courseName);

	// Teacher name
	$('input[name="teacherName"]').val(data.teacherName);

	// Days of the week
	var days = ["mon","tue","wed","thu","fri","sat","sun"];
	$(days).each(function(){
		if (data[this] == "true") {
			$('input[name="'+this+'"]').parent().addClass("active");
		}
		else {
			$('input[name="'+this+'"]').parent().removeClass("active");
		}
	});

	// Start time
	$('input[name="startTime"]').val(data.startTime);

	// End time
	$('input[name="endTime"]').val(data.endTime);
}

function loadTabOrder() {
	
	//Get the tabs
	var tabs = document.getElementsByClassName('tab-pane');
	
	//Try to load tabs
	//Sends ajax request to servlet
	$.ajax({
	    type: 'post',
	    url: '/loadtaborder',
	    dataType: 'json',
	    data: '',
	    success: function(jsonData) {
	    	// So here, we have the jsonData of tabOrder, we need to get the array and push elements to the tab order
	    	// Also, we need to make the select menu for each form to the type of the tab...
	    	
	    },
	    error: function(jsonData) {
	        alert('error');
	    }
	});
}

//Make the jQuery button action when the user saves the tab order, NEED AJAX again
/// What to send to servlet:
/// Array of JSOs with  (tabName, typeOfForm)
/// ie : [ (tabName:"HomePage", type:"Home") , (tabName : "HW 3", type : File) ... ]
/// You will get either success or failure...

function saveTabOrder() {
	
	//Gets the tab name and type of each tab
	var tabs = document.getElementsByClassName('tab-pane');
	var tabsType = document.getElementsByName('type-of-form');
	
	//Creating json string
	var JSONString = '{"tabOrder":[]}';
	for(var i = 0; i<tabs.length; i++)
	{
		
		//Parses the string into array
		JSONString = JSON.parse(JSONString);
		
		//Second object with next tabs information
		var obj2 = new Object();
		obj2.name = tabs[i].id;
		obj2.type = tabsType[i].options[tabsType[i].selectedIndex].text;
		var JSONString2 = JSON.stringify(obj2);
		
		//Parses second object string into array and pushes it into default string
		obj2 = JSON.parse(JSONString2);
		JSONString['tabOrder'].push(obj2);
		
		//Turns array back into string
		JSONString = JSON.stringify(JSONString);
		console.log(JSONString);
	}
	
	//Sends ajax request to servlet
	$.ajax({
	    type: 'post',
	    url: '/savetab',
	    dataType: 'text',
	    data: JSONString,
	    success: function(data) {
	    	alert('Success saving tab order!')
	    },
	    error: function(data) {
	        alert('Error');
	    }
	});
}