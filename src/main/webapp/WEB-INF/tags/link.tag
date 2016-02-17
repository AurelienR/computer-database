<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="target" required="true"  type="java.lang.String"%>
<%@ attribute name="pageIndex" required="true"  type="java.lang.Integer"%>
<%@ attribute name="pageSize" required="true" type="java.lang.Integer"%>
<%@ attribute name="classe" required="false" type="java.lang.String"%>


<a href="${target}?page=${pageIndex}&pageSize=${pageSize}" class="${classe}">	
	<jsp:doBody/>
</a>
