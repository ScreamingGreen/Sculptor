
$(document).ready(function() {
	
	$('#searchForm').submit(function(e) {
		e.preventDefault();
		var form = this;
		
		//Search for webId
		
		$.ajax({
		    type: 'post',
		    url: '/searchWebId',
		    dataType: 'text',
		    data: $('#search').val(),
		    success: function(data) {
		    	if(data != 'yes') {
		    		alert("No Web Id exist");
		    	} else {
		    		document.location.href = '/studentpage.jsp?webId=' + $('#search').val();
		    	}
		    },
		    error: function(jsonData) {
		        alert("Error on servlet call");
		    }
		});
	});
	
	$.ajax({
	    type: 'post',
	    url: '/searchCourse',
	    dataType: 'text',
	    success: function(jsonData) {
	    	
	    	var parsedJSON = jQuery.parseJSON(jsonData);
	    	var data = parsedJSON.data[0];
	    	
	    	var data1 = [];
	    	
	    	for(var i=0;i<parsedJSON.data.length;i++)        			    	
	    		data1.push(parsedJSON.data[i].name);
    	    
	    	var NoResultsLabel = "No Results";
	    	
	    	$( "#search" ).autocomplete({

	        	minLength: 0,
	        	source: function(request, response) {
	                var results = $.ui.autocomplete.filter(data1, request.term);

	                if (!results.length) {
	                    results = [NoResultsLabel];
	                }

	                response(results);
	            }
	        	
	        });
	    	
	    	
	    },
	    error: function(jsonData) {
	        alert('Error displaying courses' + jsonData);
	    }
  });
    
});

