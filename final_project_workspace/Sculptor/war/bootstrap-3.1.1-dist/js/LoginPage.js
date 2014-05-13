$(document).ready(function() {

	// Display a registration success message if we see in the URL
	// that registration was succesful
	if (getQueryVariable("success")=='true') {

		$.bootstrapGrowl("<b><p>Registration successful!</p>  Please login. </b>", {
		  type: 'success', // (null, 'info', 'error', 'success')
		  offset: {from: 'top', amount: 70}, // 'top', or 'bottom'
		  delay: 4000
		});
	}
});

function getQueryVariable(variable)
{
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
}