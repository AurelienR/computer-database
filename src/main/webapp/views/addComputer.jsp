<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./css/main.css" rel="stylesheet" media="screen">
</head>
<body>

	<jsp:include page="header.jsp"/>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form id="addForm" action="./createComputer" method="POST">
						<fieldset>
							<div class="form-group  has-feedback">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName"
									name="computerName" placeholder="Computer name" required></input>
							</div>
							<div class="form-group  has-feedback">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced"
									name="introduced" placeholder="Introduced date"></input>
							</div>
							<div class="form-group  has-feedback">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued" placeholder="Discontinued date"></input>
							</div>
							<div class="form-group  has-feedback">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<option value="null">Nothing selected</option>
									<c:forEach var="company" items="${requestScope.companies}">
										<option value="${company.id}"
											${company.id == requestScope.computer.company.id ? 'selected="selected"' : ''}>${company.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input id="addBtn" type="submit" value="Add"
								class="btn btn-primary"> or <a href="./computers"
								class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="./js/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/utilValidator.js"></script>
	<script src="./js/computerFormValidator.js"></script>
</body>
</html>