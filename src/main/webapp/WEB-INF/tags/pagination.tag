<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="myLib" tagdir="/WEB-INF/tags"%>
<%@ tag body-content="empty" %>
<%@ attribute name="target" required="true"  type="java.lang.String"%>
<%@ attribute name="currPage" required="true"  type="java.lang.Integer"%>
<%@ attribute name="pageSize" required="true" type="java.lang.Integer"%>
<%@ attribute name="totalPages" required="true" type="java.lang.Integer"%>



<ul class="pagination">

	<!-- Previous Button -->
	<c:if test="${currPage != 1}">
		<li>
			<myLib:link target="${target}" pageIndex="${currPage - 1}" pageSize="${pageSize}">
				<span aria-hidden="true">&larr; Previous</span>
			</myLib:link>		           
		</li>
	</c:if>

	<!-- PageNumber -->
	<c:forEach var="i" begin="1" end="${totalPages}">
		<c:choose>
	    <c:when test="${i == currPage}">
	    	<li class="active">
    	   		<myLib:link target="${target}" pageIndex="${i}" pageSize="${pageSize}">
    	   			${i}
    	   		</myLib:link>
    	   	<li>
	    </c:when>
	    <c:otherwise>
	    	<li>
				<myLib:link target="${target}" pageIndex="${i}" pageSize="${pageSize}">
					${i}
				</myLib:link>
			</li>
	    </c:otherwise>
	</c:choose>
	</c:forEach>


	<!-- Next Button -->
	<c:if test="${currPage != totalPages}">
		<li>
			<myLib:link target="${target}" pageIndex="${currPage + 1}" pageSize="${pageSize}">
				<span aria-hidden="true">Next &rarr;</span>
			</myLib:link>
		</li>
	</c:if>
	
</ul>