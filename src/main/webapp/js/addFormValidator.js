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
});



function validationNameDisplay(inputElement){
	
	if(isComputerNameValid()){
		setSuccessDisplay(inputElement);
	}
	else{
		setFailureDisplay(inputElement,"Name is required");
	}
	
}

function validationIntroducedDisplay(inputElement){
	if(isEmptyField(inputElement.val())){
		setWarningDisplay(inputElement);
	}
	else if(isIntroducedValid()){
		setSuccessDisplay(inputElement);
	}
	else{
		setFailureDisplay(inputElement,"Format dd/MM/yyyy");
	}
}

function validationDiscontinuedDisplay(inputElement){
	if(isEmptyField(inputElement.val())){
		setWarningDisplay(inputElement);
	}
	else if(isDiscontinuedValid()){
		setSuccessDisplay(inputElement);
	}
	else{
		setFailureDisplay(inputElement,"Format dd/MM/yyyy and greater than introduced date");
	}
}

function validationCompanyDisplay(selectElement){
	if(isValidId(selectElement.val())){
		setSuccessDisplay(selectElement);
	}
	else{
		setWarningDisplay(selectElement)
	}
}

function setSuccessDisplay(inputElement){
	
	var parentDivElement = inputElement.parent().closest('div')
	var spanIconElement = parentDivElement.children("span");
	var errorMessageElement = parentDivElement.children("div");
	
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

function setWarningDisplay(inputElement){
	
	var parentDivElement = inputElement.parent().closest('div')
	var spanIconElement =parentDivElement.children("span");
	var errorMessageElement =parentDivElement.children("div");
	
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

function setFailureDisplay(inputElement,errorMessageStr){
	
	var parentDivElement = inputElement.parent().closest('div')
	var spanIconElement = parentDivElement.children("span");
	var errorMessageElement = parentDivElement.children("div");
	
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
	
	parentDivElement.append("<div class=\"alert alert-danger\"> <strong>Danger</strong>  "+errorMessageStr+"  </div>")
	parentDivElement.append("<span class=\"glyphicon glyphicon-remove form-control-feedback\"></span>");	
};

function isComputerNameValid(){
	return !isEmptyField($("#computerName").val());
}

function isIntroducedValid(){
	
	var introduced = $("#introduced").val();
	var discontinued = $("#discontinued").val();
	
	if(!isEmptyField(introduced) && !isEmptyField(discontinued) && isGreaterThan(introduced,discontinued)) return false;
	if(!isEmptyField(introduced) && !isValidDate(introduced)) return false;
	
	return true;
}

function isDiscontinuedValid(){
	
	var introduced = $("#introduced").val();
	var discontinued = $("#discontinued").val();
	
	if((isEmptyField(introduced) || !isValidDate(introduced)) && !isEmptyField(discontinued) ) return false
	if(!isEmptyField(discontinued) && !isEmptyField(introduced) && isGreaterThan(introduced,discontinued)) return false;
	if(!isEmptyField(discontinued) && !isValidDate(discontinued)) return false;
	
	return true;
}

function isFormValid(){
	return isComputerNameValid() && isIntroducedValid() && isDiscontinuedValid();
}

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