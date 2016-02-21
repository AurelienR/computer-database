<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="myLib" tagdir="/WEB-INF/tags"%>
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
	<jsp:include page="header.jsp" />

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${page.matchingRowCount} computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="./computers" method="GET"
						class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="./newComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="./deleteComputer" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><c:choose>
								<c:when
									test="${page.orderBy != 'name' || page.order != 'ASC'}">
									<myLib:link target="./computers" pageIndex="1"
										pageSize="${page.pageSize}" order="ASC" orderBy="name"
										search="${page.search}">Computer name</myLib:link>
								</c:when>
								<c:otherwise>
									<myLib:link target="./computers" pageIndex="1"
										pageSize="${page.pageSize}" order="DESC" orderBy="name"
										search="${page.search}">Computer name</myLib:link>
								</c:otherwise>
							</c:choose></th>
						<th><c:choose>
								<c:when
									test="${page.orderBy != 'introduced' || page.order != 'ASC'}">
									<myLib:link target="./computers" pageIndex="1"
										pageSize="${page.pageSize}" order="ASC" orderBy="introduced"
										search="${page.search}">Introduced date</myLib:link>
								</c:when>
								<c:otherwise>
									<myLib:link target="./computers" pageIndex="1"
										pageSize="${page.pageSize}" order="DESC" orderBy="introduced"
										search="${page.search}">Introduced date</myLib:link>
								</c:otherwise>
							</c:choose></th>
						<!-- Table header for Discontinued Date -->
						<th>
							<c:choose>
								<c:when
									test="${page.orderBy != 'discontinued' || page.order != 'ASC'}">
									<myLib:link target="./computers" pageIndex="1"
										pageSize="${page.pageSize}" order="ASC" orderBy="discontinued"
										search="${page.search}">Discontinued date</myLib:link>
								</c:when>
								<c:otherwise>
									<myLib:link target="./computers" pageIndex="1"
										pageSize="${page.pageSize}" order="DESC"
										orderBy="discontinued" search="${page.search}">Discontinued date</myLib:link>
								</c:otherwise>
							</c:choose>
						</th>
						<!-- Table header for Company -->
						<th>
							<c:choose>
								<c:when
									test="${page.orderBy != 'company' || page.order != 'ASC'}">
							<myLib:link target="./computers" pageIndex="1"
								pageSize="${page.pageSize}" order="ASC"
								orderBy="company" search="${page.search}">Company</myLib:link>
								</c:when>
								<c:otherwise>
																<myLib:link target="./computers" pageIndex="1"
								pageSize="${page.pageSize}" order="DESC"
								orderBy="company" search="${page.search}">Company</myLib:link>
								</c:otherwise>
								</c:choose>
								
								</th>
							
					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach var="computer" items="${requestScope.page.computers}">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href="./computers?id=${computer.id}" onclick="">${computer.name}</a>
							</td>

							<td><fmt:parseDate pattern="yyyy-MM-dd"
									value="${computer.introduced}" var="parsedDate" /> <fmt:formatDate
									value="${parsedDate}" pattern="dd/MM/yyyy" /></td>
							<td><fmt:parseDate pattern="yyyy-MM-dd"
									value="${computer.discontinued}" var="parsedDate" /> <fmt:formatDate
									value="${parsedDate}" pattern="dd/MM/yyyy" /></td>
							<td>${computer.company.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">

			<myLib:pagination target="./computers" currPage="${page.current}"
				totalPages="${page.pageCount}" pageSize="${page.pageSize}"
				orderBy="${page.orderBy}" order="${page.order}"
				search="${page.search}" />


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
	<script src="./js/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/dashboard.js"></script>

</body>
</html>