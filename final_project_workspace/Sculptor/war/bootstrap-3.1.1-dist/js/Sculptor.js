
/* Variable used for new tab IDs */
var x = 10238412;
 
/* Adds new tab and content */
function addPage(){
	var newTabString = '#' + (x+1);
	
	/* Adds new tab for left side tab-bar */
	$("#tab-bar")
		.append(
			/* Basic attributes for left side tab-bar */
			$('<li></li>').append(
				$('<a>New Tab <span class="glyphicon glyphicon-remove pull-right newTab" onClick="removePage()"></span> </a>')
				.attr('href', newTabString)
				.attr('data-toggle', 'tab')
				.attr('id', x)
				.attr('onClick', 'tabClicked()'))
				.addClass('newTab')
		);
	x++;
	
	/* Adds content on right side */
	$(".tab-content")
		.append(
			$('<div></div>')
				.addClass('tab-pane')
				.addClass('newContentPane')
				.attr('id', x)
				.append(
					$('<h3> Title </h3><input type="text" class="form-control" placeholder="Assignments">')
					)
				.append(
					$('<h3> Information </h3><textarea class="form-control" row="3"></textarea><hr>')
					)
				.append(
					$('<input id="submit-button" type="submit" class="btn btn-default" value="Submit"> ')
					)
				.append(
					$('<input id="cancel-button" type="submit" class="btn btn-default" value="Cancel"> ')
					)	
				.append(
					$('<input id="preview-button" type="submit" class="btn btn-default" value="Preview"> ')
					)	
		);
		
	x++;
}

/* Removes current active tab */
function removePage(){
	$(".active.newTab").remove();
	$(".active.newContentPane").remove();
}

/* Dynamic editing of tab names
function tabClicked() {
	$(".newTab").click(function(event) {
		$(this).text('');
	});
}
*/

