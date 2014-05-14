$(document).ready(function() {

	// We don't need to show the alert box yet
	$('#manage-account-alert-box').hide();

	$(".form-change-password").on( "submit", function( event ) {

		removeErrors();

		var hasError = false;

		var oldPassword = $('#oldPassword').val();
		var newPassword = $('#newPassword').val();

		var errorText = "<ul>";

		// Check new password
		if (oldPassword.length < 4) {
			errorText += "<li>Old password must be longer than 4 characters.</li>"; 
			$('#oldPassword').parent().addClass("has-error");
			hasError = true;
		}

		// Check old password
		if (newPassword.length < 4) {
			errorText += "<li>New password must be longer than 4 characters.</li>";
			$('#newPassword').parent().addClass("has-error");
			hasError = true;
		}

		errorText += "</ul>";

		// There is an error, so display it and prevent form from submitting
		if (hasError) {
			event.preventDefault();
			$('#manage-account-alert-box').html('');
			$('#manage-account-alert-box').html('<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>');
			$('#manage-account-alert-box').append(errorText);
			$('#manage-account-alert-box').show();
		}
	});
});

function removeErrors() {
	$('#oldPassword').parent().removeClass("has-error");
	$('#newPassword').parent().removeClass("has-error");
}