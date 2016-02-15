function isValidDate(dateStr){
    var matches = /^(\d{4})[-\/](\d{2})[-\/](\d{2})$/.exec(dateStr);
    if (matches == null) return false;
    return true
}

function isGreaterThan(testedDate, refDate){
   return new Date(testedDate) > new Date(refDate);
}


function isEmptyField(fieldStr){
	if(fieldStr == null) return true;
	if(fieldStr == undefined) return true;
	if(fieldStr == "") return true;
	
	return false;
} 

function isValidId(id){
	if(id == null) return false;
	if(id == undefined) return false;
	if(id == "") return false;
	if(isNaN(id)) return false
	
	return true;
} 