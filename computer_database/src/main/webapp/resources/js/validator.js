$(function(){

	jQuery.validator.addMethod("dateComparison", function() {

		if ($("#introduced").val() == ""
				|| $("#discontinuedDate").val() == "") {
			return true;
		} else {
			return $("#introducedDate").val() < $("#discontinued").val();
		}

	}, "The ending date must be a later date than the start date");

	jQuery.validator.addMethod("needsIntroduced", function() {
		
		if ($("#discontinuedDate").val() == "") {
			return true;
		} else if ($("#introducedDate").val() == "") {
			return false;
		} else {
			return true;
		}

	}, "An introduced date in mandatory with a discontinued date.");

	$("#computer_form").validate({
		
		rules : {
			"name": "required",

			"introducedDate": {
				dateComparison : true,
				required : false,
				date : true
			},

			"discontinuedDate": {
				needsIntroduced : true,
				required : false,
				date : true
			},

			"companyId": {
				required : false,
				number : true
			}
		},

		messages : {
			name : "Please enter a computer name.",
			companyId : "Invalid company."
		},

		highlight : function(element) {
			$(element).parent().closest("div").removeClass('has-success').addClass('has-error')
		},

		unhighlight : function(element) {
			$(element).parent().closest("div").removeClass('has-error').addClass('has-success')
		},

		submitHandler : function(form) {
			form.submit();
		}
	});
});