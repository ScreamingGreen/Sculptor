


$(document).ready(function() {


	// We need to catch what happens when a user presses
	// a navigation tab
	$('a[data-toggle="tab"]').click(function() {

		// Get the name of the navigation tab we are trying to access
		var nameOfPressedTab = this.id;
		//alert(nameOfPressedTab);

		// Clean the current HTML form so we can place a new one
		$('div[class="tab-content"]').html("");

		// We need to now set a new form 
		loadForm(nameOfPressedTab);
		
		//Populate form
		populateForm(nameOfPressedTab);
	});

/*
	//Load the tab order from datastore, ajax
	//We should store/load arrays with JSON, would be a good idea
	/// Ask servlet for order, Servlet returns an array of Tab Names with Type too, load the order in that way 
	loadTabOrder();
	
	//Load the home form
	loadForm("Home");
	
	//Populate the home form
	populateForm("Home");
		
	//Whenever a tab is clicked, we know we have to load the form and populate
	$(".coursetab").click(function(){
		alert(this.value);
		
		//Remove the current form
		cleanForm();
		
		//Load the form of current value
		loadForm(this.value);
		
		//Fill in with datastore information
		populateForm(this.value);
	});*/
});

// Do an AJAX call to get a form (Ex. HomeForm.html)
function loadForm(typeOfForm) {

	var formPath = "forms/"+typeOfForm+"Form.html";

	$.get(formPath, function(formHTML) {

		// Set the form we got
		$('div[class="tab-content"]').html(formHTML);

		// Now fill the form with data
		populateForm(typeOfForm);
	});
}

function populateHomeForm(jsonData) {

	alert("success populateHomeForm");

	// Course title
	$('input[name="courseTitle"]').html(jsonData);	
}

function populateForm(typeOfForm, jsonData) {

	if (typeOfForm == "Home") {
		populateHomeForm(jsonData);
	}
}

function populateForm(typeOfForm) {
	
	$.ajax({
	    type: 'post',
	    url: '/populateform',
	    dataType: 'json',
	    data: typeOfForm,
	    success: function(jsonData) {
	    	
	    	populateForm(typeOfForm,jsonData);
	    },
	    error: function(jsonData) {
	        alert('Error on populating form: ' + typeOfForm);
	    }
	});
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