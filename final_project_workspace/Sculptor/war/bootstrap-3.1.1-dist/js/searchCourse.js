
	  var availableTags = ["C++","C"]; 
2

$(document).ready(function() {
	
	$(function(){
		
		$( "#search" ).autocomplete({

        	source: function(request, response){
        		
        		 $.ajax({
        			    type: 'post',
        			    url: '/searchCourse',
        			    dataType: 'text',
        			    success: function(jsonData) {

        			    	
        			    	var parsedJSON = jQuery.parseJSON(jsonData);
        			    	var data = parsedJSON.data[0];
        			    	
        			    	var data1 = [];
        			    	
        			    	for(var i=0;i<parsedJSON.data.length;i++)        			    	
        			    		data1.push(parsedJSON.data[i].webId);
        			    	
        			    	response(data1);
        			    	
        			    	
        			    },
        			    error: function(jsonData) {
        			        alert('Error on populating form: ' + typeOfForm);
        			    }
        		  });
        	}	        	
        });
		
		
	});
    
});

