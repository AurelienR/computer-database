<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AccessDenied page</title>
</head>

<!-- ***************************** HEADER ***************************** -->
<jsp:include page="../header.jsp" />

<body>
	<h2>
		<spring:message code="title.accessDenied" />
	</h2>
	<h3>
		<spring:message code="err.access_Denied" />
	</h3>	
</body>
</html>