$(function() {	
	
	jQuery.validator.addMethod("greaterThan", function(value, element, params) {    
	    if (!/Invalid|NaN/.test(new Date(value))) {
	        return new Date(value) > new Date($(params[0]).val());
	    }    
	    
	    return true;
	});

	$("#creation_form").validate({
		rules: {
			computerName: "required",
			
			introduced: {
				required: false,
				date: true
			},
			
			discontinued: {
				greaterThan: ["#introduced", "#discontinued"] && 
								function(){
						            return $("#introduced").val()!="";
						        },
				required: false,
				date: true
			},
			
			companyId: {
				required: false,
				number : true
			}
		},
		
		messages:{
			computerName: "Please enter a computer name.",
			introduced: "Please enter a introduced date or remove the discontinued date.",
			discontinued: "The Discontinued date can't be earlier than the introduced date.",
			companyId: "Invalid company."
		},
		
		highlight: function (element) {
            $(element).parent().closest("div").addClass('has-error').removeClass('has-success')
        },
        
        unhighlight: function (element) {
            $(element).parent().closest("div").removeClass('has-error').addClass('has-success')
        },
		
	    submitHandler: function(form) {
	        form.submit();
	    }
	});
});