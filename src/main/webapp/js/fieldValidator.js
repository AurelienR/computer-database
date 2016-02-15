function isValidName(name){
	return !isEmptyField(name);
}

function isValidDate(dateStr){
    var matches = /^(\d{2})[-\/](\d{2})[-\/](\d{4})$/.exec(dateStr);
    if (matches == null) return false;
    return true
}

function isEmptyField(fieldStr){
	if(fieldStr == null) return true;
	if(fieldStr == undefined) return true;
	if(fieldStr == "") return true;
	
	return false;
} 