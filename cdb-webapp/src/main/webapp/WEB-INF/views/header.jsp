<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="myLib" tagdir="/WEB-INF/tags"%>
<!-- ***************************** LOCALIZATION DECLARATION ***************************** -->
<spring:message code="property.lang" var="lang" />
<script>
	var lang = "${lang}";
</script>


<!-- ***************************** HEADER***************************** -->
<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<myLib:link target="/computer-database/computers" pageIndex="1"
			pageSize="30" classe="navbar-brand">
			<spring:message code="title.header" />
		</myLib:link>
		<ul class="nav navbar-nav navbar-right">
			<c:choose>
				<c:when test="${lang=='fr'}">
					<li class="disabled"><a href="?lang=fr">FR</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="?lang=fr">FR</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${lang=='en'}">
					<li class="disabled"><a href="?lang=en">EN</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="?lang=en">EN</a></li>
				</c:otherwise>
			</c:choose>
			<li class="disabled"><a>|</a></li>
			<c:choose>
				<c:when test="${user!='anonymousUser'}">
					<li>
						<a><span class="glyphicon glyphicon-user"></span> ${user}</a>
					</li>
					<li>
						<p class="navbar-btn">
							<a href="logout" class="btn btn-sm btn-primary" role="button">Logout</a>
						</p>
					</li>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</header>