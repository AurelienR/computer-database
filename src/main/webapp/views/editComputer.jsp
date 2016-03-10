<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="../css/font-awesome.css" rel="stylesheet" media="screen">
<link href="../css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<!-- ***************************** LOCALIZATION DECLARATION ***************************** -->
	<!-- Labels -->
	<spring:message code="label.computerName" var="labelComputerName" />
	<spring:message code="label.introduced" var="labelIntroduced" />
	<spring:message code="label.discontinued" var="labelDiscontinued" />
	<!-- Buttons -->
	<spring:message code="btn.edit" var="btnEdit" />
	<!-- Error messages -->
    <spring:message code="err.client.date_format" var="date_format_err"/>
    <spring:message code="err.client.min_date" var="min_date_err"/>
    <spring:message code="err.client.inconsistent_date" var="date_inconsistency_err"/>
    <spring:message code="err.client.field_require" var="field_required_err"/>

	<!-- ***************************** HEADER ***************************** -->
	<jsp:include page="header.jsp" />

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1>
						<spring:message code="msg.editComputer" />
					</h1>

					<form:form id="addForm" action="./edit" method="POST"
						modelAttribute="computer">
						<form:input type="hidden" id="id" path="id" value="${computer.id}" />
						<fieldset>
							<div class="form-group">
								<label for="computerName">${labelComputerName}</label>
								<form:input type="text" class="form-control" id="computerName"
									path="name" placeholder="${labelComputerName}"
									value="${computer.name}" />
							</div>
							<div class="form-group">
								<label for="introduced">${labelIntroduced}</label>
								<form:input type="date" class="form-control" id="introduced"
									path="introduced" placeholder="${labelIntroduced}"
									value="${computer.introduced}" />
							</div>
							<div class="form-group">
								<label for="discontinued">${labelDiscontinued}</label>
								<form:input type="date" class="form-control" id="discontinued"
									path="discontinued" placeholder="${labelDiscontinued}"
									value="${computer.discontinued}" />
							</div>
							<div class="form-group  has-feedback">
								<label for="companyId"> <spring:message code="label.company"/></label>
								<form:select class="form-control" id="companyId"
									path="company.Id">
									<option value="0"><spring:message
											code="msg.nothingSelected" /></option>
									<c:forEach var="company" items="${requestScope.companies}">
										<option value="${company.id}"
											${company.id == requestScope.computer.company.id ? 'selected="selected"' : ''}>${company.name}</option>
									</c:forEach>
								</form:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="${btnEdit}" class="btn btn-primary">
							<spring:message code="word.or"/> <a href="../computers" class="btn btn-default"> <spring:message code="btn.cancel"/></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<!-- ***************************** SCRIPTS ***************************** -->
	<script>
       var date_format_err = "${date_format_err}";
       var min_date_err = "${min_date_err}";
       var date_inconsistency_err = "${date_inconsistency_err}";
       var field_required_err = "${field_required_err}";
    </script>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/utilValidator.js"></script>
	<script src="../js/computerFormValidator.js"></script>
</body>
</html>