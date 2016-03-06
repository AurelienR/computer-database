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
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1>Edit Computer</h1>

					<form:form action="./${computer.id}" method="POST">
						<form:input type="hidden" id="id" path="computer.id" value="${computer.id}" />
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <form:input
									type="text" class="form-control" id="computerName" path="name"
									value="${computer.name}"/>
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <form:input
									type="date" class="form-control" id="introduced" path="introduced" value="${computer.introduced}"/>
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <form:input
									type="date" class="form-control" id="discontinued" path="discontinued" value="${computer.discontinued}"/>
							</div>
							<div class="form-group  has-feedback">
								<label for="companyId">Company</label> <form:select
									class="form-control" id="companyId" path="company.Id">
									<option value="0">Nothing selected</option>
									<c:forEach var="company" items="${requestScope.companies}">
										<option value="${company.id}"
											${company.id == requestScope.computer.company.id ? 'selected="selected"' : ''}>${company.name}</option>
									</c:forEach>
								</form:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="/computers" class="btn btn-default">Cancel</a>
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