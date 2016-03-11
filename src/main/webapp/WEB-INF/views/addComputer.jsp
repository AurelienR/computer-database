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
	<spring:message code="btn.add" var="btnAdd" />
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
					<h1>
						<spring:message code="msg.addComputer" />
					</h1>
					<form:form id="addForm" action="/computer-database/computers/new"
						method="POST" name="computerDto" modelAttribute="computerDto">
						<form:errors element="div" path="" cssClass="alert alert-danger" />
						<form:input type="hidden" id="id" path="id" value="0" />
						<form:errors element="div" path="id" cssClass="alert alert-danger" />
						<fieldset>
							<div class="form-group  has-feedback">
								<form:label for="computerName" path="name">${labelComputerName}</form:label>
								<form:input type="text" class="form-control" id="computerName"
									path="name" placeholder="${labelComputerName}"
									required="required"></form:input>
								<form:errors element="div" path="name"
									cssClass="alert alert-danger" />
							</div>
							<div class="form-group  has-feedback">
								<form:label for="introduced" path="introduced">${labelIntroduced}</form:label>
								<form:input type="text" class="form-control" id="introduced"
									path="introduced" placeholder="${labelIntroduced}"></form:input>
								<form:errors element="div" path="introduced"
									cssClass="alert alert-danger" />
							</div>
							<div class="form-group  has-feedback">
								<form:label for="discontinued" path="discontinued">${labelDiscontinued}</form:label>
								<form:input type="text" class="form-control" id="discontinued"
									path="discontinued" placeholder="${labelDiscontinued}"></form:input>
								<form:errors element="div" path="discontinued"
									cssClass="alert alert-danger" />
							</div>
							<div class="form-group  has-feedback">
								<form:label for="companyId" path="company.id"><spring:message code="label.company"/></form:label>
								<form:select class="form-control" id="companyId"
									path="company.id">
									<option value="0"><spring:message
											code="msg.nothingSelected" /></option>
									<c:forEach var="company" items="${requestScope.companies}">
										<option value="${company.id}"
											${company.id == requestScope.computer.company.id ? 'selected="selected"' : ''}>${company.name}</option>
									</c:forEach>
									<form:errors element="div" path="company.id"
										cssClass="alert alert-danger" />
								</form:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input id="addBtn" type="submit" value="${btnAdd}"
								class="btn btn-primary"> 
							<spring:message code="word.or" /> 
							<a href="../computers" class="btn btn-default"><spring:message code="btn.cancel"/></a>
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