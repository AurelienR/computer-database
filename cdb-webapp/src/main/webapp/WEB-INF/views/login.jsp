<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login page</title>
<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen" />
<link href="./css/font-awesome.css" rel="stylesheet" media="screen" />
<link href="./css/login.css" rel="stylesheet" media="screen" />
</head>

<body>
    <!-- ***************************** LOCALIZATION DECLARATION ***************************** -->
    <!-- Buttons -->
    <spring:message code="btn.search" var="btnSearch" />
    <spring:message code="btn.filterByName" var="btnFilterByName" />

    <!-- ***************************** HEADER ***************************** -->
    <jsp:include page="header.jsp" />
    <!-- Label -->
    <spring:message code="label.username" var="labelUsername" />
    <spring:message code="label.password" var="labelPassword" />
    
    
    <!-- ***************************** LOGIN ***************************** -->
	<div class="container">
		<div class="wrapper">
			<form action="${loginUrl}" method="post" class="form-signin">
				<h3 class="form-signin-heading"><spring:message code="title.login"/></h3>
				<hr class="colorgraph">
				<br>
				<c:if test="${param.error != null}">
					<div class="alert alert-danger">
						<p><spring:message code="err.login.invalid_credentials"/></p>
					</div>
				</c:if>
				<c:if test="${param.logout != null}">
					<div class="alert alert-success">
						<p><spring:message code="msg.logout"/></p>
					</div>
				</c:if>
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-user"></i></span> <input type="text"
						class="form-control" id="username" name="username"
						placeholder="${labelUsername}" autofocus="" required>
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-lock"></i></span> <input type="password"
						class="form-control" id="password" name="password"
						placeholder="${labelPassword}" required> <input type="hidden"
						name="${_csrf.parameterName}" value="${_csrf.token}" />
				</div>
                <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
                <div class="row" style="margin-top:10px">
					<div class="col-sm-6">
						<button class="btn btn-lg btn-block btn-primary" name="Submit"
							value="Login" type="Submit"><spring:message code="btn.login"/></button>
					</div>
					<div class="col-sm-6">
						<button class="btn btn-lg btn-block btn-success" name="Submit"
							value="Login" type="Submit"><spring:message code="btn.register"/></button>
					</div>
				</div>

			</form>
		</div>
	</div>
	<!-- ***************************** SCRIPTS ***************************** -->
	<script src="./js/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>