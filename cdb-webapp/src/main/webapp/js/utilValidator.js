/**
 * Check either if date is a local date formated, superior to 1970.
 * @param dateStr date string to check
 * @returns {Boolean} true if valid, else false
 */
function isValidDateStr(dateStr) {
	if((getValidLocalDate(dateStr))==null) return false;
	if (isGreaterThan("01/01/1970", dateStr)) return false;
	return true;
}

/**
 * Check either if first argument date is greater than second argument date.
 * @param testedDate
 * @param refDate
 * @returns {Boolean}
 */
function isGreaterThan(testedDate, refDate) {
	var refDateObj = getValidLocalDate(refDate);
	var testedDateObj = getValidLocalDate(testedDate);
	
	return testedDateObj > refDateObj;
}

/**
 * Check either if the argument is null, undefined or empty.
 * @param fieldStr argument to check
 * @returns {Boolean} true if empty, else false
 */
function isEmptyField(fieldStr) {
	if (fieldStr == null)
		return true;
	if (fieldStr == undefined)
		return true;
	if (fieldStr == "")
		return true;

	return false;
}

/**
 * Check either if the id is null, undefined, empty or not a number.
 * @param id id to check
 * @returns {Boolean} true if valid, else false
 */
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

/**
 * Check either if the passed date is a valid date object.
 * @param d Date object to check
 * @returns {Boolean} true if argument is a Date object, else false
 */
function isValidDateObject(d) {
	if (Object.prototype.toString.call(d) === "[object Date]") {
		// it is a date
		if (isNaN(d.getTime())) {
			// date is not valid
			return false;
		} else {
			return true
		}
	} else {
		// not a date
		return false
	}
}

/**
 * Parse ISO date string.
 * @param s string to parse
 * @returns {Date} related date object
 */
function parseISODate(s) {
	var bits = s.split('-');
	var d = new Date();
	d.setUTCFullYear(bits[0])
	d.setUTCMonth(bits[1]);
	d.setUTCDate(bits[2]);
	return d;
}

/**
 * Parse RFC date string.
 * @param s string to parse
 * @returns {Date} related date object
 */
function parseRFCDate(s) {
	var bits = s.split('/');
	var d = new Date();
	d.setUTCFullYear(bits[2])
	d.setUTCMonth(bits[0]);
	d.setUTCDate(bits[1]);
	return d;
}

/**
 * Parse FR date string.
 * @param s string to parse
 * @returns {Date} related date object
 */
function parseFRDate(s) {
	var bits = s.split('/');
	var d = new Date();
	d.setUTCFullYear(bits[2])
	d.setUTCMonth(bits[1]);
	d.setUTCDate(bits[0]);
	return d;
}

/**
 * Check if date string is ISO format.
 * @param dateStr date string to check
 * @returns {Boolean} true if date is ISO format, else false
 */
function isISODate(dateStr){
	var matches = /^(\d{4})[-\/](\d{1,2})[-\/](\d{1,2})$/.exec(dateStr);
	var bits = dateStr.split('-');
	if (matches == null) return false;
	if(!isCoherentDate(bits[2], bits[1], bits[0])) return false;
	if(!isValidDateObject(parseISODate(dateStr))) return false;
	return true
}

/**
 * Check if date string is RFC format.
 * @param dateStr date string to check
 * @returns {Boolean} true if date is RFC format, else false
 */
function isRFCDate(dateStr){
	var matches = /^(\d{1,2})[-\/](\d{1,2})[-\/](\d{4})$/.exec(dateStr);
	var bits = dateStr.split('/');
	if (matches == null) return false;
	if(!isCoherentDate(bits[1], bits[0], bits[2])) return false;
	if(!isValidDateObject(parseRFCDate(dateStr))) return false;
	return true
}

/**
 * Check if date string is FR format.
 * @param dateStr date string to check
 * @returns {Boolean} true if date is FR format, else false
 */
function isFRDate(dateStr){
	var matches = /^(\d{1,2})[-\/](\d{1,2})[-\/](\d{4})$/.exec(dateStr);
	var bits = dateStr.split('/');
	if (matches == null) return false;
	if(!isCoherentDate(bits[0], bits[1], bits[2])) return false;
	if(!isValidDateObject(parseFRDate(dateStr))) return false;
	return true
}

/**
 * Parse any ISO date to local date string format.
 * @param dateStr date string to parse
 * @returns Local date string format
 */
function toLocalFormatDateStr(dateStr){
	if(isISODate(dateStr)){
		var date = parseISODate(dateStr);
		if(lang=="fr"){
			return date.getUTCDate()+'/'+date.getUTCMonth()+'/'+date.getUTCFullYear();
		}
		if(lang=="en"){
			return date.getUTCMonth()+'/'+date.getUTCDate()+'/'+date.getUTCFullYear();
		}
	}
	return dateStr;
}

/**
 * Get valid date object based on localization.
 * @param dateStr date string to parse
 * @returns date object if date string valid for localization, else false
 */
function getValidLocalDate(dateStr){	
	
	if(lang == "fr" && isFRDate(dateStr)){
		return parseFRDate(dateStr);
	}
	else if(lang == "en" && isRFCDate(dateStr)){
		return parseRFCDate(dateStr);
	}

	return null;
}

/**
 * Calculate the number of day in a month.
 * @param m month number
 * @param y year
 * @returns number of days
 */
function daysInMonth(m, y) {
    switch (m) {
        case 1 :
            return (y % 4 == 0 && y % 100) || y % 400 == 0 ? 29 : 28;
        case 8 : case 3 : case 5 : case 10 :
            return 30;
        default :
            return 31
    }
}

/**
 * Check if date is coherent.
 * @param d day
 * @param m month
 * @param y year
 * @returns {Boolean} true if coherent, else false
 */
function isCoherentDate(d, m, y) {
    return m >= 0 && m < 12 && d > 0 && d <= daysInMonth(m, y) && y>=1970;
}


/**
 * Convert DateString to iso if current browser is chrome
 * @param dateStr date to convert
 * @returns same date string or iso date string
 */
function toISOIfChrome(dateStr){
	var isChrome = !!window.chrome && !!window.chrome.webstore;
	if(isChrome){
		return getValidLocalDate(dateStr).toISOString();
	}	
	return dateStr;
}
