<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<jsp:include page="header.jsp">
		<jsp:param name="currPage" value="${page.current}" />
		<jsp:param name="pageSize" value="${page.pageSize}" />
	</jsp:include>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${computerCount} computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

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
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>

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

							<td>
								<fmt:parseDate pattern="yyyy-MM-dd" value="${computer.introduced}" var="parsedDate" />
								<fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy" />
							</td>
							<td>								
								<fmt:parseDate pattern="yyyy-MM-dd" value="${computer.discontinued}" var="parsedDate" />
								<fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy" />
							</td>
							<td>${computer.company.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">

			<myLib:pagination target="./computers" currPage="${page.current}" totalPages="${page.pageCount}" pageSize="${page.pageSize}" />


			<div class="btn-group btn-group-sm pull-right" role="group">
				<c:choose>
					<c:when test="${page.pageSize==30}">
					<myLib:link target="./computers"
						pageIndex="1" pageSize="30" classe="btn btn-default active">30
    	   			</myLib:link>
					</c:when>
					<c:otherwise>
					<myLib:link target="./computers"
						pageIndex="1" pageSize="30" classe="btn btn-default">30
    	   			</myLib:link>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${page.pageSize==50}">
					<myLib:link target="./computers"
						pageIndex="1" pageSize="50" classe="btn btn-default active">50
    	   			</myLib:link>
					</c:when>
					<c:otherwise>
					<myLib:link target="./computers"
						pageIndex="1" pageSize="50" classe="btn btn-default">50
    	   			</myLib:link>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${page.pageSize==100}">
					<myLib:link target="./computers"
						pageIndex="1" pageSize="100" classe="btn btn-default active">100
    	   			</myLib:link>
					</c:when>
					<c:otherwise>
					<myLib:link target="./computers"
						pageIndex="1" pageSize="100" classe="btn btn-default">100
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