$(function(){

	jQuery.validator.addMethod("dateComparison", function() {

		if ($("#introducedDate").val() == "" || $("#discontinuedDate").val() == "") {
			return true;
		} else {
			var dateIntroduced = new getDateFromStr($("#introducedDate").val());
			var dateDiscontinued = new getDateFromStr($("#discontinuedDate").val())
			return dateIntroduced < dateDiscontinued;
		}

	}, localized_strings['validation.introduced']);

	function getDateFromStr(dateStr){

		var array = dateStr.split("-");

		if(localized_strings['data.lang'] === 'en_US'){
			date = new Date(array[2], array[0], array[1]);
		}
		else if(localized_strings['data.lang'] === 'fr_FR'){
			date = new Date(array[2], array[1], array[0]);
		}

		return date;

	}

	jQuery.validator.addMethod("needsIntroduced", function() {
		
		if ($("#discontinuedDate").val() == "") {
			return true;
		} else if ($("#introducedDate").val() == "") {
			return false;
		} else {
			return true;
		}

	}, localized_strings['validation.discontinued']);
	
	jQuery.validator.addMethod("validateDate", function(value, element) {

		if(value == "") {
			return true;
        }

		return value.match(localized_strings['pattern']);

	}, localized_strings['validation.date'] + ' ' + localized_strings['date.pattern']);

    jQuery.validator.addMethod("dateBefore70", function(value, element) {

        if(value == "") {
            return true;
        } else{

            if(localized_strings['data.lang'] === 'en_US'){
                var minDate = new Date("01-02-1970");

                if(value < minDate){
                    return false;
                }
            }
            else if(localized_strings['data.lang'] === 'fr_FR'){
                var minDate = new Date("02-01-1970");

                if(value < minDate){
                    return false;
                }
            }
        }

    }, localized_strings['validation.date.before']);

	$("#computer_form").validate({
		
		rules : {
			"name": "required",

			"introducedDate": {
				dateComparison : true,
				required : false,
				validateDate : true,
                dateBefore70: true
			},

			"discontinuedDate": {
				dateComparison : true,
				needsIntroduced : true,
				required : false, 
				validateDate : true,
                dateBefore70: true
			},

			"companyId": {
				required : false,
				number : true
			}
		},

		messages : {
			name : localized_strings['validation.name'],
			companyId : localized_strings['validation.company']
		},

		highlight : function(element) {
			$(element).parent().closest("div").removeClass('has-success').addClass('has-error')
		},

		unhighlight : function(element) {
			$(element).parent().closest("div").removeClass('has-error').addClass('has-success')
		},

		submitHandler : function(form) {
			form.submit();
			alert(localized_strings['validation.success']);
		}
	});
	
$("#login-form").validate({
		
		rules : {
			"username_login": "required",
			"password_login": "required"
		},

		messages : {
			"username_login" : localized_strings['validation.username'],
			"password_login" : localized_strings['validation.password']
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
	
	jQuery.validator.addMethod("validatePsw", function(value, element) {
		return ($("#password_register").val() == $("#confirm-password").val())
	});
	
	$("#register-form").validate({
		
		rules : {
			"username_register": "required",
			"password_register": "required",
			"confirm-password": {
				validatePsw : true,
				required : true
			}
		},

		messages : {
			"username_register" : localized_strings['validation.username'],
			"password_register" : localized_strings['validation.password'],
			"confirm-password" : {
				"required": localized_strings['validation.password'],
				"validatePsw": localized_strings['validation.password.confirm']
			}
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