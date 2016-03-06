<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

	<jsp:include page="header.jsp"/>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form:form id="addForm" action="/computer-database/computers/new" method="POST" modelAttribute="computerDto">
						<form:input type="hidden" id="id" path="id" value="0" />
						<fieldset>
							<div class="form-group  has-feedback">
								<form:label for="computerName" path="name">Computer name</form:label> <form:input
									type="text" class="form-control" id="computerName"
									path="name" placeholder="Computer name" required="required"></form:input>
							</div>
							<div class="form-group  has-feedback">
								<form:label for="introduced" path="introduced">Introduced date</form:label> <form:input
									type="date" class="form-control" id="introduced"
									path="introduced" placeholder="Introduced date"></form:input>
							</div>
							<div class="form-group  has-feedback">
								<form:label for="discontinued" path="discontinued">Discontinued date</form:label> <form:input
									type="date" class="form-control" id="discontinued"
									path="discontinued" placeholder="Discontinued date"></form:input>
							</div>
							<div class="form-group  has-feedback">
								<form:label for="companyId" path="company.id">Company</form:label> <form:select
									class="form-control" id="companyId" path="company.id">
									<option value="null">Nothing selected</option>
									<c:forEach var="company" items="${requestScope.companies}">
										<option value="${company.id}"
											${company.id == requestScope.computer.company.id ? 'selected="selected"' : ''}>${company.name}</option>
									</c:forEach>
								</form:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input id="addBtn" type="submit" value="Add"
								class="btn btn-primary"> or <a href="/computers"
								class="btn btn-default">Cancel</a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/utilValidator.js"></script>
	<script src="../js/computerFormValidator.js"></script>
</body>
</html>