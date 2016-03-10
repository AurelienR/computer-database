<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="myLib" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen" />
<link href="./css/font-awesome.css" rel="stylesheet" media="screen" />
<link href="./css/main.css" rel="stylesheet" media="screen" />
</head>
<body>
	<!-- ***************************** LOCALIZATION DECLARATION ***************************** -->
	<!-- Buttons -->
	<spring:message code="btn.search" var="btnSearch" />
	<spring:message code="btn.filterByName" var="btnFilterByName" />
	
	<!-- ***************************** HEADER ***************************** -->
	<jsp:include page="header.jsp" />

	<section id="main">
		<!-- ***************************** SEARCH AREA ***************************** -->
		<div class="container">
			<h1 id="homeTitle">${page.matchingRowCount}
				<spring:message code="msg.computersFound" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="./computers" method="GET"
						class="form-inline">
						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="${btnSearch}" /> <input
							type="submit" id="searchsubmit" value="${btnFilterByName}"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="./computers/new"><spring:message code="btn.addComputer"/></a>
					<a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"> <spring:message code="btn.edit"/></a>
				</div>
			</div>
		</div>

		<!-- ***************************** PAGE RESULTS ***************************** -->
		<form:form id="deleteForm"
			action="/computer-database/computers/delete" method="POST"
			modelAttribute="idsStr">
			<input type="hidden" name="selection" value="" />
		</form:form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<!-- ***************************** COLUMN HEADERS ***************************** -->
					<tr>
						<!-- Delete options -->
						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<!-- Header Computer name -->
						<th><c:choose>
								<c:when test="${page.orderBy != 'name' || page.order != 'ASC'}">
									<myLib:link target="./computers" pageIndex="1"
										pageSize="${page.pageSize}" order="ASC" orderBy="name"
										search="${page.search}">  <spring:message code="label.computerName"/></myLib:link>
								</c:when>
								<c:otherwise>
									<myLib:link target="./computers" pageIndex="1"
										pageSize="${page.pageSize}" order="DESC" orderBy="name"
										search="${page.search}"><spring:message code="label.computerName"/></myLib:link>
								</c:otherwise>
							</c:choose></th>
						<!-- Header Introduced -->
						<th><c:choose>
								<c:when
									test="${page.orderBy != 'introduced' || page.order != 'ASC'}">
									<myLib:link target="./computers" pageIndex="1"
										pageSize="${page.pageSize}" order="ASC" orderBy="introduced"
										search="${page.search}"><spring:message code="label.introduced"/></myLib:link>
								</c:when>
								<c:otherwise>
									<myLib:link target="./computers" pageIndex="1"
										pageSize="${page.pageSize}" order="DESC" orderBy="introduced"
										search="${page.search}"><spring:message code="label.introduced"/></myLib:link>
								</c:otherwise>
							</c:choose></th>
						<!-- Header Discontinued -->
						<th><c:choose>
								<c:when
									test="${page.orderBy != 'discontinued' || page.order != 'ASC'}">
									<myLib:link target="./computers" pageIndex="1"
										pageSize="${page.pageSize}" order="ASC" orderBy="discontinued"
										search="${page.search}"><spring:message code="label.discontinued"/></myLib:link>
								</c:when>
								<c:otherwise>
									<myLib:link target="./computers" pageIndex="1"
										pageSize="${page.pageSize}" order="DESC"
										orderBy="discontinued" search="${page.search}"><spring:message code="label.discontinued"/></myLib:link>
								</c:otherwise>
							</c:choose></th>
						<!-- Header Company -->
						<th><c:choose>
								<c:when
									test="${page.orderBy != 'company' || page.order != 'ASC'}">
									<myLib:link target="./computers" pageIndex="1"
										pageSize="${page.pageSize}" order="ASC" orderBy="company"
										search="${page.search}"><spring:message code="label.company"/></myLib:link>
								</c:when>
								<c:otherwise>
									<myLib:link target="./computers" pageIndex="1"
										pageSize="${page.pageSize}" order="DESC" orderBy="company"
										search="${page.search}"><spring:message code="label.company"/></myLib:link>
								</c:otherwise>
							</c:choose></th>

					</tr>
				</thead>
				<!-- ***************************** TABLE RESULTS ***************************** -->
				<tbody id="results">
					<c:forEach var="computer" items="${requestScope.page.computers}">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href="./computers/${computer.id}" onclick="">${computer.name}</a>
							</td>
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>
							<td>${computer.company.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<!-- ***************************** FOOTER ***************************** -->
	<footer class="navbar-fixed-bottom">
		<div class="container text-center">

			<!-- ***************************** PAGINATION ***************************** -->
			<myLib:pagination target="./computers" currPage="${page.current}"
				totalPages="${page.pageCount}" pageSize="${page.pageSize}"
				orderBy="${page.orderBy}" order="${page.order}"
				search="${page.search}" />

			<!-- ***************************** PAGE SIZE ***************************** -->
			<div class="btn-group btn-group-sm pull-right" role="group">
				<c:choose>
					<c:when test="${page.pageSize==30}">
						<myLib:link target="./computers" pageIndex="1" pageSize="30"
							orderBy="${page.orderBy}" order="${page.order}"
							search="${page.search}" classe="btn btn-default active">30
    	   			</myLib:link>
					</c:when>
					<c:otherwise>
						<myLib:link target="./computers" pageIndex="1" pageSize="30"
							orderBy="${page.orderBy}" order="${page.order}"
							search="${page.search}" classe="btn btn-default">30
    	   			</myLib:link>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${page.pageSize==50}">
						<myLib:link target="./computers" pageIndex="1" pageSize="50"
							orderBy="${page.orderBy}" order="${page.order}"
							search="${page.search}" classe="btn btn-default active">50
    	   			</myLib:link>
					</c:when>
					<c:otherwise>
						<myLib:link target="./computers" pageIndex="1" pageSize="50"
							orderBy="${page.orderBy}" order="${page.order}"
							search="${page.search}" classe="btn btn-default">50
    	   			</myLib:link>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${page.pageSize==100}">
						<myLib:link target="./computers" pageIndex="1" pageSize="100"
							orderBy="${page.orderBy}" order="${page.order}"
							search="${page.search}" classe="btn btn-default active">100
    	   			</myLib:link>
					</c:when>
					<c:otherwise>
						<myLib:link target="./computers" pageIndex="1" pageSize="100"
							orderBy="${page.orderBy}" order="${page.order}"
							search="${page.search}" classe="btn btn-default">100
    	   			</myLib:link>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</footer>

	<!-- ***************************** SCRIPTS ***************************** -->
	<script src="./js/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/dashboard.js"></script>

</body>
</html>