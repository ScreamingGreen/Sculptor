
$(document).ready(
function() {
	  var availableTags; 
//		 = [
//      "ActionScript",
//      "AppleScript",
//      "Asp",
//      "BASIC",
//      "C",
//      "C++",
//      "Clojure",
//      "COBOL",
//      "ColdFusion",
//      "Erlang",
//      "Fortran",
//      "Groovy",
//      "Haskell",
//      "Java",
//      "JavaScript",
//      "Lisp",
//      "Perl",
//      "PHP",
//      "Python",
//      "Ruby",
//      "Scala",
//      "Scheme"
//    ];
	
	
	$.ajax({
	    type: 'post',
	    url: '/searchCourse',
	    dataType: 'text',
	    data: $("#searchCourse").val(),
	    success: function(jsonData) {
	    	
	    	availableTags = jsonData;
	    	
	    },
	    error: function(jsonData) {
	        alert('Error on populating form: ' + typeOfForm);
	    }
	});
	  
    $( "#search" ).autocomplete({
      source: availableTags
    });
});