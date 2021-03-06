/**
 * Bind form inputs
 */
$(function() {
    $('#computerName').bind('input propertychange', function() {
    	validationNameDisplay($(this));
    	checkReadySubmission();
     });
    
    $('#introduced').bind('input propertychange', function() {
    	validationIntroducedDisplay($(this));
    	validationDiscontinuedDisplay($('#discontinued'));
    	checkReadySubmission();
     });
    
    $('#discontinued').bind('input propertychange', function() {
    	validationDiscontinuedDisplay($(this));
    	checkReadySubmission();
     });
    
    $('#companyId').change(function(){
        validationCompanyDisplay($('#companyId'));
        checkReadySubmission();
    });
    
    $('addForm').submit(function(){
    	$('#introduced').val(toLocalFormatDateStr(('#introduced').val()));
    	$('#discontinued').val(toLocalFormatDateStr(('#discontinued').val()));
    });
    
});


/**
 * Set computer name input style based on its validation
 * @param inputElement ComputerName Input element to check
 */
function validationNameDisplay(inputElement){
	
	if(isComputerNameValid()){
		setSuccessDisplay(inputElement);
	}
	else{
		setFailureDisplay(inputElement, field_required_err);
	}
	
}

/**
 * Set introduced date input style based on its validation
 * @param inputElement Introduced input element
 */
function validationIntroducedDisplay(inputElement){
	if(isEmptyField(inputElement.val())){
		setWarningDisplay(inputElement);
	}
	else if(isIntroducedValid()){
		setSuccessDisplay(inputElement);
	}
	else{
		setFailureDisplay(inputElement,"<ul><li>"+ date_format_err +" </li><li>"+min_date_err+"</li><ul>");
	}
}

/**
 * Set discontinued date input style based on its validation
 * @param inputElement Discontinued input element
 */
function validationDiscontinuedDisplay(inputElement){
	if(isEmptyField(inputElement.val())){
		setWarningDisplay(inputElement);
	}
	else if(isDiscontinuedValid()){
		setSuccessDisplay(inputElement);
	}
	else{
		setFailureDisplay(inputElement,"<ul><li>"+ date_format_err+"</li><li>"+min_date_err+"</li> <li>"+ date_inconsistency_err +" </li><ul>");
	}
}

/**
 * Set Company select style based on its validation
 * @param selectElement Company select element
 */
function validationCompanyDisplay(selectElement){
	if(isValidId(selectElement.val())){
		setSuccessDisplay(selectElement);
	}
	else{
		setWarningDisplay(selectElement)
	}
}

/**
 * Set passed element to success (work on Bootstrap classed)
 * @param inputElement any form element
 */
function setSuccessDisplay(inputElement){
	
	var parentDivElement = inputElement.parent().closest('div.form-group');
	var spanIconElement = parentDivElement.children("span");
	var errorMessageElement = parentDivElement.children("div.alert");
	
	parentDivElement.removeClass("has-error");
	parentDivElement.removeClass("has-warning");
	parentDivElement.removeClass("has-success");
	parentDivElement.addClass("has-success");
	
	if(spanIconElement != null ){
		spanIconElement.remove();
	}
	if(errorMessageElement!= null ){
		errorMessageElement.remove();
	}
	
	parentDivElement.append("<span class=\"glyphicon glyphicon-ok form-control-feedback\"></span>");	
};

/**
 * Set passed element to warning (work on Bootstrap class)
 * @param inputElement any form element
 */
function setWarningDisplay(inputElement){
	
	var parentDivElement = inputElement.parent().closest("div.form-group");
	var spanIconElement =parentDivElement.children("span");
	var errorMessageElement =parentDivElement.children("div.alert");
	
	parentDivElement.removeClass("has-error");
	parentDivElement.removeClass("has-warning");
	parentDivElement.removeClass("has-success");
	parentDivElement.addClass("has-warning");
	
	if(spanIconElement != null ){
		spanIconElement.remove();
	}
	if(errorMessageElement!= null ){
		errorMessageElement.remove();
	}
parentDivElement.append("<span class=\"glyphicon glyphicon-warning-sign form-control-feedback\"></span>");	
}

/**
 * Set passed element to failure (work on Bootstrap class)
 * @param inputElement any form element
 */
function setFailureDisplay(inputElement,errorMessageStr){
	
	var parentDivElement = inputElement.parent().closest("div.form-group");
	var spanIconElement = parentDivElement.children("span");
	var errorMessageElement = parentDivElement.children("div.alert");
	
	parentDivElement.removeClass("has-error");
	parentDivElement.removeClass("has-warning");
	parentDivElement.removeClass("has-success");	
	parentDivElement.addClass("has-error");
	
	if(spanIconElement != null ){
		spanIconElement.remove();
	}
	if(errorMessageElement != null ){
		errorMessageElement.remove();
	}
	
	parentDivElement.append("<div class=\"alert alert-danger\"> <strong>Danger:</strong>  "+errorMessageStr+"  </div>")
	parentDivElement.append("<span class=\"glyphicon glyphicon-remove form-control-feedback\"></span>");	
};

/**
 * Check #computerName field value
 * @returns true if valid, else false
 */
function isComputerNameValid(){
	return !isEmptyField($("#computerName").val());
}

/**
 * Check #introduced field value
 * @returns true if valid, else false
 */
function isIntroducedValid(){
	
	var introduced = toLocalFormatDateStr($("#introduced").val());
	var discontinued = toLocalFormatDateStr($("#discontinued").val());
	
	if((isEmptyField(introduced) || !isValidDateStr(introduced)) && !isEmptyField(discontinued) ) return false
	if(!isEmptyField(introduced) && !isValidDateStr(introduced)) return false;
	
	return true;
}

/**
 * Check #discontinued field value
 * @returns true if valid, else false
 */
function isDiscontinuedValid(){
	
	var introduced = toLocalFormatDateStr($("#introduced").val());
	var discontinued = toLocalFormatDateStr($("#discontinued").val());	

	if(!isEmptyField(discontinued) && !isEmptyField(introduced) && isGreaterThan(introduced,discontinued)) return false;
	if(!isEmptyField(discontinued) && !isValidDateStr(discontinued)) return false;
	
	return true;
}

/**
 * Check all fields are valid
 * @returns true if valid, else false
 */
function isFormValid(){
	return isComputerNameValid() && isIntroducedValid() && isDiscontinuedValid();
}

/**
 * Enable the form #addForm to be sent or not based on validation
 */
function checkReadySubmission(){	
	
	var submitBtn = $("#addBtn");	
	var form = $("#addForm");
	
	if(isFormValid()){
		submitBtn.prop('disabled', false);
		form.submit(function(ev){
			return;
		});
	}
	else{
		submitBtn.prop('disabled', true);
		form.submit(function(ev){
			ev.preventDefaullt();
		});
	}
}