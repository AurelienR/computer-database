(function() {
    $('#computerName').bind('input propertychange', function() {
    	validationNameDisplay($(this));
     });
    
    $('#introduced').bind('input propertychange', function() {
    	validationDateDisplay($(this));
     });
    
    $('#discontinued').bind('input propertychange', function() {
    	validationDateDisplay($(this));
     });  
})();


function validationNameDisplay(inputTag){
	
	var value = inputTag.val();
	var parentDiv = inputTag.parent().closest('div')
	var spanIcon = parentDiv.children("span");
	var errorMessageTag = parentDiv.children("div");
	
	if(isValidName(value)){
		successInputDisplay(parentDiv,spanIcon,errorMessageTag);
	}
	else{
		failedInputDisplay(parentDiv,spanIcon,errorMessageTag,"Name is required");
	}
}

function validationDateDisplay(inputTag){
	
	var date = inputTag.val();
	var parentDiv = inputTag.parent().closest('div')
	var spanIcon = parentDiv.children("span");
	var errorMessageTag = parentDiv.children("div");
	
	if(isEmptyField(date)){
		warningInputDisplay(parentDiv, spanIcon, errorMessageTag);
	}
	else if(isValidDate(date)){
		successInputDisplay(parentDiv,spanIcon,errorMessageTag);
	}
	else{
		failedInputDisplay(parentDiv,spanIcon,errorMessageTag,"Must be format dd/MM/yyy");
	}
}

function successInputDisplay(parentDivTag,spanIconTag,errorMessageTag){
	
	parentDivTag.removeClass("has-error");
	parentDivTag.removeClass("has-warning");
	parentDivTag.removeClass("has-success");
	
	parentDivTag.addClass("has-success");
	
	if(spanIconTag != null ){
		spanIconTag.remove();
	}
	if(errorMessageTag!= null ){
		errorMessageTag.remove();
	}
	
	parentDivTag.append("<span class=\"glyphicon glyphicon-ok form-control-feedback\"></span>");	
};

function warningInputDisplay(parentDivTag,spanIconTag,errorMessageTag){
	
	parentDivTag.removeClass("has-error");
	parentDivTag.removeClass("has-warning");
	parentDivTag.removeClass("has-success");
	
	parentDivTag.addClass("has-warning");
	
	if(spanIconTag != null ){
		spanIconTag.remove();
	}
	if(errorMessageTag!= null ){
		errorMessageTag.remove();
	}
	parentDivTag.append("<span class=\"glyphicon glyphicon-warning-sign form-control-feedback\"></span>");	
}

function failedInputDisplay(parentDivTag,spanIconTag,errorMessageTag,errorMessage){
	
	parentDivTag.removeClass("has-error");
	parentDivTag.removeClass("has-warning");
	parentDivTag.removeClass("has-success");
	
	parentDivTag.addClass("has-error");
	
	if(spanIconTag!= null ){
		spanIconTag.remove();
	}
	if(errorMessageTag != null ){
		errorMessageTag.remove();
	}
	
	parentDivTag.append("<div class=\"alert alert-danger\"> <strong>Danger</strong>  "+errorMessage+"  </div>")
	parentDivTag.append("<span class=\"glyphicon glyphicon-remove form-control-feedback\"></span>");	
};