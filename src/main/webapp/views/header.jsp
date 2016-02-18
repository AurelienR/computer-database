<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="myLib" tagdir="/WEB-INF/tags"%>
<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<myLib:link target="./computers" pageIndex="${param.currPage}"
			pageSize="${param.pageSize}" classe="navbar-brand"> Application -Computer Database </myLib:link>
	</div>
</header>