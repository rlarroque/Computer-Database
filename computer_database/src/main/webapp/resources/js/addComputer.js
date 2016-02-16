$(function() {

	jQuery.validator.addMethod("dateComparison", function() {

		if ($("#introduced").val().toString() == ""
				|| $("#discontinued").val().toString() == "") {
			return true;
		} else {
			return $("#introduced").val() < $("#discontinued").val()
					.toString();
		}

	}, "The ending date must be a later date than the start date");

	jQuery.validator.addMethod("needsIntroduced", function() {
		
		if ($("#discontinued").val().toString() == "") {
			return true;
		} else if ($("#introduced").val().toString() == "") {
			return false;
		} else {
			return true;
		}

	}, "An introduced date in mandatory with a discontinued date.");

	$("#creation_form").validate({
		
		rules : {
			computerName : "required",

			introduced : {
				dateComparison : true,
				required : false,
				date : true
			},

			discontinued : {
				dateComparison : true,
				needsIntroduced : true,
				required : false,
				date : true
			},

			companyId : {
				required : false,
				number : true
			}
		},

		messages : {
			computerName : "Please enter a computer name.",
			companyId : "Invalid company."
		},

		highlight : function(element) {
			$(element).parent().closest("div").addClass('has-error')
					.removeClass('has-success')
		},

		unhighlight : function(element) {
			$(element).parent().closest("div").removeClass('has-error')
					.addClass('has-success')
		},

		submitHandler : function(form) {
			form.submit();
			alert('Computer Successfully created');
		}
	});
});