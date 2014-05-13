$(document).ready(function() {

	// We don't need to show the alert box yet
	$('#register-alert-box').hide();

	$("#reg-form").on( "submit", function( event ) {

		// There sometimes might be an error box created by the servlet so just hide it.
		$("#login-alert-box").hide();

		var hasError = false;

		var webId = $('#registerWebId').val();
		var email = $('#registerEmail').val();
		var password = $('#registerPass').val();

		var errorText = "<ul>";

		// Check web id
		if (webId.length < 3) {
			errorText += "<li>Website ID must be longer than 3 characters.</li>";
			$('#registerWebId').parent().addClass("has-error");
			hasError = true;
		}

		// Check email
		var atPosition = email.indexOf("@");
		var dotPosition = email.lastIndexOf(".");
		var regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

		if (!regex.test(email)) {
 			errorText += "<li>Email has incorrect format.</li>";
			$('#registerEmail').parent().addClass("has-error");
			hasError = true;
  		}

		// Check password
		if (password.length < 4) {
			errorText += "<li>Password must be longer than 4 characters.</li>";
			$('#registerPass').parent().addClass("has-error");
			hasError = true;
		}

		errorText += "</ul>";

		// There is an error, so display it and prevent form from submitting
		if (hasError) {
			event.preventDefault();
			removeErrors();
			$('#register-alert-box').html('');
			$('#register-alert-box').html('<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>');
			$('#register-alert-box').append(errorText);
			$('#register-alert-box').show();
		}
	});
});

function removeErrors() {
	$('#registerWebId').parent().removeClass("has-error");
	$('#registerEmail').parent().removeClass("has-error");
	$('#registerPass').parent().removeClass("has-error");
}