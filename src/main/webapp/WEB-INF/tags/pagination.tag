<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="myLib" tagdir="/WEB-INF/tags"%>
<%@ tag body-content="empty"%>
<%@ attribute name="target" required="true" type="java.lang.String"%>
<%@ attribute name="currPage" required="true" type="java.lang.Integer"%>
<%@ attribute name="pageSize" required="true" type="java.lang.Integer"%>
<%@ attribute name="totalPages" required="true" type="java.lang.Integer"%>
<%@ attribute name="order" required="false" type="java.lang.String"%>
<%@ attribute name="orderBy" required="false" type="java.lang.String"%>
<%@ attribute name="search" required="false" type="java.lang.String"%>



<ul class="pagination">


	<c:set var="range" value="5" />
	<%-- amount of page links to be displayed --%>
	<c:set var="radius" value="2" />
	<%-- minimum link range ahead/behind --%>

	<c:set var="begin"
		value="${((currPage - radius) > 1 ? ((currPage - radius) < (totalPages - range + 1) ? ((currPage - radius) < 1 ? 1 : (currPage - radius)) : ((totalPages - range + 1) < 1 ? 1 : (totalPages - range + 1))) : 1)}" />
	<c:set var="end"
		value="${(currPage + radius) < totalPages ? ((currPage + radius) > range ? ((currPage + radius) > totalPages ? totalPages : (currPage + radius)) : (range > totalPages ? totalPages : range)) : totalPages}" />


	<!-- Previous Button -->
	<c:if test="${currPage != 1}">
		<li><myLib:link target="${target}" pageIndex="${currPage - 1}"
				pageSize="${pageSize}" order="${order}" orderBy="${orderBy}"
				search="${search}">
				<span aria-hidden="true">&larr; <spring:message code="btn.previous" /></span>
			</myLib:link></li>
	</c:if>

	<!-- Dots and first page -->
	<c:if test="${(begin) != 1}">
		<li><myLib:link target="${target}" pageIndex="1"
				pageSize="${pageSize}" order="${order}" orderBy="${orderBy}"
				search="${search}">1
				</myLib:link></li>
		<li><a class="disabled">...</a></li>
	</c:if>

	<!-- PageNumber -->
	<c:forEach var="i" begin="${begin}" end="${end}">
		<c:choose>
			<c:when test="${i == currPage}">
				<li class="active"><myLib:link target="${target}"
						pageIndex="${i}" pageSize="${pageSize}" order="${order}"
						orderBy="${orderBy}" search="${search}">
    	   			${i}
    	   		</myLib:link>
				<li>
			</c:when>
			<c:otherwise>
				<li><myLib:link target="${target}" pageIndex="${i}"
						pageSize="${pageSize}" order="${order}" orderBy="${orderBy}"
						search="${search}">
					${i}
				</myLib:link></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>


	<!-- Dots and first page -->
	<c:if test="${end != totalPages}">
		<li><a class="disabled">...</a></li>
		<li><myLib:link target="${target}" pageIndex="${totalPages}"
				pageSize="${pageSize}" order="${order}" orderBy="${orderBy}"
				search="${search}">${totalPages}
				</myLib:link></li>
	</c:if>


	<!-- Next Button -->
	<c:if test="${currPage != totalPages}">
		<li><myLib:link target="${target}" pageIndex="${currPage + 1}"
				pageSize="${pageSize}" order="${order}" orderBy="${orderBy}"
				search="${search}">
				<span aria-hidden="true"><spring:message code="btn.next" /> &rarr;</span>
			</myLib:link></li>
	</c:if>

</ul>