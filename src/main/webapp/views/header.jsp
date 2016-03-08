<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="myLib" tagdir="/WEB-INF/tags"%>
<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<myLib:link target="/computer-database/computers" pageIndex="1"
			pageSize="30" classe="navbar-brand">
			<spring:message code="title.header" />
		</myLib:link>
		  <ul class="nav navbar-nav navbar-right">
            <li><a href="?lang=fr_US">FR</a></li>
            <li><a href="?lang=en_US">EN</a></li>
          </ul>
	</div>
</header>