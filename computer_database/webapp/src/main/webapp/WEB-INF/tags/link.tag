<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="uri" required="true" description="URI of the page to go" %>
<%@ attribute name="current_page" type="com.excilys.computer_database.dto.PageDTO" description="Contains the information to build page links" %>
<%@ attribute name="override_page" description="Page number to use" %>
<%@ attribute name="override_offset" description="Offset to use" %>
<%@ attribute name="override_order" description="Order to use" %>
<%@ attribute name="override_filter" description="Filter to use" %>
<%@ attribute name="override_order_type" description="order type to use" %>

<c:set var="default_url" value=""/>

"${uri}
	<c:choose>
		<c:when test="${not empty current_page}">
			
			<c:choose>
				<c:when test="${not empty override_page}">
					?page=${override_page}
				</c:when>
				<c:otherwise>
					?page=${page.currentPage}
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${not empty override_offset}">
					&offset=${override_offset}
				</c:when>
				<c:otherwise>
					&offset=${page.offset}
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${not empty override_order}">
					&order=${override_order}
				</c:when>
				<c:otherwise>
					&order=${page.order}
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${not empty override_order_type}">
					&order_type=${override_order_type}
				</c:when>
				<c:otherwise>
					&order_type=${page.order_type}
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${not empty override_filter}">
					&filter=${override_filter}
				</c:when>
				<c:otherwise>
					&filter=${page.filter}
				</c:otherwise>
			</c:choose>	
			
		</c:when>
		<c:otherwise>${default_url}</c:otherwise> 
	</c:choose>"