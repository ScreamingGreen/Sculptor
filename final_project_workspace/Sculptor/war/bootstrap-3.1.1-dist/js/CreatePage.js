document.ready(function() {
	
	//Load the tab order from datastore, ajax
	//We should store/load arrays with JSON, would be a good idea
	/// Ask servlet for order, Servlet returns an array of Tab Names with Type too, load the order in that way 
	
	//Load the form data for all forms, ajax again	
	/// Input : Array of Tab Names and Type of Form for each
	///     ie: [ ("HomePage", "Home") , ("Hw3", File) ]
	/// Return : A JSON array of all the form data in the DB
	///     ie: [ (tabName : 123, teacherName : McKenna , ....) , (tabName : Hw#3) ]
	
	
	//Need jQuery for on switch of tabs, where it warns user that they will lose changes,
	//then reload that form with data in datastore... with ajax :( 
	
	
	//Make the jQuery button action when the user saves the tab order, NEED AJAX again
	/// What to send to servlet:
	/// Array of JSOs with  (tabName, typeOfForm)
	/// ie : [ (tabName:"HomePage", type:"Home") , (tabName : "HW 3", type : File) ... ]
	/// You will get either success or failure...
	
});