<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>AccessDenied page</title>
<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./css/main.css" rel="stylesheet" media="screen">
</head>

<!-- ***************************** HEADER ***************************** -->
<jsp:include page="../header.jsp" />

<!-- ***************************** ERROR ***************************** -->
<body>
    <section id="main">
        <div class="container">
            <div class="alert alert-danger">
                <h3><spring:message code="title.accessDenied" /></h3>
                <br />
                <!-- stacktrace -->
                <ul>
                    <li><h4>Reason : <spring:message code="err.access_Denied" /></h4></li>
                </ul>

            </div>
        </div>
    </section>

	<script src="./js/jquery.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/dashboard.js"></script>
</body>
</html>