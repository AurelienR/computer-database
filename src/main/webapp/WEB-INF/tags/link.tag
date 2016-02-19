<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="target" required="true"  type="java.lang.String"%>
<%@ attribute name="pageIndex" required="false"  type="java.lang.Integer"%>
<%@ attribute name="pageSize" required="false" type="java.lang.Integer"%>
<%@ attribute name="order" required="false" type="java.lang.String"%>
<%@ attribute name="orderBy" required="false" type="java.lang.String"%>
<%@ attribute name="search" required="false" type="java.lang.String"%>
<%@ attribute name="classe" required="false" type="java.lang.String"%>


<a href="${target}?page=${pageIndex}&pageSize=${pageSize}&orderBy=${orderBy}&order=${order}&search=${search}" class="${classe}">	
	<jsp:doBody/>
</a>
