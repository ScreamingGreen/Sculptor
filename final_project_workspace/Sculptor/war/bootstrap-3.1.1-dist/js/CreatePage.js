$(document).ready(function() {

	// Load teacher navigation
	loadTeacherNavigation();

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
	
	//Preview button
	$('#preview-button').click(function (){
		previewForm();
	});
	
	//Cancel button
	$('#cancel-button').click(function (){
		cancelForm();
	});
}

/***************************************
 *** Navigation Bar Stuff
 ***************************************/

 function bindClickToTeacherNavigation() {
	$('a[data-toggle="tab"]').unbind('click');
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
	$('a[class="addpage-option"]').unbind('click');
	$('a[class="addpage-option"]').click(function() {
	
		var nameOfPressedOption = $(this).html().trim();

		// Adds new tab for left side tab-bar 
		addPage(nameOfPressedOption);

		// Remove the item from add menu
		var liElement = $(this).parent();
		$(liElement).addClass("remove-addpage-option");
		$(".remove-addpage-option").remove();

		
	});
}

function addPage(nameOfPage) {

	// Add the item to the teacher navigation
	addPageToNavigation(nameOfPage);

	// Save the new navigation
	saveTeacherNavigation();

	// TODO: Add page to data store 
}

function addPageToNavigation(nameOfPage) {

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
	// Make sure that the new navigation button that we 
	// added can fire off an event
	bindClickToTeacherNavigation();
}

// Called when remove checkmark is pressed in teacher navigation
function removePage(removeButton) {

	// Remove the item from the teacher navigation
	var aElement = $(removeButton).parent();
	var liElement = aElement.parent();
	$(liElement).addClass("remove-the-element");
	$(".remove-the-element").remove();

	// Get the name of the removed option
	var nameOfRemovedOption = $(aElement).attr('id');

	// Add the element back to add page navigation
	addOptionToAddPageNavigation(nameOfRemovedOption);

	// Save the new navigation
	saveTeacherNavigation();

	// TODO: Remove page from data store	
}

function addOptionToAddPageNavigation(pageName) {
	$("#page-dropdown-menu")
		.append(
			$('<li></li>')
			.attr('role', 'presentation')
			.append(
				$('<a></a>')
				.attr('role', 'menuitem')
				.attr('tabindex', '-1')
				.attr('href', '#')
				.addClass('addpage-option')
				.html(pageName)
			)
		);
	bindClickToAddPageNavigation();
}


function loadTeacherNavigation() {
	
	//Get the tabs
	var tabs = document.getElementsByClassName('tab-pane');
	
	//Try to load tabs
	//Sends ajax request to servlet
	$.ajax({
	    type: 'post',
	    url: '/loadtaborder',
	    dataType: 'text',
	    data: '',
	    success: function(jsonData) {
	    	// So here, we have the jsonData of tabOrder, we need to get the array and push elements to the tab order
	    	// Also, we need to make the select menu for each form to the type of the tab...
	    	populateTeacherNavigation(jsonData);
	    },
	    error: function(jsonData) {
	        alert('Error loading teacher navigation');
	    }
	});
}

function populateTeacherNavigation(jsonData) {
	console.log(jsonData);

	var parsedJSON = jQuery.parseJSON(jsonData);
	var data = parsedJSON.tabOrder;

	// A set of all available menu options
	var allMenuOptions = new StringSet();
	allMenuOptions.add("Syllabus");
	allMenuOptions.add("Schedule");
	allMenuOptions.add("Files");

	// Add the options to the main teacher navigation
	// We don't want to add 'Home' to the menu
	for (i = 1; i < data.length; i++) {
		var nameOfPage = data[i].type;
		allMenuOptions.remove(nameOfPage);
		addPageToNavigation(nameOfPage);
	}

	// Add the rest of the options to the 'add page' menu
	var addPageMenuOptions = allMenuOptions.values();
	for (i = 0; i < addPageMenuOptions.length; i++) {
		addOptionToAddPageNavigation(addPageMenuOptions[i]);
	}
}

//Make the jQuery button action when the user saves the tab order, NEED AJAX again
/// What to send to servlet:
/// Array of JSOs with  (tabName, typeOfForm)
/// ie : [ (tabName:"HomePage", type:"Home") , (tabName : "HW 3", type : File) ... ]
/// You will get either success or failure...
function saveTeacherNavigation() {
	
	//Gets the tab name of each tab
	var tabs = $('#tab-bar').children().children();
	
	//Creating json string
	var JSONString = '{"tabOrder":[]}';
	for (var i = 0; i<tabs.length; i++) {
		
		//Parses the string into array
		JSONString = JSON.parse(JSONString);
		
		//Second object with next tabs information
		var obj2 = new Object();
		obj2.type = tabs[i].id;
		var JSONString2 = JSON.stringify(obj2);
		
		//Parses second object string into array and pushes it into default string
		obj2 = JSON.parse(JSONString2);
		JSONString['tabOrder'].push(obj2);
		
		//Turns array back into string
		JSONString = JSON.stringify(JSONString);
		
		console.log(tabs[i].id);
	}
	
	//Sends ajax request to servlet
	$.ajax({
	    type: 'post',
	    url: '/savetab',
	    dataType: 'text',
	    data: JSONString,
	    success: function(data) {
	    	//alert('Success saving tab order!')
	    },
	    error: function(data) {
	        alert('Error saving tab order!');
	    }
	});
}


/***************************************
 *** Form header stuff
 ***************************************/

function previewForm(){
	alert("preview Form Triggered");
}

function cancelForm() {

	// Get the name of the form 
	var hiddenElement = $('#pageForm #hidden-form-element');
	var nameOfForm = hiddenElement.val();

	// Reload the page which acts as canceling changes
	loadForm(nameOfForm);
}

function saveForm(){	
	   var frm = $('#pageForm');
	    frm.submit(function (ev) {
	        $.ajax({
	            type: frm.attr('method'),
	            url: frm.attr('action'),
	            data: frm.serialize(),
	            success: function (data) {
	            	$("#successSaveMsg").fadeIn("slow", function(){}); 
	            	$("#successSaveMsg").fadeOut("slow", function(){});
	            },
	        	error: function (data) {alert ("Try again please."); }
	        });

	        ev.preventDefault();
	    });
}

/***************************************
 *** Form stuff
 ***************************************/

// We need to get a new form (Ex. HomeForm.html)
function loadForm(typeOfForm) {
	var formPath = "forms/"+typeOfForm+"Form.html" + "?" + new Date().getTime();

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
	
	if (typeOfForm == "Syllabus") {
		populateSyllabusForm(jsonData);
	}
	
	if (typeOfForm == "Schedule") {
		populateScheduleForm(jsonData);
	}
	
	if (typeOfForm == "Files") {
		populateFilesForm(jsonData);
	}
}

function populateHomeForm(jsonData) {
	
	//Enables save button if disabled.
	$('#save-button').prop('disabled', false);
	
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
			var inputElement = $('input[name="'+this+'"]');
			inputElement.parent().addClass("active");
			inputElement.attr('checked', 'checked');
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

function populateSyllabusForm(jsonData){
	
	//Enables save button if disabled.
	$('#save-button').prop('disabled', false);
	
	var parsedJSON = jQuery.parseJSON(jsonData);
	var data = parsedJSON.data[0];
	
	//Description
	$('textarea[name="description"]').val(data.description);
	
	//Course Material
	$('textarea[name="materials"]').val(data.materials);
	
	//Office Hours
	$('textarea[name="infoAndHours"]').val(data.infoAndHours);
	
	//Grade breakdown
	$('textarea[name="breakdown"]').val(data.breakdown);
}

function populateScheduleForm(jsonData){
	
	//Enables save button if disabled.
	$('#save-button').prop('disabled', false);
	
	var parsedJSON = jQuery.parseJSON(jsonData);
	var data = parsedJSON.data[0];
	
	$('textarea[name="schedule"]').val(data.schedule);
}

function populateFilesForm(jsonData){
	
	//Disables save button.
	$('#save-button').prop('disabled', true);
	
	console.log(jsonData);
}


/* 	Helper library 
 *	http://stackoverflow.com/questions/4343746/is-there-a-data-structure-like-the-java-set-in-javascript
 */
function StringSet() {
    var setObj = {}, val = {};

    this.add = function(str) {
        setObj[str] = val;
    };

    this.contains = function(str) {
        return setObj[str] === val;
    };

    this.remove = function(str) {
        delete setObj[str];
    };

    this.values = function() {
        var values = [];
        for (var i in setObj) {
            if (setObj[i] === val) {
                values.push(i);
            }
        }
        return values;
    };
}

