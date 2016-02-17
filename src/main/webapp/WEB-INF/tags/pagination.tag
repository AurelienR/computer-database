<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="myLib" tagdir="/WEB-INF/tags"%>
<%@ tag body-content="empty"%>
<%@ attribute name="target" required="true" type="java.lang.String"%>
<%@ attribute name="currPage" required="true" type="java.lang.Integer"%>
<%@ attribute name="pageSize" required="true" type="java.lang.Integer"%>
<%@ attribute name="totalPages" required="true" type="java.lang.Integer"%>



<ul class="pagination">


	<c:set var="range" value="5" />
	<%-- amount of page links to be displayed --%>
	<c:set var="radius" value="${range / 2}" />
	<%-- minimum link range ahead/behind --%>

	<c:set var="begin"
		value="${((currPage - radius) > 0 ? ((currPage - radius) < (totalPages - range + 1) ? (currPage - radius) : (totalPages - range)) : 1) + 1 }" />
	<c:set var="end"
		value="${(currPage + radius) < totalPages ? ((currPage + radius) > range ? (currPage + radius) : range) : totalPages-1}" />


	<!-- Previous Button -->
	<c:if test="${currPage != 1}">
		<li><myLib:link target="${target}" pageIndex="${currPage - 1}"
				pageSize="${pageSize}">
				<span aria-hidden="true">&larr; Previous</span>
			</myLib:link></li>
	</c:if>

	<!-- First page -->
	<c:choose>
		<c:when test="${currPage == 1}">
			<li class="active"><myLib:link target="${target}"
					pageIndex="${1}" pageSize="${pageSize}">1
				</myLib:link></li>
		</c:when>
		<c:otherwise>
			<li><myLib:link target="${target}" pageIndex="${1}"
					pageSize="${pageSize}">1
				</myLib:link>
			</li>
		</c:otherwise>
	</c:choose>

	<!-- Dots -->
	<c:if test="${(currPage) > range - 1}">
		<li><a class="disabled">...</a></li>
	</c:if>

	<!-- PageNumber -->
	<c:forEach var="i" begin="${begin}" end="${end}">
		<c:choose>
			<c:when test="${i == 1}">
			</c:when>
			<c:when test="${i == totalPages}">
			</c:when>
			<c:when test="${i == currPage}">
				<li class="active"><myLib:link target="${target}"
						pageIndex="${i}" pageSize="${pageSize}">
    	   			${i}
    	   		</myLib:link>
				<li>
			</c:when>
			<c:otherwise>
				<li><myLib:link target="${target}" pageIndex="${i}"
						pageSize="${pageSize}">
					${i}
				</myLib:link></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>


	<!-- Dots -->
	<c:if test="${(currPage + range - 1) < totalPages}">
		<li><a class="disabled">...</a></li>
	</c:if>

	<!-- Last page -->
	<c:choose>
		<c:when test="${totalPages == currPage}">
			<li class="active"><myLib:link target="${target}"
					pageIndex="${totalPages}" pageSize="${pageSize}">${totalPages}
				</myLib:link></li>
		</c:when>
		<c:otherwise>
			<li><myLib:link target="${target}" pageIndex="${totalPages}"
					pageSize="${pageSize}">${totalPages}
				</myLib:link></li>
		</c:otherwise>
	</c:choose>


	<!-- Next Button -->
	<c:if test="${currPage != totalPages}">
		<li><myLib:link target="${target}" pageIndex="${currPage + 1}"
				pageSize="${pageSize}">
				<span aria-hidden="true">Next &rarr;</span>
			</myLib:link></li>
	</c:if>

</ul>