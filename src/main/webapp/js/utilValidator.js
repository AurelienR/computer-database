function isValidDate(dateStr) {
//	var matches = /^(\d{4})[-\/](\d{2})[-\/](\d{2})$/.exec(dateStr);
//	if (matches == null)
//		return false;
//	if (isGreaterThan("1970-01-01", dateStr))
//		return false
//	return true
	if((getValidDateString(dateStr))==null) return false;
	if (isGreaterThan("1970-01-01", dateStr)) return false;
	return true;
}

function isGreaterThan(testedDate, refDate) {
	var refDateObj = getValidDateString(refDate);
	var testedDateObj = getValidDateString(testedDate);
	
	return testedDateObj > refDateObj;
}

function isEmptyField(fieldStr) {
	if (fieldStr == null)
		return true;
	if (fieldStr == undefined)
		return true;
	if (fieldStr == "")
		return true;

	return false;
}

function isValidId(id) {
	if (id == null)
		return false;
	if (id == undefined)
		return false;
	if (id == "")
		return false;
	if (isNaN(id))
		return false

	return true;
}

function isValidDateObject(d) {

	if (Object.prototype.toString.call(d) === "[object Date]") {
		// it is a date
		if (isNaN(d.getTime())) {
			// date is not valid
			return false;
		} else {
			// date is valid
			return true
		}
	} else {
		// not a date
		return false
	}
}

function parseISODate(s) {
	var bits = s.split('-');
	var d = new Date();
	d.setYear(bits[0])
	d.setMonth(bits[1] - 1);
	d.setDay(bits[2]);
	return d;
}

function parseRFCDate(s) {
	var bits = s.split('/');
	var d = new Date();
	d.setYear(bits[2])
	d.setMonth(bits[0] - 1);
	d.setDay(bits[1]);
	return d;
}

function parseFRDate(s) {
	var bits = s.split('/');
	var d = new Date();
	d.setYear(bits[2])
	d.setMonth(bits[1] - 1);
	d.setDay(bits[0]);
	return d;
}

function isISODate(dateStr){
	var matches = /^(\d{4})[-\/](\d{2})[-\/](\d{2})$/.exec(dateStr);
	if (matches == null) return false;
	if(!isValidDateObject(parseISODate(dateStr))) return false;
	return true
}

function isRFCDate(dateStr){
	var matches = /^(\d{2})[-\/](\d{2})[-\/](\d{4})$/.exec(dateStr);
	if (matches == null) return false;
	if(!isValidDateObject(parseRFCDate(dateStr))) return false;
	return true
}

function isFRDate(dateStr){
	var matches = /^(\d{2})[-\/](\d{2})[-\/](\d{4})$/.exec(dateStr);
	if (matches == null) return false;
	if(!isValidDateObject(parseFRDate(dateStr))) return false;
	return true
}

function getValidDateString(dateStr){	
	
	if(isISODate(dateStr)){
		return parseISODate(dateStr);
	}
	
	else if(lang == "fr_FR" && isFRDate(dateStr)){
		return parseFRDate(dateStr);
	}
	else if(lang == "en_US" && isRFCDate(dateStr)){
		return parseRFCDate(dateStr);
	}

	return null;
}
